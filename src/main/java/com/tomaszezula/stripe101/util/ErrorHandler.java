package com.tomaszezula.stripe101.util;

import org.springframework.util.function.ThrowingSupplier;

public class ErrorHandler {

  public static <T> T tried(ThrowingSupplier<T> supplier) {
    try {
      return supplier.get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
