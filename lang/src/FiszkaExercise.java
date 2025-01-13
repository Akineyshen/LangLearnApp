public class FiszkaExercise implements IExercise {
    private String word;
    public String translation;

    public FiszkaExercise(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    @Override
    public void createExec() {
        System.out.println("Exercise created: Card.");
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return userAnswer.equalsIgnoreCase(translation);
    }

    public void showAnswer() {
        System.out.println("Word: " + word);
        System.out.println("Translation: " + translation);
    }
}