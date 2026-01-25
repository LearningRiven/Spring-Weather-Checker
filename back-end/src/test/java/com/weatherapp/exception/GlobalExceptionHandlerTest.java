package com.weatherapp.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Nested
    @DisplayName("handleRestClientException")
    class HandleRestClientException {

        @Test
        @DisplayName("returns 404 with location not found message")
        void handle404_returnsLocationNotFound() {
            // TODO
        }

        @Test
        @DisplayName("returns 401 with auth error message")
        void handle401_returnsAuthError() {
            // TODO
        }

        @Test
        @DisplayName("returns 429 with rate limit message")
        void handle429_returnsRateLimitError() {
            // TODO
        }

        @Test
        @DisplayName("returns generic message for other status codes")
        void handleOther_returnsGenericMessage() {
            // TODO
        }
    }

    @Nested
    @DisplayName("handleValidationException")
    class HandleValidationException {

        @Test
        @DisplayName("returns 400 with single validation message")
        void singleViolation_returnsSingleMessage() {
            // TODO
        }

        @Test
        @DisplayName("returns 400 with concatenated messages for multiple violations")
        void multipleViolations_returnsConcatenatedMessages() {
            // TODO
        }
    }

    @Nested
    @DisplayName("handleMissingParam")
    class HandleMissingParam {

        @Test
        @DisplayName("returns 400 with missing parameter name")
        void returnsMissingParamMessage() {
            // TODO
        }
    }

    @Nested
    @DisplayName("handleGenericException")
    class HandleGenericException {

        @Test
        @DisplayName("returns 500 with generic error message")
        void returns500WithGenericMessage() {
            // TODO
        }
    }
}