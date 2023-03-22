import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class WestminsterSkinConsultationManager implements SkinConsultationManager{

    private ArrayList<Doctor> doctors = new ArrayList<>(10);

    @Override

    public void addDoctor(Doctor doctor) {
        if( (doctors.size() > 10) ) {  //checking duplicate doctors & size of the doctor list
            System.out.println("Doctor list is Full");
        } else if ((checkForDuplicates(doctor))) {
            System.out.println("This doctor already exist.");
        }else {
            doctors.add(doctor);
            System.out.println("Successfully added Dr." + doctor.getName());
        }

    }


        @Override
    public void deleteDoctor(int medicalLicenceNumber) {
        if (doctors.size()==0){ //checking if doctor list is empty
            System.out.println("The Doctor List is empty");
            return;

        }
        int id = searchDoctor(medicalLicenceNumber);// search the doctor ID

        if (id > 0){
            Doctor temp = doctors.remove(id);
            System.out.println("\n Successfully removed Dr : " + temp.getName() +
                               "\n Medical licence number  : " + temp.getMedicalLicenceNumber() +
                               "\n ");
            return;
        }

        System.out.println("Entered licence number is Not valid\n");


    }

    @Override
    public void displayListOfDoctors() {
        Collections.sort(doctors); //sort List of doctors
        ListIterator<Doctor> iterator = doctors.listIterator();

        if (doctors.isEmpty()){ //checking if doctor list is empty
            System.out.println("No doctors to view");
        }
        else {
            while (iterator.hasNext()){
                System.out.println(iterator.next().toString() + "*".repeat(60));
            }

        }




    }

    @Override
    public void saveToFile() {
        try  {
            FileOutputStream fos = new FileOutputStream("doctorDetails.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Doctor doctor : doctors){
                oos.writeObject(doctor);
            }
            oos.close();
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        }
        System.out.println("Successfully saved data into file\n");
    }

    @Override
    public void loadFile() {
        try  {
            FileInputStream fos = new FileInputStream("doctorDetails.dat");
            ObjectInputStream oos = new ObjectInputStream(fos);
            boolean eof = false;
            while (!eof){
                try {
                    Doctor tempDoctor = (Doctor) oos.readObject();
                    doctors.add(tempDoctor);
                }catch (EOFException e){
                    eof = true;
                }
            }
            System.out.println("Successfully loaded data from file\n");
            oos.close();
        } catch (ClassNotFoundException e) {
            System.out.println("InvalidClassException " + e.getMessage());
        }catch (IOException e){
            System.out.println("IOException " + e.getMessage());
        }
    }
    private boolean checkForDuplicates(Doctor doctor){
        for(Doctor doc : doctors){
            if( (doc.getMedicalLicenceNumber() == (doctor.getMedicalLicenceNumber())) ||
                    (doc.getMobileNumber().equals(doctor.getMobileNumber())) ){
                return true;//return true if duplicates exists
            }
        }
        return false; //return false if duplicates doesn't exists
    }

    public int searchDoctor(int medicalLicenceNumber){
        for(Doctor d : doctors){
            if(d.getMedicalLicenceNumber() == (medicalLicenceNumber)){
                return doctors.indexOf(d);
            }
        }

        return -1;
    }

    public Doctor getDoc(int medicalLicenceNumber){
        return doctors.get(searchDoctor(medicalLicenceNumber));
    }
    public DefaultTableModel getTableData(){
        Object [][] tableValues = new Object[doctors.size()][4];

        for(int i = 0; i < doctors.size(); i++){
            tableValues[i][0] = doctors.get(i).getName() + " " + doctors.get(i).getSurName();
            tableValues[i][1] = doctors.get(i).getMedicalLicenceNumber();
            tableValues[i][2] = doctors.get(i).getSpecialisation();
            tableValues[i][3] = doctors.get(i).getMobileNumber();
        }

        Object [] columns = new Object[]{"Name","Licence Number", "Specialization", "MobileNumber"};

        return new DefaultTableModel(tableValues,columns);
    }

    public String [] getDoctorArr(){
        String [] doctorArrString = new String[doctors.size()];

        int i = 0;
        for (Doctor d : doctors){
            doctorArrString[i] = doctors.get(i).getName() + "-" + doctors.get(i).getMedicalLicenceNumber();
            i++;
        }

        return doctorArrString;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }
}
