package org.example;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.dto.ContactBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientSocketXML {

    public static void main(String[] args) {

        System.out.println("Client Initializer");

        Socket clientSocket;
        try {
            clientSocket = new Socket("localhost", 6787);

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String xml = reader.readLine();
            System.out.println("XML received: " + xml);

            XmlMapper mapper = new XmlMapper();
            ContactBook contactBook = mapper.readValue(xml, ContactBook.class);
            System.out.println(contactBook.toString());

            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
