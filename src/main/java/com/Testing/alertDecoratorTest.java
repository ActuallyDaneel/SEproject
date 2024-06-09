package com.Testing;

import com.alerts.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class alertDecoratorTest {

    @Test
    public void testRepeatedAlertDecorator() {
        AlertStrategy strategy = new BloodPressureStrategy();
        Alert alert = new BloodPressureAlert("patient123", "high", System.currentTimeMillis());
        Alert repeatedAlert = new RepeatedAlertDecorator(alert);

        repeatedAlert.checkAlert();
        // Check console output or use a spy to ensure the decorator functionality is invoked
    }

    @Test
    public void testPriorityAlertDecorator() {
        AlertStrategy strategy = new BloodPressureStrategy();
        Alert alert = new BloodPressureAlert("patient123", "high", System.currentTimeMillis());
        Alert priorityAlert = new PriorityAlertDecorator(alert);

        priorityAlert.checkAlert();
        // Check console output or use a spy to ensure the decorator functionality is invoked
    }
}
