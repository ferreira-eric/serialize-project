package org.example;

import utils.protobuf.ContactBookProto.Contact;
import utils.protobuf.ContactBookProto.ContactBook;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketProtobuf {

    public static void main(String[] args) {
        int port = 6767;

        var contact = Contact.newBuilder()
                .setName("Paulo")
                .setEmail("example@ufc.br")
                .setPhoneNumber("4002-8922")
                .build();

        var contact1 = Contact.newBuilder()
                .setName("Rego")
                .setEmail("example@dc.ufc.br")
                .setPhoneNumber("9090-9090")
                .build();

        var contactList = ContactBook.newBuilder()
                .addContactLists(contact)
                .addContactLists(contact1)
                .build();

        try {
            System.out.println("Initialize Server...");
            ServerSocket listenSocket = new ServerSocket(port);
            System.out.println("Server listening port: " + port);

            while (true) {
                Socket connectionSocket = listenSocket.accept();
                System.out.println("Connection accept ");
                ObjectOutputStream outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
                outToClient.writeObject(contactList);
                System.out.println("Send Object with successful");
            }
        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}
