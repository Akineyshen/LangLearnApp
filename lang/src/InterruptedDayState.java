public class InterruptedDayState implements State {
    @Override
    public void handle(DailyExercise context) {
        context.interruptExercise();
        // Можно вернуться в любое состояние или завершить
    }
}
