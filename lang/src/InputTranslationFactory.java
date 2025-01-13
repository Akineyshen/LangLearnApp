public class InputTranslationFactory extends ExerciseFactory {
    private final String correctAnswer;
    private final String wordToTranslate;

    public InputTranslationFactory(String correctAnswer, String wordToTranslate) {
        this.correctAnswer = correctAnswer;
        this.wordToTranslate = wordToTranslate;
    }

    @Override
    public IExercise createExercise() {
        return new InputTranslationExercise(wordToTranslate, correctAnswer);
    }
}