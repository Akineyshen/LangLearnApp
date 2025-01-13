public class StartedDayState implements State {
    private DailyExercise dailyExercise;

    public StartedDayState(DailyExercise dailyExercise) {
        this.dailyExercise = dailyExercise;
    }

    @Override
    public void handle() {
        System.out.println("The day has begun. You can do the exercises.");
    }
}
