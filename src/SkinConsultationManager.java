public interface SkinConsultationManager {
    void addDoctor(Doctor doctor);
    void deleteDoctor(int medicalLicenceNumber);
    void displayListOfDoctors();
    void saveToFile();
    void loadFile();
}
