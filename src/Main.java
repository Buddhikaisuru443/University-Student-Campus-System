import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static LinkedListStudent studentList =
            new LinkedListStudent();

    static ActionStack actionStack =
            new ActionStack();

    static ServiceQueue serviceQueue =
            new ServiceQueue();

    static BST bst =
            new BST();

    static StudentHashTable hashTable =
            new StudentHashTable();

    static CampusGraph campusGraph =
            new CampusGraph();


    public static void main(String[] args) {

        int choice;

        do {

            displayMenu();

            choice = readInt("Enter your choice: ");

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
                    studentList.displayAll();
                    break;

                case 5:
                    addServiceRequest();
                    break;

                case 6:
                    processServiceRequest();
                    break;

                case 7:
                    actionStack.displayActions();
                    break;

                case 8:
                    bst.displayInOrder();
                    break;

                case 9:
                    searchUsingHashing();
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
                    campusGraph.displayConnections();
                    break;

                case 15:
                    traverseCampus();
                    break;

                case 16:
                    System.out.println(
                            "\nThank you for using the system."
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please select 1-16."
                    );
            }

        } while (choice != 16);

        scanner.close();
    }


    // ============================
    // MENU
    // ============================

    public static void displayMenu() {

        System.out.println("\n");
        System.out.println(
                "============================================"
        );
        System.out.println(
                " UNIVERSITY STUDENT & CAMPUS SYSTEM"
        );
        System.out.println(
                "============================================"
        );

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

        System.out.println(
                "============================================"
        );
    }


    // ============================
    // STUDENT MANAGEMENT
    // ============================

    public static void addStudent() {

        System.out.println(
                "\n========== ADD STUDENT =========="
        );

        String id = readNonEmpty(
                "Enter Student ID: "
        );

        if (studentList.searchStudent(id) != null) {

            System.out.println(
                    "Student ID already exists."
            );

            return;
        }

        String name = readNonEmpty(
                "Enter Name: "
        );

        String programme = readNonEmpty(
                "Enter Programme: "
        );

        double marks = readMarks();

        Student student =
                new Student(
                        id,
                        name,
                        programme,
                        marks
                );

        studentList.addStudent(student);

        bst.insert(student);

        hashTable.insert(student);

        actionStack.push(
                new Action(
                        "Added student: " + id
                )
        );

        System.out.println(
                "Student added successfully."
        );
    }


    public static void updateStudent() {

        System.out.println(
                "\n========== UPDATE STUDENT =========="
        );

        String id = readNonEmpty(
                "Enter Student ID: "
        );

        Student student =
                studentList.searchStudent(id);

        if (student == null) {

            System.out.println(
                    "Student record not found."
            );

            return;
        }

        String name = readNonEmpty(
                "Enter New Name: "
        );

        String programme = readNonEmpty(
                "Enter New Programme: "
        );

        double marks = readMarks();

        studentList.updateStudent(
                id,
                name,
                programme,
                marks
        );

        /*
         * BST and Hash Table store the same Student object.
         * Therefore the updated values are automatically reflected.
         */

        actionStack.push(
                new Action(
                        "Updated student: " + id
                )
        );

        System.out.println(
                "Student updated successfully."
        );
    }


    public static void deleteStudent() {

        System.out.println(
                "\n========== DELETE STUDENT =========="
        );

        String id = readNonEmpty(
                "Enter Student ID: "
        );

        Student deleted =
                studentList.deleteStudent(id);

        if (deleted == null) {

            System.out.println(
                    "Student record not found."
            );

            return;
        }

        bst.delete(id);

        hashTable.delete(id);

        actionStack.push(
                new Action(
                        "Deleted student: " + id
                )
        );

        System.out.println(
                "Student deleted successfully."
        );
    }


    // ============================
    // QUEUE
    // ============================

    public static void addServiceRequest() {

        System.out.println(
                "\n========== ADD SERVICE REQUEST =========="
        );

        String studentId = readNonEmpty(
                "Enter Student ID: "
        );

        if (studentList.searchStudent(studentId) == null) {

            System.out.println(
                    "Student record not found."
            );

            return;
        }

        String requestType = readNonEmpty(
                "Enter Request Type: "
        );

        ServiceRequest request =
                new ServiceRequest(
                        studentId,
                        requestType
                );

        serviceQueue.enqueue(request);

        actionStack.push(
                new Action(
                        "Added service request for "
                        + studentId
                )
        );

        System.out.println(
                "Service request added to queue."
        );
    }


    public static void processServiceRequest() {

        System.out.println(
                "\n========== PROCESS REQUEST =========="
        );

        ServiceRequest request =
                serviceQueue.dequeue();

        if (request == null) {

            System.out.println(
                    "No pending service requests."
            );

            return;
        }

        System.out.println(
                "Processing: " + request
        );

        actionStack.push(
                new Action(
                        "Processed service request for "
                        + request.getStudentId()
                )
        );
    }


    // ============================
    // HASHING
    // ============================

    public static void searchUsingHashing() {

        System.out.println(
                "\n========== HASH SEARCH =========="
        );

        String id = readNonEmpty(
                "Enter Student ID: "
        );

        Student student =
                hashTable.search(id);

        if (student == null) {

            System.out.println(
                    "Student not found."
            );

            return;
        }

        System.out.println(
                "\nStudent found using Hashing:"
        );

        student.displayStudent();
    }


    // ============================
    // GRAPH
    // ============================

    public static void addCampusLocation() {

        System.out.println(
                "\n========== ADD CAMPUS LOCATION =========="
        );

        String location = readNonEmpty(
                "Enter location name: "
        );

        boolean added =
                campusGraph.addLocation(location);

        if (added) {

            actionStack.push(
                    new Action(
                            "Added campus location: "
                            + location
                    )
            );

            System.out.println(
                    "Campus location added successfully."
            );

        } else {

            System.out.println(
                    "Campus location already exists."
            );
        }
    }


    public static void removeCampusLocation() {

        System.out.println(
                "\n========== REMOVE CAMPUS LOCATION =========="
        );

        String location = readNonEmpty(
                "Enter location name: "
        );

        boolean removed =
                campusGraph.removeLocation(location);

        if (removed) {

            actionStack.push(
                    new Action(
                            "Removed campus location: "
                            + location
                    )
            );

            System.out.println(
                    "Campus location removed successfully."
            );

        } else {

            System.out.println(
                    "Campus location not found."
            );
        }
    }


    public static void addCampusConnection() {

        System.out.println(
                "\n========== ADD CAMPUS CONNECTION =========="
        );

        String location1 = readNonEmpty(
                "Enter first location: "
        );

        String location2 = readNonEmpty(
                "Enter second location: "
        );

        boolean added =
                campusGraph.addConnection(
                        location1,
                        location2
                );

        if (added) {

            actionStack.push(
                    new Action(
                            "Added connection: "
                            + location1
                            + " - "
                            + location2
                    )
            );

            System.out.println(
                    "Campus connection added successfully."
            );

        } else {

            System.out.println(
                    "Unable to add connection."
            );
        }
    }


    public static void removeCampusConnection() {

        System.out.println(
                "\n========== REMOVE CAMPUS CONNECTION =========="
        );

        String location1 = readNonEmpty(
                "Enter first location: "
        );

        String location2 = readNonEmpty(
                "Enter second location: "
        );

        boolean removed =
                campusGraph.removeConnection(
                        location1,
                        location2
                );

        if (removed) {

            actionStack.push(
                    new Action(
                            "Removed connection: "
                            + location1
                            + " - "
                            + location2
                    )
            );

            System.out.println(
                    "Campus connection removed successfully."
            );

        } else {

            System.out.println(
                    "Connection not found."
            );
        }
    }


    public static void traverseCampus() {

        System.out.println(
                "\n========== CAMPUS TRAVERSAL =========="
        );

        String start = readNonEmpty(
                "Enter starting location: "
        );

        System.out.println("\nBFS:");
        campusGraph.bfs(start);

        System.out.println("\nDFS:");
        campusGraph.dfs(start);
    }


    // ============================
    // INPUT VALIDATION
    // ============================

    public static String readNonEmpty(String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                    "Input cannot be empty."
            );
        }
    }


    public static int readInt(String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }


    public static double readMarks() {

        while (true) {

            System.out.print(
                    "Enter Marks (0-100): "
            );

            String input =
                    scanner.nextLine().trim();

            try {

                double marks =
                        Double.parseDouble(input);

                if (marks >= 0 && marks <= 100) {
                    return marks;
                }

                System.out.println(
                        "Marks must be between 0 and 100."
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid marks. Please enter a number."
                );
            }
        }
    }
}