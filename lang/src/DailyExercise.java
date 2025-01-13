import java.util.HashSet;
import java.util.Set;

public class DailyExercise {
    private State currentState;
    private Set<IExercise> exercises;
    private RaportCollector raportCollector;

    public DailyExercise(RaportCollector raportCollector) {
        this.raportCollector = raportCollector;
        this.exercises = new HashSet<>();
        this.currentState = new StartedDayState(this); // Начальное состояние
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
            raportCollector.incrementImproved(); // Увеличить количество правильных ответов
        }
        raportCollector.incrementExercises(); // Увеличить общее количество упражнений

        if (exercises.isEmpty()) {
            setState(new FinishedExercisesState(this)); // Переход в состояние завершения
        }
    }

    public void interruptDay() {
        setState(new InterruptedDayState(this)); // Переход в состояние "день прерван"
        currentState.handle(); // Обработка состояния
    }

}
