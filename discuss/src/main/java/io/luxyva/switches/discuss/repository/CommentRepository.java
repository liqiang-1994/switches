package io.luxyva.switches.discuss.repository;

import io.luxyva.switches.discuss.config.Constants;
import io.luxyva.switches.discuss.config.MongoApi;
import io.luxyva.switches.discuss.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {

	private static final String COLLECTION_NAME = "comment";

	@Autowired
	private MongoApi mongoApi;

	public void insertOne(Comment comment) {
		mongoApi.insert(Constants.DATABASE_NAME_DISCUSS, COLLECTION_NAME, comment, Comment.class);
	}

	public List<Comment> findAll() {
		List<Comment> result = mongoApi.find(Constants.DATABASE_NAME_DISCUSS, COLLECTION_NAME, Comment.class, "topicType", 34);
		return result;
	}

}
