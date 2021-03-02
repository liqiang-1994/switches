package io.luxyva.switches.discuss.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import lombok.extern.log4j.Log4j2;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.mongodb.client.model.Filters.eq;

@Log4j2
@Component
public class MongoApi implements InitializingBean, DisposableBean {

	@Value("${mongodb.hosts}")
	private String hosts;

	@Value("${mongodb.port}")
	private Integer port;

	private MongoClient mongoClient;

	@Override
	public void destroy() throws Exception {
		if (mongoClient != null) {
			mongoClient.close();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
				MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		mongoClient = MongoClients.create(
				MongoClientSettings.builder()
						.applyToClusterSettings(builder ->
								builder.hosts(Arrays.asList(new ServerAddress(hosts, port))))
						.retryWrites(true)
						.codecRegistry(pojoCodecRegistry)
						.build());
	}

	public <T,U> List<T> find(String databaseName, String collectionName,
	                          Class<T> tClass, String fieldName, U fieldValue) {
		MongoDatabase database = mongoClient.getDatabase(databaseName);
		MongoCollection<T> collection = database.getCollection(collectionName, tClass);
		return findEq(collection, fieldName, fieldValue);
	}

	public <T,U> List<T> findEq(MongoCollection<T> collection, String fieldName, U fieldValue) {
		ObservableSubscriber<T> subscriber = new ObservableSubscriber();
		collection.find(eq(fieldName, fieldValue)).subscribe(subscriber);
		try {
			List<T> list = subscriber.get(15, TimeUnit.SECONDS);
			return list;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	public <T> void insert(String dataBaseName, String collectionName,T data, Class<T> tClass) {
		MongoDatabase database = mongoClient.getDatabase(dataBaseName);
		MongoCollection<T> collection = database.getCollection(collectionName, tClass);
		collection.insertOne(data).subscribe(new Subscriber<InsertOneResult>() {
			@Override
			public void onSubscribe(Subscription subscription) {
				subscription.request(1);
			}

			@Override
			public void onNext(InsertOneResult insertOneResult) {
				log.info("insert one,{}", insertOneResult);
			}

			@Override
			public void onError(Throwable throwable) {
				log.error("insert error,{}", throwable);
			}

			@Override
			public void onComplete() {
				log.info("insert complete");
			}
		});
	}

	public <T> void batchInsert(String dataBaseName, String collectionName, List<T> data, Class<T> tClass) {
		MongoDatabase database = mongoClient.getDatabase(dataBaseName);
		MongoCollection<T> collection = database.getCollection(collectionName, tClass);
		collection.insertMany(data).subscribe(new Subscriber<InsertManyResult>() {
			@Override
			public void onSubscribe(Subscription subscription) {
				subscription.request(data.size());
			}

			@Override
			public void onNext(InsertManyResult insertManyResult) {

			}

			@Override
			public void onError(Throwable throwable) {
				log.error("insert error,{}", throwable);
			}

			@Override
			public void onComplete() {
				log.info("insert complete");
			}
		});
	}

	public <T> void createIndex(String dataBaseName, String collectionName, Class<T> tClass, String... index) {
		MongoDatabase database = mongoClient.getDatabase(dataBaseName);
		MongoCollection<T> collection = database.getCollection(collectionName, tClass);
		collection.createIndex(Indexes.descending(index))
				.subscribe(new Subscriber<String>() {
					@Override
					public void onSubscribe(Subscription subscription) {
						subscription.request(1);
					}

					@Override
					public void onNext(String s) {
						log.info("dataBase:{}, collection:{}, index:{}", dataBaseName, collectionName, index);
					}

					@Override
					public void onError(Throwable throwable) {
						log.error("create index error,dataBase:{}, collection:{}, index:{}", dataBaseName, collectionName, index);
					}

					@Override
					public void onComplete() {
						log.info("create index finish,dataBase:{}, collection:{}, index:{}", dataBaseName, collectionName, index);
					}
				});
	}



}
