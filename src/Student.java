public class Student {
    private String studentID;
    private String studentName;
    private int studentAge;
    private String gender;
    private String department;
    private float GPA;

    public Student(String studentID, String studentName, int studentAge, String gender, String department, float GPA) {
        StringBuilder errors = new StringBuilder();

        try {
            setStudentID(studentID);
        } catch (IllegalArgumentException e) {
            errors.append("• ").append(e.getMessage()).append("\n");
        }

        try {
            setStudentName(studentName);
        } catch (IllegalArgumentException e) {
            errors.append("• ").append(e.getMessage()).append("\n");
        }

        try {
            setStudentAge(studentAge);
        } catch (IllegalArgumentException e) {
            errors.append("• ").append(e.getMessage()).append("\n");
        }

        try {
            setGender(gender);
        } catch (IllegalArgumentException e) {
            errors.append("• ").append(e.getMessage()).append("\n");
        }

        try {
            setDepartment(department);
        } catch (IllegalArgumentException e) {
            errors.append("• ").append(e.getMessage()).append("\n");
        }

        try {
            setGPA(GPA);
        } catch (IllegalArgumentException e) {
            errors.append("• ").append(e.getMessage()).append("\n");
        }

        if (errors.length() > 0) {
            throw new IllegalArgumentException(errors.toString());
        }
    }


    public void setStudentID(String studentID){
        if(studentID.isEmpty() || studentID == null){
            throw new IllegalArgumentException("Student ID can't be left empty!");
        }
        this.studentID = studentID;
    }

    public void setStudentName(String studentName){
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty!");
        } else if (!studentName.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Invalid name format! Letters and spaces only.");
        }
        this.studentName = studentName.trim();
    }

    public void setStudentAge(int studentAge){
        if(studentAge <= 0){
            throw new IllegalArgumentException("Age must be positive!");
        }
        this.studentAge = studentAge;
    }

    public void setGender(String gender){
        if(!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")){
            throw new IllegalArgumentException("Invalid gender! Choose Male or Female.");
        }
        this.gender = gender;
    }

    public void setDepartment(String department){
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be empty!");
        }
        this.department = department;
    }

    public void setGPA(float GPA){
        if (GPA < 0 || GPA > 4) {
            throw new IllegalArgumentException("GPA must be between 0.0 and 4.0!");
        }
        this.GPA = GPA;
    }

    public String getStudentID(){
        return studentID;
    }

    public String getStudentName(){
        return studentName;
    }

    public String getGender(){
        return gender;
    }

    public String getDepartment(){
        return department;
    }

    public int getStudentAge(){
        return studentAge;
    }

    public float getGPA(){
        return GPA;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + ", Name: " + studentName +
                ", Age: " + studentAge + ", Gender: " + gender +
                ", Department: " + department + ", GPA: " + GPA;
    }
}
