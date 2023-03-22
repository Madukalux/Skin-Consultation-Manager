import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddConsultation extends JFrame implements ActionListener {



    private JLabel headingConsul;

    private JTextField consultDate;

    private JTextField consultTime;

    private JTextField consultTimeDuration;

    private JComboBox selectDoc;

    private JButton checkAvailability;

    private JTextField patientName;

    private JTextField patientSurName;

    private JTextField enterMobileNo;

    private JTextField dateOfBirth;

    private JTextField ID;

    private JButton addPic;

    private JTextArea AddInfo;

    private JButton Add;
    private ImageIcon image;
    private  WestminsterSkinConsultationManager west;
    private ConsultationController controller;


    public AddConsultation(WestminsterSkinConsultationManager west,ConsultationController controller) {

        this.west = west;
        this.controller = controller;

        this.getContentPane().setBackground(Color.gray);

        this.setSize(600, 460);

        this.getContentPane().setLayout(null);


        JLabel name_1 = new JLabel("Enter the name of patient :");
        patientName= new JTextField();

        name_1.setBounds(20,230,150,30);
        patientName.setBounds(250,230,150,30);

        JLabel name_2 = new JLabel("Enter the Surname of patient:");
        patientSurName= new JTextField();

        name_2.setBounds(20,270,150,30);
        patientSurName.setBounds(250,270,150,30);

        JLabel name_3 = new JLabel("Enter Date of Birth :");
        dateOfBirth = new JTextField();

        name_3.setBounds(20,310,150,30);
        dateOfBirth.setBounds(250,310,150,30);


        JLabel name_4 = new JLabel("Enter patient's Mobile number :");
        enterMobileNo = new JTextField();


        name_4.setBounds(20,350,250,30);
        enterMobileNo.setBounds(250,350,150,30);

        JLabel idLabel = new JLabel("Enter patient's ID :");
        ID = new JTextField();

        idLabel.setBounds(20,390,250,30);
        ID.setBounds(250,390,150,30);

        JLabel name_5 = new JLabel("Additional Info  :");
        AddInfo = new JTextArea();

        name_5.setBounds(20,430,250,30);
        AddInfo.setBounds(250,430,150,100);



        JLabel name_6 = new JLabel("Upload image  :");
        addPic = new JButton();

        name_6.setBounds(20,540,250,30);
        addPic.setBounds(2500,540,150,30);


        JLabel name_7 = new JLabel("Select a doctor :");
        selectDoc = new JComboBox();
        selectDoc = new JComboBox<>(west.getDoctorArr());

        name_7.setBounds(20,10,150,30);
        selectDoc.setBounds(250,15,150,30);

        JLabel name_8 = new JLabel("Enter consultation date:");
        consultDate= new JTextField();

        name_8.setBounds(20,50,150,30);
        consultDate.setBounds(250,55,150,30);


        JLabel name_9 = new JLabel("Enter consultation time :");
        consultTime= new JTextField();

        name_9.setBounds(20,90,150,30);
        consultTime.setBounds(250,95,150,30);

        JLabel name_10 = new JLabel("Enter number of hours :");
        consultTimeDuration= new JTextField();

        name_10.setBounds(20,130,150,30);
        consultTimeDuration.setBounds(250,135,150,30);


        //Buttons
        Add = new JButton("Add consultation");
        Add.setBounds(250,580,150,20);
        Add.setBackground(Color.orange);
        Add.addActionListener(this);

        checkAvailability = new JButton("Check availability");
        checkAvailability.setBounds(250,170,150,20);
        checkAvailability.setBackground(Color.yellow);

        addPic = new JButton("Add Image");
        addPic.setBounds(250,540,150,20);
        addPic.setBackground(Color.yellow);
        addPic.addActionListener(this);



        this.add(name_1);
        this.add(name_2);
        this.add(name_3);
        this.add(name_4);
        this.add(name_5);
        this.add(name_6);
        this.add(name_7);
        this.add(name_8);
        this.add(name_9);
        this.add(name_10);
        this.add(idLabel);
        this.add(ID);
        this.add(patientName);
        this.add(patientSurName);
        this.add(dateOfBirth);
        this.add(enterMobileNo);
        this.add(AddInfo);
        this.add(addPic);
        this.add(selectDoc);
        this.add(consultDate);
        this.add(consultTime);
        this.add(consultTimeDuration);
        this.add(Add);
        this.add(checkAvailability);
        this.add(AddInfo);
        this.add(selectDoc);


        this.setTitle("Westminster Skin Consultation Appointment");
        getContentPane().setBackground(Color.white);
        this.setSize(600,720);
        this.setLayout(null);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        String path;
        File selectedFile;

        if(e.getSource() == Add){

            String [] dateArr = dateOfBirth.getText().split("\\.");

            try {
                //Validate date of birth with try/catch
                LocalDate dateOfBirth = LocalDate.of(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[2]));

                //Covert ID (String --> int) validate
                Patient tempP = new Patient(patientName.getText(), patientSurName.getText(), dateOfBirth, enterMobileNo.getText(), Integer.parseInt(ID.getText()));

                String[] consultDateArr = consultDate.getText().split("\\.");
                LocalDate conDate = LocalDate.of(Integer.parseInt(consultDateArr[0]), Integer.parseInt(consultDateArr[1]), Integer.parseInt(consultDateArr[2]));


                String[] timeL = consultTime.getText().split("\\.");
                LocalTime conTime = LocalTime.of(Integer.parseInt(timeL[0]), Integer.parseInt(timeL[1]));

                Doctor tempDoctor = west.getDoc(Integer.parseInt(selectDoc.getSelectedItem().toString().split("-")[1]));

                //Covert consultTimeDuration (String --> int) validate
                //int consultTimeDuration = Integer.parseInt(consultTimeDuration.getText());
                int id = controller.addConsultation(tempDoctor,tempP,conDate,conTime,Integer.parseInt(consultTimeDuration.getText()),AddInfo.getText(),image);
                if(id < 0){
                    JOptionPane.showMessageDialog(null, "No available doctors", "westminster",JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, "Successfully added consultation ID is : " + id, "westminster",JOptionPane.INFORMATION_MESSAGE);
                }
                if(ID.getText().isBlank()){
                    JOptionPane.showMessageDialog(null, "Patient ID is required", "westminster",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                //patient information
                if(patientName.getText().isBlank()){
                    JOptionPane.showMessageDialog(null, "Patient name is required", "westminster",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if(patientSurName.getText().isBlank()){
                    JOptionPane.showMessageDialog(null, "Patient surname is required", "westminster",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                if(enterMobileNo.getText().isBlank()){
                    JOptionPane.showMessageDialog(null, "Patient mobile number is required", "westminster",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

            }
            catch (NumberFormatException err ) {
                JOptionPane.showMessageDialog(null, "Invalid Date of birth", "westminster", JOptionPane.INFORMATION_MESSAGE);
            }



        }



        if(e.getSource() == addPic){
            JFileChooser file = new JFileChooser();
            file.setCurrentDirectory(new File("user.home"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images","jpg","gif","png");
            file.addChoosableFileFilter(filter);
            int result = file.showSaveDialog(null);

            if(result == JFileChooser.APPROVE_OPTION){
                selectedFile = file.getSelectedFile();
                path = selectedFile.getAbsolutePath();
                image = resizeImage(path);

            } else if (result == JFileChooser.CANCEL_OPTION) {
                System.out.println("None selected");
            }
        }
    }
    private ImageIcon resizeImage(String imagePath){
        ImageIcon MyImage = new ImageIcon(imagePath);
        Image image = MyImage.getImage();
        Image newImage = image.getScaledInstance(480,300, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

}
