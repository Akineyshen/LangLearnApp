public class StartedDayState implements State {
    @Override
    public void handle(DailyExercise context) {
        context.startExercise();
        context.setState(new FinishedExerciseState()); // После начала упражнения можно завершить
    }
}
