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
        System.out.println("Exercise created: Enter the translation of the word." );
    }

    // Method to save user input
    public void inputAnswer(String input) {
        this.userInput = input; // Сохранение ввода пользователя
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        // Check if the answer is correct
        return userAnswer.equalsIgnoreCase(correctAnswer);
    }
}