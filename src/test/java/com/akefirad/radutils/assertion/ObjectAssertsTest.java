package com.akefirad.radutils.assertion;

import com.akefirad.radutils.util.Nullable;
import com.akefirad.radutils.util.ObjectWithID;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ObjectAssertsTest {
    private final String name = "this-should-appear-in-the-excretion-message";
    private final String message = "this-should-be-the-excretion-message";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    // assertNotNull ---------------------------------------------------------------------------------------------------
    @Test
    public void test_given_with_NotNull_then_assertNotNull_then_thenGet () throws Exception {
        Object expected = new Object();
        Object actual = Asserts.given(expected).assertNotNull().thenGet();
        assertThat(actual, is(expected));
    }

    @Test
    public void test_given_with_Null_and_Name_then_assertNotNull () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(containsString(name));

        Asserts.given((Object) null, name).assertNotNull().thenOk();
    }

    @Test
    public void test_given_with_Null_then_assertNotNull_with_Message () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(message);

        Asserts.given((Object) null).assertNotNull(message).thenOk();
    }

    @Test
    public void test_given_with_NonNull_then_assertNotNull_with_Message_then_thenGet () throws Exception {
        Object expected = new Object();
        Object actual = Asserts.given(expected).assertNotNull(message).thenGet();
        assertThat(actual, is(expected));
    }

    // assertTrue ------------------------------------------------------------------------------------------------------
    @Test
    public void test_given_then_assertTrue_with_True_then_thenGet () throws Exception {
        Object expected = new Object();
        Object actual = Asserts.given(expected).assertNotNull().assertTrue(o -> true).thenGet();
        assertThat(actual, is(expected));
    }

    @Test
    public void test_given_with_Name_then_assertTrue_with_False () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(containsString(name));

        Asserts.given(new Object(), name).assertNotNull().assertTrue(o -> false).thenOk();
    }

    @Test
    public void test_given_then_assertTrue_with_False_and_Message () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(message);

        Asserts.given(new Object()).assertNotNull().assertTrue(o -> false, message).thenOk();
    }

    @Test
    public void test_given_then_assertTrue_with_True_and_Message_then_thenGet () throws Exception {
        Object expected = new Object();
        Object actual = Asserts.given(expected).assertNotNull().assertTrue(o -> true, message).thenGet();
        assertThat(actual, is(expected));
    }

    // assertFalse ------------------------------------------------------------------------------------------------------
    @Test
    public void test_given_then_assertFalse_with_False_then_thenGet () throws Exception {
        Object expected = new Object();
        Object actual = Asserts.given(expected).assertNotNull().assertFalse(o -> false).thenGet();
        assertThat(actual, is(expected));
    }

    @Test
    public void test_given_with_Name_then_assertFalse_with_True () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(containsString(name));

        Asserts.given(new Object(), name).assertNotNull().assertFalse(o -> true).thenOk();
    }

    @Test
    public void test_given_then_assertFalse_with_True_and_Message () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(message);

        Asserts.given(new Object()).assertNotNull().assertFalse(o -> true, message).thenOk();
    }

    @Test
    public void test_given_then_assertFalse_with_False_and_Message_then_thenGet () throws Exception {
        Object expected = new Object();
        Object actual = Asserts.given(expected).assertNotNull().assertFalse(o -> false, message).thenGet();
        assertThat(actual, is(expected));
    }

    // assertEqual -----------------------------------------------------------------------------------------------------
    @Test
    public void test_given_then_assertEqual_with_EqualValues_then_thenGet () throws Exception {
        ObjectWithID expected = new ObjectWithID(0);
        ObjectWithID actual = Asserts.given(expected).assertNotNull().assertEqual(expected).thenGet();
        assertThat(actual, is(expected));
    }

    @Test
    public void test_given_with_Name_then_assertEqual_with_DifferentValues () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(containsString(name));

        Asserts.given(new Object(), name).assertNotNull().assertEqual(new Object()).thenOk();
    }

    @Test
    public void test_given_then_assertEqual_with_DifferentValues_and_Message () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(message);

        Asserts.given(new Object()).assertNotNull().assertEqual(new Object(), message).thenOk();
    }

    @Test
    public void test_given_then_assertEqual_with_DifferentValues_and_Message_then_thenGet () throws Exception {
        ObjectWithID expected = new ObjectWithID(0);
        ObjectWithID actual = Asserts.given(expected).assertNotNull().assertEqual(expected, message).thenGet();
        assertThat(actual, is(expected));
    }

    // assertNotEqual --------------------------------------------------------------------------------------------------
    @Test
    public void test_given_then_assertNotEqual_with_DifferentValues_then_thenGet () throws Exception {
        Object expected = new Object();
        Object actual = Asserts.given(expected).assertNotNull().assertNotEqual(new Object()).thenGet();
        assertThat(actual, is(expected));
    }

    @Test
    public void test_given_with_Name_then_assertNotEqual_with_EqualValues () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(containsString(name));

        Asserts.given(new ObjectWithID(0), name).assertNotNull().assertNotEqual(new ObjectWithID(0)).thenOk();
    }

    @Test
    public void test_given_then_assertNotEqual_with_EqualValues_and_Message () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(message);

        Asserts.given(new ObjectWithID(0)).assertNotNull().assertNotEqual(new ObjectWithID(0), message).thenOk();
    }

    @Test
    public void test_given_then_assertNotEqual_with_DifferentValues_and_Message_then_thenGet () throws Exception {
        Object expected = new Object();
        Object actual = Asserts.given(expected).assertNotNull().assertNotEqual(new Object(), message).thenGet();
        assertThat(actual, is(expected));
    }

    // assertSame ------------------------------------------------------------------------------------------------------
    @Test
    public void test_given_then_assertSame_with_SameValues_then_thenGet () throws Exception {
        Object expected = new Object();
        Object actual = Asserts.given(expected).assertNotNull().assertSame(expected).thenGet();
        assertThat(actual, is(expected));
    }

    @Test
    public void test_given_with_Name_then_assertSame_with_DifferentValues () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(containsString(name));

        Asserts.given(new Object(), name).assertNotNull().assertSame(new Object()).thenOk();
    }

    @Test
    public void test_given_then_assertSame_with_DifferentValues_and_Message () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(message);

        Asserts.given(new Object()).assertNotNull().assertSame(new Object(), message).thenOk();
    }

    @Test
    public void test_given_then_assertSame_with_DifferentValues_and_Message_then_thenGet () throws Exception {
        Object expected = new Object();
        Object actual = Asserts.given(expected).assertNotNull().assertSame(expected, message).thenGet();
        assertThat(actual, is(expected));
    }

    // assertNotSame ---------------------------------------------------------------------------------------------------
    @Test
    public void test_given_then_assertNotSame_with_DifferentValues_then_thenGet () throws Exception {
        Object expected = new Object();
        Object actual = Asserts.given(expected).assertNotNull().assertNotSame(new Object()).thenGet();
        assertThat(actual, is(expected));
    }

    @Test
    public void test_given_with_Name_then_assertNotSame_with_SameValues () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(containsString(name));

        Object object = new Object();
        Asserts.given(object, name).assertNotNull().assertNotSame(object).thenOk();
    }

    @Test
    public void test_given_then_assertNotSame_with_SameValues_and_Message () throws Exception {
        expectedException.expect(ObjectAssertionException.class);
        expectedException.expectMessage(message);

        Object object = new Object();
        Asserts.given(object).assertNotNull().assertNotSame(object, message).thenOk();
    }

    @Test
    public void test_given_then_assertNotSame_with_DifferentValues_and_Message_then_thenGet () throws Exception {
        Object expected = new Object();
        Object actual = Asserts.given(expected).assertNotNull().assertNotSame(new Object(), message).thenGet();
        assertThat(actual, is(expected));
    }

    // assertNotNullOrElse ---------------------------------------------------------------------------------------------
    @Test
    public void test_given_with_Null_then_assertNotNullOrElse_with_NonNull_thenGet () throws Exception {
        Object expected = new Object();
        Object actual = Asserts.given((Object) null).assertNotNullOrElse(expected).thenGet();
        assertThat(actual, is(expected));
    }

    @Test
    public void test_given_with_NonNull_then_assertNotNullOrElse_with_NonNull_then_thenGet () throws Exception {
        Object expected = new Object();
        Object actual = Asserts.given(expected).assertNotNullOrElse(new Object()).thenGet();
        assertThat(actual, is(expected));
    }

    @Test
    public void test_given_with_NonNull_then_assertNotNullOrElse_with_Null_then_thenGet () throws Exception {
        Object expected = new Object();
        Object actual = Asserts.given(expected).assertNotNullOrElse(null).thenGet();
        assertThat(actual, is(expected));
    }

    @Test(expected = ObjectAssertionException.class)
    public void test_given_with_Null_then_assertNotNullOrElse_with_Null () throws Exception {
        Asserts.given((Object) null).assertNotNullOrElse(null).thenOk();
    }

    // thenGet ---------------------------------------------------------------------------------------------------------
    @Test
    public void test_given_then_thenGet_with_NonNullFunction () throws Exception {
        Object expected = new Object();
        Object actual = Asserts.given(expected).assertNotNull().thenGet(object -> new Object());
        assertThat(actual, not(equalTo(expected)));
    }

    @Test(expected = ObjectAssertionException.class)
    public void test_given_then_thenGet_with_NullFunction () throws Exception {
        Asserts.given(new Object()).assertNotNull().thenGet(null);
    }

    // thenGet ---------------------------------------------------------------------------------------------------------
    @Test
    public void test_given_then_thenDo_with_NonNullConsumer () throws Exception {
        Object expected = new Object();
        Nullable<Object> actual = new Nullable<>();
        Asserts.given(actual).assertNotNull().thenDo(object -> object.set(expected));
        assertThat(actual.orElseThrow(AssertionError::new), is(expected));
    }

    @Test(expected = ObjectAssertionException.class)
    public void test_given_then_thenDo_with_NullConsumer () throws Exception {
        Asserts.given(new Object()).assertNotNull().thenDo(null);
    }
}