package com.cardio_generator.outputs;

import org.java_websocket.WebSocket;
import org.java_websocket.server.WebSocketServer;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A concrete implementation of the OutputStrategy interface that broadcasts data over WebSocket connections.
 *
 WebSockets are a communication protocol that provides full-duplex communication channels over a single TCP connection.
 They enable interactive communication between a client and a server in real-time.
 Unlike traditional HTTP connections, which are stateless and request-based,
 WebSocket connections allow for continuous data exchange between the client and server.

 WebSocket connections are persistent, bidirectional communication channels established between a client and a server.
 Once initiated, these connections remain open,
 allowing both parties to send messages to each other at any time without the need for repeated HTTP requests.

 WebSocket connections are commonly used in web applications for real-time features such as chat applications,
 live updates, multiplayer gaming, collaborative editing, and more.
 They provide low-latency communication and reduce overhead compared to other techniques
 like long polling or server-sent events.
 */
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WebSocketOutputStrategy implements OutputStrategy {

    private String host;
    private int port;

    public WebSocketOutputStrategy(String host, int port) {
        this.host = host;
        this.port = port;
        new SimpleServer(port).start(); // Start the simple server
    }

    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        // Format the incoming data messages
        String message = String.format("%d,%d,%s,%s", patientId, timestamp, label, data);
        // Ensure all necessary patient information is included
        sendData(message);
    }


    private void sendData(String message) {
        try (Socket socket = new Socket(host, port);
             OutputStream outputStream = socket.getOutputStream()) {
            outputStream.write(message.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Simple server class
    private static class SimpleServer extends Thread {
        private int port;

        public SimpleServer(int port) {
            this.port = port;
        }

        @Override
        public void run() {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("Server is listening on port " + port);
                while (true) {
                    try (Socket socket = serverSocket.accept()) {
                        System.out.println("New client connected");
                        // Handle client connection
                        handleClient(socket);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void handleClient(Socket socket) {
            try (OutputStream outputStream = socket.getOutputStream()) {
                // Send a welcome message or handle the client connection
                outputStream.write("Welcome to the server\n".getBytes());
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
