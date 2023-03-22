import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Home extends JFrame implements ActionListener {

    private JLabel heading;
    private JButton sortB;
    private JButton addB;
    private JButton reviewB;
    private JButton saveDataB;

    private JTable doctorTable;
    private WestminsterSkinConsultationManager west;
    private ConsultationController controller;

    public Home(WestminsterSkinConsultationManager west) {
        setTitle("Westminster Skin Consultation Manager");
        getContentPane().setBackground(Color.gray);

        this.controller = new ConsultationController(west.getDoctors());
        controller.loadFile();//load previous data
        this.west = west;

        //GUI heading
        heading = new JLabel();
        heading.setText("------WELCOME TO WESTMINSTER SKIN CONSULTATION MANAGER GUI------");
        heading.setFont(new Font(Font.DIALOG,Font.BOLD,15));
        heading.setBounds(20, 20, 700, 30);

        this.setSize(620,700);
        this.getContentPane().setLayout(null);

        doctorTable = new JTable(west.getTableData());
        JScrollPane doctorTableScrollPane = new JScrollPane(doctorTable);
        doctorTableScrollPane.setBounds(20,40,565,500);

        //Buttons

        sortB = new JButton("sort table");
        addB = new JButton("add consultation");
        reviewB = new JButton("review consultation");
        saveDataB = new JButton("save data");

        sortB.setBounds(10,580,140,50);
        sortB.setBackground(Color.orange);
        addB.setBounds(160,580,140,50);
        addB.setBackground(Color.orange);
        reviewB.setBounds(310,580,140,50);
        reviewB.setBackground(Color.orange);
        saveDataB.setBounds(460,580,140,50);
        saveDataB.setBackground(Color.orange);

        sortB.addActionListener(this);
        addB.addActionListener(this);
        reviewB.addActionListener(this);
        saveDataB.addActionListener(this);

        this.add(doctorTableScrollPane);
        this.add(sortB);
        this.add(addB);
        this.add(reviewB);
        this.add(saveDataB);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == addB){
            new AddConsultation(this.west,this.controller);
        }

        if(e.getSource() == reviewB){
            new ReviewFrame(this.controller);

        }

        if(e.getSource() == saveDataB){
            controller.saveToFile();
            JOptionPane.showMessageDialog(null, "Successfully saved data to a file", "westminster",JOptionPane.INFORMATION_MESSAGE);
        }

        if(e.getSource() == sortB){

            TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(doctorTable.getModel());
            doctorTable.setRowSorter(sorter);

            ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
            sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));//set column one as sort key and ascending order
            sorter.setSortKeys(sortKeys);
            doctorTable.repaint();//refresh the table after sorting
            JOptionPane.showMessageDialog(null, "Successfully sorted doctors according to their names", "westminster",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
