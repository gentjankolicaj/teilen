package org.teilen.web.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author gentjan kolicaj
 */
@RequiredArgsConstructor
@Getter
public enum AuthStatus {

  SUCCESS("SUCCESS"), FAILED("FAILED"), FAILED_UNKNOWN("FAILED_UNKNOWN");
  private final String value;

}
