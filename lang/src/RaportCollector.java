import java.util.ArrayList;
import java.util.List;

public class RaportCollector {
    private int numberOfExercises;
    private int numberOfImproved;

    // Increase the number of exercises
    public void incrementExercises() {
        numberOfExercises++;
    }

    // Increase the number of improvements
    public void incrementImproved() {
        numberOfImproved++;
    }

    // Report generation
    public void generateRaport() {
        System.out.println("Report:");
        System.out.println("Total exercises: " + numberOfExercises);
        System.out.println("Completed: " + numberOfImproved);
    }
}
