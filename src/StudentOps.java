import java.util.ArrayList;
public interface StudentOps {
boolean updateStudent(Student s);
ArrayList<Student>viewStudentSortedById();
boolean addStudent(Student s);
ArrayList<Student>searchStudent(String searchKey);
boolean deleteStudent(String id);
}
