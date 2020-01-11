package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import service.PatientService;

@RestController

public class PatientController {

    @Autowired
    PatientService patientService;

    @RequestMapping("/{username}")
    public ModelAndView index(@PathVariable("username") String username) {

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("patient", patientService.getPatient(username));
        return mav;

    }
}
