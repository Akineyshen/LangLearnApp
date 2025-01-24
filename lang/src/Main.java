import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Создаём упражнения
        FiszkaFactory fiszkaFactory = new FiszkaFactory("dog", "pies");
        ProxyExercise fiszkaProxy = new ProxyExercise("fiszka1", fiszkaFactory);

        CorrectTranslationFactory correctTranslationFactory = new CorrectTranslationFactory(
                "tree",
                new String[]{"drzewo", "las", "gałąź"},
                "drzewo"
        );
        ProxyExercise correctTranslationProxy = new ProxyExercise("correctTranslation1", correctTranslationFactory);

        InputTranslationFactory inputTranslationFactory = new InputTranslationFactory("drzewo", "tree");
        ProxyExercise inputTranslationProxy = new ProxyExercise("inputTranslation1", inputTranslationFactory);

        // Список всех упражнений
        List<IExercise> exercises = new ArrayList<>();
        exercises.add(fiszkaProxy);
        exercises.add(correctTranslationProxy);
        exercises.add(inputTranslationProxy);

        // Списки ответов
        List<String> userAnswers = new ArrayList<>();
        List<String> correctAnswers = new ArrayList<>();
        correctAnswers.add("pies"); // Правильный ответ для fiszka
        correctAnswers.add("drzewo"); // Правильный ответ для correctTranslation
        correctAnswers.add("drzewo"); // Правильный ответ для inputTranslation

        // Создаем объект для сбора отчетов
        RaportCollector raportCollector = new RaportCollector();

        // Создаем объект для управления состоянием упражнений с использованием Memento
        Application exerciseApp = new Application();

        // Проходим упражнения
        System.out.println("=== Rozpoczynamy ćwiczenia ===");
        for (IExercise exercise : exercises) {
            exercise.createExec();

            if (exercise instanceof FiszkaExercise) {
                System.out.println("Typ ćwiczenia: Fiszka");
                ((FiszkaExercise) exercise).showAnswer();
            } else if (exercise instanceof CorrectTranslationExercise) {
                System.out.println("Typ ćwiczenia: Wybierz poprawne tłumaczenie");
                ((CorrectTranslationExercise) exercise).displayOptions(); // Выводим варианты ответа
            } else if (exercise instanceof InputTranslationExercise) {
                System.out.println("Typ ćwiczenia: Wpisz tłumaczenie");
                System.out.print("Wprowadź tłumaczenie для słowa '" + ((InputTranslationExercise) exercise).wordToTranslate + "': ");
            }

            String userAnswer = scanner.nextLine();
            userAnswers.add(userAnswer);
            boolean isCorrect = exercise.checkAnswer(userAnswer);
            System.out.println("Twój odpowiedź: " + userAnswer);
            System.out.println("Odpowiedź poprawna? " + isCorrect);
            System.out.println("-----------------------------");

            // Собираем отчет о каждом упражнении
            raportCollector.incrementExercises();
            if (isCorrect) {
                raportCollector.incrementImproved();
            }
            raportCollector.collectReport(exercise, isCorrect);

            // Сохраняем состояние упражнения
            exerciseApp.setExerciseState(exercise.getState());

            // Запрос на сохранение состояния
            System.out.println("Wprowadź 'save' aby zapisać stan ćwiczenia, 'restore' aby восстановить stan:");
            String command = scanner.nextLine();
            if (command.equals("save")) {
                exerciseApp.saveState();
            } else if (command.equals("restore")) {
                exerciseApp.restoreState();
            }
        }

        // Отслеживание неправильных ответов
        RepeatBadOnes repeatBadOnes = new RepeatBadOnes();
        repeatBadOnes.markBadOnes(exercises, userAnswers, correctAnswers);

        // Повторяем упражнения с ошибками
        System.out.println("\n=== Powtarzamy ćwiczenia z błędami ===");
        repeatBadOnes.selectExerciseForm();

        // Проверяем, улучшились ли результаты
        System.out.println("\nWprowadź nowe odpowiedzi dla powtórzonych ćwiczeń:");
        List<String> newAnswers = new ArrayList<>();
        for (IExercise exercise : repeatBadOnes.set) {
            if (exercise instanceof InputTranslationExercise) {
                System.out.print("Wprowadź tłumaczenie dla słowa '" + ((InputTranslationExercise) exercise).wordToTranslate + "': ");
            }
            newAnswers.add(scanner.nextLine());
        }

        repeatBadOnes.compareImproved(newAnswers, correctAnswers);

        // Генерация отчета
        raportCollector.generateRaport();
    }
}
