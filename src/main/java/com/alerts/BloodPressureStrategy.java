package com.alerts;

public class BloodPressureStrategy implements AlertStrategy {
    @Override
    public boolean checkAlert(String condition, long timestamp) {
        // Implement blood pressure alert checking logic
        // Example condition: "high"
        return "high".equals(condition);
    }
}
