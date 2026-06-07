import java.util.ArrayList;
import java.util.Scanner;

class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        StudentController studentController = new StudentController();
        //defualt student dumy data
        Student s1 = new Student(1, "DAGIM", "SOFTWARE", 4);

        studentController.addStudent(s1);
        studentController.addStudent(new Student(2, "Yosef", "SOFTWARE", 3.8));



        boolean running = true;
        while(running) {
            System.out.println("\n=============Student Record Management System===================");
            System.out.println("1, Add Student ");
            System.out.println("2, Search Student ");
            System.out.println("3, Update Student Info ");
            System.out.println("4, Delete Student ");
            System.out.println("5, View All Student ");
            System.out.println("6, Exit");
            System.out.print("Enter Your Choice: ");
            int choice = scanner.nextInt();

                if(choice == 1) {
                    System.out.print("How Many Student do you want to add?: ");
                    int nos = scanner.nextInt();
                    for (int i = 1; i <= nos; i++) {
                        System.out.print("Enter studentId: ");
                        int id = scanner.nextInt();
                        System.out.print("Enter studentName: ");
                        String name = scanner.next();
                        System.out.print("Enter studentDepartment: ");
                        String department = scanner.next();
                        System.out.print("Enter studentGPA: ");
                        double gpa = scanner.nextDouble();

                        Student newStudent = new Student(id, name, department, gpa);
                        studentController.addStudent(newStudent);
                    }
                }
                else if (choice == 5) {
                    ArrayList<Student> allStudent = studentController.studentList;

                    // Header
                    System.out.println("\n=====================================================================");
                    System.out.printf("%-10s | %-20s | %-20s | %-5s%n", "ID", "Name", "Department", "GPA");
                    System.out.println("=====================================================================");

                    // Data Rows
                    for (Student s : allStudent) {
                        System.out.printf("%-10d | %-20s | %-20s | %-5.2f%n",
                                s.getStudentId(),
                                s.getName(),
                                s.getDepartment(),
                                s.getGPA()
                        );
                    }
                    System.out.println("=====================================================================");
                }

                 else if (choice == 6) {
                    System.out.println("Thank you for using our app:)");
                    running = false;
                }


            }
        }
    }
