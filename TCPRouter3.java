import java.io.*;  
import java.net.*;  
import java.util.*;    

public class TCPRouter3
{  
   private static ServerSocket ServerSocket; 
   private static InetAddress host;
   private static final int Server_PORT = 30; 
   private static final int Client_PORT = 35;
   private static Socket ClientLink = null;
   
   public static void main(String[] args)  
   {  
      System.out.println("Port açılıyor");
      {  
          try  
          {  
        	  System.out.println("TCPRoute3 Başlatılıyor..\\nLocalhost'un ip Adresi Alınıyos..");
        	  host = InetAddress.getLocalHost();  
          }  
          catch(Exception uhEx)  
          {  
              System.out.println("Host'un ID'si bulunamadı !");  
              System.exit(1);  
          }  
            
      }
      try  
      {  
    	  
    	 ServerSocket = new ServerSocket(Server_PORT);  
         ClientLink = new Socket(host,Client_PORT);
         
      }  
      catch(IOException ioEx)  
      {  
         System.out.println("Alıcı bağlantı noktasına bağlanamıyor!!");  
         System.exit(1);  
      }  
      
      handleClient();  
      
   }    
   private static String handleClient()  
   {  
      Socket ServerLink = null;                         
      
      try  
      {  
    	 ServerLink = ServerSocket.accept();            
        
         Scanner ServerInput =  
            new Scanner(ServerLink.getInputStream());  
         
         PrintWriter senderOutput = new PrintWriter(ServerLink.getOutputStream(), true);       
         
           String message = ServerInput.nextLine(); 
           
           
           Scanner receiverInput = new Scanner(ClientLink.getInputStream());      
   
           PrintWriter ClientOutput = new PrintWriter(ClientLink.getOutputStream(), true);   
        
           
         while (!message.equals("***KAPAT***")){ 
        	 
        	 if(!message.isEmpty())
        		 System.out.print("Sunucu mesajı: "+message+"\t");
        	 
        	 ClientOutput.println(message);
        	 
        	 String str=receiverInput.nextLine();
        	 
        	 if(!str.isEmpty() && !message.isEmpty())
        		 System.out.println("Alıcıdan mesaj: "+str);

        	 senderOutput.println(str);
        	 
        	 message = ServerInput.nextLine();
       
        }  
      
         
       }  

       catch(IOException ioEx)  
       {  
           ioEx.printStackTrace();  
       }    
       finally  
       {  
          try  
          {  
             System.out.println(  
                        "\n* Closing connectionions*");  
             ServerLink.close(); 
             ClientLink.close();
          }  
          catch(IOException ioEx)  
          {  
              System.out.println(  
                            "Unable to disconnect!");  
            System.exit(1);  
          }  
       }
	return null;  
   }  
   
   }
  