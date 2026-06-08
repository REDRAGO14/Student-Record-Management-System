import java.io.*;
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

    public void saveToTextFile(ArrayList<Student> studentList){
        File dir = new File("data");
        if(!dir.exists()){
            dir.mkdirs();
        }

        File file = new File(dir,"students.txt");

        try(PrintWriter writer = new PrintWriter(file)){
            for(Student s: studentList){
                writer.println(s.getStudentId() + "," + s.getName() + "," + s.getDepartment() + "," + s.getGPA());
            }
        }catch (IOException e){
            System.out.println("Error saving data" + e.getMessage());
        }
    }

    public ArrayList<Student> loadFromTextFile(){
        ArrayList<Student> studentList = new ArrayList<>();
        File file = new File("data/students.txt");

        if(!file.exists()){
            return studentList;
        }

        try(Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();

                if(line.trim().isEmpty()) continue;

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String department = data[2];
                double gpa = Double.parseDouble(data[3]);

                Student student = new Student(id, name, department, gpa);
                studentList.add(student);
            }
        }catch (IOException e){
            System.out.println("Error saving data" + e.getMessage());
        }
        this.studentList = studentList;
        return studentList;
    }

    public void saveToSerializedObject(ArrayList<Student> studentList){
        File file = new File("data/students.ser");

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(studentList);
            System.out.println("data successfully serialized to binary object file!");
        }catch (IOException e){
            System.out.println("Error Serializing data" + e.getMessage());
        }
    }

    public ArrayList<Student> loadFromSerializedObject(){
        File file = new File("data/students.ser");
        if(!file.exists()){
            return new ArrayList<>();
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){

            ArrayList<Student> studentList = (ArrayList<Student>) ois.readObject();
            this.studentList = studentList;
            return studentList;
        }catch (IOException | ClassNotFoundException e){
            System.out.println("Deserialization Error: " + e.getMessage());
            return new ArrayList<>();
        }

    }

    public ArrayList<Student> getStudentList(){
        return studentList;
    }
}
