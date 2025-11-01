import java.util.Random;
import java.util.Scanner;

/**
 * Класс обычного монстра
 * Базовый класс для всех типов монстров в игре
 */
public class Monster {
    // 🎭 Изображения и символы
    private String image = "\uD83E\uDDDF\u200D"; // 👟 Бегун
    private final int x, y;
    protected Random r = new Random();

    // 📊 Константы для балансировки игры
    private static final int MIN_COORDINATE = 0;
    private static final int MAX_OPERAND = 100;

    /**
     * Конструктор монстра
     * @param sizeBoard размер игрового поля
     */
    public Monster(int sizeBoard) {
        this.y = r.nextInt(sizeBoard - 1);
        this.x = r.nextInt(sizeBoard);
    }

    // 🔄 Геттеры и сеттеры
    public String getImage() {
        return image;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Проверка столкновения с персонажем
     * @param perX координата X персонажа
     * @param perY координата Y персонажа
     * @return true если произошло столкновение
     */
    public boolean conflictPerson(int perX, int perY) {
        return perY - 1 == this.y && perX - 1 == this.x;
    }

    /**
     * Математическая задача от монстра
     * @param difficultGame уровень сложности (1-5)
     * @return true если игрок решил задачу правильно
     */
    public boolean taskMonster(int difficultGame) {
        System.out.println("🎯 Решите задачу от монстра:");

        // Генерация случайных чисел для примера
        int x = r.nextInt(MAX_OPERAND);
        int y = r.nextInt(MAX_OPERAND);
        int trueAnswer = x + y;

        System.out.println("🧮 Реши пример: " + x + " + " + y + " = ?");

        Scanner sc = new Scanner(System.in);
        System.out.print("➡️  Ваш ответ: ");
        int ans = sc.nextInt();

        if (trueAnswer == ans) {
            System.out.println("✅ Верно! Ты победил монстра!");
            return true;
        } else {
            System.out.println("❌ Ты проиграл эту битву! Правильный ответ: " + trueAnswer);
            return false;
        }
    }

    /**
     * Получение информации о монстре
     * @return строка с описанием монстра
     */
    @Override
    public String toString() {
        return "Обычный монстр " + image + " на позиции (" + x + ", " + y + ")";
    }
}