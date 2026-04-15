import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Course Class
 * Represents a course available for enrolment.
 * Demonstrates encapsulation and includes a waitlist system (extension feature).
 *  * Author: Rohit Dahal
 *  * Student ID: S2400613
 *  * ITS206 – Assessment B
 */
public class Course {

    // Private attributes (encapsulation)
    private String courseCode;
    private String courseName;
    private int maxCapacity;
    private int currentEnrolments;

    // ── Extension Feature: Waitlist (for odd student IDs) ────────────────────
    // A Queue is used because waitlists are first-in-first-out (FIFO)
    private Queue<Student> waitlist;

    /**
     * Constructor to initialise a Course object.
     * @param courseCode    Unique course identifier (e.g., "ITS206")
     * @param courseName    Full name of the course
     * @param maxCapacity   Maximum number of students allowed
     */
    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseCode        = courseCode;
        this.courseName        = courseName;
        this.maxCapacity       = maxCapacity;
        this.currentEnrolments = 0;
        this.waitlist          = new LinkedList<>();
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public String getCourseCode()     { return courseCode; }
    public String getCourseName()     { return courseName; }
    public int    getMaxCapacity()    { return maxCapacity; }
    public int    getCurrentEnrolments() { return currentEnrolments; }

    // ── Setters ──────────────────────────────────────────────────────────────

    public void setCourseCode(String courseCode)   { this.courseCode = courseCode; }
    public void setCourseName(String courseName)   { this.courseName = courseName; }
    public void setMaxCapacity(int maxCapacity)    { this.maxCapacity = maxCapacity; }

    // ── Capacity Helpers ─────────────────────────────────────────────────────

    /**
     * Checks whether the course has available spots.
     * @return true if seats are available, false if the course is full
     */
    public boolean hasAvailableSpot() {
        return currentEnrolments < maxCapacity;
    }

    /**
     * Increments the current enrolment count by one.
     */
    public void incrementEnrolment() {
        currentEnrolments++;
    }

    /**
     * Decrements the enrolment count when a student leaves.
     */
    public void decrementEnrolment() {
        if (currentEnrolments > 0) currentEnrolments--;
    }

    // ── Extension Feature: Waitlist Methods ──────────────────────────────────

    /**
     * Adds a student to the waitlist when the course is full.
     * @param student The student to add to the waitlist
     */
    public void addToWaitlist(Student student) {
        waitlist.offer(student);
        System.out.println("⚠  Course full! " + student.getName()
                + " has been added to the waitlist for " + courseName + ".");
        System.out.println("   Waitlist position: " + waitlist.size());
    }

    /**
     * Promotes the next student from the waitlist into the course.
     * Called automatically when a spot becomes available.
     * @return The next student from the waitlist, or null if the waitlist is empty
     */
    public Student promoteFromWaitlist() {
        if (!waitlist.isEmpty()) {
            Student next = waitlist.poll();
            currentEnrolments++;
            System.out.println("✔  A spot opened up in " + courseName
                    + ". " + next.getName() + " has been promoted from the waitlist!");
            return next;
        }
        return null;
    }

    /**
     * Displays the current waitlist for this course.
     */
    public void displayWaitlist() {
        if (waitlist.isEmpty()) {
            System.out.println("   No students on the waitlist for " + courseName + ".");
        } else {
            System.out.println("   Waitlist for " + courseName + ":");
            int position = 1;
            for (Student s : waitlist) {
                System.out.println("   " + position++ + ". " + s.getName()
                        + " (" + s.getStudentID() + ")");
            }
        }
    }

    /**
     * Returns the number of students on the waitlist.
     */
    public int getWaitlistSize() { return waitlist.size(); }

    /**
     * Displays the course details to the console.
     */
    public void displayCourseDetails() {
        int spotsLeft = maxCapacity - currentEnrolments;
        System.out.println("┌─────────────────────────────────┐");
        System.out.println("│          COURSE DETAILS         │");
        System.out.println("├─────────────────────────────────┤");
        System.out.println("│ Code      : " + courseCode);
        System.out.println("│ Name      : " + courseName);
        System.out.println("│ Capacity  : " + maxCapacity);
        System.out.println("│ Enrolled  : " + currentEnrolments);
        System.out.println("│ Spots Left: " + spotsLeft);
        System.out.println("│ Waitlist  : " + waitlist.size() + " student(s)");
        System.out.println("└─────────────────────────────────┘");
    }

    /**
     * Returns a string representation of the course.
     */
    @Override
    public String toString() {
        return "Course[" + courseCode + " | " + courseName
                + " | Capacity: " + maxCapacity + "]";
    }
}
