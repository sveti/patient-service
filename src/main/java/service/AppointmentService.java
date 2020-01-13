package service;

import entity.Appointment;
import entity.Doctor;
import entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AppointmentService {


    @Autowired
    private WebClient.Builder webClientBuilder;

    public Appointment getAppointmentByID(Long id){


        Appointment appointment = webClientBuilder.build().get().uri("http://db-producer/api/appointment/" + id).retrieve().bodyToMono(Appointment.class).block();

        Patient patient = webClientBuilder.build().get().uri("http://db-producer/api/patient/findByAppointment/"+id).retrieve().bodyToMono(Patient.class).block();
        Doctor doctor = webClientBuilder.build().get().uri("http://db-producer/api/doctor/findByAppointment/" +id).retrieve().bodyToMono(Doctor.class).block();

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        return appointment;

    }

    public void addAppointment(Appointment appointment){

        final String uri = "http://localhost:8082/api/appointment/add";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation(uri,appointment);

    }

    public List<Appointment> getAppointments(String username){

        Appointment[] appointments = webClientBuilder.build().get().uri("http://db-producer/api/appointment/appointments/patient/"+ username).retrieve().bodyToMono(Appointment[].class).block();
        List<Appointment> appointmentList= Arrays.asList(appointments);

        for(Appointment appointment : appointmentList){


            Patient patient = webClientBuilder.build().get().uri("http://db-producer/api/patient/findByAppointment/"+appointment.getId()).retrieve().bodyToMono(Patient.class).block();
            Doctor doctor = webClientBuilder.build().get().uri("http://db-producer/api/doctor/findByAppointment/" +appointment.getId()).retrieve().bodyToMono(Doctor.class).block();

            appointment.setDoctor(doctor);
            appointment.setPatient(patient);


        }



        return  appointmentList;
    }

    public List<Appointment> getUnfinishedAppointments(String username){

        List<Appointment> appointmentList = getAppointments(username);
        List<Appointment> unfinished= new ArrayList<>();
        for (Appointment app: appointmentList ) {

            if(app.getDiagnosis().equals("NOTSET")){
                unfinished.add(app);
            }

        }
        return  unfinished;
    }

    public List<Appointment> getPastAppointments(String username){

        List<Appointment> appointmentList = getAppointments(username);
        List<Appointment> finished= new ArrayList<>();

        for (Appointment app: appointmentList ) {

            if(!(app.getDiagnosis().equals("NOTSET"))){
                finished.add(app);
            }

        }

        return  finished;

    }

}
