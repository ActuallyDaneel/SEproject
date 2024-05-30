package com.data_management;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class WebSocketClientImpl extends WebSocketClient implements DataReader {
    private DataStorage dataStorage;
    private CountDownLatch latch;
    private final Pattern dataPattern = Pattern.compile(
            "(\\d*),(\\d*),(\\w*),(\\d*\\.\\d*)"
    );

    public WebSocketClientImpl(String serverUri, DataStorage dataStorage) throws URISyntaxException {
        super(new URI(serverUri));
        this.dataStorage = dataStorage;
        this.latch = new CountDownLatch(1);
    }

    @Override
    public void connect(String serverUri) throws Exception {
        super.connectBlocking();
        latch.await();
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connected to WebSocket server");
        latch.countDown();
    }

    @Override
    public void onMessage(String message) {
        // Parse and store the message
        // String message = String.format("%d,%d,%s,%s", patientId, timestamp, label, data);
        Matcher matcher = dataPattern.matcher(message);
        if (matcher.find()) {
            // Extracting data using the regex groups
            int patientId = Integer.parseInt(matcher.group(1));
            long timestamp = Long.parseLong(matcher.group(2));
            String label = matcher.group(3);
            double data = Double.parseDouble(matcher.group(4));

            // Call the method in DataStorage with extracted values
            dataStorage.addPatientData(patientId, data, label, timestamp);
        }
        //dataStorage.storeData(parseMessage(message));
    }

    private String parseMessage(String message) {
        // Implement your parsing logic here
        System.out.println(message);
        return message;
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Disconnected from WebSocket server: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void close(){
        try {
            super.closeBlocking();
        } catch (Exception e) {
            // Handle the exception appropriately
            // For example, log the error or wrap it in a runtime exception
            throw new RuntimeException(e);
        }
    }

    @Override
    public String receiveData() throws Exception {
        // Not used in WebSocket scenario
        return null;
    }
}
