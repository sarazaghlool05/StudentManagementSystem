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
    public Student searchStudent(Student s){
        for(int i = 0; i < students.size(); i++) {
            Student exist = students.get(i);
            if (exist.getId().equals(s.getId())) {
                return s;
            }
        }
            return null;
        }
    
    public ArrayList<Student> viewStudentsSortedById() {
        ArrayList<Student> sorted = new ArrayList<>(students);
        Collections.sort(sorted, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return s1.getId().compareTo(s2.getId());
            }
        });
        return sorted;
    }

    public boolean updateStudent(Student s){

    }
}
