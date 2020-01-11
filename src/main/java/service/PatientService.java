package service;

import entity.Doctor;
import entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Doctor getDoctor(String username){
        Doctor doctor = webClientBuilder.build().get().uri("http://db-producer/api/doctor/username/" + username).retrieve().bodyToMono(Doctor.class).block();
        return doctor;
    }

    public List<Doctor> getDoctors(){
        Doctor[] doctorsArray;
        doctorsArray = webClientBuilder.build().get().uri("http://db-producer/api/doctor/doctors").retrieve().bodyToMono(Doctor[].class).block();
        List<Doctor> doctorsList= Arrays.asList(doctorsArray);
        return doctorsList;
    }

    public Patient getPatient(String username){

        Patient patient = webClientBuilder.build().get().uri("http://db-producer/api/patient/username/" + username).retrieve().bodyToMono(Patient.class).block();
        Doctor doctor =  webClientBuilder.build().get().uri("http://db-producer/api/patient/" + username+"/doctor").retrieve().bodyToMono(Doctor.class).block();
        patient.setDoctorGp(doctor);
        return patient;
    }


    public void updatePatient(@RequestBody Patient patient){

        final String uri = "http://localhost:8082/api/patient/update";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(uri,patient);


    }

    public static boolean isWithinMonths(Date endDate) {

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(new Date());

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);


        endCalendar.add(Calendar.MONTH, 6);

        return endCalendar.before(startCalendar);
    }

    public void deletePatient(Long id){
        final String uri = "http://localhost:8082/api/patient/delete/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(uri,id);

    }

}
