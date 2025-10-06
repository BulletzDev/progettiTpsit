package it.proiettoclient;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci l'indirizzo del server:");
        String serverAddress = scanner.nextLine();  //10.22.9.21
        System.out.println("Inserisci la porta del server:");
        int port = scanner.nextInt();
        Socket clientSocket = new Socket(serverAddress, port);

        BufferedReader in = new BufferedReader(new java.io.InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String version =in.readLine();
        System.out.println("what server said: " + version);
        String response;
        do{
            System.out.println("Inserisci Stringa:");
            String userInput = scanner.next();
            if(userInput.equals("exit")){
                out.println("exit");
                break;
            }
            out.println(userInput);
            response = in.readLine();
            System.out.println("Risposta del server: " + response);
        }while(response != null && !response.equals("exit"));
        clientSocket.close();

    }
}