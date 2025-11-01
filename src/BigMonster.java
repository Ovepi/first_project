import java.util.Scanner;
import java.util.Random;

/**
 * Класс большого монстра - наследуется от обычного монстра
 * Имеет более сложные математические задачи
 */
public class BigMonster extends Monster {
    // 🎭 Изображение большого монстра
    private String image = "\uD83D\uDC79"; // 👹 Японский огр

    // 📊 Константы для балансировки сложности
    private static final int BASE_MULTIPLIER = 10;
    private static final int BASE_SUBTRACT = 100;

    /**
     * Конструктор большого монстра
     * @param sizeBoard размер игрового поля
     */
    public BigMonster(int sizeBoard) {
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

    /**
     * Усложненная математическая задача для большого монстра
     * @param difficultGame уровень сложности (1-5)
     * @return true если игрок решил задачу правильно
     */
    @Override
    public boolean taskMonster(int difficultGame) {
        System.out.println("🔥 Большой монстр бросает вызов!");

        if (difficultGame == 1) {
            return easyTask();
        } else {
            return complexTask(difficultGame);
        }
    }

    /**
     * Простая задача (для низкой сложности)
     * @return результат решения задачи
     */
    private boolean easyTask() {
        return super.taskMonster(1); // Используем базовую задачу
    }

    /**
     * Сложная задача с умножением и вычитанием
     * @param difficultGame уровень сложности
     * @return результат решения задачи
     */
    private boolean complexTask(int difficultGame) {
        // Генерация чисел в зависимости от сложности
        int minRange = BASE_MULTIPLIER * (difficultGame - 1);
        int maxRange = BASE_MULTIPLIER * difficultGame;

        int x = r.nextInt(minRange, maxRange);
        int y = r.nextInt(minRange, maxRange);
        int z = r.nextInt(BASE_SUBTRACT * (difficultGame - 1), BASE_SUBTRACT * difficultGame);

        int trueAnswer = x * y - z;

        System.out.println("🧮 Реши пример: " + x + " × " + y + " - " + z + " = ?");
        System.out.println("💡 Подсказка: Сначала умножение, потом вычитание!");

        Scanner sc = new Scanner(System.in);
        System.out.print("➡️  Ваш ответ: ");
        int ans = sc.nextInt();

        return checkAnswer(trueAnswer, ans);
    }

    /**
     * Проверка ответа с улучшенным выводом
     * @param trueAnswer правильный ответ
     * @param userAnswer ответ пользователя
     * @return true если ответ верный
     */
    private boolean checkAnswer(int trueAnswer, int userAnswer) {
        if (trueAnswer == userAnswer) {
            System.out.println("✅ Потрясающе! Ты победил большого монстра!");
            return true;
        } else {
            System.out.println("❌ Мощь большого монстра оказалась сильнее!");
            System.out.println("📝 Правильный ответ: " + trueAnswer);
            System.out.println("💡 Твой ответ: " + userAnswer);
            return false;
        }
    }

    /**
     * Альтернативный метод для простой задачи
     * @return результат решения
     */
    public boolean taskMonster() {
        System.out.println("👹 Большой монстр решает дать тебе шанс...");
        return super.taskMonster(1);
    }

    /**
     * Получение информации о большом монстре
     * @return строка с описанием
     */
    @Override
    public String toString() {
        return "Большой монстр " + image + " на позиции (" + getX() + ", " + getY() + ")";
    }

    /**
     * Получение уровня угрозы монстра
     * @return строка с уровнем угрозы
     */
    public String getThreatLevel() {
        return "ВЫСОКИЙ";
    }
}