public class BST {

    private BSTNode root;

    // Insert student
    public void insert(Student student) {
        root = insertRecursive(root, student);
    }

    private BSTNode insertRecursive(BSTNode node, Student student) {

        if (node == null) {
            return new BSTNode(student);
        }

        int comparison = student.getStudentId()
                .compareToIgnoreCase(node.data.getStudentId());

        if (comparison < 0) {

            node.left = insertRecursive(node.left, student);

        } else if (comparison > 0) {

            node.right = insertRecursive(node.right, student);

        }

        return node;
    }

    // Search student
    public Student search(String studentId) {

        BSTNode current = root;

        while (current != null) {

            int comparison = studentId
                    .compareToIgnoreCase(current.data.getStudentId());

            if (comparison == 0) {
                return current.data;
            }

            if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    // Delete student
    public void delete(String studentId) {
        root = deleteRecursive(root, studentId);
    }

    private BSTNode deleteRecursive(
            BSTNode node,
            String studentId) {

        if (node == null) {
            return null;
        }

        int comparison = studentId
                .compareToIgnoreCase(node.data.getStudentId());

        if (comparison < 0) {

            node.left = deleteRecursive(node.left, studentId);

        } else if (comparison > 0) {

            node.right = deleteRecursive(node.right, studentId);

        } else {

            // No child
            if (node.left == null && node.right == null) {
                return null;
            }

            // Only right child
            if (node.left == null) {
                return node.right;
            }

            // Only left child
            if (node.right == null) {
                return node.left;
            }

            // Two children
            BSTNode successor = findMinimum(node.right);

            node.data = successor.data;

            node.right = deleteRecursive(
                    node.right,
                    successor.data.getStudentId()
            );
        }

        return node;
    }

    private BSTNode findMinimum(BSTNode node) {

        BSTNode current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    // In-order traversal
    public void displayInOrder() {

        if (root == null) {
            System.out.println("BST is empty.");
            return;
        }

        System.out.println("\n========== BST STUDENTS ==========");

        inOrder(root);
    }

    private void inOrder(BSTNode node) {

        if (node != null) {

            inOrder(node.left);

            System.out.println(
                    node.data.getStudentId()
                    + " | "
                    + node.data.getName()
                    + " | "
                    + node.data.getProgramme()
                    + " | "
                    + node.data.getMarks()
            );

            inOrder(node.right);
        }
    }
}