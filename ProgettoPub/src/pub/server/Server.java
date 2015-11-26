

package pub.server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Server {
	
	static final int PORTA = 80;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String USER = "server";
	static final String PASS = "server";
	static final String DB_URL = "jdbc:mysql://localhost:3306/IngDelSw";
	
	public static void main (String[] args) throws Exception{
		
		new Server();
		
		try {
			String ricevuto = "";
			ServerSocket ss = new ServerSocket(PORTA);
			Socket s = ss.accept();
			
			PrintWriter out = new PrintWriter (s.getOutputStream(),true);
			BufferedReader in = new BufferedReader (new InputStreamReader(s.getInputStream()));
			
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stm = conn.createStatement();
			ricevuto = in.readLine();
			ResultSet rs = stm.executeQuery(ricevuto);
			out.println(rs);
		
			ss.close();
			conn.close();
		} catch(IOException e){
			e.printStackTrace();			
		}
		
		
	}
	
	
}
