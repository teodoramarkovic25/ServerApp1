package server;

import request.RequestHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.out;

public class Server {
    public static void main(String[] args) {
        int portNumber = Integer.parseInt(args[0]);
        out.println("Server je startao. osluškuj na port" + portNumber);
        try (ServerSocket serverSocket = new ServerSocket(portNumber);


        ) {
            System.out.println("čekam na zahtjev");
            while (true) {
                System.out.println("čekam");
                Socket clientSocket = serverSocket.accept();
                RequestHandler requestHandler = new RequestHandler(clientSocket);
                new Thread((Runnable) requestHandler).start();
            }


        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}