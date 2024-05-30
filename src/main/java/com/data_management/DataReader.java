package com.data_management;

import java.io.IOException;

// DataReader.java (Interface)
public interface DataReader {
    void connect(String serverUri) throws Exception;
    void close() throws Exception;
    String receiveData() throws Exception;
}