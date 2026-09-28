public class Main {

    public static void main(String[] args) {

        LinkedListStudent students = new LinkedListStudent();

        Student s1 = new Student(
                "22UG3-0238",
                "Buddhika Isuru",
                "BAIT",
                75.5
        );

        Student s2 = new Student(
                "22UG3-0145",
                "Thisara Indunil",
                "BAIT",
                82.0
        );

        students.addStudent(s1);
        students.addStudent(s2);

        students.displayAll();

        Student found = students.searchStudent("22UG3-0238");

        if (found != null) {
            System.out.println("\nStudent Found:");
            found.displayStudent();
        }
    }
}