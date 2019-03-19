package patientintake;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarTest {

    private ClinicCalendar calendar;


    @BeforeAll
    static void initall() {
        System.out.println("Before All");
    }

    @BeforeEach
    void init() {
        System.out.println("Before each");
        calendar = new ClinicCalendar(LocalDate.of(2019, 03, 18));
    }

    @Test

    void allowEntryOfAnAppointment() {
        System.out.println("entry of appointment");
        calendar.addAppointment("Jane", "Smith", "johnson", "12/25/2019 1:00 pm");
        List<PatientAppointment> appointments = calendar.getAppointments();
        assertNotNull(appointments);
        assertEquals(1, appointments.size());
        PatientAppointment patient = appointments.get(0);
        assertEquals("12/25/2019 01:00 PM", patient.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")));
    }

    @Test
    void checkPatientDetails() {
        System.out.println("patient details");
        calendar.addAppointment("Bob", "Green", "avery", "01/01/2020 11:00 am");
        List<PatientAppointment> bobAppointments = calendar.getAppointments();
        PatientAppointment patient1 = bobAppointments.get(0);

        // junit when first test fails all other asserts are ignored
        // add assert all and change assert statementd to lamdas
        assertAll(
                () -> assertEquals("Bob", patient1.getPatientFirstName()),
                () -> assertEquals("Green", patient1.getPatientLastName()),
                // verify two variables are same in memory
                () -> assertSame(Doctor.avery, patient1.getDoctor())
        );
    }

    @Test
    void returnTrueForHasAppointmentsIfThereAreAppointments() {
        System.out.println("true if appointments");
        calendar.addAppointment("Jane", "Smith", "johnson", "12/25/2019 1:00 pm");
        assertTrue(calendar.hasAppointment(LocalDate.of(2019, 12,25)));
    }


    @Test
    void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
        System.out.println("false if no appointments");
        assertFalse(calendar.hasAppointment(LocalDate.of(2019, 12,25)));
    }

    @Nested
    @DisplayName("return appointments correctly")
    class AppointmentsForDay {

        @Test
        @DisplayName("appointments for today")
            // @Disabled used to skip tests but still shown in output
            // Good when tests are failing and you intend to fix.
        void returnCurrentDayAppointments() {
            calendar.addAppointment("Jane", "Smith", "johnson", "12/25/2019 1:00 pm");
            calendar.addAppointment("Jane", "Smith", "johnson", "03/18/2019 1:00 pm");
            calendar.addAppointment("Jane", "Smith", "johnson", "03/18/2019 2:00 pm");
            assertEquals(2, calendar.getTodayAppointments().size());
            // assertIterableEquals(calendar.getTodayAppointments(), calendar.getAppointments());
        }

        @Test
        @DisplayName("appointments for tomorrow")
            // @Disabled used to skip tests but still shown in output
            // Good when tests are failing and you intend to fix.
        void returnTomorrowsAppointments() {
            calendar.addAppointment("Jane", "Smith", "johnson", "12/26/2019 1:00 pm");
            calendar.addAppointment("Jane", "Smith", "johnson", "03/19/2019 1:00 pm");
            calendar.addAppointment("Jane", "Smith", "johnson", "03/19/2019 2:00 pm");
            assertEquals(2, calendar.getTomorrowAppointments().size());
            // assertIterableEquals(calendar.getTodayAppointments(), calendar.getAppointments());
        }

    }

    @Nested
    @DisplayName("return upcoming appointments")
    class UpcomingAppointments {

        @Test
        @DisplayName("no appointments schedule in future")
        void whenThereAreNone() {
            List<PatientAppointment> appointments = calendar.getUpcomingAppointments();
            assertEquals(0, appointments.size());
        }

        @Test
        @DisplayName("correctly when some appointments are in the past")
        void whenThereAreSome() {
            calendar.addAppointment("Jane", "Smith", "johnson", "12/26/2017 1:00 pm");
            calendar.addAppointment("Jane", "Smith", "johnson", "03/19/2018 1:00 pm");
            calendar.addAppointment("Jane", "Smith", "johnson", "03/19/2020 2:00 pm");
            assertEquals(1, calendar.getUpcomingAppointments().size());
        }
    }

    @AfterEach
    void tearDownEachTest() {
        System.out.println("After Each ...");
    }

    @AfterAll
    static void testDownAfterClass() {
        System.out.println("After All ...");
    }



}