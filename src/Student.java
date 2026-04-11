/**
 * Student Class
 * Represents a student in the enrolment system.
 * Demonstrates encapsulation using private attributes with getters and setters.
 *  * Author: Rohit Dahal
 *  * Student ID: S2400613
 *  * ITS206 – Assessment B
 */
public class Student {

    // Private attributes (encapsulation)
    private String studentID;
    private String name;
    private String email;

    /**
     * Constructor to initialise a Student object.
     * @param studentID Unique student identifier
     * @param name      Full name of the student
     * @param email     Email address of the student
     */
    public Student(String studentID, String name, String email) {
        this.studentID = studentID;
        this.name = name;
        this.email = email;
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public String getStudentID() { return studentID; }
    public String getName()      { return name; }
    public String getEmail()     { return email; }

    // ── Setters ──────────────────────────────────────────────────────────────

    public void setStudentID(String studentID) { this.studentID = studentID; }
    public void setName(String name)           { this.name = name; }
    public void setEmail(String email)         { this.email = email; }

    /**
     * Displays the student's information to the console.
     */
    public void displayStudentInfo() {
        System.out.println("┌─────────────────────────────┐");
        System.out.println("│        STUDENT DETAILS      │");
        System.out.println("├─────────────────────────────┤");
        System.out.println("│ ID    : " + studentID);
        System.out.println("│ Name  : " + name);
        System.out.println("│ Email : " + email);
        System.out.println("└─────────────────────────────┘");
    }

    /**
     * Returns a string representation of the student.
     */
    @Override
    public String toString() {
        return "Student[" + studentID + " | " + name + " | " + email + "]";
    }
}
