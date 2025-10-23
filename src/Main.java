import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter student name: ");
        String name1 = sc.nextLine();
        System.out.println("enter student id: ");
        String id1 = sc.nextLine();
        System.out.println("enter student age: ");
        int age1 = sc.nextInt();
        sc.nextLine();
        System.out.println("enter student gender: ");
        String gender1 = sc.nextLine();
        System.out.println("enter student department: ");
        String department1 = sc.nextLine();
        System.out.println("enter student GPA: ");
        float gpa1 = sc.nextFloat();
        sc.nextLine();

        Student s1 = new Student(id1, name1, age1, gender1, department1, gpa1);

        //Scanner sc = new Scanner(System.in);
        System.out.println("enter student name: ");
        String name2 = sc.nextLine();
        System.out.println("enter student id: ");
        String id2 = sc.nextLine();
        System.out.println("enter student age: ");
        int age2 = sc.nextInt();
        sc.nextLine();
        System.out.println("enter student gender: ");
        String gender2 = sc.nextLine();
        System.out.println("enter student department: ");
        String department2 = sc.nextLine();
        System.out.println("enter student GPA: ");
        float gpa2 = sc.nextFloat();
        sc.nextLine();

        Student s2 = new Student(id2, name2, age2, gender2, department2, gpa2);

        StudentManager students = new StudentManager();
        students.addStudent(s1);
        students.addStudent(s2);

        students.LogOut();
    }
}