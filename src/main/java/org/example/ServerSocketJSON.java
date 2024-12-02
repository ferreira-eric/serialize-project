package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.Contact;
import org.example.dto.ContactBook;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerSocketJSON {
    public static void main(String[] args) {
        int port = 6788;

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

                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(contactList);
                byte[] jsonBytes = json.getBytes();

                int dataLength = jsonBytes.length;

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
                writer.write(json);
                writer.newLine();
                writer.flush();
                System.out.println("Send Object JSON with successful: " + json);
                System.out.println("Json length: " + dataLength);
            }
        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}
