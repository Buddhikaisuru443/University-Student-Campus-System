import java.util.ArrayDeque;
import java.util.Deque;

public class ActionStack {

    private Deque<Action> stack;

    public ActionStack() {
        stack = new ArrayDeque<>();
    }

    // Add action
    public void push(Action action) {
        stack.push(action);
    }

    // Remove latest action
    public Action pop() {

        if (stack.isEmpty()) {
            return null;
        }

        return stack.pop();
    }

    // Display recent actions
    public void displayActions() {

        if (stack.isEmpty()) {
            System.out.println("No recent actions.");
            return;
        }

        System.out.println("\n========== RECENT ACTIONS ==========");

        for (Action action : stack) {
            System.out.println("- " + action);
        }
    }
}