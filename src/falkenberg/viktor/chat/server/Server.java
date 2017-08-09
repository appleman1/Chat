package falkenberg.viktor.chat.server;

import falkenberg.viktor.chat.main.Config;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket server;
    public Server() throws IOException {
        try {
            server = new ServerSocket(Config.PORT);

            while (true) {
                Socket socket = server.accept();

                Connection con = new Connection(socket);
                Connection.getConnections().add(con);
                con.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.close();
        }
    }
}
