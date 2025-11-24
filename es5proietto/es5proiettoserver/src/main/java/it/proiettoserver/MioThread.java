package it.proiettoserver;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class MioThread extends Thread {
    Socket socket;

    public MioThread(Socket s) {
        socket = s;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String request = in.readLine();
            String header;
            do{
                header = in.readLine();
                System.out.println(header);
            }while(!header.isEmpty());

            String response ="<strong>Ciao</strong> a tutti";
            String[] answer = request.split(" ");

            if(answer[1].equals("/ciao")){
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html; charset=UTF-8");
            out.println("Server: MIOserver");
            out.println("Content-Length: " + response.length());
            out.println("");
            out.println(response);
            }else{
            response = "niente pagina";
            out.println("HTTP/1.1 404 Not Found");
            out.println("Content-Type: text/html; charset=UTF-8");
            out.println("Server: MIOserver");
            out.println("Content-Length: " + response.length());
            out.println("");
            out.println(response);
            }

        } catch (Exception e) {

        }

    }
}
