package request;

import java.io.*;
import java.net.Socket;

public class WebRequestHandler implements Runnable {


    private final Socket socket;

    public WebRequestHandler(Socket socket) {
        this.socket = socket;

    }

    @Override
    public void run() {
        try {
            String resourceName = staHocesBrauzeru(socket.getInputStream());
            InputStream fileInputStream = getClass().getClassLoader().getResourceAsStream(resourceName);
            BufferedReader fbr = new BufferedReader(new InputStreamReader(fileInputStream));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println("HTTP/1.0 200 OK");
            out.println("Content-Type: text/html");
            out.println();
            String line = null;
            while ((line = fbr.readLine()) != null) {
                out.println(line);
                out.flush();

            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private String staHocesBrauzeru(InputStream inputStream) throws IOException {
        String resource = "index.html";
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line = br.readLine();
        if (line != null) {
            String[] lineParts = line.split(" ");
            if (line.contains(".html")) {
                resource = lineParts[1].replace("/", "");
            }

        }
        return resource;
    }
}
