package service;

import entity.Doctor;
import entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PatientService {

    @Autowired
    private WebClient.Builder webClientBuilder;

//    @Autowired
//    private RestTemplate restTemplate;


    public Patient getPatient(String username){


        //Patient patient= restTemplate.getForObject("http://db-producer/api/patient/username/"+ username,Patient.class);
        Patient patient = webClientBuilder.build().get().uri("http://db-producer/api/patient/username/" + username).retrieve().bodyToMono(Patient.class).block();
        return patient;
    }


}
