package solutions.thex.badgify.wrapper.badge.inline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import solutions.thex.badgify.svg.wrapper.badge.inline.InlineIconAsResponseWrapper;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class InlineIconAsResponseWrapperTests {

    private InlineIconAsResponseWrapper inlineIconAsResponseWrapper;

    @BeforeEach
    void setup(@Autowired InlineIconAsResponseWrapper inlineIconAsResponseWrapper) {
        this.inlineIconAsResponseWrapper = inlineIconAsResponseWrapper;
    }

    @Test
    void wrapMustReturnUnprocessableEntityHttpStatusAsResponseEntityWhenAnyOfParamsNotProvided() throws IOException {
        // Given
        // missing icon param for example
        String design = "s_simple_black_white_#";

        // Act & Assert
        assertThrows(solutions.thex.badgify.exception.NotSatisfiedParametersException.class,//
                () -> inlineIconAsResponseWrapper.wrap(design));
    }

    @Test
    void wrapMustReturnOkHttpStatusAsResponseEntityWhenAllParamsProvidedWithWhiteSpaceSplitter() throws IOException {
        // Given
        String design = "s simple github black white #";

        // When
        var response = inlineIconAsResponseWrapper.wrap(design);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void wrapMustReturnOkHttpStatusAsResponseEntityWhenAllParamsProvidedWithUnderLineSplitter() throws IOException {
        // Given
        String design = "s_simple_github_black_white_#";

        // When
        var response = inlineIconAsResponseWrapper.wrap(design);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void wrapMustReturnOkHttpStatusAsResponseEntityWhenAllParamsProvidedWithHyphenSplitter() throws IOException {
        // Given
        String design = "s-simple-github-black-white-#";

        // When
        var response = inlineIconAsResponseWrapper.wrap(design);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shortWrapMustReturnUnprocessableEntityHttpStatusAsResponseEntityWhenAnyOfRequiredParamsNotProvided() throws IOException {
        // Given
        // missing icon param for example
        String design = "black_white";

        // Act & Assert
        assertThrows(solutions.thex.badgify.exception.NotSatisfiedParametersException.class,//
                () -> inlineIconAsResponseWrapper.wrap(design));
    }

    @Test
    void shortWrapMustReturnOkHttpStatusAsResponseEntityWhenAllParamsProvidedWithWhiteSpaceSplitter() throws IOException {
        // Given
        String design = "github black s";

        // When
        var response = inlineIconAsResponseWrapper.wrapShort(design);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shortWrapMustReturnOkHttpStatusAsResponseEntityWhenAllParamsProvidedWithUnderLineSplitter() throws IOException {
        // Given
        String design = "github_black_s";

        // When
        var response = inlineIconAsResponseWrapper.wrapShort(design);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shortWrapMustReturnOkHttpStatusAsResponseEntityWhenAllParamsProvidedWithHyphenSplitter() throws IOException {
        // Given
        String design = "github-black-s";

        // When
        var response = inlineIconAsResponseWrapper.wrapShort(design);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
