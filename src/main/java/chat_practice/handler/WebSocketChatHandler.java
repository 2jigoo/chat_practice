package chat_practice.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import chat_practice.domain.ChatMessage;
import chat_practice.domain.ChatRoom;
import chat_practice.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 서버-클라이언트 1:N 관계
 * 여러 클라이언트가 발송한 메시지를 받아 처리해줄 Handler가 필요
 * 클라이언트로부터 받은 메시지를 log에 출력하고 Client에게 환영 메시지를 보내는 역할.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketChatHandler extends TextWebSocketHandler{

	private final ObjectMapper objectMapper;
	private final ChatService chatService;
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		log.info("payload {}", payload);
		//TextMessage textMessage = new TextMessage("Welcome to Chatting Server~");
		//session.sendMessage(textMessage);
		
		// 웹소켓 클라이언트로부터 채팅메시지를 전달받아 채팅 메시지 객체로 변환
		ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
		
		// 전달받은 메시지에 담긴 채팅방 Id로 발송 대상 채팅방 정보를 조회
		ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
		
		// 해당 채팅방에 입장해 있는 모든 클라이언트들(세션)에게 타입에 따른 메시지 발송
		room.handleActions(session, chatMessage, chatService);
	}
}
