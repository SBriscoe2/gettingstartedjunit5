package patientintake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Use @Tag in front of @test annotation or Class name to run groups of tests
// Go to edit config, + junit, select tag  from test kind add name of tag to be selected for testing

//  @Tag("dateTime")
@DisplayName("DateTimeConverter should")
class DateTimeConverterTest {

    @Nested
    // used to group tests together. original display names commented out
    @DisplayName("Converts string with 'today' keyword")
    class TodayTests {

        @Test
        @DisplayName("correctly")
                //@DisplayName(Converts strig with today keyword correctly)
            // using Lamda to produce error msg if failure only reduces processing time of msg
        void convertsTodayStringCorrectly() {
            LocalDate today = LocalDate.of(2019, 03, 18);
            LocalDateTime result = DateTimeConverter.convertsStringToDateTime("today 1:00 pm",
                    LocalDate.of(2019, 03, 18));
            assertEquals(result, LocalDateTime.of(2019, 03, 18, 13, 0),
                    () -> "Failed to convert 'today' string to expected time, today passed was: " + today);
        }

        @Test
        @DisplayName("correctly regardless of case")
                //@@DisplayName("Converts string with 'today' keyword correctly regardless of case")
            // constantly process error msg when test case run increases processing time of msg
        void convertsTodayStringCorrectlyCaseInsensitive() {
            LocalDate today = LocalDate.of(2019, 03, 18);
            LocalDateTime result = DateTimeConverter.convertsStringToDateTime("ToDay 1:00 pm",
                    LocalDate.of(2019, 03, 18));
            assertEquals(result, LocalDateTime.of(2019, 03, 18, 13, 0),
                    "Failed to convert 'today' string to expected time, today passed was: " + today);
        }
    }

    @Test
    @DisplayName("Converts pattern to Date time correctly")
    void convertsCorrectPatternToDateTime() {
        LocalDateTime result = DateTimeConverter.convertsStringToDateTime("03/18/2019 1:00 pm",
                LocalDate.of(2019, 03, 18));
        assertEquals(result, LocalDateTime.of(2019, 03, 18, 13, 0));
    }

    @Test
    @DisplayName("Throws exception if incorrect pattern provided")
    void throwExceptionIfIncorrectPatternProvided() {
        assertThrows(RuntimeException.class, () ->
                DateTimeConverter.convertsStringToDateTime("03/18/2019 100 pm",
                        LocalDate.of(2019, 03, 18)));
    }

    @Test
    @DisplayName("Throws explicit exception if incorrect pattern provided")
    void throwExceptionIfIncorrectPatternProvidedExplicitException() {
        // assert throws returns a value, the exception is caught, which can be assigned to a local variable
        // then add assertequals to verify the text message is what is expected
        Throwable error = assertThrows(RuntimeException.class, () ->
                DateTimeConverter.convertsStringToDateTime("03/18/2019 100 pm",
                        LocalDate.of(2019, 03, 18)));
        assertEquals("Unable to create date time from: [03/18/2019 100 pm], " +
                "please enter with format [M/d/yyyy h:mm a]Text '03/18/2019 100 PM' " +
                "could not be parsed at index 14" , error.getMessage() );

    }

}