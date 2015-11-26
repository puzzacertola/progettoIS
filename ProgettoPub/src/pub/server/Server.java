

package pub.server;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Server {
	
	
	
	public static void main (String[] args) throws Exception{
		
		new Server();
		try  {
			String ricevuto=" ";
			ServerSocket ss= new ServerSocket(80);
			Socket s=ss.accept();
			
			PrintWriter out= new PrintWriter (s.getOutputStream(),true);
			BufferedReader in=new BufferedReader (new InputStreamReader (s.getInputStream()));
			
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection conn= DriverManager.getConnection("");//in getConnection ci va il database del tipo "jdbc:Mysql://localhost/amici","cacca","amici"
			java.sql.Statement stm= conn.createStatement();
			ricevuto= in.readLine();
			ResultSet rs= stm.executeQuery(ricevuto);
			String risposta = new String();
			out.println("ok\n"+risposta);
			
			
			ss.close();
			conn.close();
		}
		
		
	}
	
	
}
