import java.time.LocalDate;
import java.util.Scanner;

public class main{

    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        WestminsterSkinConsultationManager manage = new WestminsterSkinConsultationManager();
        run(manage);
    }


    private static void run(WestminsterSkinConsultationManager manager){

        boolean exit = false;

        //Load exiting data if exists before program starts
        manager.loadFile();
        //print console menu for the first time
        menu();

        while(!exit){
            System.out.print("Enter your choice[enter 105 or 'PM' to print menu again] ?: ");
            String select = scanner.nextLine();

            switch (select) {
                case "100", "ADD" -> addDoctor(manager);
                case "101", "DEL" -> deleteDoctor(manager);
                case "102", "PRT" -> manager.displayListOfDoctors();
                case "103", "RCQ" -> manager.saveToFile();
                case "104", "GUI" ->openGUI(manager);
                case "105", "PM" -> menu();
                case "000", "EXT" -> exit = true;
                default -> System.out.println("Invalid Input !!");
            }
        }

    }

    private static void menu(){
        System.out.println("*".repeat(25)+ "*".repeat(25));
        System.out.println("*".repeat(5) + " WELCOME TO SKIN CONSULTATION  MANAGER " + "*".repeat(5));
        System.out.println("*".repeat(25)+ "*".repeat(25) + "\n");

        System.out.println("""
                    100 or ADD-------> Add a new doctor.
                    101 or DEL-------> Delete a doctor.
                    102 or PRT-------> Print Doctor List.                 
                    103 or SPD-------> Store Program Data into file.               
                    104 or GUI------> Open graphical user interface.
                    105 or PM--------> Print Menu.                    
                    000 or EXT-------> Exit.
                    """);
    }

    private static void addDoctor(WestminsterSkinConsultationManager manage){
        scanner = new Scanner(System.in);

        //Doctor Details

        System.out.println("************Add New Doctor*************");

        System.out.print("Enter your name : ");
        String firstName = scanner.nextLine();

        System.out.print("Enter the surname : ");
        String SurName = scanner.nextLine();

        System.out.print("Enter the date of birth[Format 'yyyy.mm.dd' ] : ");
        String DateOfBirth = scanner.nextLine();
        String [] dateArr = DateOfBirth.split("\\.");
        LocalDate dateOfBirth = LocalDate.of(Integer.parseInt(dateArr[0]),Integer.parseInt(dateArr[1]),Integer.parseInt(dateArr[2]));

        System.out.print("Enter mobile number : ");
        String mobileNo = scanner.nextLine();

        System.out.print("Enter Medical licence number : ");
        int medicalLicenceNo = gettingInput();

        System.out.print("Specialisation : ");
        String specialisation = scanner.nextLine();
        scanner.nextLine();

        manage.addDoctor(new Doctor(firstName,SurName,dateOfBirth,mobileNo,medicalLicenceNo,specialisation));

    }

    private static void deleteDoctor(WestminsterSkinConsultationManager manage){
        System.out.print("Enter the medical licence number of doctor you want to delete : "); //medical licence number required for deletion
        int medicalLicenceNumber = gettingInput();
        manage.deleteDoctor(medicalLicenceNumber);

    }

    private static int gettingInput(){
        while (!scanner.hasNextInt()){                                  //checking the Input
            System.out.println("Invalid Input Please enter again");     //Displaying Not an integer error message
            scanner.next();
        }
        return scanner.nextInt();

    }

    private static void openGUI(WestminsterSkinConsultationManager manager){
        new Home(manager);
    }


}