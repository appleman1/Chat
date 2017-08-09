package falkenberg.viktor.chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Connection extends Thread {
    private BufferedReader in;
    private PrintWriter out;
    private String name = "";
    private Socket socket;
    private static ArrayList<Connection> connections;

    public static ArrayList<Connection> getConnections() {
        return connections;
    }

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
        }catch (IOException e){
            e.printStackTrace();

        }finally {
            in.close();
            out.close();
        }
    }
    public void run() {
        try {
            name = in.readLine();

            for(Connection c : connections) {
                c.out.println(name + " cames now");
            }

            String str = "";
            while (true) {
                str = in.readLine();
                if(str.equals("exit")) break;

                for(Connection c : connections) {
                    c.out.println(name + ": " + str);
                }
            }

            for(Connection c : connections) {
                c.out.println(name + " has left.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
