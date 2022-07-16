package com.ezen.demo.chat;



import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@ServerEndpoint(value = "/websocket", configurator = HttpSessionConfig.class)
public class WebSocket {

   /* 웹소켓 세션 보관용 ArrayList */
   // private static List<Session> sessionList = new ArrayList<>();
   private static Map<String, Session> sessionMap = new HashMap<>();
   //세션을 담을 맵

   /* 웹소켓 사용자 접속시 호출됨 */
   @OnOpen
   public void handleOpen(Session session, EndpointConfig config) {
      if (session != null) {
         // String sessionId = session.getId();

         HttpSession httpSession = (HttpSession) config.getUserProperties().get("session");
         //미리 만든 config클래스에서 정의한 http세션을 담아둔 "session"의 값을 가져오도록 한다.
         String uid = (String) httpSession.getAttribute("uid");
         //그리고 그 세션에서 만들어진 "uid"의 값을 가져와 uid에 넣는다.
         System.out.println("WS_open uid :" + uid);
         System.out.println("WS_open session@ :" + session);
         sessionMap.put(uid, session);
         //만들어진 map에 uid와 그 세션을 가져 온다.

         System.out.println("client is connected. sessionId == [" + uid + "]");
         // sessionList.add(session);

         Map<String, String> map = new HashMap<>();
         // json문자열을 가장 잘 표현하는 것은 맵형식
         map.put("from", uid);
         map.put("contents", "님이 입장하였습니다.");
         //그렇기에 맵에 키 값인 문자열과 그 value인 값을 넣어 
         //다시 jsp로 보냄
         try {
            String jsStr = new ObjectMapper().writeValueAsString(map);
            // map에 있는것을 json문자열로 보낼때 쓰인다
            sendMessageToAll(jsStr);
            //그리고 그 메세지를 모두에게 보낸다.
         } catch (Exception e) {
            e.printStackTrace();
         }

         /* 웹소켓에 접속한 모든 이용자에게 메시지 전송 */

      }
   }

   /* 웹소켓 이용자로부터 메시지가 전달된 경우 실행됨 */
   @OnMessage
   public void handleMessage(String message, Session session) {

//      String sessionId = getUserBySesssion(session);
//      String to = null;
//
//      Map<String, String> smap = null;
//      try {
//         smap = new ObjectMapper().readValue(message, Map.class);
//         to = smap.get("to");
//         Session ss = sessionMap.get(to);
//         if (to.isEmpty()==false) {
//            ss.getAsyncRemote().sendText(message);
//         }
//      } catch (Exception e) {
//         e.printStackTrace();
//      }
//
//      String jsStr = "";
//      try {
//         jsStr = new ObjectMapper().writeValueAsString(smap);
//         System.out.println("WS_handleMessage : " + jsStr);
//         if (to.isEmpty()) {
//            sendMessageToAll(jsStr);
//         }
//      } catch (Exception e) {
//         e.printStackTrace();
//      }
//      return null;
//   }
	   
	   if(session != null) {
		   try {
			Map<String,String> map = new ObjectMapper().readValue(message, Map.class);
			//json 문자열로 날라온 데이터를 맵에 담아 읽어오기
			String to =  map.get("to");
			//위에 맵에 담겨있는 "to"의 value를 가져오기
			if(to!=null) {
				Session ss = sessionMap.get(to);
				//만약 to가 있다면 sessionMap에 to의 세션을 가져옴
				//즉 이미 세션에 있는 사용자 이므로 키값을 알면 세션을 얻을수 있음
				if(ss!=null) {
					ss.getAsyncRemote().sendText(message);
					//그리고 세션이 있다면 그 사람에게 메세지를 보냄
					return;
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		   sendMessageToAll(message);
		   //아니라면 걍 모든 사람게 보내줌
		}
	   
	   }
		   
	   
	   

   /* 웹소켓 이용자가 연결을 해제하는 경우 실행됨 */
   @OnClose
   public void handleClose(Session session) {
      if (session != null) {
         String sessionId = getUserBySesssion(session);
         //내가 미리 만들어 놓은 메소드(세션값으로 키값을 얻는 메소드)
         System.out.println("client is disconnected. sessionId == [" + sessionId + "]");

         Map<String, String> map = new HashMap<>();// json문자열을 map으로 변환
         map.put("from", "[" + sessionId + "]");
         map.put("contents", "님이 퇴장하였습니다..");
         //받아놓은 키값과 메세지를 맵에 담아 jsp로
         try {
            String jsStr = new ObjectMapper().writeValueAsString(map);
            sendMessageToAll(jsStr.strip());
            //모든 이용자에게 보낸다.
         } catch (Exception e) {
            e.printStackTrace();
         }
         /* 웹소켓에 접속한 모든 이용자에게 메시지 전송 */

      }
   }

   /* 웹소켓 에러 발생시 실행됨 */
   @OnError
   public void handleError(Throwable t) {
      t.printStackTrace();
   }

   /* 웹소켓에 접속한 모든 이용자에게 메시지 전송 */
   private boolean sendMessageToAll(String message) {// json 문자열
      if (sessionMap == null) {
         return false;
         //맵에 들어간게 없으면 false
      }

      int sessionCount = sessionMap.size();
      //맵에 들어간 세션의 개수(사이즈)
      if (sessionCount < 1) {
         return false;
         //그 사이즈가 없으면 false
      }

      Set<String> keys = sessionMap.keySet();
      //set은 순서를 가지고 있지 않고 ,중복값 x
      //맵에 있는 키를 순서없이 가져옴(중복도 없이)
      Iterator<String> itr = keys.iterator();
      // 반복하게 위해서 필요, 집합을 가져와서 반복자를 가져옴
      //이는 맵에는 순서대로 넣어지지 않고 정렬되어 있지 않기 때문에
      //이를 순서대로 정렬하여 반복을 할 수 있도록 하는것 같음

      while (itr.hasNext()) {
    	  //next 할께 없을 때 까지
         String key = itr.next();
         //현재 키를 반복해서 가져오는 거므로
         //키 값을 key에 넣는다.
         Session ss = sessionMap.get(key);
         //그리고 그 key를 이용해 그 키값의 세션을 얻어 ss에 넣음

         if (ss == null) {
            continue;
         }
         if (!ss.isOpen()) {
            continue;
         }
         ss.getAsyncRemote().sendText(message);// 핵심
         //그 세션에 메세지를 보낸다.
      }

//        for (int i = 0; i < sessionCount; i++) {
//            singleSession = sessionList.get(i);
//            //리스트에서 하나씩 값을 가져옴
//            if (singleSession == null) {
//               //세션이 없다면 서비스 하지 않음
//                continue;
//            }
//
//            if (!singleSession.isOpen()) {
//               //세션이 열리지 않앗다면, 서비스 하지 않음
//                continue;
//            }
//
//            sessionList.get(i).getAsyncRemote().sendText(message);
//            //세션을 가져다 채팅에 연결되 있는 클라이언트에게 메세지를 전달해라
//        }

      return true;
   }

   private String getUserBySesssion(Session ss) {
      String sessionId = null;
      for (String key : sessionMap.keySet()) {
    	  //임시의 그릇 key에 맵의 키를 담는다
         if (ss.equals(sessionMap.get(key))) {
        	//만약 세션이 담아진 key의 value와 같다면
            sessionId = key;
            //key에 저장된 키값을 sessionId에 넣는다
         }
      }
      return sessionId;
      //그리고 그 sessionId에 담아 반환
   }

}