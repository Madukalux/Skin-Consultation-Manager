import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConsultationController{

    private ArrayList <Doctor> doctors;
    private LinkedList <Consultation> consultations = new LinkedList<>();
    private static int Id = 100;

    public ConsultationController(ArrayList<Doctor> doctors){
        this.doctors = doctors;
    }
    public int addConsultation(Doctor doctor, Patient patient, LocalDate date, LocalTime time, int noOfHours, String notes, ImageIcon image){
        double cost;

        //calculate cost

        if(isPatientFirstTime(patient.getPatientId())){
            cost = noOfHours * 25;
        }else {
            cost = noOfHours * 15;
        }
        if(checkAvailability(date,time,noOfHours,doctor.getMedicalLicenceNumber())){
            Consultation c = new Consultation(doctor,patient,date,time,noOfHours,cost,notes,image);
            consultations.add(c);
            return c.getId();
        }else {
            for(Doctor d : doctors){
                if(checkAvailability(date,time,noOfHours,d.getMedicalLicenceNumber())){
                    Consultation c = new Consultation(d,patient,date,time,noOfHours,cost,notes,image);
                    consultations.add(c);
                    return c.getId();
                }
            }
        }
        return -1;

    }

    public boolean isPatientFirstTime(int patientID){    //detecting the first time patient
        for(Consultation c: consultations){
            if (c.getPatient().getPatientId() == patientID) {
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    public Consultation getConsultation(int consultId){
        for (Consultation c : consultations){
            if(c.getId() == consultId){
                return c;
            }
        }
        return null;
    }

    public boolean checkAvailability(LocalDate consultationDate, LocalTime consultationTime, int noOfHours, int doctorLicenceNumber){


        // Checking the availability of the doctor
        for (Consultation consultation : consultations){
            if(consultation.getDocLi() == doctorLicenceNumber){
                boolean dateCheck = consultationDate.equals(consultation.getDate());

                LocalTime T1 = consultation.getTime(); //old consultation start time
                LocalTime T2 = consultation.getTime().plusHours(consultation.getNoOfHours()); //old consultation end time
                LocalTime t1 = consultationTime; //new consultation start time
                LocalTime t2 = consultationTime.plusHours(noOfHours); //new consultation end time

                boolean CheckTime1 = ( t1.isAfter(T1) ) && ( t1.isBefore(T2) );
                boolean CheckTime2 = ( t2.isAfter(T1) ) && ( t2.isBefore(T2) );
                boolean CheckTime3 = ( t1.isBefore(T1) && (t2.isAfter(T2)) );
                boolean CheckTime4 = (t1.equals(T1) || t1.equals(T2) || t2.equals(T1) || t2.equals(T2));

                if( (dateCheck) && ( (CheckTime1) || (CheckTime2) || (CheckTime3) || (CheckTime4) ) ){
                    return false;
                }
            }
        }
        return true;
    }
    public void saveToFile() {  //save file
        try  {
            FileOutputStream fos = new FileOutputStream("consultations.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Consultation c : consultations){
                oos.writeObject(c);
            }
            oos.close();
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        }
    }

    public int loadFile() { //load from the file
        try  {
            FileInputStream fos = new FileInputStream("consultations.dat");
            ObjectInputStream oos = new ObjectInputStream(fos);
            boolean eof = false;
            int count = 0;
            while (!eof){
                try {
                    Consultation c = (Consultation) oos.readObject();
                    consultations.add(c);
                    count++;
                }catch (EOFException e){
                    eof = true;
                }
            }
            Consultation.setIdCounter(count);
            oos.close();
            return 1;
        } catch (ClassNotFoundException | IOException e) {
            return -1;
        }
    }
}
