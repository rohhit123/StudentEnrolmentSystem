import java.util.ArrayList;

/**
 * This is the main class that runs the Student Enrolment System.
 * It creates students and courses, enrolls them, and shows
 * how the waitlist works when a course is full.
 *
 * Author: Rohit Dahal
 * Student ID: S2400613
 * ITS206 – Assessment B
 * Demonstrates:
 *  *  - Object creation (Student, Course, Enrolment)
 *  *  - Encapsulation via getters/setters
 *  *  - Object composition (Enrolment holds Student + Course references)
 *  *  - Extension Feature: Waitlist system when a course is full (odd student ID)
 *  *
 */
public class Main {

    public static void main(String[] args) {

        printSectionHeader("STUDENT ENROLMENT SYSTEM — ITS206");

        // ── 1. Create Students ────────────────────────────────────────────────
        printSectionHeader("1. CREATING STUDENTS");

        Student s1 = new Student("S2400373", "Jeevan Ghimire",  "jeevan@email.com");
        Student s2 = new Student("S2400613", "Rohit Dahal",      "rohit@email.com");
        Student s3 = new Student("S2400005", "Niranjan Thapa",    "niranjan@email.com");  // extra for waitlist demo

        s1.displayStudentInfo();
        s2.displayStudentInfo();
        s3.displayStudentInfo();

        // ── 2. Create Courses ─────────────────────────────────────────────────
        printSectionHeader("2. CREATING COURSES");

        // maxCapacity of 2 is set deliberately so that the waitlist can be demonstrated
        Course c1 = new Course("ITS206", "Software Construction and Design", 2);
        Course c2 = new Course("ITS320", "Capstone Experience",      3);

        c1.displayCourseDetails();
        c2.displayCourseDetails();

        // ── 3. Enrol Students ─────────────────────────────────────────────────
        printSectionHeader("3. ENROLLING STUDENTS");

        ArrayList<Enrolment> enrolments = new ArrayList<>();

        // Enrol Alice into ITS206 — should succeed (spot 1 of 2)
        enrolments.add(Enrolment.createEnrolment(s1, c1));

        // Enrol Bob into ITS206 — should succeed (spot 2 of 2)
        enrolments.add(Enrolment.createEnrolment(s2, c1));

        // ── Extension Feature: Waitlist Demo ──────────────────────────────────
        // Enrol Carol into ITS206 — course is now FULL → goes to waitlist
        printSectionHeader("EXTENSION FEATURE: WAITLIST SYSTEM");
        System.out.println("Attempting to enrol Carol into ITS206 (which is already full)...");
        enrolments.add(Enrolment.createEnrolment(s3, c1));

        // Show the waitlist for ITS206
        System.out.println();
        c1.displayWaitlist();

        // Enrol Alice and Bob into ITS101 as well
        printSectionHeader("CONTINUING ENROLMENTS");
        enrolments.add(Enrolment.createEnrolment(s1, c2));
        enrolments.add(Enrolment.createEnrolment(s2, c2));

        // ── 4. Display All Enrolments ─────────────────────────────────────────
        printSectionHeader("4. ALL ENROLMENT RECORDS");

        for (Enrolment e : enrolments) {
            e.displayEnrolmentDetails();
        }

        // ── 5. Updated Course Details ─────────────────────────────────────────
        printSectionHeader("5. UPDATED COURSE STATUS");
        c1.displayCourseDetails();
        c2.displayCourseDetails();

        // ── 6. Simulate a Spot Opening — Waitlist Promotion ───────────────────
        printSectionHeader("6. SIMULATING A SPOT OPENING IN ITS206");
        System.out.println("Alice withdraws from ITS206...");
        c1.decrementEnrolment();
        System.out.println("Checking waitlist for available promotion...");
        Student promoted = c1.promoteFromWaitlist();

        if (promoted != null) {
            // Update the waitlisted enrolment record to CONFIRMED
            for (Enrolment e : enrolments) {
                if (e.getStudent().equals(promoted) && e.isWaitlisted()) {
                    e.setWaitlisted(false);
                    System.out.println("Enrolment record updated to CONFIRMED.");
                    e.displayEnrolmentDetails();
                }
            }
        }

        // ── Final Course Status ───────────────────────────────────────────────
        printSectionHeader("FINAL COURSE STATUS");
        c1.displayCourseDetails();
        c2.displayCourseDetails();

        System.out.println("\n✔  Enrollment process completed successfully. Have a good day! \n");
    }

    /**
     * Utility method to print a formatted section header.
     * @param title The header text to display
     */
    private static void printSectionHeader(String title) {
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("  " + title);
        System.out.println("----------------------------------------------");
    }
}
