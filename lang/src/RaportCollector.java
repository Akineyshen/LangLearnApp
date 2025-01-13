import java.util.ArrayList;
import java.util.List;

public class RaportCollector {
    private int numberOfExercises;
    private int numberOfImproved;

    public void incrementExercises() {
        numberOfExercises++;
    }

    public void incrementImproved() {
        numberOfImproved++;
    }

    public void generateRaport() {
        System.out.println("Raport:");
        System.out.println("Łączna liczba ćwiczeń: " + numberOfExercises);
        System.out.println("Ukończone: " + numberOfImproved);
    }
}
