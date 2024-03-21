package com.javarush.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {

        int port = ConsoleHelper.readInt();
        ServerSocket serverSocket = new ServerSocket(port);
        ConsoleHelper.writeMessage("Сервер запущен");

        try {
            while (true) {
                Socket socket = serverSocket.accept();
                new Handler(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }

    }

    public static void sendBroadcastMessage(Message message) {

        for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
            try {
                entry.getValue().send(message);
            } catch (IOException e) {
                System.out.println("Не шмогли отправить сообщение клиенту " + entry.getKey());
            }
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            Connection connection = null;
            String clientName = null;
            try {

                ConsoleHelper.writeMessage("кто-то достучался: "+socket.getRemoteSocketAddress());
                connection = new Connection(socket);
                clientName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, clientName));
                notifyUsers(connection, clientName);
                serverMainLoop(connection, clientName);

            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Ошибка соединения!!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            finally {

                ConsoleHelper.writeMessage("Лавочка закрыта!");

                if (clientName != null) {
                    connectionMap.remove(clientName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, clientName));
                }

                if (connection != null) {
                    try {
                        connection.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }

        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {

            while (true) {

                Message answer = connection.receive();
                if (answer.getType() != MessageType.TEXT) {
                    ConsoleHelper.writeMessage("Не текст!!!!!");
                    continue;
                }
                String text = answer.getData();
                text = userName + ": " + text;
                Message message = new Message(MessageType.TEXT, text);
                sendBroadcastMessage(message);

            }

        }


        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {

            String name = "";

            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message answer = connection.receive();
                name = answer.getData();

                if (answer.getType() == MessageType.USER_NAME && !name.isEmpty() && !connectionMap.containsKey(name))
                    break;
            }

            connectionMap.put(name, connection);

            connection.send(new Message(MessageType.NAME_ACCEPTED));

            return name;

        }

        private void notifyUsers(Connection connection, String userName) throws IOException {

            for (String name : connectionMap.keySet()) {
                if (userName.equals(name)) continue;
                connection.send(new Message(MessageType.USER_ADDED, name));
            }

        }

    }

}
