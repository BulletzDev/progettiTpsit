package it.proietto;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main{
    public static void main(String[] args) throws Exception {

            ServerSocket server = new ServerSocket(8080);
            Socket client = server.accept();
            System.out.println("Hello world!");
            BufferedReader in = new BufferedReader(new java.io.InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
    }
}