package com.box.delivery.app.menu;

import java.util.Scanner;

public class Menu {
    Scanner scanner;
    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void close() {
        scanner.close();
    }
}
