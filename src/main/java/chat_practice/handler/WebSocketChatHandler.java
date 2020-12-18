package chat_practice.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * 서버-클라이언트 1:N 관계
 * 여러 클라이언트가 발송한 메시지를 받아 처리해줄 Handler가 필요
 * 클라이언트로부터 받은 메시지를 log에 출력하고 Client에게 환영 메시지를 보내는 역할.
 */
@Slf4j
@Component
public class WebSocketChatHandler extends TextWebSocketHandler{

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		log.info("payload {}", payload);
		TextMessage textMessage = new TextMessage("Welcome to Chatting Server~");
		session.sendMessage(textMessage);
	}
	
	/**
	 * 서버-클라이언트 1:N 관계
	 * 여러 클라이언트가 발송한 메시지를 받아 처리해줄 Handler가 필요
	 * 클라이언트로부터 받은 메시지를 log에 출력하고 Client에게 환영 메시지를 보내는 역할.
	 */
}
