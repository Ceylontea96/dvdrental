package com.funnydvd.dvdrental.cli.main;

import com.funnydvd.dvdrental.cli.ui.AppUI;

import static com.funnydvd.dvdrental.cli.ui.AppUI.inputInteger;
import static com.funnydvd.dvdrental.cli.ui.AppUI.startScreen;

public class AppMain {

    public static void main(String[] args) {

        while (true) {

            startScreen();
            int selection = inputInteger(">>> ");

            FrontController.chooseSystem(selection);

        }
    }
}
