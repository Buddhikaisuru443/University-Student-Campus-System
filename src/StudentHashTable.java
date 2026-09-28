public class StudentHashTable {

    private static final int TABLE_SIZE = 31;

    private HashEntry[] table;

    private static class HashEntry {

        String key;
        Student value;
        HashEntry next;

        HashEntry(String key, Student value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public StudentHashTable() {
        table = new HashEntry[TABLE_SIZE];
    }

    // Hash function
    private int hash(String key) {

        int hashValue = 0;

        for (int i = 0; i < key.length(); i++) {
            hashValue = 31 * hashValue + key.charAt(i);
        }

        return Math.abs(hashValue) % TABLE_SIZE;
    }

    // Insert
    public void insert(Student student) {

        int index = hash(student.getStudentId());

        HashEntry current = table[index];

        while (current != null) {

            if (current.key.equalsIgnoreCase(
                    student.getStudentId())) {

                current.value = student;
                return;
            }

            current = current.next;
        }

        HashEntry newEntry = new HashEntry(
                student.getStudentId(),
                student
        );

        newEntry.next = table[index];
        table[index] = newEntry;
    }

    // Search
    public Student search(String studentId) {

        int index = hash(studentId);

        HashEntry current = table[index];

        while (current != null) {

            if (current.key.equalsIgnoreCase(studentId)) {
                return current.value;
            }

            current = current.next;
        }

        return null;
    }

    // Delete
    public void delete(String studentId) {

        int index = hash(studentId);

        HashEntry current = table[index];
        HashEntry previous = null;

        while (current != null) {

            if (current.key.equalsIgnoreCase(studentId)) {

                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                return;
            }

            previous = current;
            current = current.next;
        }
    }

    // Display hash table
    public void display() {

        System.out.println("\n========== HASH TABLE ==========");

        for (int i = 0; i < TABLE_SIZE; i++) {

            HashEntry current = table[i];

            if (current != null) {

                System.out.print("Index " + i + ": ");

                while (current != null) {

                    System.out.print(
                            current.key + " -> "
                    );

                    current = current.next;
                }

                System.out.println("NULL");
            }
        }
    }
}