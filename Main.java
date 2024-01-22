import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Создаем сканер для чтения данных, введенных пользователем
        //Scanner scanner = new Scanner(System.in);
        String yesNo;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                // Запрашиваем данные у пользователя
                System.out.println("Требуемые данные!: Фамилия, Имя, Отчество, Дата рождения, Номер телефона, Пол");
                System.out.println("Формат ввода!!: Фамилия, Имя и Отчество - буквы (рус/лат), Дата рождения - dd.mm.yyyy," +
                        " Номер телефона - цифры 1234567890, Пол - буквы 'м, ж, m, f'");
                System.out.print("Введите данные через пробел!!: ");

                String input = scanner.nextLine();

                String[] data = input.split(" ");

                // Проверка 1 -количество данных соответствует требуемому
                if (data.length != 6) {
                    System.out.println("Неверное количество данных");
                    break;
                }

                // Извлекаем данные из введенной строки
                String lastName = data[0];
                String name = data[1];
                String patronymic = data[2];
                String birthDay = data[3];
                String phoneNumber = data[4];
                String gender = data[5];
                String fio = data[0] + data[1] + data[2];

                // Проверка 2 - ФИО только буквы

                if (!fio.matches("[A-Za-zА-Яа-яЁё]+")) {
                    System.out.println("Фамилия, Имя и Отчество должны содержать только русские или латинские буквы!!");
                    break;
                }

                // Проверка 3 - Формат даты
                try {
                    LocalDate date = LocalDate.parse(birthDay, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                } catch (DateTimeParseException e) {
                    System.out.println("Неверный формат даты рождения!!");
                    break;
                }

                // Проверка - 4 - номер телефона целое число
                try {
                    Long.parseLong(phoneNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Номер телефона должен содержать целое число!!");
                    break;
                }

                // Проверка - 5 - пол является символом 'f', 'm', 'м', 'ж'
                if (!gender.equals("f") && !gender.equals("m") && !gender.equals("ж") && !gender.equals("м")){
                    System.out.println("Пол должен быть символом 'm' - 'f' или 'м' - 'ж' !!");
                    break;
                }

                // Строка для записи в файл если все проверки пройдены
                String member = lastName + " " + name + " " + patronymic + " " + birthDay + " " + phoneNumber + " " + gender;

                // Имя файла = фамилия
                String fileName = lastName + ".txt";
                // Добавление записи в новый файл или существующий
                try (FileWriter writer = new FileWriter(fileName, true)) {
                    writer.write(member + System.lineSeparator());
                    System.out.println("Данные записаны в файл " + fileName);
                } catch (IOException e) {
                    System.out.println("Ошибка при записи данных в файл!!");
                    e.printStackTrace();
                }
                break;
            }
            System.out.print("\nЖелаете продолжить (y/n)?: ");
            yesNo = scanner.next();
            if (yesNo.equals("n")) {
                break;
            }
        }
    }
}
