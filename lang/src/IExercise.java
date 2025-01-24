public interface IExercise {
    void createExec();
    boolean checkAnswer(String answer);
    String getState(); // Сохранить состояние
    void setState(String state); // Восстановить состояние
}
