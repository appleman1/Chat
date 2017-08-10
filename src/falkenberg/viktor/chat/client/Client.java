package falkenberg.viktor.chat.client;

import falkenberg.viktor.chat.main.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private BufferedReader reader;
    private String name;
    private String ip;
    private Socket clientSocket;
    protected static BufferedReader in;
    protected static PrintWriter out;
    private String stringToServer = "";
    private MessagesToConsole messagesToConsole;

    public Client() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите IP адрес для подключения к серверу.");
        System.out.println("Формат: ххх.ххх.ххх.ххх");
        try {
            ip = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientSocket = new Socket(ip, Config.PORT);
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("Введите свой ник: ");
        name = reader.readLine();
        out.println(name);

        messagesToConsole = new MessagesToConsole();
        messagesToConsole.start();
        System.out.println(name + " " + Config.HELLO_MESSAGE);
        try {
            while (!"exit".equals(stringToServer)) {
                stringToServer = reader.readLine();
                out.println(stringToServer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            messagesToConsole.setStoped(true);
            in.close();
            out.close();
            clientSocket.close();
        }

    }
}
