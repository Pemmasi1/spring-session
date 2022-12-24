package com.siva.ems.platform;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
public class LambdaUtil {


    public static <T> Optional<T> safeGet(final Supplier<T> supplier) {
        try {
            return Optional.ofNullable(supplier.get());
        } catch (Exception exp) {
            log.error("Error processing attribute value {}", exp.getCause().toString());
            return Optional.empty();
        }
    }
}
