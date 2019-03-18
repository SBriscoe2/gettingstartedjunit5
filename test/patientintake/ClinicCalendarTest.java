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
        assertEquals("12/25/2019 01:00 PM", patient.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")));
    }

    @Test
    public void checkPatientDetails() {
        ClinicCalendar calendar = new ClinicCalendar();
        calendar.addAppointment("Bob", "Green", "avery", "01/01/2020 11:00 am");
        List<PatientAppointment> bobAppointments = calendar.getAppointments();
        PatientAppointment patient1 = bobAppointments.get(0);
        assertEquals("Bob", patient1.getPatientFirstName());
        assertEquals("Green", patient1.getPatientLastName());
        assertEquals(Doctor.avery, patient1.getDoctor());

    }

    

}