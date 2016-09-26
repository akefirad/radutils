/*
 * Copyright © 2016 Rad Akefirad rad@akefirad.com
 *
 * This program is free software. It comes without any warranty, to the extent
 * permitted by applicable law. You can redistribute it and/or modify it under
 * the terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */

package com.akefirad.radutils.assertion;

import java.util.Objects;
import java.util.function.Predicate;

public interface Asserts {
    static <T> NullableAsserts<T, ObjectAsserts<T>> given (T object) {
        return given(object, "object");
    }

    static <T> NullableAsserts<T, ObjectAsserts<T>> given (T object, String name) {
        return new NullableAsserts<>(object, name, new ObjectAsserts<>(object, name));
    }

    static NullableAsserts<String, StringAsserts> given (String string) {
        return given(string, "string");
    }

    static NullableAsserts<String, StringAsserts> given (String string, String name) {
        return new NullableAsserts<>(string, name, new StringAsserts(string, name));
    }

    static void assertNotNull (Object object, String message) {
        if (object == null)
            throw new NullObjectException(message);
    }

    static void assertEqual (Object object1, Object object2, String message) {
        assertTrue(Objects.equals(object1, object2), message);
    }

    static void assertNotEqual (Object object1, Object object2, String message) {
        assertFalse(Objects.equals(object1, object2), message);
    }

    static void assertSame (Object object1, Object object2, String message) {
        assertTrue(object1 == object2, message);
    }

    static void assertNotSame (Object object1, Object object2, String message) {
        assertFalse(object1 == object2, message);
    }

    static void assertTrue (boolean value, String message) {
        if (!value)
            throw new InvalidObjectException(message);
    }

    static void assertFalse (boolean value, String message) {
        if (value)
            throw new InvalidObjectException(message);
    }

    static <T> void assertTrue (Predicate<? super T> predicate, T value, String message) {
        assertNotNull(predicate, "Predicate is null!");
        assertTrue(predicate.test(value), message);
    }

    static <T> void assertFalse (Predicate<? super T> predicate, T value, String message) {
        assertNotNull(predicate, "Predicate is null!");
        assertFalse(predicate.test(value), message);
    }
}
