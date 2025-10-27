package it.proiettoserver;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MioThread extends Thread{
    Socket socket;

    public MioThread(Socket s){
        socket = s;
    }

    public void run(){
        try{
            BufferedReader in = new BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Hello from server");
            String input;
            do {
                input = in.readLine();
                out.println(input.toUpperCase());
            } while (input != null && !input.equals("exit"));
            socket.close();
        }catch(Exception e){

        }

    }
}
