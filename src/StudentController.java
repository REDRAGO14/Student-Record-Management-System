import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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
        try{
        if (!file.exists()) {
           file.createNewFile();
        }}catch (IOException e){
            System.out.println(e.getMessage());
        }
        try(PrintWriter writer = new PrintWriter(file)){
            for(Student s: studentList){
                writer.println(s.getStudentId() + "," + s.getName() + "," + s.getDepartment() + "," + s.getGPA());
            }
        }catch (IOException e){
            System.out.println("Error saving data" + e.getMessage());
        }
    }


    public  ArrayList<Student> loadFromTextFile(){
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

    public void saveToBinaryFile(ArrayList<Student> studentList){
        File file = new File("data/students.dat");

        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))){
            dos.writeInt(studentList.size());

            for(Student s: studentList){
                dos.writeInt(s.getStudentId());
                dos.writeUTF(s.getName());
                dos.writeUTF(s.getDepartment());
                dos.writeDouble(s.getGPA());
            }
            System.out.println("data successfully saved into binary file with primitive type!");
        }catch (IOException e){
            System.out.println("Encoding Error: " + e.getMessage());
        }
    }

    public ArrayList<Student> loadFromBinaryFile(){
        ArrayList<Student> loadedStudent = new ArrayList<>();
        File file = new File("data/students.dat");
        if(!file.exists()){
            return new ArrayList<>();
        }

        try(DataInputStream dis = new DataInputStream(new FileInputStream(file))){


            int listLength = dis.readInt();

            for(int i = 0; i < listLength; i++){
                int id = dis.readInt();
                String name = dis.readUTF();
                String department = dis.readUTF();
                double gpa = dis.readDouble();

                loadedStudent.add(new Student(id, name, department, gpa));
            }

        }catch (IOException e){
            System.out.println("Binary Read Error: " + e.getMessage());
        }
        studentList = loadedStudent;
        return loadedStudent;
    }

    public void getTotalStudent(){
        int totalStudents = studentList.size();
        System.out.println("Total Students: " + totalStudents);
    }

    public List<Student> getHighestGpa(){
        double highestGpa = studentList.stream()
                .mapToDouble(Student::getGPA)
                .max()
                .orElse(0.00);
        return studentList.stream()
                .filter(s -> s.getGPA() == highestGpa)
                .collect(Collectors.toList());
    }

    public List<Student> getLowestGpa(){
        double lowestGpa = studentList.stream()
                .mapToDouble(Student::getGPA)
                .min()
                .orElse(0.00);
        return studentList.stream()
                .filter(s -> s.getGPA() == lowestGpa)
                .collect(Collectors.toList());
    }

    public double getAverageGpa(){
        double sum = 0;
        for(Student s: studentList){
            sum += s.getGPA();
        }
        return sum / studentList.size();
    }

    public  void displayFileProperties(String filePath) {
        System.out.println(filePath);
        File file = new File(filePath);

        System.out.println("\n=====  FILE METADATA  =====");

        if (file.exists()) {

            System.out.println("File Name:        " + file.getName());


            System.out.println("Absolute Path:    " + file.getAbsolutePath());


            System.out.println("File Size:        " + file.length() + " bytes");


            Date lastModifiedDate = new Date(file.lastModified());
            System.out.println("Last Modified:    " + lastModifiedDate);


            System.out.println("Is Directory?     " + file.isDirectory());
        } else {
            System.out.println("❌ Cannot display properties. File does not exist at: " + filePath);
        }
        System.out.println("=========================================\n");
    }

    public static void createBackup() {
        File sourceFile = new File("data/students.dat");
        File backupFile = new File("data/students_backup.dat");

        if (!sourceFile.exists()) {
            System.out.println("⚠️ Backup aborted: Source data file does not exist yet.");
            return;
        }try (
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(backupFile))
        ) {

            byte[] buffer = new byte[1024];
            int bytesRead;


            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            System.out.println("🚀 Backup created successfully using Buffered Streams!");
            System.out.println("Original Size: " + sourceFile.length() + " bytes | Backup Size: " + backupFile.length() + " bytes");

        } catch (IOException e) {
            System.out.println("❌ Critical Error during backup process: " + e.getMessage());
        }
    }

    public ArrayList<Student> getStudentList(){
        return studentList;
    }
}
