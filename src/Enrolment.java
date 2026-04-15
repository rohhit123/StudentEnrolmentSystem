import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Enrolment Class
 * Represents a single enrolment linking a Student to a Course.
 * Demonstrates object composition (a core OOP concept) by holding
 * references to both a Student and a Course object.
 *  * Author: Rohit Dahal
 *  * Student ID: S2400613
 *  * ITS206 – Assessment B
 */
public class Enrolment {

    // Private attributes (encapsulation)
    private Student student;       // Composition: reference to a Student object
    private Course  course;        // Composition: reference to a Course object
    private String  enrolmentDate;
    private boolean isWaitlisted;  // Extension feature: tracks waitlist status

    /**
     * Constructor — creates an Enrolment and automatically sets today's date.
     * @param student      The student being enrolled
     * @param course       The course to enrol into
     * @param isWaitlisted Whether this enrolment is on the waitlist
     */
    public Enrolment(Student student, Course course, boolean isWaitlisted) {
        this.student       = student;
        this.course        = course;
        this.isWaitlisted  = isWaitlisted;
        // Automatically capture today's date
        this.enrolmentDate = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public Student getStudent()       { return student; }
    public Course  getCourse()        { return course; }
    public String  getEnrolmentDate() { return enrolmentDate; }
    public boolean isWaitlisted()     { return isWaitlisted; }

    // ── Setters ──────────────────────────────────────────────────────────────

    public void setStudent(Student student)           { this.student = student; }
    public void setCourse(Course course)              { this.course = course; }
    public void setEnrolmentDate(String date)         { this.enrolmentDate = date; }
    public void setWaitlisted(boolean isWaitlisted)   { this.isWaitlisted = isWaitlisted; }

    /**
     * Factory-style method: attempts to create an enrolment for a student.
     * If the course is full, the student is added to the waitlist instead.
     *
     * @param student The student to enrol
     * @param course  The target course
     * @return A new Enrolment object (either confirmed or waitlisted)
     */
    public static Enrolment createEnrolment(Student student, Course course) {
        if (course.hasAvailableSpot()) {
            course.incrementEnrolment();
            System.out.println("✔  " + student.getName()
                    + " successfully enrolled in " + course.getCourseName() + ".");
            return new Enrolment(student, course, false);
        } else {
            // Extension Feature: Waitlist — course is full, add to waitlist
            course.addToWaitlist(student);
            return new Enrolment(student, course, true);
        }
    }

    /**
     * Displays the enrolment details to the console.
     */
    public void displayEnrolmentDetails() {
        String status = isWaitlisted ? "WAITLISTED" : "CONFIRMED";
        System.out.println("┌──────────────────────────────────────┐");
        System.out.println("│          ENROLMENT DETAILS           │");
        System.out.println("├──────────────────────────────────────┤");
        System.out.println("│ Student  : " + student.getName()
                + " (" + student.getStudentID() + ")");
        System.out.println("│ Course   : " + course.getCourseName()
                + " (" + course.getCourseCode() + ")");
        System.out.println("│ Date     : " + enrolmentDate);
        System.out.println("│ Status   : " + status);
        System.out.println("└──────────────────────────────────────┘");
    }

    /**
     * Returns a string representation of the enrolment.
     */
    @Override
    public String toString() {
        return "Enrolment[" + student.getName() + " -> "
                + course.getCourseCode()
                + " | " + enrolmentDate
                + " | " + (isWaitlisted ? "Waitlisted" : "Confirmed") + "]";
    }
}
