public class Student {
    private String studentID;
    private String studentName;
    private int studentAge;
    private String gender;
    private String department;
    private float GPA;

    public Student(String studentID, String studentName, int studentAge, String gender, String department, float GPA){
        setStudentID(studentID);
        setStudentName(studentName);
        setStudentAge(studentAge);
        setDepartment(department);
        setGender(gender);
        setGPA(GPA);
    }

    public void setStudentID(String studentID){
        this.studentID = studentID;
    }

    public void setStudentName(String studentName){
        if(!studentName.matches("[a-zA-Z]+") || studentName.isEmpty()){
            System.out.println("false name format!");
            this.studentName = " ";
        }
        else{
            this.studentName = studentName;
        }
    }

    public void setStudentAge(int studentAge){
        if(studentAge > 0){
            this.studentAge = studentAge;
        }
        else{
            System.out.println("Age can't be negative!");
            this.studentAge = 0;
        }
    }

    public void setGender(String gender){
        if(gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")){
            this.gender = gender;
        }
        else{
            System.out.println("invalid gender");
            this.gender = "female";
        }
    }

    public void setDepartment(String department){
        this.department = department;
    }

    public void setGPA(float GPA){
        if(GPA > 4 || GPA < 0){
            System.out.println("invalid GPA!");
            this.GPA = 0;
        }
        else{
            this.GPA = GPA;
        }
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
}
