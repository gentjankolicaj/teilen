package org.teilen.web.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


/**
 * @author gentjan kolicaj
 */
@RequiredArgsConstructor
@Getter
public enum Sex {

  M("M"), F("F"), O("O");

  private final String value;

}
