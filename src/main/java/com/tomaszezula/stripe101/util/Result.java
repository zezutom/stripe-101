package com.tomaszezula.stripe101.util;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Represents a result of an operation. Inspired by the project <a href="https://github.com/HubSpot/algebra">Algebra</a>.
 *
 * @param <SUCCESS_TYPE>
 */
public class Result<SUCCESS_TYPE> {

    public static <SUCCESS_TYPE> Result<SUCCESS_TYPE> some(SUCCESS_TYPE success) {
        return new Result<>(success, null);
    }

    public static <SUCCESS_TYPE> Result<SUCCESS_TYPE> error(String errorMessage) {
        return new Result<>(null, errorMessage);
    }

    public static <SUCCESS_TYPE> Result<SUCCESS_TYPE> none() {
        return new Result<>(null, null);
    }

    private SUCCESS_TYPE success;
    private String errorMessage;

    private Result(SUCCESS_TYPE success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public <R> R match(
            Function<SUCCESS_TYPE, R> successFunction,
            Function<String, R> errorFunction,
            Supplier<R> noneFunction
    ) {
        if (success != null) {
            return successFunction.apply(success);
        } else if (errorMessage != null) {
            return errorFunction.apply(errorMessage);
        } else {
            return noneFunction.get();
        }
    }
}
