import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReviewFrame extends JFrame implements ActionListener {

    private JButton check;
    private JButton previous;
    private JTextField field;

    private JLabel consultationID_C;
    private JLabel patientName_C;
    private JLabel doctorName_C;
    private JLabel date_C;
    private JLabel time_C;
    private JLabel noOfHours_C;
    private JLabel cost_C;
    private JLabel notes_C;
    private JLabel image_C;
    private ConsultationController controller;
    public ReviewFrame(ConsultationController controller) {

        this.controller = controller;

        this.setSize(700,500);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(Color.gray);

        JLabel line = new JLabel("Enter the consultation ID");
        field = new JTextField();

        //Buttons
        check = new JButton("Check Consultation");
        previous = new JButton("Go to previous");


        line.setBounds(10, 10, 150, 25);
        field.setBounds(160, 10, 120, 25);
        check.setBounds(160, 40, 300, 20);
        check.setBackground(Color.yellow);
        previous.setBounds(160, 65, 300, 20);
        previous.setBackground(Color.orange);

        //Buttons
        check.addActionListener(this);
        previous.addActionListener(this);

        image_C = new JLabel();
        consultationID_C = new JLabel();
        patientName_C = new JLabel();
        doctorName_C = new JLabel();
        date_C = new JLabel();
        time_C = new JLabel();
        noOfHours_C = new JLabel();
        cost_C = new JLabel();
        notes_C = new JLabel();

        JPanel reviewer = new JPanel(new GridLayout(7,1,0,0));
        reviewer.setBounds(160,80,480,200);
        image_C.setBounds(160,120,400,300);

        reviewer.add(consultationID_C);
        reviewer.add(patientName_C);
        reviewer.add(doctorName_C);
        reviewer.add(date_C);
        reviewer.add(time_C);
        reviewer.add(noOfHours_C);
        reviewer.add(cost_C);
        reviewer.add(notes_C);

        this.add(line);
        this.add(field);
        this.add(check);
        this.add(previous);
        this.add(reviewer);
        this.add(image_C);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == check){
            Consultation consultation = controller.getConsultation(Integer.parseInt(field.getText()));
            if (consultation != null) { //Check consultation ID existence
                String[] consultationInformation = consultation.getData();

                image_C.setIcon(consultation.getImage());
                consultationID_C.setText(consultationInformation[0]);
                patientName_C.setText(consultationInformation[1]);
                doctorName_C.setText(consultationInformation[2]);
                date_C.setText(consultationInformation[3]);
                time_C.setText(consultationInformation[4]);
                noOfHours_C.setText(consultationInformation[5]);
                cost_C.setText(consultationInformation[6]);
                notes_C.setText(consultationInformation[7]);
            }else {
                System.out.println("Invalid ID");
                JOptionPane.showMessageDialog(null, "Invalid ID", "Consultation manager",JOptionPane.INFORMATION_MESSAGE);
            }
        }



    }
}
