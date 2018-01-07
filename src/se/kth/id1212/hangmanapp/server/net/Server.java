package se.kth.id1212.hangmanapp.server.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        Server server = new Server();
        server.acceptSockets();
    }

    private void acceptSockets() {
        ServerSocket listeningSocket;
        try {
            listeningSocket = new ServerSocket(8080);

            while (true) {
                Socket clientSocket = listeningSocket.accept();
                startClientThread(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startClientThread(Socket socket) {
        Client client = new Client(socket);
        Thread clientThread = new Thread(client);
        clientThread.setPriority(Thread.MAX_PRIORITY);
        clientThread.start();
    }
}
