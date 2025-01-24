public class FiszkaExercise implements IExercise {
    private String word;
    public String translation;

    public FiszkaExercise(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    @Override
    public void createExec() {
        System.out.println("Ćwiczenie utworzone: Karta.");
        showAnswer();  // Показываем ответ на карточке
    }
    @Override
    public boolean checkAnswer(String userAnswer) {
        return userAnswer.equalsIgnoreCase(translation);
    }

    public void showAnswer() {
        System.out.println("Słowo: " + word);
        System.out.println("Tłumaczenie: " + translation);
        System.out.print("Wpisz tłumaczenie słowa: ");
    }

    // Добавляем метод для сохранения состояния
    @Override
    public String getState() {
        // Сохраняем состояние в строке формата: "word;translation"
        return word + ";" + translation;
    }

    // Добавляем метод для восстановления состояния
    @Override
    public void setState(String state) {
        // Восстанавливаем состояние из строки
        String[] parts = state.split(";");
        this.word = parts[0];
        this.translation = parts[1];
    }
    public String getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
    }
}