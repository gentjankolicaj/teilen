package org.teilen.server.engine;

import org.teilen.common.domain.Room;
import org.teilen.common.domain.User;
import org.teilen.server.gui.ActivityPanel;

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
                    this.activityPanel.addRoom(new Room(1));

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
