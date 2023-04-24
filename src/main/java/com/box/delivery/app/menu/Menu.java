package com.box.delivery.app.menu;

import java.util.Scanner;
import org.jetbrains.annotations.NotNull;

public abstract class Menu {

    public abstract void run();

    protected int getIntUserInput(Scanner scanner, String parameterName) {
        Printer.printBlankLine();
        Printer.printObject(parameterName + ": ");
        while(scanner.hasNext()){
            if(scanner.hasNextInt(10)){
                return scanner.nextInt();
            } else{
                Printer.printLine("The " + parameterName + " needs to be an Integer number");
                scanner.next();
                Printer.printBlankLine();
                Printer.printObject(parameterName + ": ");
            }
            skipSpecialChars(scanner);
        }
        return -1;
    }

    protected int getPositiveIntUserInput(Scanner scanner, String parameterName) {
        int intUserInput;

        do {
            intUserInput = getIntUserInput(scanner, parameterName);
            if(intUserInput < 0) {
                Printer.printLine("The " + parameterName + " " + intUserInput + " is invalid. Choose a positive number");
            }
        } while(intUserInput < 0);

        return intUserInput;
    }

    protected String getUserInput(Scanner scanner, String parameterName) {
        Printer.printBlankLine();
        Printer.printObject(parameterName + ": ");
        return scanner.next();
    }

    private void skipSpecialChars(@NotNull Scanner scanner) {
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
    }
}
