import java.util.ArrayList;

public class StudentManager {
    private ArrayList<Student> students;
    public StudentManager() {
        students = new ArrayList<>();
    }
    public boolean addStudent(Student s){
        for(int i = 0; i < students.size(); i++){
          Student exist =students.get(i);
          if(exist.getId().equals(s.getId())){
              return false;
          }
          students.add(s);
          return true;
        }
    }
}
