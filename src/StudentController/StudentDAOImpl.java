package StudentController;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class StudentDAOImpl implements StudentDAO {

    @Override
    public void save(Student students) {

        PreparedStatement preparedStatement;

        try {
            Connection connection = StudentDb.connection;
            preparedStatement = connection.prepareStatement("INSERT INTO students(name, course, fee) VALUES (?, ?, ?)");
            preparedStatement.setString(1, students.getName());
            preparedStatement.setString(2, students.getCourse());
            preparedStatement.setInt(3, students.getFee());
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Saved");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Record not saved");
        }
    }

    @Override
    public void update(Student students) {

        try {
            Connection connection = StudentDb.connection;
            PreparedStatement preparedStatement = connection.prepareStatement("update students set name = ?, course = ?, fee = ? where id = ?");
            preparedStatement.setString(1, students.getName());
            preparedStatement.setString(2, students.getCourse());
            preparedStatement.setInt(3, students.getFee());
            preparedStatement.setInt(4, students.getId());
            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Updated");
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
    }


    @Override
    public void delete(Student students) {
        try{
            Connection connection = StudentDb.connection;
            PreparedStatement preparedStatement = connection.prepareStatement("delete from students where id = ?");
            preparedStatement.setInt(1, students.getId());
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deleted");
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    @Override
    public Student get(int id) {
        Student student = new Student();

        try{
            Connection connection = StudentDb.connection;
            PreparedStatement preparedStatement = connection.prepareStatement("select * from students where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setCourse(resultSet.getString("course"));
                student.setFee(resultSet.getInt("fee"));
            }
            }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
        return student;
        }

    @Override
    public List<Student> list() {
        List<Student> list = new ArrayList<Student>();
        try {
            Connection connection = StudentDb.connection;
            PreparedStatement preparedStatement = connection.prepareStatement("select * from students");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setCourse(resultSet.getString("course"));
                student.setFee(resultSet.getInt("fee"));

                list.add(student);
            }
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
        return list;

        }
}






