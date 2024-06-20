package org.teilen.desktop.global;

public class GlobalConfig {

  //Activity engine
  public static final int aeThreadSleep = 200; //millis
  public static final int aePacketNumber = 10;
  //IO engine
  public static final int ioThreadSleep = 200; //millis
  public static final int ioPacketNumber = 10;
  public static final long ioRWTimeout = 100;
  //ClientFrame config
  public static int xBound = 100;
  public static int yBound = 100;
  public static int clientWidth = 800;
  public static int clientHeight = 700;
  //WebcamFrame config
  public static int webcamWidth = 660;
  public static int webcamHeight = 310;
  //RoomPanel
  public static long RPRefreshThreshold = 1000; //millis
  //ActivityPanel
  public static long APRefreshThreshold = 1000; //millis


}
