package entity;

import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable {


    private Long id;
    private Date dateOfAppointment;
    private String diagnosis;
    private String medication;
    private Date sickLeaveStartDate;
    private int sickLeaveDays;
    private Doctor doctor;
    private Patient patient;

    public Appointment(){}

    public Appointment(Long id, Date dateOfAppointment, String diagnosis, String medication, Date sickLeaveStartDate, int sickLeaveDays, Doctor doctor, Patient patient) {
        this.id = id;
        this.dateOfAppointment = dateOfAppointment;
        this.diagnosis = diagnosis;
        this.medication = medication;
        this.sickLeaveStartDate = sickLeaveStartDate;
        this.sickLeaveDays = sickLeaveDays;
        this.doctor = doctor;
        this.patient = patient;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(Date dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public Date getSickLeaveStartDate() {
        return sickLeaveStartDate;
    }

    public void setSickLeaveStartDate(Date sickLeaveStartDate) {
        this.sickLeaveStartDate = sickLeaveStartDate;
    }

    public int getSickLeaveDays() {
        return sickLeaveDays;
    }

    public void setSickLeaveDays(int sickLeaveDays) {
        this.sickLeaveDays = sickLeaveDays;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", dateOfAppointment=" + dateOfAppointment +
                ", diagnosis='" + diagnosis + '\'' +
                ", medication='" + medication + '\'' +
                ", sickLeaveStartDate=" + sickLeaveStartDate +
                ", sickLeaveDays=" + sickLeaveDays +
                ", doctor=" + doctor +
                ", patient=" + patient +
                '}';
    }
}
