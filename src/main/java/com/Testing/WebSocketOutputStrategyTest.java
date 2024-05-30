package com.Testing;

import com.cardio_generator.outputs.WebSocketOutputStrategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import static org.junit.jupiter.api.Assertions.*;

public class WebSocketOutputStrategyTest {

    private WebSocketOutputStrategy outputStrategy;
    private final int port = 8080;
    private final String host = "localhost";

    @BeforeEach
    public void setUp() {
        outputStrategy = new WebSocketOutputStrategy(host, port);
    }

    @AfterEach
    public void tearDown() {
        // Here you could add any necessary cleanup code, if required
    }

    @Test
    public void testOutput() throws Exception {
        int patientID = 12345;
        long timestamp = 21382319230l;
        String label = "label";
        String data = "data";

        // Start a client socket to listen for the message sent by WebSocketOutputStrategy
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(port);
                 Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                String receivedMessage = in.readLine();
                assertNotNull(receivedMessage);

            } catch (Exception e) {
                fail("Exception in test server: " + e.getMessage());
            }
        }).start();

        // Give the server socket some time to start
        Thread.sleep(1000);

        // Send the message using WebSocketOutputStrategy
        outputStrategy.output(patientID, timestamp, label, data);

        // Give the client some time to receive the message
        Thread.sleep(1000);
    }

    @Test
    public void testSimpleServer() throws Exception {
        // Connect to the server and check for welcome message
        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String welcomeMessage = in.readLine();
            assertNotNull(welcomeMessage);
            assertEquals("Welcome to the server", welcomeMessage.trim());
        }
    }
}
