/*
 * Copyright © 2016 Rad Akefirad rad@akefirad.com
 *
 * This program is free software. It comes without any warranty, to the extent
 * permitted by applicable law. You can redistribute it and/or modify it under
 * the terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */

package com.akefirad.radutils.assertion;

public class NullableAsserts<T, S extends ObjectAsserts<T>> {
    private T object;
    private String name;
    private S asserts;

    NullableAsserts (T object, String name, S asserts) {
        this.object = object;
        this.name = name;
        this.asserts = asserts;
    }

    public S assertNotNull () {
        Asserts.assertNotNull(object, name + " is null!");
        return asserts;
    }

    public S assertNotNull (String message) {
        Asserts.assertNotNull(object, message);
        return asserts;
    }

    public S assertNotNullOrElse (T value) {
        object = object == null ? value : object;
        asserts.object = object;
        return assertNotNull();
    }
}
