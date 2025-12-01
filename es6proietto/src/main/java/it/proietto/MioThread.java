package it.proietto;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MioThread extends Thread {
    Socket socket;

    public MioThread(Socket s) {
        socket = s;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            DataOutputStream outBinary = new DataOutputStream(socket.getOutputStream());

            String request = in.readLine();
            String header;
            do{
                header = in.readLine();
            }while(!header.isEmpty());

            String[] answer = request.split(" ");
            System.out.println(answer[0] + " " + answer[1]);
            String method = answer[0];
            String path = answer[1];

            if(!method.equals("GET")){
            out.println("HTTP/1.1 405 Method Not Allowed");
            out.println("Allow: GET");
            out.println("Server: MIOserver");
            out.println("Content-Length: " + 0);
            out.println("");
            System.out.println("Metodo non consentito");
            } else if (path.endsWith("/")) {
                path += "index.html";
            }

            path = path.contains("htdocs") ? path : "/htdocs" + path;
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
            while ((n = input.read(buf)) != 0) {
                outBinary.write(buf, 0, n);
            }
            input.close();
            }



        } catch (Exception e) {

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
