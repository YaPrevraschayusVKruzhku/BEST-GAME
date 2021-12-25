import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class Game {

    private int rows;                    //ряды
    private int columns;                //столбцы
    private int quantityOfBeatles;     //жуки (враги)
    private int amountOfPollen;       //пыльца
    private int motionsLeft;         //ходов осталось
    private int pollenGatherd;      //собрано пыльцы
    private int amountOfFlowers;   //цветы
    private int triesToRegenerate = 10;
    private boolean isGameFinished = false;
    private ArrayList<Enemy> enemyArrayList = new ArrayList<Enemy>();
    private ArrayList<Flower> flowerArrayList = new ArrayList<Flower>();
    private Field field;
    private Random randomNumber = new Random();
    private Player player;
    private Scanner sc = new Scanner(System.in);

    public Game(int rows, int columns, int quantityOfBeatles, int amountOfPollen, int motionsLeft, int amountOfFlowers) {
        this.rows = rows;
        this.columns = columns;
        this.quantityOfBeatles = quantityOfBeatles;
        this.amountOfPollen = amountOfPollen;
        this.motionsLeft = motionsLeft;
        this.amountOfFlowers = amountOfFlowers;
        field = new Field(rows, columns);
    }

    public Field getField() {
        return this.field;
    }

    public ArrayList<Flower> getFlowerArrayList() {

        return this.flowerArrayList;

    }

    public void setPollenGatherd(int pollenToAdd) {

        this.pollenGatherd += pollenToAdd;

    }

    public void fillFieldWithEmptyObjects() {

        for (int i = 0; i < rows; i++) {
            for (int q = 0; q < columns; q++) {
                field.setFieldable(i, q, new Empty());

            }
        }
    }

    public void startGame() {

        possessPlayer();
        posesEnemies();
        PossesFlowers();


        while (!isGameFinished) {

            showField();
            playerTurn();
            computerTurn();
            chekIfGameNotFinished();

        }
    }

    private void possessPlayer() {
        int vk = randomNumber.nextInt(rows);
        int tg = randomNumber.nextInt(columns);
        player = new Player(vk, tg, this);

    }

    private void playerTurn() {
        System.out.println("");
        System.out.println("Передвигайся на " + "a, " + "s, " + "w, " + "d, " + "z, ");

        String jo = sc.nextLine();
        player.makeMove(jo);
    }

    private void computerTurn() {

        enemyMove();
        PossesFlowers();

        motionsLeft--;
    }


    private void chekIfGameNotFinished() {

        if (motionsLeft == 0) {
            System.out.println("ИГРА ОКОНЧЕНА");
            System.out.println("");
            isGameFinished = true;
        } else if (pollenGatherd >= amountOfPollen) {
            System.out.println("ПОБЕДА!!!");
            System.out.println("");
            isGameFinished = true;
        }
    }


    private void showField() {
        System.out.println("\n\nОсталось ходов: " + motionsLeft + "\nСобрано " + pollenGatherd + " пыльцы из " + amountOfPollen);

        field.showField();
    }

    private void PossesFlowers() {

        for (int i = amountOfFlowers - flowerArrayList.size(); i > 0; ) {

            int flowerAmountOfPollen = randomNumber.nextInt(9) + 1; //пыльца на цветках
            int flowerRowPosition = randomNumber.nextInt(rows);
            int flowerColumnPosition = randomNumber.nextInt(columns);

            if (field.getFieldable(flowerRowPosition, flowerColumnPosition) instanceof Player) {

                pollenGatherd = pollenGatherd + flowerAmountOfPollen;
                i--;

            } else if (field.getFieldable(flowerRowPosition, flowerColumnPosition) instanceof Empty) {

                Flower flower = new Flower(flowerAmountOfPollen, flowerRowPosition, flowerColumnPosition);

                field.setFieldable(flowerRowPosition, flowerColumnPosition, flower);
                flowerArrayList.add(flower);
                i--;


            }

        }
    }


    private void enemyMove() {

        int rowIndex = 0;
        int columnIndex = 0;
        int newRowIndex = 0;
        int newColumnIndex = 0;
        int regenerateIndex = 0;
        boolean isNeedToRegenerate = true;

        for (Enemy enemy: enemyArrayList) {
            rowIndex = enemy.getRowIndex();
            columnIndex = enemy.getColumnIndex();
            do {

                int deltaRow = randomNumber.nextInt(3) - 1;
                int deltaColumn = randomNumber.nextInt(3) - 1;

                newRowIndex = rowIndex + deltaRow;
                newColumnIndex = columnIndex + deltaColumn;

                if ((newRowIndex < 0) || (newColumnIndex < 0) || (newRowIndex >= field.getRows())
                        || (newColumnIndex >= field.getColumns())
                        || field.getFieldable(newRowIndex, newColumnIndex)
                        instanceof Player || field.getFieldable(newRowIndex, newColumnIndex)
                        instanceof Enemy) {

                    regenerateIndex++;
                    isNeedToRegenerate = true;

                } else {
                    if (field.getFieldable(newRowIndex, newColumnIndex) instanceof Flower) {
                        Flower flower = (Flower) field.getFieldable(newRowIndex, newColumnIndex);
                        flowerArrayList.remove(flower);


                        isNeedToRegenerate = swapEnemy(rowIndex, columnIndex, newRowIndex, newColumnIndex, enemy);
                    } else {

                        isNeedToRegenerate = swapEnemy(rowIndex, columnIndex, newRowIndex, newColumnIndex, enemy);;

                    }

                }

            }
            while (isNeedToRegenerate && regenerateIndex <= 10);
        }
    }

    private boolean swapEnemy (int rowIndex, int columnIndex, int newRowIndex, int newColumnIndex, Enemy enemy){
        field.setFieldable(newRowIndex, newColumnIndex, enemy);
        field.setFieldable(rowIndex, columnIndex, new Empty());
        enemy.setRowIndex(newRowIndex);
        enemy.setColumnIndex(newColumnIndex);

return false;
    }

    private void posesEnemies() {
        for (int i = quantityOfBeatles - enemyArrayList.size(); i > 0; ) {

            int enemyRowPosition = randomNumber.nextInt(rows);
            int enemyColumnPosition = randomNumber.nextInt(columns);

            if (field.getFieldable(enemyRowPosition, enemyColumnPosition) instanceof Empty) {

                Enemy enemy = new Enemy( enemyRowPosition, enemyColumnPosition);

                field.setFieldable(enemyRowPosition, enemyColumnPosition, enemy);
                enemyArrayList.add(enemy);
                i--;
            }
        }
    }
}

