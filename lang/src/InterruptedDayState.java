public class InterruptedDayState implements State {
    private DailyExercise dailyExercise;

    public InterruptedDayState(DailyExercise dailyExercise) {
        this.dailyExercise = dailyExercise;
    }

    @Override
    public void handle() {
        System.out.println("The day was interrupted. The exercises are not completed.");
    }
}
