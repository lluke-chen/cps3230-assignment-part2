package com.uom.cps3230.website;

public class WebsiteSystem {
    private boolean loggedIn = false, viewingAlerts = false;

    boolean login() {
        if (!(loggedIn && viewingAlerts)) {
            loggedIn = true;
        }
        else {
            throw new IllegalStateException();
        }
        return true;
    }

    boolean logOut() {
        if (loggedIn) {
            loggedIn = false;
            viewingAlerts = false;
        }
        else {
            throw new IllegalStateException();
        }
        return true;
    }

    boolean viewAlerts() {
        if (loggedIn) {
            viewingAlerts = true;
        }
        else {
            throw new IllegalStateException();
        }
        return true;
    }

    boolean isLoggedIn() {
        return loggedIn;
    }

    boolean isViewingAlerts() {
        return viewingAlerts;
    }
}
