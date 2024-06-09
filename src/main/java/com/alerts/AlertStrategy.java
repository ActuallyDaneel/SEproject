package com.alerts;

public interface AlertStrategy {
    boolean checkAlert(String condition, long timestamp);
}
