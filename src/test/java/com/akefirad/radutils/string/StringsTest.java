package com.akefirad.radutils.string;

import org.junit.Test;

import static com.akefirad.radutils.string.Strings.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class StringsTest {
    @Test
    public void test_given_trimToEmpty_when_null_then_returnsEmpty () {
        String actual = Strings.trimToEmpty(null);
        assertThat(actual, emptyString());
    }

    @Test
    public void test_given_trimToEmpty_when_onlySpaces_then_returnsEmpty () {
        String actual = Strings.trimToEmpty("  ");
        assertThat(actual, emptyString());
    }

    @Test
    public void test_given_trimToEmpty_when_withSpaces_then_returnsWithoutSpaces () {
        String expected = " foo bar ";
        String actual = Strings.trimToEmpty(expected);
        assertThat(actual, is(expected.trim()));
    }

    @Test
    public void test_given_toString_when_null_then_returnsNull () {
        String actual = Strings.toString(null);
        assertThat(actual, nullValue());
    }

    @Test
    public void test_given_toString_when_nonNull_then_returnsNonNull () {
        Object object = new Object();
        String actual = Strings.toString(object);
        assertThat(actual, is(object.toString()));
    }

    @Test
    public void test_given_toSingleLineString_when_null_then_returnsNull () {
        String actual = toSingleLineString(null);
        assertThat(actual, nullValue());
    }

    @Test
    public void test_given_toSingleLineString_when_singleLine_then_returnsSingleLine () {
        String expected = "foo bar";
        String actual = toSingleLineString(expected);
        assertThat(actual, is(expected));
    }

    @Test
    public void test_given_toSingleLineString_when_multiLines_withoutNewLine_then_returnsSingleLine () {
        String expected = "foo" + NEW_LINE_STRING + NEW_LINE_STRING + "bar";
        String actual = toSingleLineString("foo" + NEW_LINE + NEW_LINE + "bar");
        assertThat(actual, is(expected));
    }

    @Test
    public void test_given_toSingleLineString_when_multiLines_withNewLine_then_returnsSingleLine () {
        String expected = "foo" + NEW_LINE_STRING + NEW_LINE_STRING_ESCAPED + NEW_LINE_STRING + "bar";
        String actual = toSingleLineString("foo" + NEW_LINE + NEW_LINE_STRING + NEW_LINE + "bar");
        assertThat(actual, is(expected));
    }

    @Test
    public void test_given_toSingleLineStringBuilder_when_null_then_returnsNull () {
        String actual = toSingleLineStringBuilder(null).toString();
        assertThat(actual, nullValue());
    }

    @Test
    public void test_given_toSingleLineStringBuilder_when_nonNull_then_returnsNonNull () {
        String expected = "foo" + NEW_LINE_STRING + NEW_LINE_STRING_ESCAPED + NEW_LINE_STRING + "bar";
        String actual = toSingleLineStringBuilder("foo" + NEW_LINE + NEW_LINE_STRING + NEW_LINE + "bar").toString();
        assertThat(actual, is(expected));
    }
}
