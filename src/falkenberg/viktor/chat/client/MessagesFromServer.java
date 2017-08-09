package falkenberg.viktor.chat.client;

import java.io.IOException;

public class MessagesFromServer extends Thread{
    private boolean stoped;
    public void setStoped(){
        stoped = true;
    }

    @Override
    public void run(){
        try {
            while (!stoped) {
                String str = Client.in.readLine();
                System.out.println(str);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при получении сообщения.");
            e.printStackTrace();
        }
    }
}
