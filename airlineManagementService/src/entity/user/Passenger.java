package entity.user;

public class Passenger {
    private final String name;
    private final String emailId;
    private final String phoneNumber;
    private final int age;
    private final Gender gender;


    public Passenger(String name, String emailId, String phoneNumber, int age, Gender gender) {
        this.name = name;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.gender = gender;
    }
}
