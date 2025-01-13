import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create exercise factories
        ExerciseFactory correctFactory = new CorrectTranslationFactory(
                "dog",
                new String[]{"pies", "kot", "ptach"},
                "pies"
        );
        ExerciseFactory fiszkaFactory = new FiszkaFactory("cat", "kot");
        ExerciseFactory inputFactory = new InputTranslationFactory("cat", "kot");

        // Create a report collector
        RaportCollector raportCollector = new RaportCollector();

        // Create an object to repeat incorrect answers
        RepeatBadOnes repeatBadOnes = new RepeatBadOnes();

        // Create a daily exercise
        DailyExercise dailyExercise = new DailyExercise(raportCollector);

        // Create exercises through factories and add them to DailyExercise
        IExercise correctExercise = correctFactory.createExercise();
        IExercise fiszkaExercise = fiszkaFactory.createExercise();
        IExercise inputExercise = inputFactory.createExercise();

        dailyExercise.addExercise(correctExercise);
        dailyExercise.addExercise(fiszkaExercise);
        dailyExercise.addExercise(inputExercise);

        // Start doing the exercises
        dailyExercise.startExercise();

        List<IExercise> allExercises = Arrays.asList(correctExercise, fiszkaExercise, inputExercise);
        List<String> userAnswers = new ArrayList<>();
        List<String> correctAnswers = Arrays.asList("pies", "kot", "kot");

        for (IExercise exercise : allExercises) {
            exercise.createExec();

            if (exercise instanceof CorrectTranslationExercise) {
                CorrectTranslationExercise correctEx = (CorrectTranslationExercise) exercise;
                correctEx.displayOptions();

                System.out.print("Enter your answer number: ");
                int userChoice = scanner.nextInt();
                scanner.nextLine();

                String userAnswer = correctEx.possibleAnswers[userChoice - 1];
                userAnswers.add(userAnswer);

                boolean isCorrect = exercise.checkAnswer(userAnswer);
                dailyExercise.completeExercise(exercise, isCorrect);

                if (!isCorrect) {
                    System.out.println("Wrong. Correct answer: " + correctEx.correctAnswer);
                } else {
                    System.out.println("Correct answer!");
                }

            } else if (exercise instanceof FiszkaExercise) {
                FiszkaExercise fiszkaEx = (FiszkaExercise) exercise;
                fiszkaEx.showAnswer();

                System.out.print("Enter word translation: ");
                String userAnswer = scanner.nextLine();
                userAnswers.add(userAnswer);

                boolean isCorrect = exercise.checkAnswer(userAnswer);
                dailyExercise.completeExercise(exercise, isCorrect);

                if (!isCorrect) {
                    System.out.println("Wrong. Correct answer: " + fiszkaEx.translation);
                } else {
                    System.out.println("Correct answer!");
                }

            } else if (exercise instanceof InputTranslationExercise) {
                InputTranslationExercise inputEx = (InputTranslationExercise) exercise;

                System.out.print("Enter the translation of " + inputEx.wordToTranslate + ": ");
                String userAnswer = scanner.nextLine();
                inputEx.inputAnswer(userAnswer);
                userAnswers.add(userAnswer);

                boolean isCorrect = exercise.checkAnswer(userAnswer);
                dailyExercise.completeExercise(exercise, isCorrect);

                if (!isCorrect) {
                    System.out.println("Wrong. Correct answer: " + inputEx.correctAnswer);
                } else {
                    System.out.println("Correct answer!");
                }
            }
        }

        // Mark incorrect answers
        repeatBadOnes.markBadOnes(allExercises, userAnswers, correctAnswers);

        // Repeat exercises with errors
        List<String> newAnswers = new ArrayList<>(); // Для хранения новых ответов
        System.out.println("Repeating exercises with mistakes:");
        for (IExercise exercise : repeatBadOnes.set) {
            exercise.createExec();

            if (exercise instanceof CorrectTranslationExercise) {
                CorrectTranslationExercise correctEx = (CorrectTranslationExercise) exercise;
                correctEx.displayOptions();

                System.out.print("Enter your answer number: ");
                int userChoice = scanner.nextInt();
                scanner.nextLine();

                String userAnswer = correctEx.possibleAnswers[userChoice - 1];
                newAnswers.add(userAnswer); // Save the new answer

                if (exercise.checkAnswer(userAnswer)) {
                    System.out.println("Correct answer!");
                } else {
                    System.out.println("Wrong. Correct answer: " + correctEx.correctAnswer);
                }

            } else if (exercise instanceof FiszkaExercise) {
                FiszkaExercise fiszkaEx = (FiszkaExercise) exercise;
                fiszkaEx.showAnswer();

                System.out.print("Enter word translation: ");
                String userAnswer = scanner.nextLine();
                newAnswers.add(userAnswer);

                if (exercise.checkAnswer(userAnswer)) {
                    System.out.println("Correct answer!");
                } else {
                    System.out.println("Wrong. Correct answer: " + fiszkaEx.translation);
                }

            } else if (exercise instanceof InputTranslationExercise) {
                InputTranslationExercise inputEx = (InputTranslationExercise) exercise;

                System.out.print("Enter translation: ");
                String userAnswer = scanner.nextLine();
                newAnswers.add(userAnswer);

                if (exercise.checkAnswer(userAnswer)) {
                    System.out.println("Correct answer!");
                } else {
                    System.out.println("Wrong. Correct answer: " + inputEx.correctAnswer);
                }
            }
        }

        // Compare improvements
        repeatBadOnes.compareImproved(newAnswers, correctAnswers);


        // Generate the final report
        raportCollector.generateRaport();
        System.out.println("Exercises completed!");
    }
}
