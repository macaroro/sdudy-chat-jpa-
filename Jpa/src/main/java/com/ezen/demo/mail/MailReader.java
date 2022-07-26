package com.ezen.demo.mail;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeUtility;

import org.springframework.stereotype.Service;


@Service
public class MailReader
{
   String pop3Host = "pop.gmail.com";
   String userName = "whalsrud0826@gmail.com";
   String password = "xbhfmktjboounlvr";

   public void readEmail()
   {
       //Set properties
       Properties props = new Properties();
       props.put("mail.store.protocol", "pop3");
       props.put("mail.pop3.host", pop3Host);
       props.put("mail.pop3.port", "995");
       props.put("mail.pop3.starttls.enable", "true");
    
       // Get the Session object.
       Session session = Session.getInstance(props);
    
       try {
           //Create the POP3 store object and connect to the pop store.
      Store store = session.getStore("pop3s");
      store.connect(pop3Host, userName, password);
    
      //폴더 이름 확인하기
      Folder root = store.getDefaultFolder();
      Folder[] folders = root.list();
      for(int i=0;i<folders.length;i++) {
         
      }

      //Create the folder object and open it in your mailbox.
      Folder emailFolder = store.getFolder("INBOX");
      
      if (emailFolder == null) {
            throw new Exception("Invalid folder");
        }
        // try to open read/write and if that fails try read-only
        try {
           emailFolder.open(Folder.READ_WRITE);
           System.out.println("읽고 쓰기 모드");
        } catch (MessagingException ex) {
           emailFolder.open(Folder.READ_ONLY);
           System.out.println("읽기모드");
        }
    
        int totalCnt = emailFolder.getMessageCount();
        
        System.out.println(totalCnt);
        
      //Retrieve the messages from the folder object.
      Message[] messages = emailFolder.getMessages();
       System.out.println(messages.length);
    
      //Iterate the messages
      for (int i=0; i < totalCnt; i++) 
      {
         if((i%10) != 0) continue;
         
         //Message message = emailFolder.getMessage(i);
         Message message = messages[i];
         /*
         Calendar cal = Calendar.getInstance();
         cal.set(2022, 0,1);
         Date date = cal.getTime();
         if(message.getSentDate().before(date)) continue;
         */
         Address[] toAddress = 
                message.getRecipients(Message.RecipientType.TO);
           
            
            String subject = message.getSubject();
            //제목의 한글이 깨지면 프로젝트 > 마우스 우측 > Properties > Resources > Text File Encoding 항목 설정 변경
           
             
           
            String from = MimeUtility.decodeText(message.getFrom()[0].toString());
          
    
            //Iterate recipients 
            for(int j = 0; j < toAddress.length; j++){
               String to = MimeUtility.decodeText(toAddress[j].toString());
             
            }
           
            message.getSentDate();
           
            message.getContentType();
           
            try {  // 텍스트 메시지인 경우
               String contents = (String)message.getContent();
              System.out.println(contents);
              continue;
           }catch(ClassCastException cce) {
              
           }
           
           //Iterate multiparts
           Multipart multipart = (Multipart) message.getContent();
           
           for(int k = 0; k < multipart.getCount(); k++){

              BodyPart bodyPart = multipart.getBodyPart(k);
              
              String bodyContentType = bodyPart.getContentType();
              System.out.println(bodyContentType);
              
              if (bodyPart.isMimeType("text/*")) //멀티 파트 안쪽에 안에 있는 텍스트 파트
              {
                 String strContent = bodyPart.getContent().toString();
                 System.out.println(strContent);
                 continue;
              }
              else
              {
                 String fname = bodyPart.getFileName();
                 if(fname==null || fname.equals("")) continue;
                 
                 fname = MimeUtility.decodeText(fname);
                 System.out.println(fname);
                 
                 BufferedInputStream bin = new BufferedInputStream(bodyPart.getInputStream());
                 byte[] buf = new byte[1024*10];
                 
                 BufferedOutputStream bout = null;
                 try {
                    bout = new BufferedOutputStream(new FileOutputStream(fname));
                 }catch(FileNotFoundException fne) {
                    continue;
                 }
                 int read = 0;
                 while((read=bin.read(buf, 0, buf.length)) != -1)
                 {
                    bout.write(buf, 0, read);
                 }
                 bout.close();
                 bin.close();
                 System.out.println(fname);
              }

          
            } //outer for() */

         }
    
         //close the folder and store objects
         emailFolder.close(false);
         store.close();
      } catch (NoSuchProviderException e) {
         e.printStackTrace();
      } catch (MessagingException e){
         e.printStackTrace();
      } catch (Exception e) {
             e.printStackTrace();
      }
    
   }
   
   public void deleteMail(Message message )
   {
      try {
         int num= message.getMessageNumber();//메시지 번호가져오기
         message.setFlag(Flags.Flag.DELETED, true);//삭제
          System.out.println(num);
         
      } catch (MessagingException e) {
         e.printStackTrace();
      }
   }
   
   public boolean removeMail(int num) 
   {
       //Set properties
       Properties props = new Properties();
       props.put("mail.store.protocol", "pop3");
       props.put("mail.pop3.host", pop3Host);
       props.put("mail.pop3.port", "995");
       props.put("mail.pop3.starttls.enable", "true");
    
       // Get the Session object.
       Session session = Session.getInstance(props);
    
       try {
           //Create the POP3 store object and connect to the pop store.
         Store store = session.getStore("pop3s");
         store.connect(pop3Host, userName, password);

         Folder emailFolder = store.getFolder("INBOX");

           try {
              emailFolder.open(Folder.READ_WRITE);
              
           } catch (MessagingException ex) {
              emailFolder.open(Folder.READ_ONLY);
              
           }

         Message[] messages = emailFolder.getMessages();
         
         messages[num].setFlag(Flags.Flag.DELETED, true);
        
         
         return true;
       }catch(Exception ex) {
          ex.printStackTrace();
       }
      return false;
   }
}