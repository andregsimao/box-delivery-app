package com.box.delivery.app.menu;

public class Printer {

    public static void printMessage(Object message) {
        System.out.println(message.toString());
    }

    public static void printSeparationLines(int num) {
        for (int i = 0; i < num; i++) {
            printMessage("-----------------------------------------------------------------");
        }
    }

    public static void printBlankLine() {
        printMessage("");
    }
}
