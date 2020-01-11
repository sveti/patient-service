package entity;

public class DoctorModelAndView {

    String username;

    public DoctorModelAndView() {
    }

    public DoctorModelAndView(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "DoctorModelAndView{" +
                "username='" + username + '\'' +
                '}';
    }
}
