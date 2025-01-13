public class InputTranslationExercise implements IExercise {
    private String userInput;
    public String correctAnswer;
    public String wordToTranslate;

    public InputTranslationExercise(String correctAnswer, String wordToTranslate) {
        this.correctAnswer = correctAnswer;
        this.wordToTranslate = wordToTranslate;
    }

    @Override
    public void createExec() {
        System.out.println("Ćwiczenie utworzone: Wpisz tłumaczenie słowa." );
    }

    public void inputAnswer(String input) {
        this.userInput = input;
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return userAnswer.equalsIgnoreCase(correctAnswer);
    }
}