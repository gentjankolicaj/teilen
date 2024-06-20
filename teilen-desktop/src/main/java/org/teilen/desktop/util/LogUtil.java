package org.teilen.desktop.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {

  private static final Logger LOGGER = LogManager.getLogger(LogUtil.class);

  public static void info(String info) {
    LOGGER.info(info);
  }

  public static void warn(String info) {
    LOGGER.warn(info);
  }

  public static void error(String info) {
    LOGGER.error(info);
  }
}
