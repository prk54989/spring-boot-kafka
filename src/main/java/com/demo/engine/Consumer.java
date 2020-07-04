package com.demo.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import org.springframework.kafka.annotation.TopicPartition;
@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Producer.class);

//    @KafkaListener(topics = "event-listener-grp-tier", groupId = "group_id")
//    public void consume(String message) throws IOException {
//        logger.info(String.format("#### -> Consumed message -> %s", message));
//    }
    
  
    
//	@KafkaListener(id = "fooGroup", topics = "event-listener-grp-tier")
//	public void listen(String in) {
//		logger.info("Consumer: " + in);
//		if (in.equals("foo")) {
//			throw new RuntimeException("\n\n\t\t >> listen : failed");
//		}
//	}
    
    

//	@KafkaListener(id = "dltGroup", topics = "event-listener-grp-tier.DLT")
//	public void dltListen(String in) {
//		logger.info("Consumer from DLT: " + in);
//	}   
	
	
	@KafkaListener(topicPartitions 
	          = @TopicPartition(topic = "event-listener-grp-tier.DLT", partitionOffsets = {
	          @org.springframework.kafka.annotation.PartitionOffset(partition = "0", initialOffset = "0")}),groupId = "group_id",
	        containerFactory = "kafkaListenerContainerFactory")
	public void listenAllMsg(@Payload String message,@Header(org.springframework.kafka.support.KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
	    System.out.println("DLT  all msg Received Messasge in group 'foo': " + message+"RECEIVED_PARTITION_ID - "+partition);
		logger.info("DLT Consumer: listenAllMsg : message :{} ", message);
//		if (message.contains("praveen")) {
//			String msg= "\n Consumer :  listen : throwing RuntimeException failed....s";
//			logger.info(msg);
//			throw new RuntimeException(msg);
//		}
	}
	
	
	@KafkaListener(topicPartitions 
	          = @TopicPartition(topic = "event-listener-grp-tier", partitionOffsets = {
	          @org.springframework.kafka.annotation.PartitionOffset(partition = "0", initialOffset = "0")}),groupId = "group_id",
	        containerFactory = "kafkaListenerContainerFactory")
	public void listenAllMsg2(@Payload String message,@Header(org.springframework.kafka.support.KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
	    System.out.println("NON DLT  all msg Received Messasge in group 'foo': " + message+"RECEIVED_PARTITION_ID - "+partition);
		logger.info("DLT Consumer: listenAllMsg : message :{} ", message);
//		if (message.contains("praveen")) {
//			String msg= "\n Consumer :  listen : throwing RuntimeException failed....s";
//			logger.info(msg);
//			throw new RuntimeException(msg);
//		}
	}
	
	
    
//    @KafkaListener(id = "fooGroup", topics = "event-listener-grp-tier")
//    public void listen(Foo2 foo) {
//      logger.info("Received: " + foo);
//      if (foo.getFoo().startsWith("fail")) {
//        throw new RuntimeException("failed");
//      }
//    }
//
//    @KafkaListener(id = "dltGroup", topics = "event-listener-grp-tier.DLT")
//    public void dltListen(Foo2 in) {
//      logger.info("Received from DLT: " + in);
//    }

    
}
