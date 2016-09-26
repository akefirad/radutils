/*
 * Copyright © 2016 Rad Akefirad rad@akefirad.com
 *
 * This program is free software. It comes without any warranty, to the extent
 * permitted by applicable law. You can redistribute it and/or modify it under
 * the terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */

package com.akefirad.radutils.assertion;

public class StringAsserts extends ObjectAsserts<String> {
    StringAsserts (String string, String name) {
        super(string, name);
    }

    public StringAsserts thenTrim () {
        object = object.trim();
        return this;
    }

    public StringAsserts assertEmpty () {
        Asserts.assertTrue(object.isEmpty(), name + " is not empty!");
        return this;
    }

    public StringAsserts assertEmpty (String message) {
        Asserts.assertTrue(object.isEmpty(), message);
        return this;
    }

    public StringAsserts assertNotEmpty () {
        Asserts.assertFalse(object.isEmpty(), name + " is empty!");
        return this;
    }

    public StringAsserts assertNotEmpty (String message) {
        Asserts.assertFalse(object.isEmpty(), message);
        return this;
    }

    public StringAsserts assertStartsWith (String prefix) {
        Asserts.assertNotNull(prefix, "Prefix is null!");
        Asserts.assertTrue(object.startsWith(prefix), name + " does not start with " + prefix);
        return this;
    }

    public StringAsserts assertStartsWith (String prefix, String message) {
        Asserts.assertNotNull(prefix, "Prefix is null!");
        Asserts.assertTrue(object.startsWith(prefix), message);
        return this;
    }

    public StringAsserts assertNotStartWith (String prefix) {
        Asserts.assertNotNull(prefix, "Prefix is null!");
        Asserts.assertFalse(object.startsWith(prefix), name + " starts with " + prefix);
        return this;
    }

    public StringAsserts assertNotStartWith (String prefix, String message) {
        Asserts.assertNotNull(prefix, "Prefix is null!");
        Asserts.assertFalse(object.startsWith(prefix), message);
        return this;
    }

    public StringAsserts assertEndsWith (String suffix) {
        Asserts.assertNotNull(suffix, "Suffix is null!");
        Asserts.assertTrue(object.endsWith(suffix), name + " does not start with " + suffix);
        return this;
    }

    public StringAsserts assertEndsWith (String suffix, String message) {
        Asserts.assertNotNull(suffix, "Suffix is null!");
        Asserts.assertTrue(object.endsWith(suffix), message);
        return this;
    }

    public StringAsserts assertNotEndWith (String suffix) {
        Asserts.assertNotNull(suffix, "Suffix is null!");
        Asserts.assertFalse(object.endsWith(suffix), name + " starts with " + suffix);
        return this;
    }

    public StringAsserts assertNotEndWith (String suffix, String message) {
        Asserts.assertNotNull(suffix, "Suffix is null!");
        Asserts.assertFalse(object.endsWith(suffix), message);
        return this;
    }

    public StringAsserts assertMatch (String pattern) {
        Asserts.assertNotNull(pattern, "Pattern is null!");
        Asserts.assertTrue(object.matches(pattern), name + " does not match pattern " + pattern);
        return this;
    }

    public StringAsserts assertMatch (String pattern, String message) {
        Asserts.assertNotNull(pattern, "Pattern is null!");
        Asserts.assertTrue(object.matches(pattern), message);
        return this;
    }

    public StringAsserts assertNotMatch (String pattern) {
        Asserts.assertNotNull(pattern, "Pattern is null!");
        Asserts.assertFalse(object.matches(pattern), name + " matches pattern " + pattern);
        return this;
    }

    public StringAsserts assertNotMatch (String pattern, String message) {
        Asserts.assertNotNull(pattern, "Pattern is null!");
        Asserts.assertFalse(object.matches(pattern), message);
        return this;
    }
}
