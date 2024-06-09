package com.alerts;

public class RepeatedAlertDecorator extends AlertDecorator {
    public RepeatedAlertDecorator(Alert decoratedAlert) {
        super(decoratedAlert);
    }


        @Override
        public void checkAlert() {
            super.checkAlert();
            // Additional functionality for priority alert
            // Example: Adding priority tagging to the alert
            System.out.println("Adding priority to alert for patient: " + getPatientId());
        }


}
