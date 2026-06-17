import java.io.Serializable;

public class Student  implements Serializable {
    private int studentId;
    private String name;
    private String department;
    private double GPA;

    public Student(){}
    public Student(int studentId, String name, String department, double GPA){
        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.GPA = GPA;
    }

    public int getStudentId() {return studentId;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getDepartment() {return department;}
    public void setDepartment(String department) {this.department = department;}

    public double getGPA() {return GPA;}
    public void setGPA(double GPA) {this.GPA = GPA;}


}
