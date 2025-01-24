public class DailyExercise {
    private State currentState;

    public DailyExercise() {
        this.currentState = new StartedDayState(); // Начинаем с начального состояния
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public void handle() {
        currentState.handle(this); // Передаем управление состоянию
    }

    public void startExercise() {
        System.out.println("Ćwiczenie rozpoczęte!");
    }

    public void interruptExercise() {
        System.out.println("Ćwiczenie przerwane!");
    }

    public void finishExercise() {
        System.out.println("Ćwiczenie zakończone!");
    }
}
