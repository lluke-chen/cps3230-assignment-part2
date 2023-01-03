package com.uom.cps3230.alerts;

public class AlertsSystem {
    private boolean alertsEmpty;

    boolean createAlert() {
        alertsEmpty = false;
        return true;
    }

    boolean deleteAlerts() {
        alertsEmpty = true;
        return true;
    }

    boolean isAlertsEmpty() {
        return alertsEmpty;
    }
}
