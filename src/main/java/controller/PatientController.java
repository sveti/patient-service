package controller;

import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import service.AppointmentService;
import service.PatientService;

import javax.jws.WebParam;
import java.util.Date;
import java.util.List;

@RestController

public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    AppointmentService appointmentService;

    @RequestMapping("/{username}")
    public ModelAndView index(@PathVariable("username") String username) {
        //if (patientService.getAuthToken(username)){
        Patient p = patientService.getPatient(username);

        if (p.getActive() == 1){
            ModelAndView mav = new ModelAndView("index");
            mav.addObject("username",username);
            mav.addObject("patient", p);
            return mav;
        }
        return new ModelAndView("redirect:/deletedProfile");
        //}

        //return new ModelAndView("redirect:/accessDenied");
    }

    @RequestMapping("/accessDenied")
    public ModelAndView accessDenied(){
        return new ModelAndView("accessDenied");
    }

    @RequestMapping("/deletedProfile")
    public ModelAndView deletedProfile(){
        return new ModelAndView("deletedProfile");
    }

    @RequestMapping("/edit/{username}")
    public ModelAndView edit(@PathVariable("username") String username) {

        ModelAndView mav = new ModelAndView("edit");
        Patient patient = patientService.getPatient(username);


        boolean changeGP = patientService.isWithinMonths(patient.getDateOfChangedGp());

        mav.addObject("username",username);
        mav.addObject("showChangeGP",changeGP);
        mav.addObject("patient", patient);
        mav.addObject("patientUsername",username);
        return mav;

    }

    @RequestMapping(value = "/update/{username}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable("username") String username, @ModelAttribute Patient patient) {

        Patient newPatient= patientService.getPatient(username);


        if(!patient.getName().equals(newPatient.getName())){
            newPatient.setName(patient.getName());
        }



        patientService.updatePatient(newPatient);
        return new ModelAndView("redirect:/" + username);

    }


    @RequestMapping(value = "/changeGP/{username}", method = RequestMethod.GET)
    public ModelAndView changeGP(@PathVariable("username") String username) {

        List<Doctor> doctors = patientService.getDoctors();
        DoctorModelAndView dmv = new DoctorModelAndView();

        ModelAndView mav = new ModelAndView("changeGP");
        mav.addObject("username",username);
        mav.addObject("doctors",doctors);
        mav.addObject("doctorModelAndView",dmv);
        return mav;
    }

    @RequestMapping(value = "/updateGP/{username}", method = RequestMethod.POST)
    public ModelAndView updateGP(@PathVariable("username") String username,@ModelAttribute("selectedDoctor") DoctorModelAndView doctorModelAndView) {

        Patient dbPatient = patientService.getPatient(username);

        if(dbPatient.getDoctorGp().getUsername()!= doctorModelAndView.getUsername()){
            Doctor doctor = patientService.getDoctor(doctorModelAndView.getUsername());
            Date date = new Date();
            dbPatient.setDateOfChangedGp(date);
            dbPatient.setDoctorGp(doctor);
            patientService.updatePatient(dbPatient);
        }


        return new ModelAndView("redirect:/" + username);
    }

    @RequestMapping(value = "/delete/{username}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("username") String username) {

        Patient patient= patientService.getPatient(username);
        patientService.deletePatient(patient.getId());


        return new ModelAndView("redirect:/successfullyDeleted");

    }

    @RequestMapping("/successfullyDeleted")
    public ModelAndView successfullyDeleted() {

        ModelAndView mav = new ModelAndView("successfullyDeleted");
        return mav;

    }
    @RequestMapping("/appointments/{username}")
    public ModelAndView appointments(@PathVariable("username") String username){

        List<Appointment> appointments = appointmentService.getUnfinishedAppointments(username);

        ModelAndView mav = new ModelAndView("appointments");

        boolean hasAppointments = !(appointments.isEmpty());

        mav.addObject("hasAppointments",hasAppointments);
        mav.addObject("appointments",appointments);
        mav.addObject("username",username);

        return mav;
    }

    @RequestMapping("/addAppointment/{username}")
    public ModelAndView addAppointment(@PathVariable("username") String username){

        List<Doctor> doctors = patientService.getDoctors();
        AppointmentModelAndView appointmentModelAndView = new AppointmentModelAndView();



        ModelAndView mav = new ModelAndView("addAppointment");
        mav.addObject("doctors",doctors);
        mav.addObject("appointmentModelAndView",appointmentModelAndView);
        mav.addObject("username",username);
        return mav;

    }
    @RequestMapping("/makeAppointment/{username}")
    public ModelAndView makeAppointment(@PathVariable("username")String username,@ModelAttribute("appointmentModelAndView") AppointmentModelAndView appointmentModelAndView){

        Appointment appointment = new Appointment();
        appointment.setPatient(patientService.getPatient(username));
        appointment.setDoctor(patientService.getDoctor(appointmentModelAndView.getDoctorUsername()));
        appointment.setDiagnosis("NOTSET");
        appointment.setDateOfAppointment(new Date());

        appointmentService.addAppointment(appointment);

        return new ModelAndView("redirect:/appointments/" + username);
    }

    @RequestMapping("/seeappointment/{username}/{id}")
    public ModelAndView seeappointment(@PathVariable("username")String username,
                                       @PathVariable("id") Long id) {

        Appointment appointment = appointmentService.getAppointmentByID(id);

        ModelAndView mav = new ModelAndView("seeAppointment");
        mav.addObject("appointment",appointment);
        mav.addObject("username",username);


        return mav;
    }

    @RequestMapping("/pastAppointments/{username}")
    public ModelAndView pastAppointments(@PathVariable("username") String username){

        List<Appointment> appointments = appointmentService.getPastAppointments(username);
        ModelAndView mav = new ModelAndView("pastAppointments");

        boolean hasAppointments = !(appointments.isEmpty());

        mav.addObject("hasAppointments",hasAppointments);
        mav.addObject("appointments",appointments);
        mav.addObject("username",username);

        return mav;
    }
}
