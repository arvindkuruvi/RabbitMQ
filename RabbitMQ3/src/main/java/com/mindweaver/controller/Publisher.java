package com.mindweaver.controller;

import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.mindweaver.config.ApplicationConstant;
import com.mindweaver.config.Constants;
import com.mindweaver.domain.OrderRequest;


@RestController
@RequestMapping("/rabbit")
public class Publisher 
{
	 @Resource(name = "RabbitTemplate2")
	 private RabbitTemplate template;
	 
	 private static final Logger LOGGER = LoggerFactory.getLogger(Publisher.class);
	 
	 @RabbitListener(queues = Constants.QUEUE2)
	    public void consumeMessageFromQueue(OrderRequest request) 
		{
			
	        try {
	        	System.out.println();
				LOGGER.info("<-----------------Consumer----------------------->");
				LOGGER.info("Message recieved from queue : " + request);
		        System.out.println();
				
			} catch (HttpClientErrorException ex) {
				if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
					LOGGER.info("Delay...");
					try {
						Thread.sleep(ApplicationConstant.MESSAGE_RETRY_DELAY);
					} catch (InterruptedException e) {
					}
					LOGGER.info("Throwing exception so that message will be requed in the queue.");
					// Note: Typically Application specific exception should be thrown below
					throw new RuntimeException();
				} else {
					throw new AmqpRejectAndDontRequeueException(ex);
				}
			} catch (Exception e) {
				LOGGER.error("Internal server error occurred in API call. Bypassing message requeue {}", e);
				throw new AmqpRejectAndDontRequeueException(e);
			}
	    }
}