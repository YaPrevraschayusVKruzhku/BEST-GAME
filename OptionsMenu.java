import java.util.Scanner;

public class OptionsMenu {

   static int command;

  static  Scanner scanner = new Scanner(System.in);

    public static void showOptionsMenu() {

do    {
   System.out.println("""
           Выбери нужный тебе пункт:
           1. Показать текущие настройки
           2. Изменить текущие настройки
           3. Выход""");


          command = scanner.nextInt();
          switch (command)  {
              case 1:
                  System.out.println("\nТекущие настройки:\n" + "Ряды: " +
                          Main.rows + "\nСтолбики " + Main.columns + "\nЖуки: " +
                          Main.quantityOfBeatles + "\nПыльца: " + Main.amountOfPollen + "\nХоды: " + Main.motions + "\n Цветы: " + Main.amountOfFlowers);
                  break;

              case 2:
                  System.out.println("Чтобы оставить текущее значение, нажмите Enter");
                  System.out.println("");
                  String value;
                  System.out.println ("Выбери новое значение для количества рядов (сейчас "+ Main.rows + ")");

                  scanner.nextLine();

                  value  = scanner.nextLine();
                  if (!value.isBlank())
                  {
                      Main.rows = Integer.parseInt(value);
                  }

                  System.out.println ("Выбери новое значение для количества столбиков (сейчас "+ Main.columns + ")");

                  value  = scanner.nextLine();
                  if (!value.isBlank())
                  {
                      Main.columns = Integer.parseInt(value);
                  }

                  System.out.println ("Выбери новое значение для количества врагов (сейчас "+ Main.quantityOfBeatles + ")");

                  value  = scanner.nextLine();
                  if (!value.isBlank())
                  {
                      Main.quantityOfBeatles = Integer.parseInt(value);
                  }



                  System.out.println ("Выбери новое значение для количества пыльцы (сейчас "+ Main.amountOfPollen + ")");

                  value  = scanner.nextLine();
                  if (!value.isBlank())
                  {
                      Main.amountOfPollen = Integer.parseInt(value);
                  }



                  System.out.println ("Выбери новое значение для количества ходов (сейчас "+ Main.motions + ")");

                  value  = scanner.nextLine();
                  if (!value.isBlank())
                  {
                      Main.motions = Integer.parseInt(value);
                  }



                  System.out.println ("Выбери новое значение для количества цветов (сейчас "+ Main.amountOfFlowers + ")");

                  value  = scanner.nextLine();
                  if (!value.isBlank())
                  {
                      Main.amountOfFlowers = Integer.parseInt(value);
                  }

                  if (isValuesPlayable()){
                      System.out.println("Невозможно создать игру с такими параметрами, значения возвращены по умолчанию");
                      System.out.println("");
                      Main.rows=10;
                      Main.columns=10;
                      Main.quantityOfBeatles=5;
                      Main.amountOfPollen=50;
                      Main.motions=40;
                      Main.amountOfFlowers=10;
                  }
                  break;


              case 3:
                  break;

              default:
                  System.out.println("НЕ ПОНЕЛ");

                  break;
          }
}
while (command !=3);

    }

    private static Boolean isValuesPlayable(){

        int fieldSize = Main.rows*Main.columns;

        int alObjects = Main.amountOfFlowers + Main.quantityOfBeatles +1;

        boolean isValuesPlayable=((alObjects/fieldSize) >0.65);
        return isValuesPlayable;
    }
}
