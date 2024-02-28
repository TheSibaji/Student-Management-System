import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Student {
    private int id;
    private String name;
    private String grade;
    private String dateOfBirth;
    private String address;

    public Student(int id, String name, String grade, String dateOfBirth, String address) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    // Constructor, getters, and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return "Student " +
                "id: " + id +
                ", name: '" + name + '\'' +
                ", grade: " + grade + ", DOB: " + dateOfBirth + ", Address: " + address;

    }
}

class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void viewStudents() {
        students.forEach(System.out::println);
    }

    public void updateStudent(int studentId, String studentNewName, String studentNewGrade, String studentNewDOB,
            String studentNewAddress) {

        for (Student s : students) {
            if (s.getId() == studentId) {
                s.setName(studentNewName);
                s.setGrade(studentNewGrade);
                s.setDateOfBirth(studentNewDOB);
                s.setAddress(studentNewAddress);
                break;
            }
        }
    }

    public void deleteStudent(int studentId) {
        students.removeIf(s -> s.getId() == studentId);
    }

    public Student searchStudentByName(String name) {
        for (Student s : students) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }

    public Student enterStudent() {
        int studentId;
        String name;
        String grade;
        String dateOfBirth = null;
        String address;

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the studentId:");
            if (sc.hasNextInt()) {
                studentId = sc.nextInt();
                break;
            } else {
                sc.next();
                System.out.println("Invalid input. Please enter an integer.");
            }
        }

        System.out.println("Enter the name:");
        Scanner sc1 = new Scanner(System.in);
        name = sc1.nextLine();
        System.out.println("Enter the grade:");
        Scanner sc2 = new Scanner(System.in);
        grade = sc2.nextLine();

        Scanner sc3 = new Scanner(System.in);
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                System.out.println("Enter the date of birth (ddmmyyyy):");
                dateOfBirth = sc3.nextLine();
                if (isValidDateOfBirthFormat(dateOfBirth)) {
                    System.out.println("Date of birth entered: " + dateOfBirth);
                    isValidInput = true;
                } else {
                    System.out.println("Invalid date of birth format. Please enter in ddmmyyyy format.");
                }
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }

        System.out.println("Enter the address:");
        Scanner sc4 = new Scanner(System.in);
        address = sc4.nextLine();

        Student objinput = new Student(studentId, name, grade, dateOfBirth, address);
        return objinput;

    }

    private static boolean isValidDateOfBirthFormat(String dateOfBirth) {
        return dateOfBirth.length() == 8 && dateOfBirth.matches("\\d+");
    }

}


public class Project {

    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu:");
            System.out.println("1.Add Student");
            System.out.println("2.Update Student");
            System.out.println("3.Delete Student");
            System.out.println("4.View Student");
            System.out.println("5.Search Student");
            System.out.println("6.Exit");

            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Student obj = system.enterStudent();
                    system.addStudent(obj);
                    break;

                case 2:
                    System.out.println("Enter id of student");
                    Scanner sc10 = new Scanner(System.in);
                    int studentId = sc10.nextInt();

                    System.out.println("Enter new name of student:");
                    Scanner sc8 = new Scanner(System.in);
                    String studentNewName = sc8.nextLine();

                    System.out.println("Enter new grade for student :");
                    Scanner sc9 = new Scanner(System.in);
                    String studentNewGrade = sc9.nextLine();

                    System.out.println("Enter new date of birth for student :");
                    Scanner sc6 = new Scanner(System.in);
                    String newDOB = sc6.nextLine();

                    System.out.println("Enter new address for student :");
                    Scanner sc7 = new Scanner(System.in);
                    String newAddress = sc7.nextLine();

                    system.updateStudent(studentId, studentNewName, studentNewGrade, newDOB, newAddress);
                    System.out.println("\nAfter updating student details:");
                    break;

                case 3:
                    System.out.println("Enter the ID of the student to delete:");
                    Scanner deleteScanner = new Scanner(System.in);
                    int idToDelete = deleteScanner.nextInt();
                    system.deleteStudent(idToDelete);
                    System.out.println("Student with ID " + idToDelete + " deleted.");
                    break;

                case 4:
                    System.out.println("Student lists:");
                    system.viewStudents();
                    break;

                case 5:
                    System.out.println("Enter a studentName to search:");
                    Scanner sc5 = new Scanner(System.in);
                    String studentName = sc5.nextLine();

                    System.out.println("\nSearching for studentName " + studentName);
                    Student foundStudent = system.searchStudentByName(studentName);
                    System.out.println(foundStudent != null ? foundStudent : "Student not found.");
                    break;

                case 6:
                    System.out.println("Exiting program.");
                    closeScanner(scanner);
                    System.out.println("Closed!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Method to close the scanner
    private static void closeScanner(Scanner scanner) {
        if (scanner != null) {
            scanner.close();
        }
    }

}
