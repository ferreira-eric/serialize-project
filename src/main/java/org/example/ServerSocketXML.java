package org.example;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.dto.OrderRequest;
import org.example.dto.Product;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ServerSocketXML {
    public static void main(String[] args) {
        int port = 6787;

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setProducts(Arrays.asList(
                new Product("pc", 2222, 2),
                new Product("tv", 111111, 1)));

        try {
            System.out.println("Initialize Server...");
            ServerSocket listenSocket = new ServerSocket(port);
            System.out.println("Server listening port: " + port);

            while (true) {
                Socket connectionSocket = listenSocket.accept();
                System.out.println("Connection accept ");

                XmlMapper mapper = new XmlMapper();
                String json = mapper.writeValueAsString(orderRequest);

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
                writer.write(json);
                writer.newLine();
                writer.flush();
                System.out.println("Send Object XML with successful");
            }
        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}
