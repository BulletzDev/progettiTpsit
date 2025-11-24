package it.proiettoserver;

import java.net.ServerSocket;
import java.net.Socket;

public class Main{
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(3001);
        do {
            Socket socket = server.accept();
            MioThread t = new MioThread(socket);
            t.start();

        } while (true);
    }
}