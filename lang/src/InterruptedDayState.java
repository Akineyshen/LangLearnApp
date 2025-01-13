public class InterruptedDayState implements State {
    private DailyExercise dailyExercise;

    public InterruptedDayState(DailyExercise dailyExercise) {
        this.dailyExercise = dailyExercise;
    }

    @Override
    public void handle() {
        System.out.println("Dzień został przerwany. Ćwiczenia nie zostały ukończone.");
    }
}
