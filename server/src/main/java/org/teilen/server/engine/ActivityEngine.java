package org.teilen.server.engine;

import org.teilen.common.domain.Room;
import org.teilen.common.domain.User;
import org.teilen.server.gui.ActivityPanel;
import org.teilen.server.util.LogUtil;

public class ActivityEngine implements Runnable {
    private ActivityPanel activityPanel;

    private static int counter;
    @Override
    public void run() {
        while (true) {
            try {
                if (activityPanel != null) {
                    Thread.sleep(2000);
                    activityPanel.addUser(new User(counter, "John", "Doe"));

                    activityPanel.addRoom(new Room(counter, "Test room"));
                    counter++;
                    LogUtil.info("User & room added.");
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
