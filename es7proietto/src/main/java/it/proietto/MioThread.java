package it.proietto;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;

public class MioThread extends Thread {
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    DataOutputStream outBinary;

    public MioThread(Socket s) {
        socket = s;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            outBinary = new DataOutputStream(socket.getOutputStream());
            String request = in.readLine();
            String header;

            int contentLength = 0;
            do {
                header = in.readLine();
                if (header.startsWith("Content-Length:")) {
                    String[] parts = header.split(" ");
                    if (parts.length == 2) {
                        try {
                            contentLength = Integer.parseInt(parts[1]);
                            // gestisci il body se contentLength>0
                            
                        } catch (NumberFormatException e) {
                            // header Content-Length malformatos
                        }
                    }
                }

            } while (!header.isEmpty());

            String[] answer = request.split(" ");
            if (answer.length < 3) {
                out.println("HTTP/1.1 400 Bad Request");
                socket.close();
                return;
            }
            System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
            String method = answer[0];
            String path = answer[1];
            switch (method) {
                case "GET":
                    if (path.endsWith("/")) {
                        path += "index.html";
                    }
                    if (!path.contains("htdocs")) {
                        path = "htdocs" + path;
                    }
                    path = path.contains("htdocs") ? path : "htdocs" + path;
                    System.out.println("Path richiesto: " + path);
                    File file = new File(path);
                    if (file.isDirectory()) {
                        out.println("HTTP/1.1 301 Moved Permanently");
                        out.println("Location: " + path + "/");
                        out.println("");
                    } else if (file.exists()) {
                        out.println("HTTP/1.1 200 OK");
                        out.println("Content-Length: " + file.length() + "");
                        out.println("Content-Type: " + getContentType(file) + "");
                        out.println("");
                        InputStream input = new FileInputStream(file);
                        byte[] buf = new byte[8193];
                        int n;
                        while ((n = input.read(buf)) > 0) {
                            outBinary.write(buf, 0, n);
                        }
                        input.close();
                    } else {
                        out.println("HTTP/1.1 404 Not Found");
                        out.println("Content-Length: " + 0);
                        out.println("");
                        System.out.println("File non trovato");
                    }
                    break;
                case "HEAD":
                    if (path.endsWith("/")) {
                        path += "index.html";
                    }
                    path = path.contains("htdocs") ? path : "htdocs" + path;
                    System.out.println("Path richiesto: " + path);
                    file = new File(path);
                    if (file.isDirectory()) {
                        out.println("HTTP/1.1 301 Moved Permanently");
                        out.println("Location: " + path + "/");
                        out.println("");
                    } else if (file.exists()) {
                        out.println("HTTP/1.1 200 OK");
                        out.println("");
                    } else {
                        out.println("HTTP/1.1 404 Not Found");
                        out.println("Content-Length: " + 0);
                        out.println("");
                        System.out.println("File non trovato");
                    }
                    break;
                case "POST":
                    if (path.endsWith("/")) {
                        path += "index.html";
                    }
                    path = path.contains("htdocs") ? path : "/htdocs" + path;
                    System.out.println("Path richiesto: " + path);
                    file = new File(path);
                    if (file.isDirectory()) {
                        out.println("HTTP/1.1 301 Moved Permanently");
                        out.println("Location: " + path + "/");
                        out.println("");
                    } else if (file.exists()) {
                        out.println("HTTP/1.1 200 OK");
                        out.println("Content-Length: " + file.length() + "");
                        out.println("Content-Type: " + getContentType(file) + "");
                        out.println("");
                        InputStream input = new FileInputStream(file);
                        byte[] buf = new byte[8193];
                        int n;
                        while ((n = input.read(buf)) > 0) {
                            outBinary.write(buf, 0, n);
                        }
                        input.close();
                    } else {
                        out.println("HTTP/1.1 404 Not Found");
                        out.println("Content-Length: " + 0);
                        out.println("");
                        System.out.println("File non trovato");
                    }
                    break;
                default:
                    out.println("HTTP/1.1 405 Method Not Allowed");
                    out.println("Allow: GET, HEAD, POST");
                    out.println("Server: MIOserver");
                    out.println("Content-Length: " + 0);
                    out.println("");
                    System.out.println("Metodo non consentito");
                    break;
            }

        } catch (IOException e) {

        }

    }

    private static String getContentType(File f) {
        String name = f.getName();
        String[] ext = name.split("\\.");
        switch (ext[1]) {
            case "html":
            case "htm":
                return "text/html";
            case "txt":
                return "text/plain";
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            case "css":
                return "text/css";
            case "js":
                return "application/javascript";
            default:
                return "application/octet-stream";
        }
    }
}
