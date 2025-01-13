import java.util.HashSet;
import java.util.Set;

public class DailyExercise {
    private State currentState;
    private Set<IExercise> exercises;
    private RaportCollector raportCollector;

    public DailyExercise(RaportCollector raportCollector) {
        this.raportCollector = raportCollector;
        this.exercises = new HashSet<>();
        this.currentState = new StartedDayState(this);
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public void addExercise(IExercise exercise) {
        exercises.add(exercise);
    }

    public void startExercise() {
        currentState.handle();
    }

    public void completeExercise(IExercise exercise, boolean isCorrect) {
        exercises.remove(exercise);
        if (isCorrect) {
            raportCollector.incrementImproved();
        }
        raportCollector.incrementExercises();

        if (exercises.isEmpty()) {
            setState(new FinishedExercisesState(this));
        }
    }

    public void interruptDay() {
        setState(new InterruptedDayState(this));
        currentState.handle();
    }

}
