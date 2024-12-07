package org.example;

import org.example.dto.Contact;
import org.example.dto.ContactBook;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerSocketTCP {
    public static void main(String[] args) {
        int port = 6789;

        var contact = Contact.builder()
                .name("Paulo")
                .email("example@ufc.br")
                .phoneNumber("4002-8922")
                .build();

        var contact1 = Contact.builder()
                .name("Rego")
                .email("example@dc.ufc.br")
                .phoneNumber("9090-9090")
                .build();

        List<Contact> listContact = new ArrayList<>();
        listContact.add(contact);
        listContact.add(contact1);

        var contactList = ContactBook.builder()
                .contactLists(listContact)
                .build();

        try {
            System.out.println("Initialize Server...");
            ServerSocket listenSocket = new ServerSocket(port);
            System.out.println("Server listening port: " + port);

            while (true) {
                Socket connectionSocket = listenSocket.accept();
                System.out.println("Connection accept ");

                long initialTime = System.currentTimeMillis();

                ObjectOutputStream outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
                outToClient.writeObject(contactList);
                System.out.println("Send Object with successful");

                System.out.println("execution time: " + (System.currentTimeMillis() - initialTime));
            }
        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}
