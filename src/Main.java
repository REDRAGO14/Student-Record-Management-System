import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main{
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        StudentController studentController = new StudentController();


        studentController.loadFromBinaryFile();
        for(Student s: studentController.getHighestGpa()){
            int id = s.getStudentId();
            String name = s.getName();
            String dept = s.getDepartment();
            double gpa = s.getGPA();
            System.out.println(name + " (ID: " + id + ") from " + dept + " has the highest GPA: " + gpa);
        }
        boolean running = true;
        while(running) {
            System.out.println("\n=============Student Record Management System===================");
            System.out.println("1, Add Student ");
            System.out.println("2, Search Student ");
            System.out.println("3, Update Student Info ");
            System.out.println("4, Delete Student ");
            System.out.println("5, View All Student ");
            System.out.println("6, view Student Report");
            System.out.println("7, View File Metadata");
            System.out.println("8, Exit");
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

                        studentController.addStudent(new Student(id, name, department, gpa));

                    }
                    studentController.saveToTextFile(studentController.studentList);
                    studentController.saveToSerializedObject(studentController.studentList);
                    studentController.saveToBinaryFile(studentController.studentList);
                } else if (choice == 2) {
                    System.out.print("Enter the id of student to search: ");
                    int stdId = scanner.nextInt();
                    scanner.nextLine();

                    studentController.searchStudent(stdId);
                } else if (choice == 3) {
                    System.out.print("Enter Student Id you want to update: ");
                    int stdId = scanner.nextInt();
                    scanner.nextLine();

                    studentController.updateStudentInfo(stdId);

                    studentController.saveToTextFile(studentController.studentList);
                    studentController.saveToSerializedObject(studentController.studentList);
                    studentController.saveToBinaryFile(studentController.studentList);
                } else if (choice == 4) {
                    System.out.print("Enter the id of the student you want to delete: ");
                    int stdId = scanner.nextInt();
                    scanner.nextLine();

                    studentController.deleteStudent(stdId);

                    studentController.saveToTextFile(studentController.studentList);
                    studentController.saveToSerializedObject(studentController.studentList);
                    studentController.saveToBinaryFile(studentController.studentList);
                } else if (choice == 5) {
                    studentController.displayAllStudent();
                } else if (choice == 6) {
                    System.out.println("    1, Highest GPA");
                    System.out.println("    2, Lowest GPA");
                    System.out.println("    3, Average GPA");
                    System.out.println("    4, Total Student");
                    System.out.print("What do you want to view: ");
                    int reportChoice = scanner.nextInt();
                    scanner.nextLine();
                    if(reportChoice == 1){
                        List<Student> highestGpaStudents = studentController.getHighestGpa();
                        if(highestGpaStudents != null){
                            for(Student s: highestGpaStudents){
                                System.out.println(s.getName() +" from " + s.getDepartment()+ " department has scored: " + s.getGPA());
                            }
                        }
                    }else if(reportChoice == 2){
                        List<Student> lowestGpaStudent = studentController.getLowestGpa();
                        if(lowestGpaStudent != null){
                            for(Student s: lowestGpaStudent){
                                System.out.println(s.getName() +" from " + s.getDepartment()+ " department has scored: " + s.getGPA());
                            }
                        }
                    } else if (reportChoice == 3) {
                        System.out.println("the average GPA is: " + studentController.getAverageGpa());
                    } else if (reportChoice == 4) {
                        studentController.getTotalStudent();
                    }
                } else if (choice == 7) {
                    System.out.print("Enter File Path: ");
                    String filePath = scanner.next();
                    studentController.displayFileProperties(filePath);
                } else if (choice == 8) {
                    studentController.b
                } else if (choice == 9) {
                    System.out.println("Thank you for using our app:)");
                    running = false;
                }


            }
        }
    }
