import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

  Socket socket;
  BufferedReader br;
  PrintWriter out;

  public Client()
  {
    try {
      System.out.println("Sending Request to Server...");
      socket = new Socket("192.168.1.203", 1111);
      System.out.println("Connection Done...");
      
       br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

       out = new PrintWriter(socket.getOutputStream());
     
    startReading();
    startWriting();

    } catch (Exception e) {
     
    }
  }
   public void startReading(){
  //  thread - read karke deta rhega
 
  Runnable r1 = ()->{
       System.out.println("Reader started : ");
       while(true){
        try{
       String msg = br.readLine();
         if(msg.equals("exit"))
         {
          System.out.println("Server  terminated the chat ");
          break;
         }
         System.out.println("Server : "+msg);
        }catch(Exception e){
          e.printStackTrace();
        }
  }
  };
  new Thread(r1).start();
}
 public void startWriting(){
//   thread - data ko user se lega and then send karega client tak : 
System.out.println("Writer started : ");
  Runnable r2 = ()->{
    
   while(true){
    try {
      BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
      String content = br1.readLine();
      out.println(content);
      out.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
   }
  };
   new Thread(r2).start();
  }
  public static void main(String[] args) {
    new Client();
    System.out.println("This is client");
  }
}
