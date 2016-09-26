/*
 * Copyright © 2016 Rad Akefirad rad@akefirad.com
 *
 * This program is free software. It comes without any warranty, to the extent
 * permitted by applicable law. You can redistribute it and/or modify it under
 * the terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */

package com.akefirad.radutils.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * The utility class for calling or running with exception handling
 *
 * @author Rad Akefirad (rad@akefirad.com)
 */
public interface Exceptions {
    static <T extends Exception, R> TryCatchCaller<T, R> callAndCatch (CallableWithException<T, R> callable) {
        return new TryCatchCaller<>(callable);
    }

    static <T extends Exception> TryCatchRunner<T> runAndCatch (RunnableWithException<T> runnable) {
        return new TryCatchRunner<>(callAndCatch(() -> {
            runnable.run();
            return null;
        }));
    }

    static String getStackTraceAsString (Throwable throwable) {
        if (throwable == null)
            return null;

        StringWriter sw = new StringWriter();
        throwable.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    static Object getStackTraceAsStringBuilder (Throwable throwable) {
        return new Object() {
            public String toString () {
                return Exceptions.getStackTraceAsString(throwable);
            }
        };
    }
}
