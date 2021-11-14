package org.teilen_webcam.server.engine;

import org.teilen_webcam.common.domain.Room;
import org.teilen_webcam.common.domain.User;
import org.teilen_webcam.server.gui.ActivityPanel;

public class ActivityEngine implements Runnable {
    private ActivityPanel activityPanel;


    @Override
    public void run() {
        while (true) {
            try {
                if (activityPanel != null) {
                    Thread.sleep(4000);
                    this.activityPanel.addUser(new User(1, "John", "Doe"));
                    this.activityPanel.addUser(new User(1, "John", "Doe"));
                    this.activityPanel.addRoom(new Room());

                } else {
                    Thread.sleep(100);
                }
            } catch (Exception e) {

            }
        }

    }

    public void setActivityPanel(ActivityPanel activityPanel) {
        this.activityPanel = activityPanel;
    }
}
