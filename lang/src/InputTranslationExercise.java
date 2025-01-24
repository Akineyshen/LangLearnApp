public class InputTranslationExercise implements IExercise {
    private String userInput;
    public String correctAnswer;
    public String wordToTranslate;

    public InputTranslationExercise(String correctAnswer, String wordToTranslate) {
        this.correctAnswer = correctAnswer;
        this.wordToTranslate = wordToTranslate;
    }

    @Override
    public void createExec() {
        System.out.println("Ćwiczenie utworzone: Wpisz tłumaczenie słowa.");
        System.out.print("Wprowadź tłumaczenie для słowa '" + wordToTranslate + "': ");
    }
    public void inputAnswer(String input) {
        this.userInput = input;
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return userAnswer.equalsIgnoreCase(correctAnswer);
    }

    @Override
    public String getState() {
        // Возвращаем состояние в виде строки
        return String.format("%s;%s",
                wordToTranslate, correctAnswer, userInput);
    }

    @Override
    public void setState(String state) {
        // Восстанавливаем состояние из строки
        String[] parts = state.split(";");
        for (String part : parts) {
            String[] keyValue = part.split(":");
            if (keyValue.length == 2) {
                switch (keyValue[0]) {
                    case "WordToTranslate":
                        wordToTranslate = keyValue[1];
                        break;
                    case "CorrectAnswer":
                        correctAnswer = keyValue[1];
                        break;
                    case "UserInput":
                        userInput = keyValue[1];
                        break;
                }
            }
        }
    }
    public String getWordToTranslate() {
        return wordToTranslate;
    }

}