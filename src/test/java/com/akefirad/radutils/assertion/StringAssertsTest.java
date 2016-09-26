package com.akefirad.radutils.assertion;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Date;

import static com.akefirad.radutils.string.Strings.EMPTY;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StringAssertsTest {
    private final String name = "this-should-appear-in-the-excretion-message";
    private final String message = "this-should-be-the-excretion-message";
    private final String string = "this-is-not-null-not-empty-string";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void test_thenTrim_then_thenGet () {
        String expected = string;
        String actual = Asserts.given(" " + string + " ").assertNotNull().thenTrim().thenGet();
        assertThat(actual, is(expected));
    }

    // assertEmpty -----------------------------------------------------------------------------------------------------
    @Test
    public void test_given_with_Empty_then_assertEmpty_then_thenGet () throws Exception {
        String expected = EMPTY;
        String actual = Asserts.given(expected).assertNotNull().assertEmpty().thenGet();
        assertThat(actual, is(expected));
    }

    @Test
    public void test_given_with_NonEmpty_and_Name_then_assertEmpty () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(containsString(name));

        Asserts.given(name, name).assertNotNull().assertEmpty().thenOk();
    }

    @Test
    public void test_given_with_Empty_then_assertEmpty_with_Message () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(message);

        Asserts.given(message).assertNotNull().assertEmpty(message).thenOk();
    }

    @Test
    public void test_given_with_Empty_then_assertEmpty_with_Message_then_thenGet () throws Exception {
        String expected = EMPTY;
        String actual = Asserts.given(expected).assertNotNull().assertEmpty(message).thenGet();
        assertThat(actual, is(expected));
    }

    // assertNotEmpty --------------------------------------------------------------------------------------------------
    @Test
    public void test_given_with_NonEmpty_then_assertNotEmpty_then_thenGet () throws Exception {
        String expected = string;
        String actual = Asserts.given(expected).assertNotNull().assertNotEmpty().thenGet();
        assertThat(actual, is(expected));
    }

    @Test
    public void test_given_with_Empty_and_Name_then_assertNotEmpty () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(containsString(name));

        Asserts.given(EMPTY, name).assertNotNull().assertNotEmpty().thenOk();
    }

    @Test
    public void test_given_with_Empty_then_assertNotEmpty_with_Message () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(message);

        Asserts.given(EMPTY).assertNotNull().assertNotEmpty(message).thenOk();
    }

    @Test
    public void test_given_with_NonEmpty_then_assertNotEmpty_with_Message_then_thenGet () throws Exception {
        String expected = string;
        String actual = Asserts.given(expected).assertNotNull().assertNotEmpty(message).thenGet();
        assertThat(actual, is(expected));
    }

    // assertStartsWith -------------------------------------------------------------------------------------------------
    @Test
    public void test_given_then_assertStartsWith () throws Exception {
        Asserts.given(string + new Date()).assertNotNull().assertStartsWith(string).thenOk();
    }

    @Test
    public void test_given_with_Name_then_assertStartsWith () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(containsString(name));

        Asserts.given(EMPTY, name).assertNotNull().assertStartsWith(EMPTY + new Date()).thenOk();
    }

    @Test
    public void test_given_then_assertStartsWith_with_Message () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(message);

        Asserts.given(EMPTY).assertNotNull().assertStartsWith(EMPTY + new Date(), message).thenOk();
    }

    @Test
    public void test_given_then_assertStartsWith_with_Message_then_thenGet () throws Exception {
        String expected = string + new Date();
        String actual = Asserts.given(expected).assertNotNull().assertStartsWith(string, message).thenGet();
        assertThat(actual, is(expected));
    }

    // assertNotStartWith ----------------------------------------------------------------------------------------------
    @Test
    public void test_given_then_assertNotStartWith () throws Exception {
        Asserts.given(new Date() + string).assertNotNull().assertNotStartWith(string).thenOk();
    }

    @Test
    public void test_given_with_Name_then_assertNotStartWith () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(containsString(name));

        Asserts.given(name + new Date(), name).assertNotNull().assertNotStartWith(name).thenOk();
    }

    @Test
    public void test_given_then_assertNotStartWith_with_Message () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(message);

        Asserts.given(message + new Date()).assertNotNull().assertNotStartWith(message, message).thenOk();
    }

    @Test
    public void test_given_then_assertNotStartWith_with_Message_then_thenGet () throws Exception {
        String expected = new Date() + string;
        String actual = Asserts.given(expected).assertNotNull().assertNotStartWith(string, message).thenGet();
        assertThat(actual, is(expected));
    }

    // assertEndsWith -------------------------------------------------------------------------------------------------
    @Test
    public void test_given_then_assertEndsWith () throws Exception {
        Asserts.given(new Date() + string).assertNotNull().assertEndsWith(string).thenOk();
    }

    @Test
    public void test_given_with_Name_then_assertEndsWith () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(containsString(name));

        Asserts.given(EMPTY, name).assertNotNull().assertEndsWith(EMPTY + new Date()).thenOk();
    }

    @Test
    public void test_given_then_assertEndsWith_with_Message () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(message);

        Asserts.given(EMPTY).assertNotNull().assertEndsWith(EMPTY + new Date(), message).thenOk();
    }

    @Test
    public void test_given_then_assertEndWith_with_Message_then_thenGet () throws Exception {
        String expected = new Date() + string;
        String actual = Asserts.given(expected).assertNotNull().assertEndsWith(string, message).thenGet();
        assertThat(actual, is(expected));
    }

    // assertNotEndWith ------------------------------------------------------------------------------------------------
    @Test
    public void test_given_then_assertNotEndWith () throws Exception {
        Asserts.given(string + new Date()).assertNotNull().assertNotEndWith(string).thenOk();
    }

    @Test
    public void test_given_with_Name_then_assertNotEndWith () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(containsString(name));

        Asserts.given(new Date() + name, name).assertNotNull().assertNotEndWith(name).thenOk();
    }

    @Test
    public void test_given_then_assertNotEndWith_with_Message () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(message);

        Asserts.given(new Date() + message).assertNotNull().assertNotEndWith(message, message).thenOk();
    }

    @Test
    public void test_given_then_assertNotEndWith_with_Message_then_thenGet () throws Exception {
        String expected = string + new Date();
        String actual = Asserts.given(expected).assertNotNull().assertNotEndWith(string, message).thenGet();
        assertThat(actual, is(expected));
    }

    // assertMatch -----------------------------------------------------------------------------------------------------
    @Test
    public void test_given_then_assertMatch () throws Exception {
        Asserts.given(string).assertNotNull().assertMatch(string).thenOk();
    }

    @Test
    public void test_given_with_Name_then_assertMatch () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(containsString(name));

        Asserts.given(EMPTY, name).assertNotNull().assertMatch("\\W+").thenOk();
    }

    @Test
    public void test_given_then_assertMatch_with_Message () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(message);

        Asserts.given(EMPTY).assertNotNull().assertMatch("\\W+", message).thenOk();
    }

    @Test
    public void test_given_then_assertMatch_with_Message_then_thenGet () throws Exception {
        String expected = string;
        String actual = Asserts.given(expected).assertNotNull().assertMatch(string, message).thenGet();
        assertThat(actual, is(expected));
    }

    // assertNotMatch --------------------------------------------------------------------------------------------------
    @Test
    public void test_given_then_assertNotMatch () throws Exception {
        Asserts.given(EMPTY).assertNotNull().assertNotMatch(string).thenOk();
    }

    @Test
    public void test_given_with_Name_then_assertNotMatch () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(containsString(name));

        Asserts.given(EMPTY, name).assertNotNull().assertNotMatch(EMPTY).thenOk();
    }

    @Test
    public void test_given_then_assertNotMatch_with_Message () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(message);

        Asserts.given(EMPTY).assertNotNull().assertNotMatch(EMPTY, message).thenOk();
    }

    @Test
    public void test_given_then_assertNotMatch_with_Message_then_thenGet () throws Exception {
        String expected = EMPTY;
        String actual = Asserts.given(expected).assertNotNull().assertNotMatch(string, message).thenGet();
        assertThat(actual, is(expected));
    }
}