package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

public class TCPConnectionP2 extends Thread{
	
	private Main ref;
	
	@Override
	public void run() {
		try {
			ServerSocket server = new ServerSocket(6000);
			System.out.println("Esperando cliente en el 6000...");
			Socket socket = server.accept();
			System.out.println("Player 2 Conectado");

			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader breader = new BufferedReader(isr);

			while (true) {

				System.out.println("Esperando mensaje...");
				String mensajeRecibido = breader.readLine();
				System.out.println(mensajeRecibido);	
				Gson gson = new Gson();
				Coordenada c = gson.fromJson(mensajeRecibido, Coordenada.class);
				ref.notificar(c, this);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void setMain(Main main) {
		this.ref = main;
	}
	
	
}