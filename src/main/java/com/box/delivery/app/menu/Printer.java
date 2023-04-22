package com.box.delivery.app.menu;

public class Printer {

    public static void printLine(Object message) {
        printObject(message + System.lineSeparator());
    }

    public static void printObject(Object message) {
        System.out.print(message.toString());
    }

    public static void printSeparationLines(int num) {
        for (int i = 0; i < num; i++) {
            printLine("-----------------------------------------------------------------");
        }
    }

    public static void printBlankLine() {
        printLine("");
    }
}
