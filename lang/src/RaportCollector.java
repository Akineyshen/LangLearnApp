import java.util.ArrayList;
import java.util.List;

public class RaportCollector {
    private int numberOfExercises;
    private int numberOfImproved;
    private final List<String> reports = new ArrayList<>();

    public void incrementExercises() {
        numberOfExercises++;
    }

    public void incrementImproved() {
        numberOfImproved++;
    }

    // Метод для сбора отчетов
    public void collectReport(IExercise exercise, boolean isCorrect) {
        String result = "Exercise: " + exercise.getState() + " - " + (isCorrect ? "Correct" : "Incorrect");
        reports.add(result);
    }

    public void generateRaport() {
        System.out.println("Raport:");
        System.out.println("Łączna liczba ćwiczeń: " + numberOfExercises);
        System.out.println("Ukończone: " + numberOfImproved);
        System.out.println("Szczegóły ćwiczeń:");
        for (String report : reports) {
            System.out.println(report);
        }
    }
}
