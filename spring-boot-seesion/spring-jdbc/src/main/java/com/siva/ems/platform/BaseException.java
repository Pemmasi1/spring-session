package com.siva.ems.platform;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseException extends RuntimeException {

    public BaseException(String message, int errorCode) {
        this(message, null, errorCode);
    }

    public BaseException(String message, Object o, int errorCode) {

    }
}
