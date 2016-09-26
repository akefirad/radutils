/*
 * Copyright © 2016 Rad Akefirad rad@akefirad.com
 *
 * This program is free software. It comes without any warranty, to the extent
 * permitted by applicable law. You can redistribute it and/or modify it under
 * the terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */

package com.akefirad.radutils.string;

public interface Strings {
    String EMPTY = "";
    String NEW_LINE = System.lineSeparator();
    String NEW_LINE_STRING = NEW_LINE.equals("\r\n") ? "\\r\\n" :
            NEW_LINE.equals("\r") ? "\\r" : NEW_LINE.equals("\n") ? "\\n" : EMPTY;
    String NEW_LINE_STRING_ESCAPED = NEW_LINE.equals("\r\n") ? "\\\\r\\\\n" :
            NEW_LINE.equals("\r") ? "\\\\r" : NEW_LINE.equals("\n") ? "\\\\n" : EMPTY;
    String NEW_LINE_STRING_DOUBLE_ESCAPED = NEW_LINE.equals("\r\n") ? "\\\\\\\\r\\\\\\\\n" :
            NEW_LINE.equals("\r") ? "\\\\\\\\r" : NEW_LINE.equals("\n") ? "\\\\\\\\n" : EMPTY;

    static String trimToEmpty (String string) {
        return string == null ? EMPTY : string.trim();
    }

    static String toString (Object object) {
        return (object == null) ? null : object.toString();
    }

    static String toSingleLineString (String string) {
        return (string == null) ? null : string
                .replaceAll(NEW_LINE_STRING_ESCAPED, NEW_LINE_STRING_DOUBLE_ESCAPED) // Escape new line string
                .replaceAll(NEW_LINE_STRING, NEW_LINE_STRING_ESCAPED); // replace new line character
    }

    static String toSingleLineString (Object object) {
        return toSingleLineString(toString(object));
    }

    static Object toSingleLineStringBuilder (Object object) {
        return new Object() {
            public String toString () {
                return Strings.toSingleLineString(object);
            }
        };
    }

}
