package com.mindweaver.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.mindweaver.config.Constants;
import com.mindweaver.domain.OrderRequest;

//
//@Component
//public class Consumer 
//{
//	private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);
//	
//	@RabbitListener(queues = Constants.QUEUE)
//    public void consumeMessageFromQueue(OrderRequest request) 
//	{
//		System.out.println();
//		LOGGER.info("<-----------------Consumer----------------------->");
//		LOGGER.info("Message recieved from queue : " + request);
//        System.out.println();
//    }
//}