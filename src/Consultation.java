import javax.swing.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Consultation implements Serializable {
    private Doctor doctor;

    private Patient patient;

    private LocalDate date;

    private LocalTime time;

    private int noOfHours;

    private double cost;

    private String notes;

    private ImageIcon image;

    private static int idCounter = 0;
    private int id;

    public Consultation(Doctor doctor, Patient patient, LocalDate date, LocalTime time, int noOfHours, double cost, String notes, ImageIcon image) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.time = time;
        this.noOfHours = noOfHours;
        this.cost = cost;
        this.notes = notes;
        this.image = image;
        this.id = ++idCounter;

        System.out.println("Your id is = " + id);
    }

    public LocalDate getDate (){
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Time getTime(Time time) {
        return time;
    }

    public String [] getData() {
        return new String[] {"Consultation ID ='" + id,
                "Doctor Name =" + doctor.getName(),
                "Patient Name =" + patient.getName(),
                "Date =" + date.getYear() + "." + date.getMonth() + "." + "." +date.getDayOfMonth(),
                "Time =" + time.getHour() + "h" + time.getMinute() + "min",
                "No Of Hours =" + noOfHours,
                "Cost =" + "£" + cost + '\n',
                "Notes =" + notes};
    }

    public ImageIcon getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public static void setIdCounter(int idCounter) {
        Consultation.idCounter = idCounter;
    }
    public int getDocLi(){
        return doctor.getMedicalLicenceNumber();
    }
    public LocalTime getTime(){
        return time;
    }

    public int getNoOfHours() {
        return noOfHours;
    }
}


