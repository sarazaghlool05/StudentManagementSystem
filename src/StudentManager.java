import java.util.ArrayList;
import java.util.logging.FileHandler;

public class StudentManager extends FileHandler {
    private ArrayList<Student> students;
    public StudentManager() {
        students = new ArrayList<>();
        students=loadFromFile;
    }
    public boolean addStudent(Student s){
        for(int i = 0; i < students.size(); i++) {
            Student exist = students.get(i);
            if (exist.getId().equals(s.getId())) {
                return false;
            }
        }
          students.add(s);
        saveToFile(students);
          return true;

    }
    public boolean deleteStudent(Student s){
        for(int i = 0; i < students.size(); i++) {
            Student exist = students.get(i);
            if (exist.getId().equals(s.getId())) {
                return false;
            }
        }
        students.remove(s);
        saveToFile(students);
        return true;
    }
    
}
