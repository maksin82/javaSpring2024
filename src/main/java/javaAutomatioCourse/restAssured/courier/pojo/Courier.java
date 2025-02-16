package javaAutomatioCourse.restAssured.courier.pojo;

import org.apache.commons.lang3.RandomStringUtils;

public class Courier {

    private String login;
    private String password;
    private String firstname;

    public Courier() {
    }

    public Courier(String login, String password, String firstname) {
        this.login = login;
        this.password = password;
        this.firstname = firstname;
    }

    //    public static Courier generic() {
//        var courier = new Courier("Marks", "P@ssw0rd", "Markovich");
//        return courier;
//    }
    public static Courier random() {
        var courier = new Courier("Marks" + RandomStringUtils.randomAlphabetic(1, 4), "P@ssw0rd", "Markovich");
        return courier;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
