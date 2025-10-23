import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class StudentManager extends FileHandler {
    public static ArrayList<Student> students;
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "12345";


    public StudentManager() {
        students = new ArrayList<>();
        students = loadFromFile();
    }
    public ArrayList<Student> getStudents() {
        return students;
    }

    @Override
    public boolean addStudent(Student s) {
        for (int i = 0; i < students.size(); i++) {
            Student exist = students.get(i);
            if (exist.getStudentID().equals(s.getStudentID())) {
                return false;
            }
        }
        students.add(s);
        return true;

    }
@Override
    public boolean deleteStudent(String id) {
        for (int i = 0; i < students.size(); i++) {
            Student exist = students.get(i);
            if (exist.getStudentID().equals(id)) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }

@Override
    public ArrayList<Student> searchStudent(String searchKey) {
        ArrayList<Student> results = new ArrayList<>();
        String key = searchKey.toLowerCase();

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);

            if (s.getStudentID().toLowerCase().contains(key) ||
                    s.getStudentName().toLowerCase().contains(key)) {
                results.add(s);
            }
        }
        return results;
    }

@Override
    public ArrayList<Student> viewStudentSortedById() {
        ArrayList<Student> sorted = new ArrayList<>(students);
        Collections.sort(sorted, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return s1.getStudentID().compareTo(s2.getStudentID());
            }
        });
        return sorted;
    }
@Override
    public boolean updateStudent(Student s) {
        for (int i = 0; i < students.size(); i++) {
            Student current = students.get(i);
            if(current.getStudentID().equals(s.getStudentID())){
                students.set(i,s);
                return true;
            }
        }
        return false;
    }
}