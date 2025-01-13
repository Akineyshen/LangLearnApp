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
            System.out.println("Ładowanie ćwiczenia z pamięci podręcznej: " + cacheKey);
        } else {
            this.exercise = factory.createExercise();
            cache.put(cacheKey, this.exercise);
            System.out.println("Tworzenie nowego ćwiczenia i zapisywanie go w pamięci podręcznej: " + cacheKey);
        }
    }

    @Override
    public void createExec() {
        exercise.createExec();
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return exercise.checkAnswer(userAnswer);
    }

    public static void clearCache() {
        cache.clear();
        System.out.println("Pamięć podręczna została wyczyszczona.");
    }
}
