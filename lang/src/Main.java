import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ExerciseFactory correctFactory = new CorrectTranslationFactory(
                "dog",
                new String[]{"pies", "kot", "ptach"},
                "pies"
        );
        ExerciseFactory fiszkaFactory = new FiszkaFactory("cat", "kot");
        ExerciseFactory inputFactory = new InputTranslationFactory("cat", "kot");

        RaportCollector raportCollector = new RaportCollector();

        RepeatBadOnes repeatBadOnes = new RepeatBadOnes();

        DailyExercise dailyExercise = new DailyExercise(raportCollector);

        IExercise correctExercise = correctFactory.createExercise();
        IExercise fiszkaExercise = fiszkaFactory.createExercise();
        IExercise inputExercise = inputFactory.createExercise();

        dailyExercise.addExercise(correctExercise);
        dailyExercise.addExercise(fiszkaExercise);
        dailyExercise.addExercise(inputExercise);

        dailyExercise.startExercise();

        List<IExercise> allExercises = Arrays.asList(correctExercise, fiszkaExercise, inputExercise);
        List<String> userAnswers = new ArrayList<>();
        List<String> correctAnswers = Arrays.asList("pies", "kot", "kot");

        for (IExercise exercise : allExercises) {
            exercise.createExec();

            if (exercise instanceof CorrectTranslationExercise) {
                CorrectTranslationExercise correctEx = (CorrectTranslationExercise) exercise;
                correctEx.displayOptions();

                System.out.print("Wpisz numer swojej odpowiedzi:");
                int userChoice = scanner.nextInt();
                scanner.nextLine();

                String userAnswer = correctEx.possibleAnswers[userChoice - 1];
                userAnswers.add(userAnswer);

                boolean isCorrect = exercise.checkAnswer(userAnswer);
                dailyExercise.completeExercise(exercise, isCorrect);

                if (!isCorrect) {
                    System.out.println("Niepoprawnie. Poprawna odpowiedź: " + correctEx.correctAnswer);
                } else {
                    System.out.println("Poprawna odpowiedź!");
                }

            } else if (exercise instanceof FiszkaExercise) {
                FiszkaExercise fiszkaEx = (FiszkaExercise) exercise;
                fiszkaEx.showAnswer();

                System.out.print("Wpisz tłumaczenie słowa: ");
                String userAnswer = scanner.nextLine();
                userAnswers.add(userAnswer);

                boolean isCorrect = exercise.checkAnswer(userAnswer);
                dailyExercise.completeExercise(exercise, isCorrect);

                if (!isCorrect) {
                    System.out.println("Niepoprawnie. Poprawna odpowiedź: " + fiszkaEx.translation);
                } else {
                    System.out.println("Poprawna odpowiedź!");
                }

            } else if (exercise instanceof InputTranslationExercise) {
                InputTranslationExercise inputEx = (InputTranslationExercise) exercise;

                System.out.print("Wpisz tłumaczenie dla " + inputEx.wordToTranslate + ": ");
                String userAnswer = scanner.nextLine();
                inputEx.inputAnswer(userAnswer);
                userAnswers.add(userAnswer);

                boolean isCorrect = exercise.checkAnswer(userAnswer);
                dailyExercise.completeExercise(exercise, isCorrect);

                if (!isCorrect) {
                    System.out.println("Niepoprawnie. Poprawna odpowiedź: " + inputEx.correctAnswer);
                } else {
                    System.out.println("Poprawna odpowiedź!");
                }
            }
        }

        repeatBadOnes.markBadOnes(allExercises, userAnswers, correctAnswers);

        List<String> newAnswers = new ArrayList<>();
        System.out.println("Powtarzanie ćwiczeń z błędami:");
        for (IExercise exercise : repeatBadOnes.set) {
            exercise.createExec();

            if (exercise instanceof CorrectTranslationExercise) {
                CorrectTranslationExercise correctEx = (CorrectTranslationExercise) exercise;
                correctEx.displayOptions();

                System.out.print("Wpisz numer swojej odpowiedzi: ");
                int userChoice = scanner.nextInt();
                scanner.nextLine();

                String userAnswer = correctEx.possibleAnswers[userChoice - 1];
                newAnswers.add(userAnswer);

                if (exercise.checkAnswer(userAnswer)) {
                    System.out.println("Poprawna odpowiedź!");
                } else {
                    System.out.println("Niepoprawnie. Poprawna odpowiedź: " + correctEx.correctAnswer);
                }

            } else if (exercise instanceof FiszkaExercise) {
                FiszkaExercise fiszkaEx = (FiszkaExercise) exercise;
                fiszkaEx.showAnswer();

                System.out.print("Wpisz tłumaczenie słowa: ");
                String userAnswer = scanner.nextLine();
                newAnswers.add(userAnswer);

                if (exercise.checkAnswer(userAnswer)) {
                    System.out.println("Poprawna odpowiedź!");
                } else {
                    System.out.println("Niepoprawnie. Poprawna odpowiedź: " + fiszkaEx.translation);
                }

            } else if (exercise instanceof InputTranslationExercise) {
                InputTranslationExercise inputEx = (InputTranslationExercise) exercise;

                System.out.print("Wpisz tłumaczenie: ");
                String userAnswer = scanner.nextLine();
                newAnswers.add(userAnswer);

                if (exercise.checkAnswer(userAnswer)) {
                    System.out.println("Poprawna odpowiedź!");
                } else {
                    System.out.println("Niepoprawnie. Poprawna odpowiedź: " + inputEx.correctAnswer);
                }
            }
        }

        repeatBadOnes.compareImproved(newAnswers, correctAnswers);


        raportCollector.generateRaport();
        System.out.println("Ćwiczenia zakończone!");
    }
}
