package chat_practice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import lombok.RequiredArgsConstructor;


/**
 * 만들어준 WebSocketHandler를 이용해 WebSocket을 활성하기 위한 Config 작성.
 */
@RequiredArgsConstructor
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	private final WebSocketHandler webSocketHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler, "/ws/chat").setAllowedOrigins("*");
		// 웹소켓에 접속하기 위한 endpoint는 /ws/chat/
		// 다른 서버에서도 접속 가능하도록 CORS : setAllowedOrigins("*") 설정 추가
	}
}
