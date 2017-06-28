package controller.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer {
	
	private int port;
	private ClientHandler ch;
	private volatile boolean stop;

	public MyServer(int port, ClientHandler ch) {
		this.port=port;
		this.ch=ch;
		this.stop=false;
	}
	
	private void runServer()throws Exception { 
		
		ServerSocket server=new ServerSocket(this.port);
		System.out.println("Server alive");
		server.setSoTimeout(1000); 
		
		while(!stop){//we want to wait to the next client- we handle the clients in a line
			try{
				Socket aClient=server.accept(); // blocking call
				
				System.out.println("The client is connected");
				
				InputStream inFromClient=aClient.getInputStream();
				OutputStream outToClient=aClient.getOutputStream();
				this.ch.handleClient(inFromClient,outToClient);
				
				inFromClient.close(); 
				outToClient.close(); 
				aClient.close(); 
			}catch(SocketTimeoutException e) {continue;} 
		} 
		
		server.close();
	}
	
	public void start(){ 
		new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					System.out.println("Running");
					runServer();
				}catch (Exception e){e.printStackTrace();}
			} 
		}).start();
	}

	public void stop(){ 
		stop=true; 
	}
	
	
}

