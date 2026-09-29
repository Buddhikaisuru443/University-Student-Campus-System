import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    // Data Structures
    private static LinkedListStudent studentList = new LinkedListStudent();
    private static ActionStack actionStack = new ActionStack();
    private static ServiceQueue serviceQueue = new ServiceQueue();
    private static BST bst = new BST();
    private static StudentHashTable hashTable = new StudentHashTable();
    private static CampusGraph campusGraph = new CampusGraph();

    public static void main(String[] args) {

        int choice;

        // First menu display
        do {
            displayMenu();

            choice = readInt("Enter your choice: ");

            System.out.println();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    updateStudent();
                    break;

                case 3:
                    deleteStudent();
                    break;

                case 4:
                    displayAllStudents();
                    break;

                case 5:
                    addServiceRequest();
                    break;

                case 6:
                    processServiceRequest();
                    break;

                case 7:
                    displayRecentActions();
                    break;

                case 8:
                    displayStudentsBST();
                    break;

                case 9:
                    searchStudentUsingHashing();
                    break;

                case 10:
                    addCampusLocation();
                    break;

                case 11:
                    removeCampusLocation();
                    break;

                case 12:
                    addCampusConnection();
                    break;

                case 13:
                    removeCampusConnection();
                    break;

                case 14:
                    displayCampusConnections();
                    break;

                case 15:
                    traverseCampus();
                    break;

                case 16:
                    System.out.println("============================================");
                    System.out.println(" Thank you for using the system.");
                    System.out.println(" System exited successfully.");
                    System.out.println("============================================");
                    break;

                default:
                    System.out.println("Invalid choice.");
                    System.out.println("Please select a number between 1 and 16.");
            }

            // Do not show this after Exit
            if (choice != 16) {
                pressEnterToReturn();
            }

        } while (choice != 16);

        scanner.close();
    }


    // ============================================================
    // MAIN MENU
    // ============================================================

    private static void displayMenu() {

        System.out.println();
        System.out.println("============================================");
        System.out.println(" UNIVERSITY STUDENT & CAMPUS SYSTEM");
        System.out.println("============================================");
        System.out.println("1. Add Student Record");
        System.out.println("2. Update Student Record");
        System.out.println("3. Delete Student Record");
        System.out.println("4. Display All Records using Linked List");
        System.out.println("5. Add Service Request to Queue");
        System.out.println("6. Process Next Service Request");
        System.out.println("7. Display Recent Actions using Stack");
        System.out.println("8. Display Students using BST");
        System.out.println("9. Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus using BFS/DFS");
        System.out.println("16. Exit");
        System.out.println("============================================");
    }


    // ============================================================
    // OPTION 1 - ADD STUDENT
    // ============================================================

    private static void addStudent() {

        System.out.println("========== ADD STUDENT ==========");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Programme: ");
        String programme = scanner.nextLine();

        double marks = readDouble("Enter Marks (0-100): ");

        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks. Marks must be between 0 and 100.");
            return;
        }

        Student student = new Student(
                studentId,
                name,
                programme,
                marks
        );

        boolean added = studentList.addStudent(student);

        if (added) {

            // Add to BST
            bst.insert(student);

            // Add to Hash Table
            hashTable.insert(student);

            // Add action to Stack
            actionStack.push(
                    new Action("Added student: " + studentId)
            );

            System.out.println("Student added successfully.");

        } else {
            System.out.println("Student ID already exists.");
        }
    }


    // ============================================================
    // OPTION 2 - UPDATE STUDENT
    // ============================================================

    private static void updateStudent() {

        System.out.println("========== UPDATE STUDENT ==========");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        Student existingStudent =
                studentList.searchStudent(studentId);

        if (existingStudent == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println();
        System.out.println("Current Student Details:");
        existingStudent.displayStudent();

        System.out.println();

        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter New Programme: ");
        String programme = scanner.nextLine();

        double marks = readDouble("Enter New Marks (0-100): ");

        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks. Marks must be between 0 and 100.");
            return;
        }

        boolean updated =
                studentList.updateStudent(
                        studentId,
                        name,
                        programme,
                        marks
                );

        if (updated) {

            // Update hash table
            hashTable.insert(existingStudent);

            // Action stack
            actionStack.push(
                    new Action("Updated student: " + studentId)
            );

            System.out.println("Student updated successfully.");

        } else {
            System.out.println("Student update failed.");
        }
    }


    // ============================================================
    // OPTION 3 - DELETE STUDENT
    // ============================================================

    private static void deleteStudent() {

        System.out.println("========== DELETE STUDENT ==========");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        Student deletedStudent =
                studentList.deleteStudent(studentId);

        if (deletedStudent != null) {

            bst.delete(studentId);
            hashTable.delete(studentId);

            actionStack.push(
                    new Action("Deleted student: " + studentId)
            );

            System.out.println("Student deleted successfully.");

        } else {
            System.out.println("Student not found.");
        }
    }


    // ============================================================
    // OPTION 4 - DISPLAY LINKED LIST
    // ============================================================

    private static void displayAllStudents() {

        System.out.println("========== DISPLAY ALL STUDENTS ==========");

        studentList.displayAll();
    }


    // ============================================================
    // OPTION 5 - ADD SERVICE REQUEST
    // ============================================================

    private static void addServiceRequest() {

        System.out.println("========== ADD SERVICE REQUEST ==========");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter Request Type: ");
        String requestType = scanner.nextLine();

        ServiceRequest request =
                new ServiceRequest(
                        studentId,
                        requestType
                );

        serviceQueue.enqueue(request);

        actionStack.push(
                new Action(
                        "Added service request for: "
                        + studentId
                )
        );

        System.out.println("Service request added to queue successfully.");
    }


    // ============================================================
    // OPTION 6 - PROCESS SERVICE REQUEST
    // ============================================================

    private static void processServiceRequest() {

        System.out.println("========== PROCESS SERVICE REQUEST ==========");

        ServiceRequest request =
                serviceQueue.dequeue();

        if (request == null) {
            System.out.println("No pending service requests.");
            return;
        }

        System.out.println("Processing request:");
        System.out.println(request);

        actionStack.push(
                new Action(
                        "Processed service request for: "
                        + request.getStudentId()
                )
        );

        System.out.println("Service request processed successfully.");
    }


    // ============================================================
    // OPTION 7 - DISPLAY RECENT ACTIONS
    // ============================================================

    private static void displayRecentActions() {

        System.out.println("========== DISPLAY RECENT ACTIONS ==========");

        actionStack.displayActions();
    }


    // ============================================================
    // OPTION 8 - DISPLAY BST
    // ============================================================

    private static void displayStudentsBST() {

        System.out.println("========== DISPLAY STUDENTS USING BST ==========");

        bst.displayInOrder();
    }


    // ============================================================
    // OPTION 9 - SEARCH USING HASHING
    // ============================================================

    private static void searchStudentUsingHashing() {

        System.out.println("========== SEARCH STUDENT USING HASHING ==========");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        Student student =
                hashTable.search(studentId);

        if (student != null) {

            System.out.println();
            System.out.println("Student found:");
            student.displayStudent();

        } else {
            System.out.println("Student not found.");
        }
    }


    // ============================================================
    // OPTION 10 - ADD CAMPUS LOCATION
    // ============================================================

    private static void addCampusLocation() {

        System.out.println("========== ADD CAMPUS LOCATION ==========");

        System.out.print("Enter Location Name: ");
        String location = scanner.nextLine();

        boolean added =
                campusGraph.addLocation(location);

        if (added) {

            actionStack.push(
                    new Action(
                            "Added campus location: "
                            + location
                    )
            );

            System.out.println("Campus location added successfully.");

        } else {
            System.out.println("Campus location already exists.");
        }
    }


    // ============================================================
    // OPTION 11 - REMOVE CAMPUS LOCATION
    // ============================================================

    private static void removeCampusLocation() {

        System.out.println("========== REMOVE CAMPUS LOCATION ==========");

        System.out.print("Enter Location Name: ");
        String location = scanner.nextLine();

        boolean removed =
                campusGraph.removeLocation(location);

        if (removed) {

            actionStack.push(
                    new Action(
                            "Removed campus location: "
                            + location
                    )
            );

            System.out.println("Campus location removed successfully.");

        } else {
            System.out.println("Campus location not found.");
        }
    }


    // ============================================================
    // OPTION 12 - ADD CAMPUS CONNECTION
    // ============================================================

    private static void addCampusConnection() {

        System.out.println("========== ADD CAMPUS CONNECTION / ROAD ==========");

        System.out.print("Enter First Location: ");
        String location1 = scanner.nextLine();

        System.out.print("Enter Second Location: ");
        String location2 = scanner.nextLine();

        boolean added =
                campusGraph.addConnection(
                        location1,
                        location2
                );

        if (added) {

            actionStack.push(
                    new Action(
                            "Added road: "
                            + location1
                            + " - "
                            + location2
                    )
            );

            System.out.println("Campus connection added successfully.");

        } else {
            System.out.println(
                    "Unable to add connection."
            );
            System.out.println(
                    "Make sure both locations exist and the connection is not duplicated."
            );
        }
    }


    // ============================================================
    // OPTION 13 - REMOVE CAMPUS CONNECTION
    // ============================================================

    private static void removeCampusConnection() {

        System.out.println("========== REMOVE CAMPUS CONNECTION / ROAD ==========");

        System.out.print("Enter First Location: ");
        String location1 = scanner.nextLine();

        System.out.print("Enter Second Location: ");
        String location2 = scanner.nextLine();

        boolean removed =
                campusGraph.removeConnection(
                        location1,
                        location2
                );

        if (removed) {

            actionStack.push(
                    new Action(
                            "Removed road: "
                            + location1
                            + " - "
                            + location2
                    )
            );

            System.out.println("Campus connection removed successfully.");

        } else {
            System.out.println("Campus connection not found.");
        }
    }


    // ============================================================
    // OPTION 14 - DISPLAY CAMPUS CONNECTIONS
    // ============================================================

    private static void displayCampusConnections() {

        System.out.println("========== DISPLAY CAMPUS CONNECTIONS ==========");

        campusGraph.displayConnections();
    }


    // ============================================================
    // OPTION 15 - BFS / DFS
    // ============================================================

    private static void traverseCampus() {

        System.out.println("========== CAMPUS TRAVERSAL ==========");

        System.out.print("Enter Starting Location: ");
        String startLocation = scanner.nextLine();

        System.out.println();

        System.out.println("1. BFS");
        System.out.println("2. DFS");

        int traversalChoice =
                readInt("Enter traversal choice: ");

        System.out.println();

        if (traversalChoice == 1) {

            campusGraph.bfs(startLocation);

        } else if (traversalChoice == 2) {

            campusGraph.dfs(startLocation);

        } else {

            System.out.println("Invalid traversal choice.");
        }
    }


    // ============================================================
    // PRESS ENTER
    // ============================================================

    private static void pressEnterToReturn() {

        System.out.println();
        System.out.println("Press ENTER to return to the menu...");
        scanner.nextLine();
    }


    // ============================================================
    // READ INTEGER
    // ============================================================

    private static int readInt(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }


    // ============================================================
    // READ DOUBLE
    // ============================================================

    private static double readDouble(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine();

            try {

                return Double.parseDouble(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a valid number."
                );
            }
        }
    }
}