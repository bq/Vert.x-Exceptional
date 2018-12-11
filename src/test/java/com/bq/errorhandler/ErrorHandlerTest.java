package com.bq.errorhandler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.bq.errorhandler.dtos.ErrorBag;
import com.bq.errorhandler.dtos.ErrorDto;
import com.bq.errorhandler.errors.CustomError;
import com.bq.errorhandler.errors.InvalidInboundEntityException;
import com.bq.errorhandler.errors.NotFoundError;
import com.bq.errorhandler.errors.UnexpectedErrorException;
import com.bq.errorhandler.reporters.ErrorReporter;
import com.bq.errorhandler.reporters.ReporterFactory;
import com.bq.errorhandler.reporters.ReporterType;
import io.vertx.core.json.Json;
import io.vertx.reactivex.core.http.HttpServerResponse;
import io.vertx.reactivex.ext.web.RoutingContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@RunWith(MockitoJUnitRunner.class)
public class ErrorHandlerTest {

    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Mock
    private RoutingContext routingContext;

    @Mock
    private HttpServerResponse response;

    @Before
    public void setUp() {
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void tearDown() {
        System.setErr(System.err);
    }

    @Test
    public void testItDoesntReportNotFoundError() {
        CustomError error = new NotFoundError("");

        ErrorReporter errorReporter = ReporterFactory.INSTANCE.newReporter(ReporterType.STDOUT);
        ErrorHandler errorHandler = new ErrorHandler(errorReporter);
        errorHandler.reportError(error);

        assertTrue(errContent.toString().isEmpty());
    }

    @Test
    public void testItReportsUnexpectedErrorException() {
        CustomError error = new UnexpectedErrorException("");
        String actualLog = String.format("[ERROR] {\"message\": \"%s\",\"tags\": [%s],\"error\":\"%s\"}\n", "", "", error);

        ErrorReporter errorReporter = ReporterFactory.INSTANCE.newReporter(ReporterType.STDOUT);
        ErrorHandler errorHandler = new ErrorHandler(errorReporter);
        errorHandler.reportError(error);

        assertEquals(actualLog, errContent.toString());
    }

    @Test
    public void testItReportsJavaException() {
        RuntimeException error = new RuntimeException("");
        String actualLog = String.format("[ERROR] {\"message\": \"%s\",\"tags\": [%s],\"error\":\"%s\"}\n", "", "", error);

        ErrorReporter errorReporter = ReporterFactory.INSTANCE.newReporter(ReporterType.STDOUT);
        ErrorHandler errorHandler = new ErrorHandler(errorReporter);
        errorHandler.reportError(error);

        assertEquals(actualLog, errContent.toString());
    }

    @Test
    public void testItRendersJavaExceptionAsUnexpectedErrorException() {
        Exception error = new Exception();

        when(routingContext.response()).thenReturn(response);
        when(response.setStatusCode(anyInt())).thenReturn(response);
        when(response.putHeader(anyString(), anyString())).thenReturn(response);

        ErrorHandler errorHandler = Mockito.mock(ErrorHandler.class);
        Mockito.doCallRealMethod().when(errorHandler).renderError(routingContext, error);

        errorHandler.renderError(routingContext, error);

        ErrorDto expectedError = new ErrorDto(
                UnexpectedErrorException.STATUS_CODE,
                UnexpectedErrorException.ERROR_CODE,
                UnexpectedErrorException.ERROR_MSG,
                new ErrorBag());

        verify(response).end(Json.encode(expectedError));
    }

    @Test
    public void testItRendersCustomExceptionAsItIs() {
        InvalidInboundEntityException error = new InvalidInboundEntityException("");

        when(routingContext.response()).thenReturn(response);
        when(response.setStatusCode(anyInt())).thenReturn(response);
        when(response.putHeader(anyString(), anyString())).thenReturn(response);

        ErrorHandler errorHandler = Mockito.mock(ErrorHandler.class);
        Mockito.doCallRealMethod().when(errorHandler).renderError(routingContext, error);

        errorHandler.renderError(routingContext, error);

        ErrorDto expectedError = new ErrorDto(
                InvalidInboundEntityException.STATUS_CODE,
                InvalidInboundEntityException.ERROR_CODE,
                InvalidInboundEntityException.ERROR_MSG,
                null);

        verify(response).end(Json.encode(expectedError));
    }

}
