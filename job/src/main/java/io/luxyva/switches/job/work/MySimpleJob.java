package io.luxyva.switches.job.work;

import lombok.extern.log4j.Log4j2;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
@Component
public class MySimpleJob implements SimpleJob {

	@Override
	public void execute(ShardingContext shardingContext) {
		log.info("Item: {} | Time: {} | Thread: {} | {}",
				shardingContext.getShardingItem(), new SimpleDateFormat("HH:mm:ss").format(new Date()), Thread.currentThread().getId(), "SIMPLE");
		log.info("liqiang ok");
	}
}
