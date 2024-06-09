package com.alerts;

public class HeartRateStrategy implements AlertStrategy {
    @Override
    public boolean checkAlert(String condition, long timestamp) {
        // Implement heart rate alert checking logic
        // Example condition: "abnormal"
        return "abnormal".equals(condition);
    }
}
