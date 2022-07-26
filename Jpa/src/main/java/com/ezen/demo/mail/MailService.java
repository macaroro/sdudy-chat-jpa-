package com.ezen.demo.mail;

import java.io.File;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service

public class MailService {

	@Autowired
	private JavaMailSender sender;
	@Autowired
	private MaliRepasitory dao;
	
	public boolean sendSimpleText() {
		  //여려명에게 메일을 보내기
	      List<String> receivers = new ArrayList<>();
	      receivers.add("cho_0826@naver.com");
	      

	      String[] arrReceiver = (String[])receivers.toArray(new String[receivers.size()]);
	      //리스트의 개수만큼의 배열이 만들어지고 그 배열에 리스트를 담아준다
	      
	      
	      SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
	      
	      simpleMailMessage.setTo(arrReceiver);//배열에 있는 사람들에게
	      simpleMailMessage.setSubject("Spring Boot Mail Test");
	      simpleMailMessage.setText("스프링에서 메일 보내기 테스트");
	    //  simpleMailMessage.setText("<a href='/mail/auth/"+ createRandomStr()+"'>인증</a>");
	      
	      sender.send(simpleMailMessage);

	      return true;
		
	}
	public boolean sendMimeMessage()
	   {
	      MimeMessage mimeMessage = sender.createMimeMessage();

	      try {
	         InternetAddress[] addressTo = new InternetAddress[1];
	         addressTo[0] = new InternetAddress("cho_0826@naver.com");

	         mimeMessage.setRecipients(Message.RecipientType.TO, addressTo);

	         mimeMessage.setSubject("마임 메시지 테스트");
	         
	         mimeMessage.setContent("<a href='http://naver.com'>확인</a>", "text/html;charset=utf-8");
	         
	         sender.send(mimeMessage);
	         return true;
	      } catch (MessagingException e) {
	      e.printStackTrace();
	      }

	      return false;
	   }
	
	
	public boolean sendMimeMessage2()
	   {
	      MimeMessage mimeMessage = sender.createMimeMessage();

	      try {
	         InternetAddress[] addressTo = new InternetAddress[1];
	         addressTo[0] = new InternetAddress("cho_0826@naver.com");

	         mimeMessage.setRecipients(Message.RecipientType.TO, addressTo);

	         mimeMessage.setSubject("마임 메시지 테스트");
	         
	         mimeMessage.setContent("<a href='http://localhost/mail/auth/"+ getRandomText()+"'>메일인증</a>", "text/html;charset=utf-8");
	         
	         sender.send(mimeMessage);
	         return true;
	      } catch (MessagingException e) {
	      e.printStackTrace();
	      }

	      return false;
	   }
	
	private String getRandomText() {
		UUID randomUUID = UUID.randomUUID();
		return randomUUID.toString().replaceAll("-","");
		//문자열 안에 있는 -를 없애버림
	}
	
	public boolean sendAttachMail(MultipartFile[] mfiles,HttpServletRequest request, String savePath)
	   {
		
	      MimeMessage mimeMessage = sender.createMimeMessage();
	      //mimemessage==>겉 봉투의 역학
	      
	      Multipart multipart = new MimeMultipart();

	      try {
	         InternetAddress[] addressTo = new InternetAddress[1];
	         addressTo[0] = new InternetAddress("cho_0826@naver.com");

	         mimeMessage.setRecipients(Message.RecipientType.TO, addressTo);
	         //수신자의 주소를 넣는다

	         mimeMessage.setSubject("마임 메시지(HTML) 테스트");
	         //보낼 메일의 제목
	         
	         // Fill the message<서버에서 돌아감>
	         BodyPart messageBodyPart = new MimeBodyPart();

	         messageBodyPart.setContent("<a href='http://localhost/mail/auth/abc123'>메일주소 인증</a>", "text/html;charset=utf-8");
	         //내용
	         
	         multipart.addBodyPart(messageBodyPart);
	         //body안에 내용을 넣어줌(여러개 넣어줄 수 있음)
	          
	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         //또 bodypart의 새로운 객체에 파일을 담아줄수 있게 한다
	         
	         for(int i=0;i<mfiles.length;i++) {
					//파일이 들어온 배열의 길이 만큼 반목
					String fname_orign = mfiles[i].getOriginalFilename();
					//파일의 원래 이름
					File file = new File(savePath+"/"+fname_orign);
			         FileDataSource fds = new FileDataSource(file);
			         messageBodyPart.setDataHandler(new DataHandler(fds));
			         String fileName = fds.getName();
			         messageBodyPart.setFileName(fileName);
			         
			         multipart.addBodyPart(messageBodyPart);
	         }
	         
	          
	         // Put parts in message
	         mimeMessage.setContent(multipart);
	         //part안에 body를 다시 message에 넣어 보낸다.
	         
	         sender.send(mimeMessage);
	         
	         return true;
	      }catch(Exception ex) {
	         ex.printStackTrace();
	      }
	      return false;
	   }
	
	
	public boolean sendAttachMail(List<String> list, String title, String contents, String email) {
		
	      MimeMessage mimeMessage = sender.createMimeMessage();
	      //mimemessage==>겉 봉투의 역학
	      
	      Multipart multipart = new MimeMultipart();

	      try {
	         InternetAddress[] addressTo = new InternetAddress[1];
	         addressTo[0] = new InternetAddress(email);

	         mimeMessage.setRecipients(Message.RecipientType.TO, addressTo);
	         //수신자의 주소를 넣는다

	         mimeMessage.setSubject(title);
	         //보낼 메일의 제목
	         
	         // Fill the message<서버에서 돌아감>
	         BodyPart messageBodyPart = new MimeBodyPart();

	         messageBodyPart.setContent(contents, "text/html;charset=utf-8");
	         //내용
	         
	         multipart.addBodyPart(messageBodyPart);
	         //body안에 내용을 넣어줌(여러개 넣어줄 수 있음)
	          
	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         //또 bodypart의 새로운 객체에 파일을 담아줄수 있게 한다
	         
	         for(int i=0;i<list.size();i++) {
					//파일이 들어온 배열의 길이 만큼 반목
					String fname_orign = list.get(i);
					//파일의 원래 이름
					File file = new File("C:/Users/201-08/Desktop/java/springwork/demo/src/main/webapp/WEB-INF/files/"+ list.get(i));
			         FileDataSource fds = new FileDataSource(file);
			         messageBodyPart.setDataHandler(new DataHandler(fds));
			         String fileName = fds.getName();
			         messageBodyPart.setFileName(fileName);
			         
			         multipart.addBodyPart(messageBodyPart);
	         }  
	        mimeMessage.setContent(multipart);
	        
	        // Put parts in message
	       
	         //part안에 body를 다시 message에 넣어 보낸다.
	         
	         sender.send(mimeMessage);
	         return true;
		
	}catch (Exception e) {
		e.printStackTrace();
	}
		return false;
	
	      }
	public int find(String email) {
	
	   return dao.selectEmpnoByEmail(email);
		
	}
	public String findEamil(int empno) {
		return dao.selectEmailByEmpno(empno);
		
	}
	
}


