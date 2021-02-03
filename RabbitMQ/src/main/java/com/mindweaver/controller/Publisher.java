package com.mindweaver.controller;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindweaver.config.Constants;
import com.mindweaver.domain.OrderRequest;
import com.mindweaver.domain.OrderResponse;


@RestController
@RequestMapping("/rabbit/publish")
public class Publisher 
{
	 @Autowired
	 private RabbitTemplate template;
	 
	 private static final Logger LOGGER = LoggerFactory.getLogger(Publisher.class);

	    @PostMapping("/order")
	    public ResponseEntity<OrderResponse>  bookOrder( @RequestBody OrderRequest order) 
	    {
	    	Random rand = new Random(); 
	    	  
	        int randId= rand.nextInt(1000); 
	        
	    	order.setOrderId(randId);
	    	
	    	LOGGER.info("<-----------------------------Producer1------------------------------------------>");
	    	LOGGER.info("Sending Order : " + order );
	    	
	    	template.convertAndSend(Constants.EXCHANGE, Constants.ROUTING_KEY, order);
	    	
	    	OrderResponse response = new OrderResponse();
	    	response.setMessage("order placed succesfully");
	    	response.setOderPlaced(true);
	   
	        return ResponseEntity.ok().body(response);
	    }
}