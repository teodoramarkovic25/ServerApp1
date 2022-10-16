package request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RequestHandler implements run {
    private final Socket clientSocket;

    public RequestHandler(Socket socket) {
        this.clientSocket = socket;

    }
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        PrintWriter out=new PrintWriter(clientSocket.getOutputStream());

        ){
            String inputOfKlijenta = null;
            while ((inputOfKlijenta= br.readLine())!=null){
                System.out.println("Primljeno"+ inputOfKlijenta);
                out.println("Dosta je više bilo ne mogu servirati sadržaje vviše"+ "servira ovaj tred" + Thread.currentThread().getName() );
                out.flush();
            }
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}

