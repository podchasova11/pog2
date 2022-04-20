package lsn06;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Сервер_запущен, ожидает соединение...");

            Socket socket = serverSocket.accept();
            System.out.println("Клиент подключился к серверу");

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            while (true) {
                String clientRequest = dataInputStream.readUTF();
                if ("end".equals(clientRequest)) break;

                System.out.println("Получили строчку" + clientRequest);
                dataOutputStream.writeUTF("Сервер отвечает" + clientRequest);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
