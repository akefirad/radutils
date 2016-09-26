package com.akefirad.radutils.exception;

import com.akefirad.radutils.util.Nullable;
import com.akefirad.radutils.util.SomeException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static com.akefirad.radutils.Threads.startNewThread;
import static com.akefirad.radutils.exception.Exceptions.*;
import static java.lang.Thread.currentThread;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class ExceptionsTest {
    private final String exceptionMessage = "Some exception message";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    // callAndCatch --------------------------------------------------------------------------------------------------
    @Test
    public void test_callAndCatch_with_NoException_then_call_should_returns_Value () {
        Object expected = new Object();
        Object actual = callAndCatch(() -> expected)
                .call();
        assertThat(actual, is(expected));
    }

    @Test
    public void test_callAndCatch_with_Exception_then_call_should_throws_UncaughtException () {
        expectedException.expect(UncaughtException.class);
        expectedException.expectMessage(exceptionMessage);

        callAndCatch(() -> throwAnException(exceptionMessage))
                .call();
    }

    @Test
    public void test_callAndCatch_with_NoException_then_andHandleIfAny_with_Handler_then_call_should_returns_Value () {
        Object expected = new Object();
        Object actual = callAndCatch(() -> expected)
                .andHandleIfAny(newCallableExceptionHandlerThrowException())
                .call();
        assertThat(actual, is(expected));
    }

    @Test
    public void test_callAndCatch_with_Exception_then_andHandleIfAny_with_Handler_then_call_should_throws_RuntimeException () {
        expectedException.expect(SomeException.class);
        expectedException.expectMessage(exceptionMessage);

        callAndCatch(() -> throwAnException(exceptionMessage))
                .andHandleIfAny(newCallableExceptionHandlerThrowException())
                .call();
    }

    @Test
    public void test_callAndCatch_with_Exception_then_andHandleIfAny_with_Handler_and_Class_then_call_should_throws_RuntimeException () {
        expectedException.expect(SomeException.class);
        expectedException.expectMessage(exceptionMessage);

        callAndCatch(() -> throwAnException(exceptionMessage))
                .andHandleIfAny(newCallableExceptionHandlerThrowException(), Exception.class)
                .call();
    }

    @Test
    public void test_callAndCatch_with_Exception_then_andHandleIfAny_with_Handler_and_Class_then_call_should_throws_UncaughtException () {
        expectedException.expect(UncaughtException.class);
        expectedException.expectMessage(exceptionMessage);

        callAndCatch(() -> throwAnException(exceptionMessage))
                .andHandleIfAny(newCallableExceptionHandlerThrowException(), IOException.class)
                .call();
    }

    @Test
    public void test_callAndCatch_with_NoException_then_andHandleIfAny_with_Handler_and_Classes_then_call_should_returns_Value () {
        Object expected = new Object();
        Object actual = callAndCatch(() -> expected)
                .andHandleIfAny(newCallableExceptionHandlerThrowException(), IllegalArgumentException.class)
                .andHandleIfAny(newCallableExceptionHandlerThrowException(), RuntimeException.class)
                .call();
        assertThat(actual, is(expected));
    }

    @Test
    public void test_callAndCatch_with_Exception_then_andHandleIfAny_with_Handler_and_Classes_then_call_should_throws_RuntimeException () {
        expectedException.expect(SomeException.class);
        expectedException.expectMessage(exceptionMessage);

        callAndCatch(() -> throwAnException(exceptionMessage))
                .andHandleIfAny(newCallableExceptionHandlerThrowException(), RuntimeException.class)
                .andHandleIfAny(newCallableExceptionHandlerThrowException(), Exception.class)
                .call();
    }

    @Test
    public void test_callAndCatch_with_MultipleException_then_andHandleIfAny_with_Handler_and_Classes_then_call_should_throws_RuntimeException () {
        expectedException.expect(SomeException.class);
        expectedException.expectMessage(exceptionMessage);

        callAndCatch(() -> throwSomeException(exceptionMessage))
                .andHandleIfAny(newCallableExceptionHandlerThrowException(), IOException.class)
                .andHandleIfAny(newCallableExceptionHandlerThrowException(), CloneNotSupportedException.class)
                .call();
    }

    @Test
    public void test_callAndCatch_with_MultipleException_then_andHandleIfAny_with_Handler_and_Classes_then_call_should_throws_UncaughtException () {
        expectedException.expect(UncaughtException.class);
        expectedException.expectMessage(exceptionMessage);

        callAndCatch(() -> throwSomeException(exceptionMessage))
                .andHandleIfAny(newCallableExceptionHandlerThrowException(), IllegalArgumentException.class)
                .andHandleIfAny(newCallableExceptionHandlerThrowException(), RuntimeException.class)
                .call();
    }

    @Test
    public void test_callAndCatch_with_NoException_then_andReturnIfAny_then_call_should_returns_Value () {
        Object expected = new Object();
        Object actual = callAndCatch(() -> expected)
                .andReturnIfAny(new Object())
                .call();
        assertThat(actual, is(expected));
    }

    @Test
    public void test_callAndCatch_with_Exception_then_andReturnIfAny_then_call_should_returns_DefaultValue () {
        Object expected = new Object();
        Object actual = callAndCatch(() -> throwAnException(exceptionMessage))
                .andReturnIfAny(expected)
                .call();
        assertThat(actual, is(expected));
    }

    @Test
    public void test_callAndCatch_with_NoException_then_andThrowIfAny_with_Supplier_then_call_should_returns_Value () {
        Object expected = new Object();
        Object actual = callAndCatch(() -> expected)
                .andThrowIfAny(SomeException::new)
                .call();
        assertThat(actual, is(expected));
    }

    @Test
    public void test_callAndCatch_with_Exception_then_andThrowIfAny_with_Supplier_then_call_should_throws_RuntimeException () {
        expectedException.expect(SomeException.class);
        expectedException.expectMessage(exceptionMessage);

        callAndCatch(() -> throwAnException(exceptionMessage))
                .andThrowIfAny(SomeException::new)
                .call();
    }

    @Test
    public void test_callAndCatch_with_NoException_then_andThrowIfAny_then_call_should_returns_Value () {
        Object expected = new Object();
        Object actual = callAndCatch(() -> expected)
                .andThrowIfAny()
                .call();
        assertThat(actual, is(expected));
    }

    @Test
    public void test_callAndCatch_with_Exception_then_andThrowIfAny_then_call_should_throws_CaughtException () {
        expectedException.expect(CaughtException.class);
        expectedException.expectMessage(exceptionMessage);

        callAndCatch(() -> throwAnException(exceptionMessage))
                .andThrowIfAny()
                .call();
    }

    // callAndCatch_andIfInterrupted -----------------------------------------------------------------------------------
    @Test
    public void test_callAndCatch_with_NoException_then_andHandleIfInterrupted_with_Handler_then_call_should_returns_Value () throws InterruptedException {
        Object expected = new Object();
        Nullable<Object> actual = new Nullable<>();
        Nullable<Exception> exception = new Nullable<>();
        Nullable<Boolean> interrupted = new Nullable<>();

        Thread thread = startNewThread(() -> {
            try {
                Object result = callAndCatch(() -> expected)
                        .andHandleIfInterrupted(newCallableExceptionHandlerThrowException())
                        .call();
                actual.set(result);
                interrupted.set(currentThread().isInterrupted());
            }
            catch (Exception e) {
                exception.set(e);
            }
        });

        thread.join();

        assertThat(exception.orElse(null), nullValue());
        assertThat(actual.orElseThrow(AssertionError::new), is(expected));
        assertFalse(interrupted.orElse(true));
    }

    @Test
    public void test_callAndCatch_with_Exception_then_andHandleIfInterrupted_with_Handler_then_call_should_throws_RuntimeException () throws Exception {
        expectedException.expect(SomeException.class);
        expectedException.expectMessage(exceptionMessage);

        Nullable<Exception> exception = new Nullable<>();
        Nullable<Boolean> interrupted = new Nullable<>();

        startNewThread(() -> {
            try {
                callAndCatch(() -> throwInterruptedException(exceptionMessage))
                        .andHandleIfInterrupted(newCallableExceptionHandlerThrowException())
                        .call();
            }
            catch (Exception e) {
                exception.set(e);
                interrupted.set(currentThread().isInterrupted());
            }
        }).join();

        assertTrue(interrupted.orElse(false));
        throw exception.orElseThrow(AssertionError::new);
    }

    @Test
    public void test_callAndCatch_with_NoException_then_andReturnIfInterrupted_with_Handler_then_call_should_returns_Value () throws InterruptedException {
        Object expected = new Object();
        Nullable<Object> actual = new Nullable<>();
        Nullable<Exception> exception = new Nullable<>();
        Nullable<Boolean> interrupted = new Nullable<>();

        startNewThread(() -> {
            try {
                Object result = callAndCatch(() -> expected)
                        .andReturnIfInterrupted(new Object())
                        .call();
                actual.set(result);
                interrupted.set(currentThread().isInterrupted());
            }
            catch (Exception e) {
                exception.set(e);
            }
        }).join();

        assertThat(exception.orElse(null), nullValue());
        assertThat(actual.orElseThrow(AssertionError::new), is(expected));
        assertFalse(interrupted.orElse(true));
    }

    @Test
    public void test_callAndCatch_with_Exception_then_andReturnIfInterrupted_with_Handler_then_call_should_throws_RuntimeException () throws InterruptedException {
        Object expected = new Object();
        Nullable<Object> actual = new Nullable<>();
        Nullable<Exception> exception = new Nullable<>();
        Nullable<Boolean> interrupted = new Nullable<>();

        startNewThread(() -> {
            try {
                Object result = callAndCatch(() -> throwInterruptedException(exceptionMessage))
                        .andReturnIfInterrupted(expected)
                        .call();
                actual.set(result);
                interrupted.set(currentThread().isInterrupted());
            }
            catch (Exception e) {
                exception.set(e);
            }
        }).join();

        assertThat(exception.orElse(null), nullValue());
        assertThat(actual.orElseThrow(AssertionError::new), is(expected));
        assertTrue(interrupted.orElse(false));
    }

    @Test
    public void test_callAndCatch_with_NoException_then_andThrowIfInterrupted_with_Handler_then_call_should_returns_Value () throws InterruptedException {
        Object expected = new Object();
        Nullable<Object> actual = new Nullable<>();
        Nullable<Exception> exception = new Nullable<>();
        Nullable<Boolean> interrupted = new Nullable<>();

        startNewThread(() -> {
            try {
                Object result = callAndCatch(() -> expected)
                        .andThrowIfInterrupted(SomeException::new)
                        .call();
                actual.set(result);
                interrupted.set(currentThread().isInterrupted());
            }
            catch (Exception e) {
                exception.set(e);
            }
        }).join();

        assertThat(exception.orElse(null), nullValue());
        assertThat(actual.orElseThrow(AssertionError::new), is(expected));
        assertFalse(interrupted.orElse(true));
    }

    @Test
    public void test_callAndCatch_with_Exception_then_andThrowIfInterrupted_with_Handler_then_call_should_throws_RuntimeException () throws Exception {
        expectedException.expect(SomeException.class);
        expectedException.expectMessage(exceptionMessage);

        Nullable<Exception> exception = new Nullable<>();
        Nullable<Boolean> interrupted = new Nullable<>();

        startNewThread(() -> {
            try {
                callAndCatch(() -> throwInterruptedException(exceptionMessage))
                        .andThrowIfInterrupted(SomeException::new)
                        .call();
            }
            catch (Exception e) {
                exception.set(e);
                interrupted.set(currentThread().isInterrupted());
            }
        }).join();

        assertTrue(interrupted.orElse(false));
        throw exception.orElseThrow(AssertionError::new);
    }

    // runAndCatch -----------------------------------------------------------------------------------------------------
    @Test
    public void test_runInTryCatch_with_NoException_then_call_should_returns_Value () {
        Object expected = new Object();
        Nullable<Object> actual = new Nullable<>();
        runAndCatch(() -> actual.set(expected))
                .run();
        assertThat(actual.orElseThrow(AssertionError::new), is(expected));
    }

    @Test
    public void test_runInTryCatch_with_Exception_then_call_should_throws_UncaughtException () {
        expectedException.expect(UncaughtException.class);
        expectedException.expectMessage(exceptionMessage);

        runAndCatch(() -> throwAnException(exceptionMessage))
                .run();
    }

    @Test
    public void test_runInTryCatch_with_NoException_then_andHandleIfAny_with_Handler_then_call_should_returns_Value () {
        Object expected = new Object();
        Nullable<Object> actual = new Nullable<>();
        runAndCatch(() -> actual.set(expected))
                .andHandleIfAny(newRunnableExceptionHandlerThrowException())
                .run();
        assertThat(actual.orElseThrow(AssertionError::new), is(expected));
    }

    @Test
    public void test_runInTryCatch_with_Exception_then_andHandleIfAny_with_Handler_then_call_should_returns_Value () {
        Object expected = new Object();
        Nullable<Object> actual = new Nullable<>();
        runAndCatch(() -> throwAnException(exceptionMessage))
                .andHandleIfAny(exception -> actual.set(expected))
                .run();
        assertThat(actual.orElseThrow(AssertionError::new), is(expected));
    }

    @Test
    public void test_runInTryCatch_with_Exception_then_andHandleIfAny_with_Handler_then_call_should_throws_RuntimeException () {
        expectedException.expect(SomeException.class);
        expectedException.expectMessage(exceptionMessage);

        runAndCatch(() -> throwAnException(exceptionMessage))
                .andHandleIfAny(newRunnableExceptionHandlerThrowException())
                .run();
    }

    @Test
    public void test_runInTryCatch_with_Exception_then_andHandleIfAny_with_Handler_and_Class_then_call_should_throws_RuntimeException () {
        expectedException.expect(SomeException.class);
        expectedException.expectMessage(exceptionMessage);

        runAndCatch(() -> throwAnException(exceptionMessage))
                .andHandleIfAny(newRunnableExceptionHandlerThrowException(), Exception.class)
                .run();
    }

    @Test
    public void test_runInTryCatch_with_Exception_then_andHandleIfAny_with_Handler_and_Class_then_call_should_throws_UncaughtException () {
        expectedException.expect(UncaughtException.class);
        expectedException.expectMessage(exceptionMessage);

        runAndCatch(() -> throwAnException(exceptionMessage))
                .andHandleIfAny(newRunnableExceptionHandlerThrowException(), IOException.class)
                .run();
    }

    @Test
    public void test_runInTryCatch_with_NoException_then_andHandleIfAny_with_Handler_and_Classes_then_call_should_returns_Value () {
        Object expected = new Object();
        Nullable<Object> actual = new Nullable<>();
        runAndCatch(() -> actual.set(expected))
                .andHandleIfAny(newRunnableExceptionHandlerThrowException(), IllegalArgumentException.class)
                .andHandleIfAny(newRunnableExceptionHandlerThrowException(), RuntimeException.class)
                .run();
        assertThat(actual.orElseThrow(AssertionError::new), is(expected));
    }

    @Test
    public void test_runInTryCatch_with_Exception_then_andHandleIfAny_with_Handler_and_Classes_then_call_should_throws_RuntimeException () {
        expectedException.expect(SomeException.class);
        expectedException.expectMessage(exceptionMessage);

        runAndCatch(() -> throwAnException(exceptionMessage))
                .andHandleIfAny(newRunnableExceptionHandlerThrowException(), RuntimeException.class)
                .andHandleIfAny(newRunnableExceptionHandlerThrowException(), Exception.class)
                .run();
    }

    @Test
    public void test_runInTryCatch_with_MultipleException_then_andHandleIfAny_with_Handler_and_Classes_then_call_should_throws_RuntimeException () {
        expectedException.expect(SomeException.class);
        expectedException.expectMessage(exceptionMessage);

        runAndCatch(() -> throwSomeException(exceptionMessage))
                .andHandleIfAny(newRunnableExceptionHandlerThrowException(), IOException.class)
                .andHandleIfAny(newRunnableExceptionHandlerThrowException(), CloneNotSupportedException.class)
                .run();
    }

    @Test
    public void test_runInTryCatch_with_MultipleException_then_andHandleIfAny_with_Handler_and_Classes_then_call_should_throws_UncaughtException () {
        expectedException.expect(UncaughtException.class);
        expectedException.expectMessage(exceptionMessage);

        runAndCatch(() -> throwSomeException(exceptionMessage))
                .andHandleIfAny(newRunnableExceptionHandlerThrowException(), IllegalArgumentException.class)
                .andHandleIfAny(newRunnableExceptionHandlerThrowException(), RuntimeException.class)
                .run();
    }

    @Test
    public void test_runAndCatch_with_NoException_then_andReturnIfAny_then_call_should_returns_Value () {
        Object expected = new Object();
        Nullable<Object> actual = new Nullable<>();
        runAndCatch(() -> actual.set(expected))
                .andReturnIfAny()
                .call();
        assertThat(actual.orElseThrow(AssertionError::new), is(expected));
    }

    @Test
    public void test_runAndCatch_with_Exception_then_andReturnIfAny_then_call_should_returns_DefaultValue () {
        runAndCatch(() -> throwAnException(exceptionMessage))
                .andReturnIfAny()
                .call();
    }

    @Test
    public void test_runInTryCatch_with_NoException_then_andThrowIfAny_with_Supplier_then_call_should_returns_Value () {
        Object expected = new Object();
        Nullable<Object> actual = new Nullable<>();
        runAndCatch(() -> actual.set(expected))
                .andThrowIfAny(SomeException::new)
                .run();
        assertThat(actual.orElseThrow(AssertionError::new), is(expected));
    }

    @Test
    public void test_runInTryCatch_with_Exception_then_andThrowIfAny_with_Supplier_then_call_should_throws_RuntimeException () {
        expectedException.expect(SomeException.class);
        expectedException.expectMessage(exceptionMessage);

        runAndCatch(() -> throwAnException(exceptionMessage))
                .andThrowIfAny(SomeException::new)
                .run();
    }

    @Test
    public void test_runInTryCatch_with_NoException_then_andThrowIfAny_then_call_should_returns_Value () {
        Object expected = new Object();
        Nullable<Object> actual = new Nullable<>();
        runAndCatch(() -> actual.set(expected))
                .andThrowIfAny()
                .run();
        assertThat(actual.orElseThrow(AssertionError::new), is(expected));
    }

    @Test
    public void test_runInTryCatch_with_Exception_then_andThrowIfAny_then_call_should_throws_CaughtException () {
        expectedException.expect(CaughtException.class);
        expectedException.expectMessage(exceptionMessage);

        runAndCatch(() -> throwAnException(exceptionMessage))
                .andThrowIfAny()
                .run();
    }

    // runAndCatch_andIfInterrupted ------------------------------------------------------------------------------------
    @Test
    public void test_runAndCatch_with_NoException_then_andHandleIfInterrupted_with_Handler_then_run_succeeds () throws InterruptedException {
        Object expected = new Object();
        Nullable<Object> actual = new Nullable<>();
        Nullable<Exception> exception = new Nullable<>();
        Nullable<Boolean> interrupted = new Nullable<>();

        startNewThread(() -> {
            try {
                runAndCatch(() -> actual.set(expected))
                        .andHandleIfInterrupted(newRunnableExceptionHandlerThrowException())
                        .run();
                interrupted.set(currentThread().isInterrupted());
            }
            catch (Exception e) {
                exception.set(e);
            }
        }).join();

        assertThat(exception.orElse(null), nullValue());
        assertThat(actual.orElseThrow(AssertionError::new), is(expected));
        assertFalse(interrupted.orElse(true));
    }

    @Test
    public void test_runAndCatch_with_Exception_then_andHandleIfInterrupted_with_Handler_then_run_should_succeeds () throws InterruptedException {
        Object expected = new Object();
        Nullable<Object> actual = new Nullable<>();
        Nullable<Exception> exception = new Nullable<>();
        Nullable<Boolean> interrupted = new Nullable<>();

        startNewThread(() -> {
            try {
                runAndCatch(() -> throwInterruptedException(exceptionMessage))
                        .andHandleIfInterrupted(e -> actual.set(expected))
                        .run();
                interrupted.set(currentThread().isInterrupted());
            }
            catch (Exception e) {
                exception.set(e);
            }
        }).join();

        assertThat(exception.orElse(null), nullValue());
        assertThat(actual.orElseThrow(AssertionError::new), is(expected));
        assertTrue(interrupted.orElse(false));
    }

    @Test
    public void test_runAndCatch_with_Exception_then_andHandleIfInterrupted_with_Handler_then_run_should_throws_RuntimeException () throws Exception {
        expectedException.expect(SomeException.class);
        expectedException.expectMessage(exceptionMessage);

        Nullable<Exception> exception = new Nullable<>();
        Nullable<Boolean> interrupted = new Nullable<>();

        startNewThread(() -> {
            try {
                runAndCatch(() -> throwInterruptedException(exceptionMessage))
                        .andHandleIfInterrupted(newRunnableExceptionHandlerThrowException())
                        .run();
            }
            catch (Exception e) {
                exception.set(e);
                interrupted.set(currentThread().isInterrupted());
            }
        }).join();

        assertTrue(interrupted.orElse(false));
        throw exception.orElseThrow(AssertionError::new);
    }

    @Test
    public void test_runAndCatch_with_NoException_then_andReturnIfInterrupted_with_Handler_then_run_should_succeeds () throws InterruptedException {
        Object expected = new Object();
        Nullable<Object> actual = new Nullable<>();
        Nullable<Exception> exception = new Nullable<>();
        Nullable<Boolean> interrupted = new Nullable<>();

        startNewThread(() -> {
            try {
                runAndCatch(() -> actual.set(expected))
                        .andReturnIfInterrupted()
                        .run();
                interrupted.set(currentThread().isInterrupted());
            }
            catch (Exception e) {
                exception.set(e);
            }
        }).join();

        assertThat(exception.orElse(null), nullValue());
        assertThat(actual.orElseThrow(AssertionError::new), is(expected));
        assertFalse(interrupted.orElse(true));
    }

    @Test
    public void test_runAndCatch_with_Exception_then_andReturnIfInterrupted_with_Handler_then_run_should_throws_RuntimeException () throws InterruptedException {
        Object expected = new Object();
        Nullable<Object> actual = new Nullable<>();
        Nullable<Exception> exception = new Nullable<>();
        Nullable<Boolean> interrupted = new Nullable<>();

        startNewThread(() -> {
            try {
                runAndCatch(() -> throwInterruptedException(exceptionMessage))
                        .andReturnIfInterrupted()
                        .run();
                actual.set(expected);
                interrupted.set(currentThread().isInterrupted());
            }
            catch (Exception e) {
                exception.set(e);
            }
        }).join();

        assertThat(exception.orElse(null), nullValue());
        assertThat(actual.orElseThrow(AssertionError::new), is(expected));
        assertTrue(interrupted.orElse(false));
    }

    @Test
    public void test_runAndCatch_with_NoException_then_andThrowIfInterrupted_with_Handler_then_run_should_succeeds () throws InterruptedException {
        Object expected = new Object();
        Nullable<Object> actual = new Nullable<>();
        Nullable<Exception> exception = new Nullable<>();
        Nullable<Boolean> interrupted = new Nullable<>();

        startNewThread(() -> {
            try {
                runAndCatch(() -> actual.set(expected))
                        .andThrowIfInterrupted(SomeException::new)
                        .run();
                interrupted.set(currentThread().isInterrupted());
            }
            catch (Exception e) {
                exception.set(e);
            }
        }).join();

        assertThat(exception.orElse(null), nullValue());
        assertThat(actual.orElseThrow(AssertionError::new), is(expected));
        assertFalse(interrupted.orElse(true));
    }

    @Test
    public void test_runAndCatch_with_Exception_then_andThrowIfInterrupted_with_Handler_then_run_should_throws_RuntimeException () throws Exception {
        expectedException.expect(SomeException.class);
        expectedException.expectMessage(exceptionMessage);

        Nullable<Exception> exception = new Nullable<>();
        Nullable<Boolean> interrupted = new Nullable<>();

        startNewThread(() -> {
            try {
                runAndCatch(() -> throwInterruptedException(exceptionMessage))
                        .andThrowIfInterrupted(SomeException::new)
                        .run();
            }
            catch (Exception e) {
                exception.set(e);
                interrupted.set(currentThread().isInterrupted());
            }
        }).join();

        assertTrue(interrupted.orElse(false));
        throw exception.orElseThrow(AssertionError::new);
    }

    // StackTrace as String and StringBuilder --------------------------------------------------------------------------
    @Test
    public void test_getStackTraceAsString_with_Null_should_returns_Null () {
        assertThat(getStackTraceAsString(null), nullValue());
    }

    @Test
    public void test_getStackTraceAsString_with_NonNull_should_returns_NonNull () {
        String actual, expected;

        try {
            throw new Exception();
        }
        catch (Exception exception) {
            actual = getStackTraceAsString(exception);
            StringWriter sw = new StringWriter();
            exception.printStackTrace(new PrintWriter(sw));
            expected = sw.toString();
        }
        assertThat(actual, is(expected));
    }

    @Test
    public void test_getStackTraceAsStringBuilder_with_Null_should_returns_Null () {
        assertThat(getStackTraceAsStringBuilder(null).toString(), nullValue());
    }

    @Test
    public void test_getStackTraceAsStringBuilder_with_NonNull_should_returns_NonNull () {
        String actual, expected;

        try {
            throw new Exception();
        }
        catch (Exception exception) {
            actual = getStackTraceAsStringBuilder(exception).toString();
            StringWriter sw = new StringWriter();
            exception.printStackTrace(new PrintWriter(sw));
            expected = sw.toString();
        }
        assertThat(actual, is(expected));
    }

    private Object throwAnException (String exceptionMessage) throws Exception {
        throw new Exception(exceptionMessage);
    }

    private Object throwSomeException (String exceptionMessage) throws IOException, CloneNotSupportedException {
        throw new IOException(exceptionMessage);
    }

    private Object throwInterruptedException (String exceptionMessage) throws InterruptedException {
        throw new InterruptedException(exceptionMessage);
    }

    private CallableExceptionHandler<Void> newCallableExceptionHandlerThrowException () {
        return exception -> {
            throw new SomeException(exception);
        };
    }

    private RunnableExceptionHandler newRunnableExceptionHandlerThrowException () {
        return exception -> {
            throw new SomeException(exception);
        };
    }
}