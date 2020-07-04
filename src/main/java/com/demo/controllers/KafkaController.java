package com.demo.controllers;

import com.demo.engine.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final Producer producer;
    
//	@Autowired
//	private KafkaTemplate<Object, Object> template;

    @Autowired
    KafkaController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestBody String message) {
        this.producer.sendMessage(message);
        
    }
    
//    @PostMapping(path = "/publish/foo/{what}")
//	public void sendFoo(@RequestBody String what) {
//		this.template.send("topic1", new Foo1(what));
//	}
    
    
}
