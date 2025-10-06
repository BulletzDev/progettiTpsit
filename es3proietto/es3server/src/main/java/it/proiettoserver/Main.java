package it.proiettoserver;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main{
    public static void main(String[] args) throws Exception {

            ServerSocket server = new ServerSocket(8080);
            Socket serverSocket = server.accept();
            System.out.println("Executing server...");
            BufferedReader in = new BufferedReader(new java.io.InputStreamReader(serverSocket.getInputStream()));
            PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
            out.println("Hello from server");
            String input;
            do{
            input = in.readLine();
            out.println("You said: " + input.toUpperCase());
            }while(input != null && !input.equals("exit"));
            serverSocket.close();
    }
}