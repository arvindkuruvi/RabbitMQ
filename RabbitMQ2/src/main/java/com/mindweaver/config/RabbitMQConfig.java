package com.mindweaver.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitMQConfig 
{
	 	@Bean
	    public Queue queue() {
	        return new Queue(Constants.QUEUE);
	    }

	    @Bean
	    public TopicExchange exchange() {
	        return new TopicExchange(Constants.EXCHANGE);
	    }

	    @Bean
	    public Binding binding(Queue queue, TopicExchange exchange) {
	        return BindingBuilder.bind(queue).to(exchange).with(Constants.ROUTING_KEY);
	    }

	    @Bean
	    public MessageConverter converter() {
	        return new Jackson2JsonMessageConverter();
	    }
	    
	    @Bean(name = "ConnectionFactory")
//	    @Primary
	    public CachingConnectionFactory publicConnectionFactory() {
	        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
	        connectionFactory.setHost(Constants.HOST);
	        connectionFactory.setPort(Constants.PORT);
	        connectionFactory.setUsername(Constants.USERNAME);
	        connectionFactory.setPassword(Constants.PASSWORD);
	        System.out.println("connectionFactory for rideRequest:"+connectionFactory);
	        return connectionFactory;
	    }
	    
	    
//	    public RabbitTemplate publicRabbitTemplate(
//	            @Qualifier("rideRequestConnectionFactory") ConnectionFactory connectionFactory
//	           ) {
//	        RabbitTemplate v1RabbitTemplate = new RabbitTemplate(connectionFactory);
//	        v1RabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
//	        return v1RabbitTemplate;
//	    }
	    
	    

	    @Bean
	    public AmqpTemplate template(@Qualifier("ConnectionFactory") ConnectionFactory connectionFactory) {
	        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	        rabbitTemplate.setMessageConverter(converter());
	        return rabbitTemplate;
	    }
	    
	    
//	    -----------------------------------------------------------------------------------------------------------------------
	    @Bean(name = "queue2")
	    public Queue queue1() {
	        return new Queue(Constants.QUEUE2);
	    }

	    @Bean(name = "topicExchange2")
	    public TopicExchange exchange1() {
	        return new TopicExchange(Constants.EXCHANGE2);
	    }

	    @Bean
	    public Binding binding2(@Qualifier("queue2") Queue queue,@Qualifier("topicExchange2") TopicExchange exchange) {
	        return BindingBuilder.bind(queue).to(exchange).with(Constants.ROUTING_KEY2);
	    }
	    
	    @Bean(name = "RabbitTemplate2")
	    public AmqpTemplate template2(@Qualifier("ConnectionFactory") ConnectionFactory connectionFactory) {
	        final RabbitTemplate rabbitTemplate2 = new RabbitTemplate(connectionFactory);
	        rabbitTemplate2.setMessageConverter(converter());
	        return rabbitTemplate2;
	    }
	    
}