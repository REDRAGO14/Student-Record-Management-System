import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class StudentController {
    ArrayList<Student> studentList;
    Scanner scanner = new Scanner(System.in);


    public StudentController() {
        studentList = new ArrayList<>();
    }

    public void addStudent(Student student){
        studentList.add(student);
        System.out.println("Student Added Successfully✅");
    }

    public void deleteStudent(int stdId){
        Optional<Student> foundStudent = studentList.stream()
                .filter(s -> s.getStudentId() == stdId)
                .findFirst();
        if(foundStudent.isPresent()){
            Student studentsToDelete = foundStudent.get();
            studentList.remove(studentsToDelete);
            System.out.println("⚠️Student is removed");
        }else{
            System.out.println("Student Not Founded");
        }
    }

    public  void displayAllStudent(){
        System.out.println("\n=====================================================================");
        System.out.printf("%-10s | %-20s | %-20s | %-5s%n", "ID", "Name", "Department", "GPA");
        System.out.println("=====================================================================");

        // Data Rows
        for (Student s : studentList) {
            System.out.printf("%-10d | %-20s | %-20s | %-5.2f%n",
                    s.getStudentId(),
                    s.getName(),
                    s.getDepartment(),
                    s.getGPA()
            );
        }
        System.out.println("=====================================================================");

    }

    public void updateStudentInfo(int stdId){
        Optional<Student> foundStudent = studentList.stream()
                .filter(s -> s.getStudentId() == stdId)
                .findFirst();
        if(foundStudent.isPresent()){
            Student studentToUpdate = foundStudent.get();
            System.out.println("Student Found: " + studentToUpdate.getName());
            System.out.println("1. Update There Department");
            System.out.println("2. Update There GPA");
            System.out.print("choose which to modify: ");
            int Modifychoice = scanner.nextInt();
            scanner.nextLine();

            if(Modifychoice == 1){
                System.out.print("Enter new Department: ");
                String newdepartment = scanner.nextLine();

                studentToUpdate.setDepartment(newdepartment);
                System.out.println("Department has been Updated Successfully");
            } else if (Modifychoice == 2) {
                System.out.println("Enter new GPA: ");
                double newGPA = scanner.nextDouble();
                scanner.nextLine();

                studentToUpdate.setGPA(newGPA);
                System.out.println("GPA has been updated Successfully");
            }

        }else{
            System.out.println("Student is Not Found!");
        }
    }

    public void searchStudent(int stdId){
        Optional<Student> foundStudent = studentList.stream()
                .filter(s -> s.getStudentId() == stdId)
                .findFirst();

        if(foundStudent.isPresent()){
            System.out.println("======================Founded Student============================\n");
            System.out.printf("%-10s | %-20s | %-20s | %-5s%n", "ID", "Name", "Department", "GPA");
            System.out.println("------------------------------------------------------------------");
            Student s = foundStudent.get();
            System.out.printf("%-10d | %-20s | %-20s | %-5.2f%n",
                    s.getStudentId(),
                    s.getName(),
                    s.getDepartment(),
                    s.getGPA()
            );
        }
    }


}
