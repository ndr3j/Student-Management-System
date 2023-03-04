package StudentController;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static StudentController.StudentDb.Connect;


public class StudentForm {
    private JButton saveButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JTextField txtcourse;
    private JTextField txtname;
    private JTextField txtfee;
    private JTable table1;
    private JPanel StudentForm;

    public static void main(String[] args) {
        JFrame frame = new JFrame("StudentForm");
        frame.setContentPane(new StudentForm().StudentForm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    int search;
    Connection connection;
    PreparedStatement preparedStatement;



    public void TableLoad() {

        try {
            Connection connection = StudentDb.connection;
            preparedStatement = connection.prepareStatement("select * from students");
            ResultSet resultSet = preparedStatement.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public StudentForm() {
        Connect();
        TableLoad();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student student = new Student();
                String name = txtname.getText();
                String course = txtcourse.getText();
                int fee = Integer.parseInt(txtfee.getText());

                student.setName(name);
                student.setCourse(course);
                student.setFee(fee);

                StudentDAOImpl dao = new StudentDAOImpl();
                dao.save(student);
                TableLoad();
                txtname.setText("");
                txtcourse.setText("");
                txtfee.setText("");
                txtname.requestFocus();

            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search = Integer.parseInt(JOptionPane.showInputDialog("Enter Student ID"));

                StudentDAOImpl dao = new StudentDAOImpl();
                Student student = dao.get(search);

                txtname.setText(student.getName());
                txtcourse.setText(student.getCourse());
                txtfee.setText(String.valueOf(student.getFee()));

            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search = Integer.parseInt((JOptionPane.showInputDialog("Enter Student ID")));
            Student student = new Student();

            String name = txtname.getText();
            String course = txtcourse.getText();
            int fee = Integer.parseInt(((txtfee.getText())));

            student.setName(name);
            student.setCourse(course);
            student.setFee(fee);
            student.setId(search);

            StudentDAOImpl dao = new StudentDAOImpl();
            dao.update(student);
            TableLoad();
            txtname.setText("");
            txtcourse.setText("");
            txtfee.setText("");
            txtname.requestFocus();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student student = new Student();
                student.setId(search);
                StudentDAOImpl dao = new StudentDAOImpl();
                dao.delete(student);
                TableLoad();
                txtname.setText("");
                txtcourse.setText("");
                txtfee.setText("");
                txtname.requestFocus();
            }
        });
    }


}






