package patientintake;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarTest {

    @Test

    public void allowEntryOfAnAppointment() {
        ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
        calendar.addAppointment("Jane", "Smith", "johnson", "12/25/2019 1:00 pm");
        List<PatientAppointment> appointments = calendar.getAppointments();
        assertNotNull(appointments);
        assertEquals(1, appointments.size());
        PatientAppointment patient = appointments.get(0);
        assertEquals("12/25/2019 01:00 PM", patient.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")));
    }

    @Test
    public void checkPatientDetails() {
        ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
        calendar.addAppointment("Bob", "Green", "avery", "01/01/2020 11:00 am");
        List<PatientAppointment> bobAppointments = calendar.getAppointments();
        PatientAppointment patient1 = bobAppointments.get(0);
        assertEquals("Bob", patient1.getPatientFirstName());
        assertEquals("Green", patient1.getPatientLastName());
        // verify two variables are same in memory
        assertSame(Doctor.avery, patient1.getDoctor());

    }

    @Test
    void returnTrueForHasAppointmentsIfThereAreAppointments() {
        ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
        calendar.addAppointment("Jane", "Smith", "johnson", "12/25/2019 1:00 pm");
        assertTrue(calendar.hasAppointment(LocalDate.of(2019, 12,25)));
    }


    @Test
    void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
        ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
        assertFalse(calendar.hasAppointment(LocalDate.of(2019, 12,25)));

    }

    @Test
    void returnCurrentDayAppointments() {
        ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
        calendar.addAppointment("Jane", "Smith", "johnson", "12/25/2019 1:00 pm");
        calendar.addAppointment("Jane", "Smith", "johnson", "03/18/2019 1:00 pm");
        calendar.addAppointment("Jane", "Smith", "johnson", "03/18/2019 2:00 pm");
        assertEquals(2, calendar.getTodayAppointments().size());
        // assertIterableEquals(calendar.getTodayAppointments(), calendar.getAppointments());

    }



}