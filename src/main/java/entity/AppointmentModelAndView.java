package entity;

import java.util.Date;

public class AppointmentModelAndView {

    String patientUsername;
    String doctorUsername;

    public AppointmentModelAndView(String patientUsername, String doctorUsername) {
        this.patientUsername = patientUsername;
        this.doctorUsername = doctorUsername;
    }

    public AppointmentModelAndView() {
    }

    public String getPatientUsername() {
        return patientUsername;
    }

    public void setPatientUsername(String patientUsername) {
        this.patientUsername = patientUsername;
    }

    public String getDoctorUsername() {
        return doctorUsername;
    }

    public void setDoctorUsername(String doctorUsername) {
        this.doctorUsername = doctorUsername;
    }

    @Override
    public String toString() {
        return "AppointmentModelAndView{" +
                "patientUsername='" + patientUsername + '\'' +
                ", doctorUsername='" + doctorUsername + '\'' +
                '}';
    }
}
