package com.dinnerinmotion.mockservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.util.List;

@RestController
@RequestMapping("/mock-consumer")
public class Controller {
    @KafkaListener(id = "mockGroup", topics = "mockTopic")
	public void processMessage(String message,
							   @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List partitions,
							   @Header(KafkaHeaders.RECEIVED_TOPIC) List topics,
							   @Header(KafkaHeaders.OFFSET) List offsets) {

		System.out.printf("%s-%d[%d] \"%s\"\n", topics.get(0), partitions.get(0), offsets.get(0), message);
	}
}
