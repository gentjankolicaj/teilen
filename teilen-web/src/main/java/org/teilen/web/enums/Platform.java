package org.teilen.web.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Platform {

  WEB("WEB"), DESKTOP("DESKTOP"), MOBILE("MOBILE");

  private final String value;

}
