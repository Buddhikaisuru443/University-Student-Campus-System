public class LinkedListStudent {

    private StudentNode head;

    // Add a student
    public boolean addStudent(Student student) {

        if (searchStudent(student.getStudentId()) != null) {
            return false;
        }

        StudentNode newNode = new StudentNode(student);

        if (head == null) {
            head = newNode;
            return true;
        }

        StudentNode current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;

        return true;
    }

    // Search student by ID
    public Student searchStudent(String studentId) {

        StudentNode current = head;

        while (current != null) {

            if (current.data.getStudentId().equalsIgnoreCase(studentId)) {
                return current.data;
            }

            current = current.next;
        }

        return null;
    }

    // Update student
    public boolean updateStudent(
            String studentId,
            String name,
            String programme,
            double marks) {

        Student student = searchStudent(studentId);

        if (student == null) {
            return false;
        }

        student.setName(name);
        student.setProgramme(programme);
        student.setMarks(marks);

        return true;
    }

    // Delete student
    public Student deleteStudent(String studentId) {

        if (head == null) {
            return null;
        }

        // If first node
        if (head.data.getStudentId().equalsIgnoreCase(studentId)) {

            Student deletedStudent = head.data;
            head = head.next;

            return deletedStudent;
        }

        StudentNode current = head;

        while (current.next != null) {

            if (current.next.data.getStudentId()
                    .equalsIgnoreCase(studentId)) {

                Student deletedStudent = current.next.data;

                current.next = current.next.next;

                return deletedStudent;
            }

            current = current.next;
        }

        return null;
    }

    // Display all students
    public void displayAll() {

        if (head == null) {
            System.out.println("No student records found.");
            return;
        }

        StudentNode current = head;

        System.out.println("\n========== STUDENT RECORDS ==========");

        while (current != null) {

            current.data.displayStudent();

            System.out.println("-------------------------------------");

            current = current.next;
        }
    }
}