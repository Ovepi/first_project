import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String castle = "\uD83C\uDFF0";
        int sizeBoard = 5;

        Person person = new Person(sizeBoard);
        int step = 0;

        String[][] board = new String[sizeBoard][sizeBoard];
        for (int y = 0; y < sizeBoard; y++) {
            for (int x = 0; x < sizeBoard; x++) {
                board[y][x] = "  ";
            }
        }

        int countMonster = sizeBoard * sizeBoard - sizeBoard - 5;
        Random r = new Random();

        Monster[] arrMonster = new Monster[countMonster + 1];
        int count = 0;

        while (count <= countMonster) {
            Monster test;
            int monsterType = r.nextInt(3); // 0 - обычный, 1 - большой, 2 - призрак

            switch (monsterType) {
                case 0:
                    test = new Monster(sizeBoard);
                    break;
                case 1:
                    test = new BigMonster(sizeBoard);
                    break;
                case 2:
                    test = new Gost(sizeBoard); // Добавляем призраков
                    break;
                default:
                    test = new Monster(sizeBoard);
            }

            if (board[test.getY()][test.getX()].equals("  ")) {
                board[test.getY()][test.getX()] = test.getImage();
                arrMonster[count] = test;
                count++;
            }
        }

        int castleX = r.nextInt(sizeBoard);
        int castleY = 0;
        board[castleY][castleX] = castle;

        System.out.println("Привет! Ты готов начать играть в игру? (Напиши: ДА или НЕТ)");

        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        System.out.println("Ваш ответ: " + answer);

        switch (answer) {
            case "ДА" -> {
                System.out.println("Выбери сложность игры(от 1 до 5):");
                int difficultGame = sc.nextInt();
                sc.nextLine(); // очистка буфера
                System.out.println("Выбранная сложность: " + difficultGame);

                while (person.getLive() > 0) {
                    board[person.getY() - 1][person.getX() - 1] = person.getImage();
                    outputBoard(board, person.getLive());

                    System.out.println("Введите направление (W - вверх, S - вниз, A - влево, D - вправо):");
                    String direction = sc.nextLine().toUpperCase();

                    int newX = person.getX();
                    int newY = person.getY();

                    switch (direction) {
                        case "W" -> newY--;
                        case "S" -> newY++;
                        case "A" -> newX--;
                        case "D" -> newX++;
                        default -> {
                            System.out.println("❌ Неверная команда! Используйте W, A, S, D");
                            continue;
                        }
                    }

                    if (person.moveCorrect(newX, newY)) {
                        String nextCell = board[newY - 1][newX - 1];

                        if (nextCell.equals("  ")) {
                            // Свободная клетка
                            board[person.getY() - 1][person.getX() - 1] = "  ";
                            person.move(newX, newY);
                            step++;
                            System.out.println("✅ Ход корректный; Новые координаты: " + person.getX() + ", " + person.getY() +
                                    "\nХод номер: " + step);
                        } else if (nextCell.equals(castle)) {
                            // Победа!
                            System.out.println("🎉 Поздравляем! Вы прошли игру!");
                            break;
                        } else {
                            // Встреча с монстром
                            for (Monster monster : arrMonster) {
                                if (monster != null && monster.conflictPerson(newX, newY)) {
                                    System.out.println("⚔️  Встреча с " +
                                            (monster instanceof Gost ? "призраком" :
                                                    monster instanceof BigMonster ? "большим монстром" : "монстром"));

                                    if (monster.taskMonster(difficultGame)) {
                                        board[person.getY() - 1][person.getX() - 1] = "  ";
                                        person.move(newX, newY);
                                        board[person.getY() - 1][person.getX() - 1] = person.getImage();
                                    } else {
                                        person.downLive();
                                        System.out.println("💔 Осталось жизней: " + person.getLive());
                                    }
                                    break;
                                }
                            }
                        }
                    } else {
                        System.out.println("❌ Некорректный ход! Вы не можете выйти за границы поля.");
                    }

                    // Проверка на проигрыш
                    if (person.getLive() <= 0) {
                        System.out.println("💀 Игра окончена! У вас закончились жизни.");
                        break;
                    }
                }
            }
            case "НЕТ" -> System.out.println("Жаль, приходи еще!");
            default -> System.out.println("Данные введены некорректно");
        }

        sc.close();
    }

    static void outputBoard(String[][] board, int live) {
        String wall = "+ —— + —— + —— + —— + —— +";

        for (String[] raw : board) {
            System.out.println(wall);
            System.out.print("| ");
            for (String col : raw) {
                System.out.print(col + " | ");
            }
            System.out.println();
        }
        System.out.println(wall);
        System.out.println("❤️  Количество жизней: " + live + "\n");
    }
}