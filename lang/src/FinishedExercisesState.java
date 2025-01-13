public class FinishedExercisesState implements State {
    private DailyExercise dailyExercise;

    public FinishedExercisesState(DailyExercise dailyExercise) {
        this.dailyExercise = dailyExercise;
    }

    @Override
    public void handle() {
        System.out.println("All exercises are completed. The day is over.");
    }
}
