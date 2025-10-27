package it.proiettoserver;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Calculator extends Thread {
    Socket socket;

    public Calculator(Socket s) {
        socket = s;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("v:2.1");
            String action1, num1, num2;
            do {
                double result, n1, n2;
                action1 = in.readLine();
                if (action1.equals("0")) {
                    socket.close();
                }
                num1 = in.readLine();
                num2 = in.readLine();
                try {
                    n1 = Double.parseDouble(num1);
                    n2 = Double.parseDouble(num2);
                } catch (Exception e) {
                    out.println("KO:Invalid number format");
                    continue;
                }
                switch (action1) {
                    case "1":
                        result = n1 + n2;
                        break;
                    case "2":
                        result = n1 - n2;
                        break;
                    case "3":
                        result = n1 * n2;
                        break;
                    case "4":
                        if (n2 != 0) {
                            result = n1 / n2;
                        } else {
                            out.println("KO:Division by zero");
                            continue;
                        }
                        break;
                    default:
                        out.println("KO:Invalid operation");
                        continue;
                }
                out.println("OK:" + result);
            } while (true);
        } catch (Exception e) {

        }

    }
}
