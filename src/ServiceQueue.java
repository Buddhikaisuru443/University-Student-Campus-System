import java.util.LinkedList;
import java.util.Queue;

public class ServiceQueue {

    private Queue<ServiceRequest> queue;

    public ServiceQueue() {
        queue = new LinkedList<>();
    }

    // Add request
    public void enqueue(ServiceRequest request) {
        queue.offer(request);
    }

    // Process first request
    public ServiceRequest dequeue() {

        if (queue.isEmpty()) {
            return null;
        }

        return queue.poll();
    }

    // Display queue
    public void displayQueue() {

        if (queue.isEmpty()) {
            System.out.println("No pending service requests.");
            return;
        }

        System.out.println("\n========== SERVICE REQUEST QUEUE ==========");

        for (ServiceRequest request : queue) {
            System.out.println(request);
        }
    }
}