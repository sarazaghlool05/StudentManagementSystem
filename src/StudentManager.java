import java.util.*;


public class StudentManager extends FileHandler {
    private ArrayList<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
        students = loadFromFile();
    }

    public boolean addStudent(Student s) {
        for (int i = 0; i < students.size(); i++) {
            Student exist = students.get(i);
            if (exist.getStudentID().equals(s.getStudentID())) {
                return false;
            }
        }
        students.add(s);
        saveToFile(students);
        return true;

    }

    public boolean deleteStudent(String id) {
        for (int i = 0; i < students.size(); i++) {
            Student exist = students.get(i);
            if (exist.getStudentID().equals(id)) {
                students.remove(i);
                saveToFile(students);
                return true;
            }
        }
        return false;
    }


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


    public ArrayList<Student> viewStudentsSortedById() {
        ArrayList<Student> sorted = new ArrayList<>(students);
        Collections.sort(sorted, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return s1.getStudentID().compareTo(s2.getStudentID());
            }
        });
        return sorted;
    }

    public boolean updateStudent(Student s) {
        for (int i = 0; i < students.size(); i++) {
            Student current = students.get(i);
            if(current.getStudentID().equals(s.getStudentID())){
                students.set(i,s);
                saveToFile(students);
                return true;
            }
        }
        return false;
    }
}