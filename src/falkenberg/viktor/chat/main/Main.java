package falkenberg.viktor.chat.main;

import falkenberg.viktor.chat.client.Client;
import falkenberg.viktor.chat.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Запустить программу в режиме сервера или клиента? (S (Server) / C (Client))");
        try {
            while (true) {
                String answer = reader.readLine().toLowerCase();
                if ("s".equals(answer)) {
                    new Server();
                    break;
                } else if ("c".equals(answer)) {
                    new Client();
                    break;
                } else {
                    System.out.println("Некорректный ввод. Повторите.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
