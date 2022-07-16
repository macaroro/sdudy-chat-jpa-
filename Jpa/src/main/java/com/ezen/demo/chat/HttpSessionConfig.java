package com.ezen.demo.chat;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;


public class HttpSessionConfig extends Configurator {

	@Override
	public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
		HttpSession session = (HttpSession) request.getHttpSession();
		//http세션을 얻어옴
		ServletContext context = session.getServletContext();
		//servletContext 세션을 얻어옴
		
		
		config.getUserProperties().put("session", session);
		//얻어온 http 세션을  "sessino"이른ㄴ 그릇에 담아준다
		config.getUserProperties().put("context", context);
		
		
	}
	


}
