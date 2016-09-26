/*
 * Copyright © 2016 Rad Akefirad rad@akefirad.com
 *
 * This program is free software. It comes without any warranty, to the extent
 * permitted by applicable law. You can redistribute it and/or modify it under
 * the terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */

package com.akefirad.radutils.assertion;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * The class providing assertions for non-nullable objects
 *
 * @author Rad Akefirad (rad@akefirad.com)
 */
public class ObjectAsserts<T> {
    T object;
    String name;

    ObjectAsserts (T object, String name) {
        this.object = object;
        this.name = name;
    }

    public ObjectAsserts<T> assertTrue (Predicate<? super T> predicate) {
        Asserts.assertTrue(predicate, object, name + " is not valid value!");
        return this;
    }

    public ObjectAsserts<T> assertTrue (Predicate<? super T> predicate, String message) {
        Asserts.assertTrue(predicate, object, message);
        return this;
    }

    public ObjectAsserts<T> assertFalse (Predicate<? super T> predicate) {
        Asserts.assertFalse(predicate, object, name + " is not valid value!");
        return this;
    }

    public ObjectAsserts<T> assertFalse (Predicate<? super T> predicate, String message) {
        Asserts.assertFalse(predicate, object, message);
        return this;
    }

    public ObjectAsserts<T> assertEqual (T expected) {
        Asserts.assertEqual(object, expected, name + " is not equal to " + expected);
        return this;
    }

    public ObjectAsserts<T> assertEqual (T expected, String message) {
        Asserts.assertEqual(object, expected, message);
        return this;
    }

    public ObjectAsserts<T> assertNotEqual (T expected) {
        Asserts.assertNotEqual(object, expected, name + " is equal to " + expected);
        return this;
    }

    public ObjectAsserts<T> assertNotEqual (T expected, String message) {
        Asserts.assertNotEqual(object, expected, message);
        return this;
    }

    public ObjectAsserts<T> assertSame (T expected) {
        Asserts.assertSame(object, expected, name + " is not same as " + expected);
        return this;
    }

    public ObjectAsserts<T> assertSame (T expected, String message) {
        Asserts.assertSame(object, expected, message);
        return this;
    }

    public ObjectAsserts<T> assertNotSame (T expected) {
        Asserts.assertNotSame(object, expected, name + " is same as " + expected);
        return this;
    }

    public ObjectAsserts<T> assertNotSame (T expected, String message) {
        Asserts.assertNotSame(object, expected, message);
        return this;
    }

    public T thenGet () {
        return object;
    }

    public <R> R thenGet (Function<? super T, ? extends R> function) {
        Asserts.assertNotNull(function, "Asserting " + name + ": function is null!");
        return function.apply(object);
    }

    public void thenDo (Consumer<? super T> consumer) {
        Asserts.assertNotNull(consumer, "Asserting " + name + ": consumer is null!");
        consumer.accept(object);
    }

    public void thenOk () {
        // no-operation
    }
}
