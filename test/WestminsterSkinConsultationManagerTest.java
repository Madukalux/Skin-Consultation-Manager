import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDate;

public class WestminsterSkinConsultationManagerTest extends TestCase {
    @Test
    public void testAddDoctorShouldSaveADoctor() {
        String name = "Amber";
        String surname = "jim";
        String dateOfBirth = "2012.12.12";
        String [] dateArr = dateOfBirth.split("\\.");
        LocalDate DOB = LocalDate.of(Integer.parseInt(dateArr[0]),Integer.parseInt(dateArr[1]),Integer.parseInt(dateArr[2]));
        String mobileNumber = "0756677890";
        int medicalLicenceNumber = 100;
        String specialization = "Surgery";

        Doctor doctor = new Doctor(name,surname,DOB,mobileNumber,medicalLicenceNumber,specialization);


        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        manager.addDoctor(doctor);
        assertEquals(manager.getDoctors().get(0).getName(),"Amber");
    }

    @Test
    public void testDeleteDoctorShouldRemoveADoctor() {
        String name = "Amber";
        String surname = "Jim";
        String dateOfBirth = "2012.12.12";
        String [] dateArr = dateOfBirth.split("\\.");
        LocalDate DOB = LocalDate.of(Integer.parseInt(dateArr[0]),Integer.parseInt(dateArr[1]),Integer.parseInt(dateArr[2]));
        String mobileNumber = "0756677890";
        int medicalLicenceNumber = 100;
        String specialization = "Surgery";


        Doctor doctor = new Doctor(name,surname,DOB,mobileNumber,medicalLicenceNumber,specialization);

        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        manager.addDoctor(doctor);
        assertEquals(manager.getDoctors().get(0).getName(),"Amber");
        manager.deleteDoctor(100);
        assertEquals(manager.getDoctors().size(),1);

    }

        @Test
    public void testSaveDataShouldRead() {
        String name = "Amber";
        String surname = "Jim";
        String dateOfBirth = "2012.12.12";
        String [] dateArr = dateOfBirth.split("\\.");
        LocalDate DOB = LocalDate.of(Integer.parseInt(dateArr[0]),Integer.parseInt(dateArr[1]),Integer.parseInt(dateArr[2]));
        String mobileNumber = "0756677890";
        int medicalLicenceNumber = 100;
        String specialization = "Surgery";

        Doctor doctor = new Doctor(name,surname,DOB,mobileNumber,medicalLicenceNumber,specialization);

        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        manager.addDoctor(doctor);
        manager.saveToFile();
        manager.loadFile();
        assertEquals(manager.getDoctors().get(0).getName(),"Amber");
    }


}