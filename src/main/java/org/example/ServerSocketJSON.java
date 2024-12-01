package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.Contact;
import org.example.dto.ContactBook;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

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

        ContactBook contactBook = new ContactBook();
        contactBook.setContactLists(Arrays.asList(
                new Contact("Paulo", "4002-8922", "example@ufc.br", null),
                new Contact("Rego", "9090-9090", "example@dc.ufc.br", null)));

        try {
            System.out.println("Initialize Server...");
            ServerSocket listenSocket = new ServerSocket(port);
            System.out.println("Server listening port: " + port);

            while (true) {
                Socket connectionSocket = listenSocket.accept();
                System.out.println("Connection accept ");
                //ObjectOutputStream outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
                //ObjectInputStream inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
                //outToClient.writeObject(contactBook);

                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(contactBook);

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
                writer.write(json);
                writer.newLine();
                writer.flush();
                System.out.println("Send Object JSON with successful");
            }
        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}
