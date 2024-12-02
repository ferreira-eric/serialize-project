package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.ContactBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientSocketJSON {
    public static void main(String[] args) {

        System.out.println("Client Initializer");

        Socket clientSocket;
        try {
            clientSocket = new Socket("localhost", 6788);
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String json = reader.readLine();
            System.out.println("JSON received: " + json);

            ObjectMapper mapper = new ObjectMapper();
            ContactBook contactBook = mapper.readValue(json, ContactBook.class);
            System.out.println(contactBook.toString());

            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
