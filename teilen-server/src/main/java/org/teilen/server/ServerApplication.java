package org.teilen.server;

import org.teilen.server.engine.ActivityEngine;
import org.teilen.server.engine.IOEngine;
import org.teilen.server.gui.ServerFrame;


public class ServerApplication {

  /**
   * To launch the application : java -jar teilen-server.jar -g -a -g => launch gui -a => launch
   * rest api
   */
  public static void main(String[] args) {
    boolean launchGui = false;
    boolean launchApi = false;
    if (args != null && args.length != 0) {
      for (int i = 0; i < args.length; i++) {
        if (args[i].equals("-G") || args[i].equals("-g")) {
          launchGui = true;
        } else if (args[i].equals("-A") || args[i].equals("-a")) {
          launchApi = true;
        }
      }
    }
    ActivityEngine activityEngine = new ActivityEngine();
    IOEngine ioEngine = new IOEngine(launchGui);

    ioEngine.start();
    activityEngine.start();

    if (launchGui) {
      new ServerFrame(activityEngine, ioEngine);
    }
  }


}
