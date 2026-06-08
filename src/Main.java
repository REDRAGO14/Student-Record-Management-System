import java.util.Scanner;

class Main{
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        StudentController studentController = new StudentController();
        studentController.loadFromTextFile();

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

                        studentController.addStudent(new Student(id, name, department, gpa));

                    }
                    studentController.saveToTextFile(studentController.studentList);
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
                } else if (choice == 4) {
                    System.out.print("Enter the id of the student you want to delete: ");
                    int stdId = scanner.nextInt();
                    scanner.nextLine();

                    studentController.deleteStudent(stdId);

                    studentController.saveToTextFile(studentController.studentList);
                } else if (choice == 5) {
                    studentController.displayAllStudent();
                }

                 else if (choice == 6) {
                    System.out.println("Thank you for using our app:)");
                    running = false;
                }


            }
        }
    }
