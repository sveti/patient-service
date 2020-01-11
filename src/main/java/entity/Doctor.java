package entity;

import java.io.Serializable;

public class Doctor implements Serializable {

    private Long id;

    private String username; // will retrieve the DB for user with such EGN as Id

    private String name; // User's full name


    private String medicalSpeciality;

    private boolean isGp;


    public Doctor() {
    }


    public Doctor(Long id, String username, String name, String medicalSpeciality, boolean isGp) {
        this.id = id;
        this.username = username;
        this.name = name;

        this.medicalSpeciality = medicalSpeciality;
        this.isGp = isGp;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getMedicalSpeciality() {
        return medicalSpeciality;
    }

    public void setMedicalSpeciality(String medicalSpeciality) {
        this.medicalSpeciality = medicalSpeciality;
    }

    public boolean isGp() {
        return isGp;
    }

    public void setGp(boolean gp) {
        isGp = gp;
    }


    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", medicalSpeciality='" + medicalSpeciality + '\'' +
                ", isGp=" + isGp +
                '}';
    }
}


