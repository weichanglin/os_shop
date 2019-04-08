package club.osnote.os_shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OsShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(OsShopApplication.class, args);

	}

	@Bean
	public Logger logger(){
		return LoggerFactory.getLogger(OsShopApplication.class);
	}

}
