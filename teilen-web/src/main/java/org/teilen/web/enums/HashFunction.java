package org.teilen.web.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum HashFunction {

  MD5("MD5"), SHA_512("SHA-512"), SHA_256("SHA-256"), NONE("NONE");

  private final String value;

}
