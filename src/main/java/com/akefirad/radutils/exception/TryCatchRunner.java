/*
 * Copyright © 2016 Rad Akefirad rad@akefirad.com
 *
 * This program is free software. It comes without any warranty, to the extent
 * permitted by applicable law. You can redistribute it and/or modify it under
 * the terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */

package com.akefirad.radutils.exception;

public final class TryCatchRunner<T extends Exception> {
    private final TryCatchCaller<T, Void> caller;

    TryCatchRunner (TryCatchCaller<T, Void> caller) {
        this.caller = caller;
    }

    @SafeVarargs
    public final TryCatchRunner<T> andHandleIfAny (RunnableExceptionHandler handler, Class<? extends T>... classes) {
        caller.andHandleIfAny(exception -> {
            handler.handle(exception);
            return null;
        }, classes);
        return this;
    }

    @SafeVarargs
    public final TryCatchRunner<T> andThrowIfAny (ExceptionSupplier<? extends RuntimeException> supplier,
                                                  Class<? extends T>... classes) {
        caller.andThrowIfAny(supplier, classes);
        return this;
    }

    @SafeVarargs
    public final TryCatchCaller<T, Void> andReturnIfAny (Class<? extends T>... classes) {
        return caller.andReturnIfAny(null, classes);
    }

    @SafeVarargs
    public final TryCatchRunner<T> andThrowIfAny (Class<? extends T>... classes) {
        caller.andThrowIfAny(classes);
        return this;
    }

    public TryCatchRunner<T> andHandleIfInterrupted (RunnableExceptionHandler handler) {
        caller.andHandleIfInterrupted(exception -> {
            handler.handle(exception);
            return null;
        });
        return this;
    }

    public TryCatchRunner<T> andReturnIfInterrupted () {
        caller.andReturnIfInterrupted(null);
        return this;
    }

    public TryCatchRunner<T> andThrowIfInterrupted (ExceptionSupplier<? extends RuntimeException> supplier) {
        caller.andThrowIfInterrupted(supplier);
        return this;
    }

    public void run () {
        caller.call();
    }
}
