import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatBadOnes {
    public Set<IExercise> set; // Набор упражнений
    public List<String> incorrectUserAnswers; // Неправильные ответы пользователя

    public RepeatBadOnes() {
        this.set = new HashSet<>();
        this.incorrectUserAnswers = new java.util.ArrayList<>();
    }


    public void markBadOnes(List<IExercise> allExercises, List<String> userAnswers, List<String> correctAnswers) {
        for (int i = 0; i < allExercises.size(); i++) {
            if (!userAnswers.get(i).equalsIgnoreCase(correctAnswers.get(i))) {
                set.add(allExercises.get(i));
                incorrectUserAnswers.add(userAnswers.get(i));
            }
        }
    }

    // Select exercise type (example)
    public void selectExerciseForm() {
        for (IExercise exercise : set) {
            exercise.createExec();
        }
    }

    // Compare improvements
    public void compareImproved(List<String> newAnswers, List<String> correctAnswers) {
        int improvedCount = 0;

        for (int i = 0; i < newAnswers.size(); i++) {
            String newAnswer = newAnswers.get(i);

            // If the new answer matches the correct one, it is considered corrected
            if (newAnswer.equalsIgnoreCase(correctAnswers.get(i))) {
                improvedCount++;
            }
        }

        System.out.println("Corrected answers: " + improvedCount);
    }

}
