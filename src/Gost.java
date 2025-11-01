import java.util.Scanner;
import java.util.Random;

public class Gost extends Monster {
    private String image = "\uD83D\uDC7B";
    private Random r = new Random();

    Gost(int sizeBoard) {
        super(sizeBoard);
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    // Уникальные математические примеры для призрака
    @Override
    public boolean taskMonster(int difficultGame) {
        System.out.println("👻 Призрак бросает вам вызов!");

        if (difficultGame == 1) {
            return easyTask();
        } else if (difficultGame == 2) {
            return mediumTask();
        } else if (difficultGame == 3) {
            return hardTask();
        } else if (difficultGame == 4) {
            return veryHardTask();
        } else {
            return extremeTask();
        }
    }

    // Легкие примеры (сложение/вычитание)
    private boolean easyTask() {
        int a = r.nextInt(20) + 1;
        int b = r.nextInt(20) + 1;
        int operation = r.nextInt(2); // 0 - сложение, 1 - вычитание

        if (operation == 0) {
            int trueAnswer = a + b;
            System.out.println("Реши пример: " + a + " + " + b + " = ?");
            return checkAnswer(trueAnswer);
        } else {
            // Убедимся, что результат положительный
            int max = Math.max(a, b);
            int min = Math.min(a, b);
            int trueAnswer = max - min;
            System.out.println("Реши пример: " + max + " - " + min + " = ?");
            return checkAnswer(trueAnswer);
        }
    }

    // Средние примеры (умножение)
    private boolean mediumTask() {
        int a = r.nextInt(10) + 1;
        int b = r.nextInt(10) + 1;
        int trueAnswer = a * b;

        System.out.println("Реши пример: " + a + " × " + b + " = ?");
        return checkAnswer(trueAnswer);
    }

    // Сложные примеры (деление)
    private boolean hardTask() {
        int result = r.nextInt(10) + 1;
        int b = r.nextInt(8) + 2; // от 2 до 9
        int a = result * b; // чтобы деление было без остатка

        System.out.println("Реши пример: " + a + " ÷ " + b + " = ?");
        return checkAnswer(result);
    }

    // Очень сложные примеры (комбинированные)
    private boolean veryHardTask() {
        int a = r.nextInt(15) + 1;
        int b = r.nextInt(15) + 1;
        int c = r.nextInt(10) + 1;
        int trueAnswer = (a + b) * c;

        System.out.println("Реши пример: (" + a + " + " + b + ") × " + c + " = ?");
        return checkAnswer(trueAnswer);
    }

    // Экстремальные примеры (с порядком операций)
    private boolean extremeTask() {
        int a = r.nextInt(20) + 1;
        int b = r.nextInt(10) + 1;
        int c = r.nextInt(15) + 1;
        int trueAnswer = a + b * c; // сначала умножение, потом сложение

        System.out.println("Реши пример: " + a + " + " + b + " × " + c + " = ?");
        System.out.println("Внимание! Сначала выполняется умножение!");
        return checkAnswer(trueAnswer);
    }

    private boolean checkAnswer(int trueAnswer) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ваш ответ: ");
        int ans = sc.nextInt();

        if (trueAnswer == ans) {
            System.out.println("✅ Верно! Ты победил призрака!");
            return true;
        } else {
            System.out.println("❌ Неверно! Правильный ответ: " + trueAnswer);
            System.out.println("Ты проиграл эту битву призраку!");
            return false;
        }
    }
}