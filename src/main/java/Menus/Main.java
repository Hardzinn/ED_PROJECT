package Menus;

import Exceptions.EmptyCollectionException;
import Exceptions.NonComparableElementException;
import Menus.BotMenu;
import Menus.FlagMenu;
import org.example.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        GameMenu gameMenu= new GameMenu();
        gameMenu.gameMenu();
    }

}