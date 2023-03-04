package StudentController;

import java.util.List;

public interface StudentDAO {

    void save(Student students);

    void update(Student students);

    void delete(Student students);

    Student get(int id);

    List<Student> list();

}
