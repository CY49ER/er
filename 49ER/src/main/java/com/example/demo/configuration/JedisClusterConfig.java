package com.example.demo.configuration;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClusterConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


public class JedisClusterConfig  {
	
    private static Logger logger = LoggerFactory.getLogger(JedisClusterConfig.class);
    @Value("${redis.cluster.nodes}")
    private String clusterNodes;
    @Value("${redis.cluster.timeout}")
    private int timeout;
    @Value("${redis.cluster.max-redirects}")
    private int redirects;
    
 
    @Bean
    public RedisClusterConfiguration getClusterConfiguration() {
        Map<String, Object> source = new HashMap();
        source.put("spring.redis.cluster.nodes", clusterNodes);
        logger.info("clusterNodes: {}", clusterNodes);
        source.put("spring.redis.cluster.max-redirects", redirects);
        return new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
    }
    @Bean
    public JedisConnectionFactory getConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(getClusterConfiguration());
        jedisConnectionFactory.setTimeout(timeout);
        return jedisConnectionFactory;
    }
    @Bean
    public JedisClusterConnection getJedisClusterConnection() {
        return (JedisClusterConnection) getConnectionFactory().getConnection();
    }
    @Bean
    public RedisTemplate getRedisTemplate() {
        RedisTemplate clusterTemplate = new RedisTemplate();
        clusterTemplate.setConnectionFactory(getConnectionFactory());
        clusterTemplate.setKeySerializer(new StringRedisSerializer());
        clusterTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        return clusterTemplate;
    }
	 
	 

	 
}
