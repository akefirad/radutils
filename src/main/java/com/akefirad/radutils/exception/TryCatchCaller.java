/*
 * Copyright © 2016 Rad Akefirad rad@akefirad.com
 *
 * This program is free software. It comes without any warranty, to the extent
 * permitted by applicable law. You can redistribute it and/or modify it under
 * the terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */

package com.akefirad.radutils.exception;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.Thread.currentThread;
import static java.util.stream.Stream.of;

public final class TryCatchCaller<T extends Exception, R> {
    private final static ExceptionSupplier<CaughtException> DEFAULT_SUPPLIER = CaughtException::new;
    private final static CallableExceptionHandler<?> DEFAULT_HANDLER = e -> {
        throw new UncaughtException(e);
    };

    private final CallableWithException<T, R> callable;
    private final Map<Class<? extends T>, CallableExceptionHandler<? extends R>> handlers;

    TryCatchCaller (CallableWithException<T, R> callable) {
        this.callable = callable;
        this.handlers = new LinkedHashMap<>();
    }

    @SafeVarargs
    public final TryCatchCaller<T, R> andHandleIfAny (CallableExceptionHandler<? extends R> handler,
                                                      Class<? extends T>... classes) {
        if (classes.length == 0)
            handlers.put(castAndGet(Exception.class), handler);
        else
            of(classes).forEach(klass -> handlers.put(klass, handler));
        return this;
    }

    @SafeVarargs
    public final TryCatchCaller<T, R> andReturnIfAny (R defaultValue, Class<? extends T>... classes) {
        return andHandleIfAny(exception -> defaultValue, classes);
    }

    @SafeVarargs
    public final TryCatchCaller<T, R> andThrowIfAny (ExceptionSupplier<? extends RuntimeException> supplier,
                                                     Class<? extends T>... classes) {
        return andHandleIfAny(exception -> {
            throw supplier.get(exception);
        }, classes);
    }

    @SafeVarargs
    public final TryCatchCaller<T, R> andThrowIfAny (Class<? extends T>... classes) {
        return andHandleIfAny(exception -> {
            throw DEFAULT_SUPPLIER.get(exception);
        }, classes);
    }

    public final TryCatchCaller<T, R> andHandleIfInterrupted (CallableExceptionHandler<? extends R> handler) {
        return andHandleIfAny(exception -> {
            currentThread().interrupt();
            return handler.handle(exception);
        }, castAndGet(InterruptedException.class));
    }

    public final TryCatchCaller<T, R> andReturnIfInterrupted (R defaultValue) {
        return andHandleIfAny(exception -> {
            currentThread().interrupt();
            return defaultValue;
        }, castAndGet(InterruptedException.class));
    }

    public final TryCatchCaller<T, R> andThrowIfInterrupted (ExceptionSupplier<? extends RuntimeException> supplier) {
        return andHandleIfAny(exception -> {
            currentThread().interrupt();
            throw supplier.get(exception);
        }, castAndGet(InterruptedException.class));
    }

    public final R call () {
        try {
            return callable.call();
        }
        catch (Exception exception) {
            return handlers.entrySet().stream()
                    .filter(entry -> entry.getKey().isInstance(exception))
                    .findFirst()
                    .orElse(new SimpleEntry<>(castAndGet(exception.getClass()), getDefaultHandler()))
                    .getValue()
                    .handle(exception);
        }
    }

    private CallableExceptionHandler<? extends R> getDefaultHandler () {
        //noinspection unchecked
        return (CallableExceptionHandler<? extends R>) DEFAULT_HANDLER;
    }

    private Class<? extends T> castAndGet (Class klass) {
        //noinspection unchecked
        return (Class<? extends T>) klass;
    }
}
