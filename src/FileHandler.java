import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class FileHandler <T> {

    protected static final String FILE_NAME = "students.txt";
    public static void saveToFile(List<Student> students) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(FILE_NAME));

            for (int i = 0; i < students.size(); i++) {
                Student s = students.get(i);
                String line = s.getId() + "," +
                        s.getName() + "," +
                        s.getAge() + "," +
                        s.getGender() + "," +
                        s.getDepartment() + "," +
                        s.getGpa();
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
                System.err.println("⚠️ Error closing writer: " + e.getMessage());
            }
        }
    }

}
