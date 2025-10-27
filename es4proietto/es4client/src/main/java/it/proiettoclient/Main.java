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
        System.out.println("Server Version" + version);
        String response;
        do {
            System.out.println("Inserisci l'operazione matematica\n1: +\n2: -\n3: *\n4: /\n0: exit per terminare:");
            String command = scanner.next();
            switch (command) {
                case "+":
                    out.println("1");
                    break;
                case "-":  
                    out.println("2");
                    break;
                case "*":
                    out.println("3");
                    break;
                case "/":
                    out.println("4");
                    break;
                case "exit":
                    out.println("0");
                    return;
                default:
                    out.println(command);
                    break;
            }
            System.out.println("Inserisci il primo numero:");
            String num1 = scanner.next();
            out.println(num1);  
            System.out.println("Inserisci il secondo numero:");
            String num2 = scanner.next();
            out.println(num2);
            
            response = in.readLine();
            System.out.println("\n"+response);
        } while (!response.equals("exit"));
    }
}