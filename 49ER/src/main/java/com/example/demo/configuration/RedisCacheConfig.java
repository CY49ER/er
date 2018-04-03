package com.example.demo.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {
	
	 
	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,MessageListenerAdapter listenerAdapter) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
														 container.setConnectionFactory(connectionFactory);
														 container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
											return  container;
	}
	
	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		
	}
	
	@Bean
	@Bean
	@Bean
	@Bean
	
	
 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
