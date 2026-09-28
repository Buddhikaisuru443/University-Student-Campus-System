public class StackQueueTest {

    public static void main(String[] args) {

        // STACK TEST
        ActionStack actionStack = new ActionStack();

        actionStack.push(
                new Action("Added student 22UG3-0238")
        );

        actionStack.push(
                new Action("Updated student 22UG3-0238")
        );

        actionStack.push(
                new Action("Deleted student 22UG3-0238")
        );

        actionStack.displayActions();


        // QUEUE TEST
        ServiceQueue serviceQueue = new ServiceQueue();

        serviceQueue.enqueue(
                new ServiceRequest(
                        "22UG3-0238",
                        "Transcript Request"
                )
        );

        serviceQueue.enqueue(
                new ServiceRequest(
                        "22UG3-0145",
                        "Registration Request"
                )
        );

        serviceQueue.displayQueue();

        System.out.println("\nProcessing:");

        ServiceRequest request = serviceQueue.dequeue();

        if (request != null) {
            System.out.println(request);
        }
    }
}