import java.util.ArrayList;
import java.util.logging.FileHandler;

public class StudentManager extends FileHandler {
    private ArrayList<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
        students = loadFromFile;
    }

    public boolean addStudent(Student s) {
        for (int i = 0; i < students.size(); i++) {
            Student exist = students.get(i);
            if (exist.getId().equals(s.getId())) {
                return false;
            }
        }
        students.add(s);
        saveToFile(students);
        return true;

    }

    public boolean deleteStudent(Student s) {
        for (int i = 0; i < students.size(); i++) {
            Student exist = students.get(i);
            if (exist.getId().equals(s.getId())) {
                return false;
            }
        }
        students.remove(s);
        saveToFile(students);
        return true;
    }

    public ArrayList<Student> searchStudent(String searchkey) {
        ArrayList<Student> results = new ArrayList<>();
        String key = searchkey.toLowerCase();

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);

            if (s.getId().toLowerCase().contains(key) ||
                    s.getName().toLowerCase().contains(key)) {
                results.add(s);
            }
        }
        return results;
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

    public boolean updateStudent(Student s) {
        for (int i = 0; i < students.size(); i++) {
            Student current = students.get(i);
            if(current.getId().equals(s.getId())){
                students.set(i,s);
                saveToFile(students);
                return true;
            }
        }
        return false;
    }
}