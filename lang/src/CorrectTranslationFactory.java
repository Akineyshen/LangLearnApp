public class CorrectTranslationFactory extends ExerciseFactory {
    private String word;
    private String[] possibleAnswers;
    private String correctAnswer;

    public CorrectTranslationFactory(String word, String[] possibleAnswers, String correctAnswer) {
        this.word = word;
        this.possibleAnswers = possibleAnswers;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public IExercise createExercise() {
        return new CorrectTranslationExercise(word, possibleAnswers, correctAnswer);
    }
}