import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {
    private String name;
    private String surName;
    private LocalDate dateOfBirth;
    private String mobileNumber;

    public Person (String name, String surName, LocalDate dateOfBirth, String mobileNumber){
        this.name = name;
        this.surName = surName;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;

    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public String toString() {
        return  "name= " + name + '\n' +
                "surName= " + surName + '\n' +
                "dateOfBirth= " + dateOfBirth + "\n" +
                "mobileNumber= " + mobileNumber + '\n' ;
    }
}
