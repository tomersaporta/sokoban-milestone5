package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import commands.Commands;

public class ModelClient {

	private String serverIp;
	private int serverPort;
	private Gson json;

	public ModelClient(String serverIp,int serverPort) {
		GsonBuilder jsonBuilder = new GsonBuilder();
		this.json = jsonBuilder.create();
		this.serverIp=serverIp;
		this.serverPort=serverPort;	
	}

	public String createServerConnection(Commands command,String params) {
		Socket theServer = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			theServer = new Socket(this.serverIp, this.serverPort);
			System.out.println("connected to server");

			in = new BufferedReader(new InputStreamReader(theServer.getInputStream()));
			out = new PrintWriter(theServer.getOutputStream());

			String comJson = this.json.toJson(command);
			out.println(comJson);
			out.flush();

			out.println(params);
			out.flush();

			switch (command) {
			case DB_QUERY:
			case GET_SOLUTION:
				String result=in.readLine();
				return result;
			default:
				return null;
			}
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
				theServer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
