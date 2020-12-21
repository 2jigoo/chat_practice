package chat_practice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ ContextDataSource.class, WebSocketConfig.class, ContextSqlSession.class})
@ComponentScan(basePackages = {
		"chat_practice.mapper",
		"chat_practice.service",
		"chat_practice.repository"})
public class ContextRoot {
	
}