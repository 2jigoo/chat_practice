package chat_practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@Import({ ContextDataSource.class /*, ContextSqlSession.class*/})
@ComponentScan(basePackages = {
		/*"chat_practice.mapper",*/
		"chat_practice.service",
		"chat_practice.handler"})
public class ContextRoot {
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}