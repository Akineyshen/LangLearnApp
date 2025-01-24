public class Originator {
    private String exerciseState;

    public void setExerciseState(String state) {
        this.exerciseState = state;
    }

    public String getExerciseState() {
        return exerciseState;
    }

    public Memento saveStateToMemento() {
        return new Memento(exerciseState);
    }

    public void restoreStateFromMemento(Memento memento) {
        this.exerciseState = memento.getExerciseState();
    }
}
