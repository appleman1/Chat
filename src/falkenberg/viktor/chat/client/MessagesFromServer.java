package falkenberg.viktor.chat.client;

import java.io.IOException;

public class MessagesFromServer extends Thread{
    private boolean stoped = false;

    public void setStoped(boolean stoped) {
        this.stoped = stoped;
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
