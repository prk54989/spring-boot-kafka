package com.demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.converter.Jackson2JavaTypeMapper.TypePrecedence;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.messaging.MessageHeaders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootApplication
public class SpringBootWithKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithKafkaApplication.class, args);
	}


//	private ListenerExecutionFailedException listen3Exception;
	
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory(
//	    ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
//	    ConsumerFactory<Object, Object> kafkaConsumerFactory) {
//	  ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
//	  configurer.configure(factory, kafkaConsumerFactory);
//	  factory.setErrorHandler(new SeekToCurrentErrorHandler()); // <<<<<<
//	  return factory;
//	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory(
	    ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
	    ConsumerFactory<Object, Object> kafkaConsumerFactory,
	    KafkaTemplate<Object, Object> template) {
	  ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
	  configurer.configure(factory, kafkaConsumerFactory);
	  factory.setErrorHandler(new SeekToCurrentErrorHandler(
	      new DeadLetterPublishingRecoverer(template)));
	  factory.setConcurrency(5);
	  return factory;
	}
	
//	@Bean
//	public ConsumerAwareListenerErrorHandler listen3ErrorHandler() {
//	return (m, e, c) -> {
//	    this.listen3Exception = e;
//	    MessageHeaders headers = m.getHeaders();
//	    c.seek(new org.apache.kafka.common.TopicPartition(
//	            headers.get(KafkaHeaders.RECEIVED_TOPIC, String.class),
//	            headers.get(KafkaHeaders.RECEIVED_PARTITION_ID, Integer.class)),
//	            headers.get(KafkaHeaders.OFFSET, Long.class));
//	    return null;
//	   };
//	}
	
	
//	@Bean
//	public RecordMessageConverter converter() {
////	  return new StringJsonMessageConverter();
//		  StringJsonMessageConverter converter = new StringJsonMessageConverter();
//		  DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
//		  typeMapper.setTypePrecedence(TypePrecedence.TYPE_ID);
//		  typeMapper.addTrustedPackages("*");
//		  Map<String, Class<?>> mappings = new HashMap<>();
//		  mappings.put("foo", String.class);
////		  mappings.put("bar", Bar2.class);
//		  typeMapper.setIdClassMapping(mappings);
//		  converter.setTypeMapper(typeMapper);
//		  return converter;	
//		
//	}
	

	
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<String, GenericRecord>
//	kafkaListenerContainerFactory()  {
//
//	    ConcurrentKafkaListenerContainerFactory<String, GenericRecord> factory
//	            = new ConcurrentKafkaListenerContainerFactory<>();
//	    factory.setConsumerFactory(consumerFactory());
//	    factory.getContainerProperties().setErrorHandler(new ErrorHandler() {
//	        @Override
//	        public void handle(Exception thrownException, List<ConsumerRecord<?, ?>> records, Consumer<?, ?> consumer, MessageListenerContainer container) {
//	            String s = thrownException.getMessage().split("Error deserializing key/value for partition ")[1].split(". If needed, please seek past the record to continue consumption.")[0];
//	            String topics = s.split("-")[0];
//	            int offset = Integer.valueOf(s.split("offset ")[1]);
//	            int partition = Integer.valueOf(s.split("-")[1].split(" at")[0]);
//
//	            TopicPartition topicPartition = new TopicPartition(topics, partition);
//	            //log.info("Skipping " + topic + "-" + partition + " offset " + offset);
//	            consumer.seek(topicPartition, offset + 1);
//	            System.out.println("OKKKKK");
//	        }
//
//	        @Override
//	        public void handle(Exception e, ConsumerRecord<?, ?> consumerRecord) {
//
//	        }
//
//	        @Override
//	        public void handle(Exception e, ConsumerRecord<?, ?> consumerRecord, Consumer<?,?> consumer) {
//	            String s = e.getMessage().split("Error deserializing key/value for partition ")[1].split(". If needed, please seek past the record to continue consumption.")[0];
//	            String topics = s.split("-")[0];
//	            int offset = Integer.valueOf(s.split("offset ")[1]);
//	            int partition = Integer.valueOf(s.split("-")[1].split(" at")[0]);
//
//	            TopicPartition topicPartition = new TopicPartition(topics, partition);
//	            //log.info("Skipping " + topic + "-" + partition + " offset " + offset);
//	            consumer.seek(topicPartition, offset + 1);
//	            System.out.println("OKKKKK");
//
//
//	        }
//	    });
//
//
//	    return factory;
//	}
//	
	
//    @Bean
//    public Map<String, Object> consumerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
//                bootstrapServers);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
//                StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
//                JsonDeserializer.class);
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, serviceGroupId);
//        props.put(JsonDeserializer.TRUSTED_PACKAGES, trustedPackage);
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        return props;
//    }
	

}
