import java.util.Random;

/**
 * Класс персонажа игрока
 * Управляет перемещением и состоянием игрока
 */
public class Person {
    // 📍 Координаты и состояние
    protected int x, y;
    private String image = "\uD83E\uDDD9\u200D"; // 🧙 Маг
    private int live = 3;
    private Random r = new Random();

    // 📏 Константы для валидации
    private static final int MIN_POSITION = 1;

    /**
     * Конструктор персонажа
     * @param sizeBoard размер игрового поля
     */
    public Person(int sizeBoard) {
        y = sizeBoard;
        int n = r.nextInt(sizeBoard);
        x = n == 0 ? MIN_POSITION : n;
    }

    /**
     * Конструктор с конкретными координатами
     * @param x координата X
     * @param y координата Y
     */
    public Person(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Конструктор по умолчанию
     */
    public Person() {
        this(MIN_POSITION, MIN_POSITION);
    }

    // 🔄 Геттеры
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLive() {
        return live;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Проверка корректности хода
     * @param x целевая координата X
     * @param y целевая координата Y
     * @return true если ход допустим
     */
    public boolean moveCorrect(int x, int y) {
        boolean horizontalMove = this.y == y && Math.abs(this.x - x) == 1;
        boolean verticalMove = this.x == x && Math.abs(this.y - y) == 1;
        return horizontalMove || verticalMove;
    }

    /**
     * Перемещение персонажа
     * @param x новая координата X
     * @param y новая координата Y
     */
    void move(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println("🎯 Персонаж перемещен в позицию (" + x + ", " + y + ")");
    }

    /**
     * Уменьшение количества жизней
     */
    public void downLive() {
        live--;
        System.out.println("💔 Потеряна жизнь! Осталось: " + live);
    }

    /**
     * Проверка жив ли персонаж
     * @return true если у персонажа есть жизни
     */
    public boolean isAlive() {
        return live > 0;
    }

    /**
     * Получение информации о персонаже
     * @return строка с описанием
     */
    @Override
    public String toString() {
        return "Персонаж " + image + " на позиции (" + x + ", " + y + ") с " + live + " жизнями";
    }
}