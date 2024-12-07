package org.example;


import utils.protobuf.ContactBookProto.ContactBook;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientSocketProtobuf {
    public static void main(String[] args) {

        System.out.println("Client Initializer");

        Socket clientSocket;
        try {
            long initialTime = System.currentTimeMillis();
            clientSocket = new Socket("localhost", 6767);
            ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());

            ContactBook receive = (ContactBook) inFromServer.readObject();
            clientSocket.close();

            System.out.println(receive.toString());

            System.out.println("execution time: " + (System.currentTimeMillis() - initialTime));
        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
