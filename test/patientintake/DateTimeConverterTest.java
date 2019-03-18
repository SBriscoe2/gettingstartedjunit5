package patientintake;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeConverterTest {

    @Test
    void convertsTodayStringCorrectly() {
        System.out.println("Converts Today String");
        LocalDateTime result = DateTimeConverter.convertsStringToDateTime("today 1:00 pm",
                LocalDate.of(2019, 03, 18));
        assertEquals(result, LocalDateTime.of(2019, 03, 18, 13, 0));
    }

    @Test
    void convertsCorrectPatternToDateTime() {
        System.out.println("Converts pattern to Date time");
        LocalDateTime result = DateTimeConverter.convertsStringToDateTime("03/18/2019 1:00 pm",
                LocalDate.of(2019, 03, 18));
        assertEquals(result, LocalDateTime.of(2019, 03, 18, 13, 0));
    }

    @Test
    void throwExceptionIfIncorrectPatternProvided() {
        System.out.println("Throws exception if incorrect pattern provided");
        assertThrows(RuntimeException.class, () ->
                DateTimeConverter.convertsStringToDateTime("03/18/2019 100 pm",
                        LocalDate.of(2019, 03, 18)));
    }

    @Test
    void throwExceptionIfIncorrectPatternProvidedExplicitException() {
        // assert throws returns a value, the exception is caught, which can be assigned to a local variable
        // then add assertequals to verify the text message is what is expected
        System.out.println("Throws explicit exception if incorrect pattern provided");
        Throwable error = assertThrows(RuntimeException.class, () ->
                DateTimeConverter.convertsStringToDateTime("03/18/2019 100 pm",
                        LocalDate.of(2019, 03, 18)));
        assertEquals("Unable to create date time from: [03/18/2019 100 pm], " +
                "please enter with format [M/d/yyyy h:mm a]Text '03/18/2019 100 PM' " +
                "could not be parsed at index 14" , error.getMessage() );

    }

}