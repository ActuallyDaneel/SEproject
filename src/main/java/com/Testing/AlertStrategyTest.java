package com.Testing;

import com.alerts.AlertStrategy;
import com.alerts.BloodPressureStrategy;
import com.alerts.HeartRateStrategy;
import com.alerts.OxygenSaturationStrategy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlertStrategyTest {

    @Test
    public void testBloodPressureStrategy() {
        AlertStrategy strategy = new BloodPressureStrategy();
        assertTrue(strategy.checkAlert("high", System.currentTimeMillis()), "Alert should be triggered");
        assertFalse(strategy.checkAlert("normal", System.currentTimeMillis()), "Alert should not be triggered");
    }

    @Test
    public void testHeartRateStrategy() {
        AlertStrategy strategy = new HeartRateStrategy();
        assertTrue(strategy.checkAlert("abnormal", System.currentTimeMillis()), "Alert should be triggered");
        assertFalse(strategy.checkAlert("normal", System.currentTimeMillis()), "Alert should not be triggered");
    }

    @Test
    public void testOxygenSaturationStrategy() {
        AlertStrategy strategy = new OxygenSaturationStrategy();
        assertTrue(strategy.checkAlert("low", System.currentTimeMillis()), "Alert should be triggered");
        assertFalse(strategy.checkAlert("normal", System.currentTimeMillis()), "Alert should not be triggered");
    }
}
