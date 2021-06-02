package eCommerce.ui;

import java.util.Scanner;

public class InputHelper {

    private final Scanner scanner;
    public InputHelper() {
        scanner = new Scanner(System.in);
    }

    public String readString() {
        return scanner.nextLine();
    }

    public int readInt(Integer lowerBound, Integer upperBound) {
        String str;
        do {
            str = scanner.nextLine();
        } while (!(str.matches("[0-9.]+") &&
                (lowerBound == null || Integer.parseInt(str) >= lowerBound) &&
                (upperBound == null || Integer.parseInt(str) <= upperBound)));

        return Integer.parseInt(str);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void pressAnyKeyToContinue()
    {
        System.out.println("Нажмите любую клавишу чтобы продолжить...");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
    }
}
