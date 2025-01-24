public class CorrectTranslationExercise implements IExercise {
    private String word;
    public String[] possibleAnswers;
    public String correctAnswer;

    public CorrectTranslationExercise(String word, String[] possibleAnswers, String correctAnswer) {
        this.word = word;
        this.possibleAnswers = possibleAnswers;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public void createExec() {
        System.out.println("Ćwiczenie utworzone: Wybierz poprawne tłumaczenie.");
        displayOptions();  // Выводим варианты ответа
    }

    public String getWordToTranslate() {
        return word;
    }

    public String[] getPossibleAnswers() {
        return possibleAnswers;
    }

    public void displayOptions() {
        System.out.println("Wybierz tłumaczenie dla słowa: " + word);
        for (int i = 0; i < possibleAnswers.length; i++) {
            System.out.println((i + 1) + ". " + possibleAnswers[i]);
        }
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return userAnswer.equalsIgnoreCase(correctAnswer);
    }

    // Добавляем метод для сохранения состояния
    @Override
    public String getState() {
        // Сохраняем состояние в строке формата: "word;correctAnswer"
        return word + ";" + correctAnswer;
    }

    // Добавляем метод для восстановления состояния
    @Override
    public void setState(String state) {
        // Восстанавливаем состояние из строки
        String[] parts = state.split(";");
        this.word = parts[0];
        this.correctAnswer = parts[1];
    }
}