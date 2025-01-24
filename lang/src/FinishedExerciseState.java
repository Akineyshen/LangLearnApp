public class FinishedExerciseState implements State {
    @Override
    public void handle(DailyExercise context) {
        context.finishExercise();
        // Можно вернуться в начальное состояние или завершить
    }
}
