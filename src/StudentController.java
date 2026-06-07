import java.util.ArrayList;
import java.util.Scanner;

public class StudentController {
    ArrayList<Student> studentList;


    public StudentController() {
        studentList = new ArrayList<>();
    }

    public void addStudent(Student student){
        studentList.add(student);
        System.out.println("Student Added Successfully✅");
    }

    public ArrayList<Student> getStudentList(){return studentList;}
}
