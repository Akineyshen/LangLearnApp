import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatBadOnes {
    Set<IExercise> set = new HashSet<>();
    private final Application exerciseApp = new Application();

    // Отметить неправильные задания
    public void markBadOnes(List<IExercise> exercises, List<String> userAnswers, List<String> correctAnswers) {
        for (int i = 0; i < exercises.size(); i++) {
            IExercise exercise = exercises.get(i);
            String userAnswer = userAnswers.get(i);
            String correctAnswer = correctAnswers.get(i);

            if (!userAnswer.equals(correctAnswer)) {
                set.add(exercise);
                // Сохраняем состояние каждого неправильного упражнения
                exerciseApp.setExerciseState(exercise.getState());
                exerciseApp.saveState();
            }
        }
    }

    // Повторить неправильные задания
    public void selectExerciseForm() {
        for (IExercise exercise : set) {
            System.out.println("=== Powtarzamy ćwiczenie ===");
            exercise.createExec();

            if (exercise instanceof FiszkaExercise) {
                System.out.println("Typ ćwiczenia: Fiszka");
                ((FiszkaExercise) exercise).showAnswer();
            } else if (exercise instanceof CorrectTranslationExercise) {
                System.out.println("Typ ćwiczenia: Wybierz poprawne tłumaczenie");
                ((CorrectTranslationExercise) exercise).displayOptions(); // Выводим варианты ответа
            } else if (exercise instanceof InputTranslationExercise) {
                System.out.println("Typ ćwiczenia: Wpisz tłumaczenie");
                System.out.print("Wprowadź tłumaczenie dla słowa '" + ((InputTranslationExercise) exercise).wordToTranslate + "': ");
            }
        }
    }

    // Сравниваем улучшенные ответы
    public void compareImproved(List<String> newAnswers, List<String> correctAnswers) {
        int improved = 0;
        for (int i = 0; i < newAnswers.size(); i++) {
            if (newAnswers.get(i).equals(correctAnswers.get(i))) {
                improved++;
            }
        }
        System.out.println("Poprawione odpowiedzi: " + improved);
    }
}
