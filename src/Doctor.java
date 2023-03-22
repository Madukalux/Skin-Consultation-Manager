import java.io.Serializable;
import java.time.LocalDate;

public class Doctor extends Person implements Comparable<Doctor>, Serializable {
    private int medicalLicenceNumber;
    private String  specialisation;

    public Doctor(String name, String surName, LocalDate dateOfBirth, String mobileNumber, int medicalLicenceNumber, String specialisation) {
        super(name, surName, dateOfBirth, mobileNumber);
        this.medicalLicenceNumber = medicalLicenceNumber;
        this.specialisation = specialisation;
    }

    public int getMedicalLicenceNumber() {
        return medicalLicenceNumber;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    @Override
    public int compareTo(Doctor doctor) {

        if(doctor.getSurName().equalsIgnoreCase(this.getSurName())){
            return 0;
        }else if(doctor.getSurName().compareToIgnoreCase(this.getSurName()) < 0){
            return 1;
        }else {
            return -1;
        }

    }

    @Override
    public String toString() {
        return  super.toString() +
                "medicalLicenceNumber =" + medicalLicenceNumber +"\n"+
                "specialisation= " + specialisation + "\n";
    }
}
