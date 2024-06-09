package com.alerts;

public class OxygenSaturationStrategy implements AlertStrategy {
    @Override
    public boolean checkAlert(String condition, long timestamp) {
        // Implement oxygen saturation alert checking logic
        // Example condition: "low"
        return "low".equals(condition);
    }
}
