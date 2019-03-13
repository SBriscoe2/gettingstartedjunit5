package patientintake;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClinicCalendarTest {

    @Test

    public void allowEntryOfAnAppointment() {
        ClinicCalendar calendar = new ClinicCalendar();
        calendar.addAppointment("Jane", "Smith", "johnson", "12/25/2019 1:00 pm");
        List<PatientAppointment> appointments = calendar.getAppointments();
        assertNotNull(appointments);
        assertEquals(1, appointments.size());
        PatientAppointment patient = appointments.get(0);
        assertEquals("Jane", patient.getPatientFirstName());
        assertEquals("Smith", patient.getPatientLastName());
        assertEquals(Doctor.johnson, patient.getDoctor());
        assertEquals("12/25/2019 01:00 PM", patient.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")));
    }

}