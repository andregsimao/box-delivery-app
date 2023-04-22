package com.box.delivery.app.menu;

import java.util.Scanner;

public abstract class Menu {

    public abstract void run();

    int getIntUserInput(Scanner scanner) {
        Printer.printBlankLine();
        Printer.printMessage("Option: ");
        while(scanner.hasNext()){
            if(scanner.hasNextInt(10)){
                return scanner.nextInt();
            } else{
                Printer.printLine("The option needs to be an Integer number");
                scanner.next();
            }
            skipSpecialChars(scanner);
        }
        return -1;
    }

    private void skipSpecialChars(Scanner scanner) {
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
    }
}
