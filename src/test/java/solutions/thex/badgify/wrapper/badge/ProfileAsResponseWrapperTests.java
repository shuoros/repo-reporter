package solutions.thex.badgify.wrapper.badge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import solutions.thex.badgify.svg.wrapper.badge.ProfileAsResponseWrapper;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class ProfileAsResponseWrapperTests {

    private ProfileAsResponseWrapper profileAsResponseWrapper;

    @BeforeEach
    void setup(@Autowired ProfileAsResponseWrapper profileAsResponseWrapper) {
        this.profileAsResponseWrapper = profileAsResponseWrapper;
    }

    @Test
    void wrapMustReturnUnprocessableEntityHttpStatusAsResponseEntityWhenIdNotProvided() throws IOException {
        // Given
        Map<String, String> params = Map.of(//
                "id", "-1",//
                "theme", "simple",//
                "bg", "000",//
                "color", "rgb(255, 255, 255)");

        // Act & Assert
        assertThrows(solutions.thex.badgify.exception.NotSatisfiedParametersException.class,//
                () -> profileAsResponseWrapper.wrap(params));
    }

    @Test
    void wrapMustReturnOKHttpStatusAsResponseEntityWhenIdProvided() throws IOException {
        // Given
        Map<String, String> params = Map.of(//
                "id", "TheXSolutions",//
                "theme", "simple",//
                "bg", "000",//
                "color", "rgb(255, 255, 255)");

        // When
        var response = profileAsResponseWrapper.wrap(params);

        // Then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}
