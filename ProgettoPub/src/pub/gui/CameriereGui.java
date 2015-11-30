package pub.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import pub.server.Server;

public class CameriereGui {
	public static void main(String[] args){
		Socket s;
		try {
			s = new Socket(Server.HOST, Server.PORTA);
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);

			String req = "pub:\n" + Server.SELECT_CAMERIERE_ORDINI_TAVOLO + "\nid:2";						 
			out.println(req);

			String line = in.readLine();
			while(line.length() > 0){
				System.out.println(line);
				line = in.readLine();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
