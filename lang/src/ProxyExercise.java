import java.util.HashMap;
import java.util.Map;

class ProxyExercise implements IExercise {
    public IExercise exercise;
    private static Map<String, IExercise> cache = new HashMap<>();
    private String cacheKey;

    public ProxyExercise(String cacheKey, ExerciseFactory factory) {
        this.cacheKey = cacheKey;
        if (cache.containsKey(cacheKey)) {
            this.exercise = cache.get(cacheKey);
            System.out.println("Loading an exercise from cache: " + cacheKey);
        } else {
            this.exercise = factory.createExercise();
            cache.put(cacheKey, this.exercise);
            System.out.println("Creating a new exercise and saving it to cache: " + cacheKey);
        }
    }

    @Override
    public void createExec() {
        exercise.createExec();
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return exercise.checkAnswer(userAnswer); // Delegation of response verification
    }

    public static void clearCache() {
        cache.clear();
        System.out.println("The cache has been cleared.");
    }
}
