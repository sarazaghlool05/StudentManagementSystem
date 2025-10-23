import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class FileHandler implements StudentOps{

    protected static final String FILE_NAME = "students.txt";
    public  abstract ArrayList<Student> viewStudentSortedById();
    public abstract ArrayList<Student> searchStudent(String searchkey);
    public abstract boolean addStudent(Student s);
    public abstract boolean updateStudent(Student s);
    public abstract boolean deleteStudent(String id);
    public static void saveToFile(List<Student> students) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(FILE_NAME,true));

            for (int i = 0; i < students.size(); i++) {
                Student s = students.get(i);
                String line = s.getStudentID() + "," +
                        s.getStudentName() + "," +
                        s.getStudentAge() + "," +
                        s.getGender() + "," +
                        s.getDepartment() + "," +
                        s.getGPA();
                writer.write(line);
                writer.newLine();
            }

            System.out.println("Students saved successfully.");

        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.err.println("Error closing writer: " + e.getMessage());
            }
        }
    }
    public static List<Student> loadFromFile() {
        List<Student> students = new ArrayList<>();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String id =parts[0];
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String gender = parts[3];
                    String dept = parts[4];
                    float gpa = Float.parseFloat(parts[5]);

                    Student s = new Student(id, name, age, gender, dept, gpa);
                    students.add(s);
                }
            }

            System.out.println("Students loaded successfully: " + students.size());

        } catch (FileNotFoundException e) {
            System.err.println("File not found. It will be created when you save.");
        } catch (IOException e) {
            System.err.println("Error reading students: " + e.getMessage());
        }
        return students;
    }
}
