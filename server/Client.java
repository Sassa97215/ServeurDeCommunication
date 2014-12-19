package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket sock; 
    private String id; // Unique identifier
    private PrintWriter writer;
    private BufferedReader reader;
    
    
    public Client (Socket sock) {
        this.sock = sock;
        this.id = genererId();
        try {
            this.writer = new PrintWriter(this.sock.getOutputStream());
            this.reader = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void send (String msg) {
        this.writer.print(msg);
        this.writer.flush();
    }
    
    public String receive () throws IOException {
        return this.reader.readLine();
    }

    private String genererId() {
        StringBuilder id = new StringBuilder();
        int val;
        
        for ( int i = 0; i < 8; i++) {
            val = (int)(Math.random()*94+33);
            if((char)val == ':')
                i--;
            else
                id.append((char)val);
            
        }
        
        return id.toString();
    }

    public String getId() {
        return id;
    }
    
}
