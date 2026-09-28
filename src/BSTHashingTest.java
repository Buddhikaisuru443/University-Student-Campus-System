public class BSTHashingTest {

    public static void main(String[] args) {

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

        Student s3 = new Student(
                "22UG3-0425",
                "Pasindu Kavinda",
                "BAIT",
                68.5
        );


        // BST
        BST bst = new BST();

        bst.insert(s1);
        bst.insert(s2);
        bst.insert(s3);

        bst.displayInOrder();

        Student foundBST = bst.search("22UG3-0145");

        if (foundBST != null) {
            System.out.println("\nBST Search Result:");
            foundBST.displayStudent();
        }


        // HASHING
        StudentHashTable hashTable =
                new StudentHashTable();

        hashTable.insert(s1);
        hashTable.insert(s2);
        hashTable.insert(s3);

        Student foundHash =
                hashTable.search("22UG3-0425");

        if (foundHash != null) {
            System.out.println("\nHash Search Result:");
            foundHash.displayStudent();
        }

        hashTable.display();
    }
}