package Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController  
public class ExampleController {  
  
      
    @Autowired  
    private IRedisService redisService;  
    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;
      
    @RequestMapping("/redis/set")  
    public ResponseModal redisSet(@RequestParam("value") String value){  
    	System.out.println(jedisConnectionFactory.getPassword());
        boolean isOk = redisService.set("name", value);  
        return new ResponseModal(isOk ? 200 : 500, isOk, isOk ? "success" : "error" , null);  
    }  
      
    @RequestMapping("/redis/get")  
    public ResponseModal redisGet(){  
        String name = redisService.get("name");  
        return new ResponseModal(200, true,"success",name);  
    }  
      
}  

