public class FiszkaFactory extends ExerciseFactory {
    private String word;
    private String translation;

    public FiszkaFactory(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    @Override
    public IExercise createExercise() {
        return new FiszkaExercise(word, translation);
    }
}