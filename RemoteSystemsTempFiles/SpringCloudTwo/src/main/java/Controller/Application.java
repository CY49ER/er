package Controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;

@SpringBootApplication
//@ConfigurationProperties(prefix = "application",locations = "classpath:application.properties") 
public class Application implements EmbeddedServletContainerCustomizer {
    public static void main(String[] args) {
    	//通过注解加载Bean
    	SpringApplication.run(Application.class, args);
//    	从Spring配置文件加载Bean
//    	SpringApplication.run("classpath:applicationContext.xml", args);
//    	SpringApplication app=new SpringApplication(Application.class);
//    	app.run(args);
    }

	public void customize(ConfigurableEmbeddedServletContainer container) {
		// TODO Auto-generated method stub
		container.setPort(9000);
	}
}