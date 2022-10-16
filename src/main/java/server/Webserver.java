package server;

import request.WebRequestHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Webserver {
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(args[0]);
        System.out.println("Web server startao na portu"+ port);
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("ja web server čekam https request");
        while(true){
            Socket socket = serverSocket.accept();
            WebRequestHandler webRequestHandler = new WebRequestHandler(socket);
            new Thread(webRequestHandler).start();
        }
    }

}
