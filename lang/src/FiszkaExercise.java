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
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return userAnswer.equalsIgnoreCase(translation);
    }

    public void showAnswer() {
        System.out.println("Słowo: " + word);
        System.out.println("Tłumaczenie: " + translation);
    }
}