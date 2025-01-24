import java.util.Stack;

public class Application {
    public String exerciseState;
    private final Stack<Memento> mementoHistory = new Stack<>();

    public void setExerciseState(String state) {
        this.exerciseState = state;
    }

    public String getExerciseState() {
        return exerciseState;
    }

    public void saveState() {
        mementoHistory.push(new Memento(exerciseState));
        System.out.println("State saved: " + exerciseState);
    }

    public void restoreState() {
        if (!mementoHistory.isEmpty()) {
            Memento memento = mementoHistory.pop();
            exerciseState = memento.getExerciseState();
            System.out.println("State restored: " + exerciseState);
        } else {
            System.out.println("No states to restore!");
        }
    }
}
