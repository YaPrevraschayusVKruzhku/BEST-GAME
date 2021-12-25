import java.util.Scanner;


public class Main  {
    // количество по умолчанию:
    public static int rows=10;                   // ряды
    public static int columns=10;               //столбцы
    public static int quantityOfBeatles=5;     //жуки (враги)
    public static int amountOfPollen=50;      //пыльца
    public static int motions=40;            //ходы
    public static int amountOfFlowers=10;   //цветы


    public static void main (String[] args) {

        String command;
        do {

            System.out.println("Добро пожаловать игрок, выбери нужный пункт");

            System.out.println("");

            System.out.println("1. Начать");

            System.out.println("2. Настройки");

            System.out.println("3. О создателе ");

            System.out.println("4. Правила");

            System.out.println("5. Выйти");

            Scanner scanner = new Scanner(System.in);

             command = scanner.nextLine();

            switch (command) {

                case "1":
                    startNewGame();
                    break;

                case "2":
                    OptionsMenu.showOptionsMenu();
                    break;

                case "3":
                    showCredits();
                    break;

                case "4":
                    showRules();
                    break;

                case "5":
                    break;

                default:
                    System.out.println("НЕ ПОНЕЛ");
                    break;
            }
        }

        while (!command.equals("5"));

    }

    private static void showRules() {
        System.out.println(" Правила игры: Вы (@) спавнитесь на игровом поле и ваша задача собрать за определённое количество ходов нужное количество пыльцы, лежащей на цветах\n" +
                " (циферки на поле). Враги (*) также подбирают пыльцу и рандомно ходят, больше они ничем не мешают. Так как игра оказалась слишком простой, то запрещено заходить\n" +
                " за границу поля. Победа засчитывается, если Вы подобрали всю необходимую пыльцу, а проигрыш, если Вы зашли за поле или закончились ходы. Приятного времяпровождения!");
        System.out.println("");
    }

    private static void showCredits() {
        System.out.println("Создатель этой игры превратился в кружку(");
        System.out.println("");
    }

    private static void startNewGame() {

Game game = new Game (rows, columns, quantityOfBeatles, amountOfPollen, motions, amountOfFlowers);

game.fillFieldWithEmptyObjects();


game.startGame();


    }
}
