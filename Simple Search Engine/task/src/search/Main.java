package search;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner g = new Scanner(System.in);
    public static String[] data;

    public static void main(String[] args) {
        if (args[0].equals("--data") && !args[1].equals("")) {
            // the function will take the data from the user and return an array of it.
            data = fillData(args[1]);
        }
        if (data == null) {
            System.out.println("Your file is empty or not found");
        }
        state.start();


    }

    public static String[] fillData(String path) {
        try {
            File file = new File(path);
            Scanner reader = new Scanner(file);
            ArrayList<String> info = new ArrayList<>();
            while (reader.hasNextLine()) {
                info.add(reader.nextLine());
            }
            reader.close();
            return info.toArray(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void search(String[] people) {


        System.out.println("Enter a name or email to search all suitable");
        String data = g.nextLine().trim().toLowerCase();
        for (int i = 0; i < people.length; i++) {
            if (people[i].toLowerCase().contains(data)) {
                System.out.println(people[i]);
            }


        }


    }


    public static void print(String[] people) {
        for (int i = 0; i < people.length; i++) {
            System.out.println(people[i]);
        }

    }
}

class state extends Main {
    public static Scanner scanner = new Scanner(System.in);
    public static boolean isRunning = true; // an indicator of whether the program is running or not.
    public static State currentState = State.MENU; // the default state is to choose an action.

    enum State { // the enum is helping in organizing the states of the machine.
        MENU, FIND_A_PERSON, PRINT_ALL_PEOPLE, EXIT
    }

    public static void start() { // This function starts the machine.
        while (state.isRunning) {
            state.operationsManager(state.currentState);
        }


    }

    public static void operationsManager(State state) { // This function will operate differently according to the current state.
        switch (state) {
            case MENU:
                System.out.println("=== Menu ===\n1. Find a person\n2. print all people\n0. Exit");
                int action = scanner.nextInt();
                switch (action) { // this nested switch is meant to change the state.
                    case 1:
                        currentState = State.FIND_A_PERSON;
                        break;
                    case 2:
                        currentState = State.PRINT_ALL_PEOPLE;
                        break;
                    case 0:
                        currentState = State.EXIT;
                        System.out.println("Bye!");
                        isRunning = false;
                        break;

                }
                break;
            // From here, the cases will act according to the state that have been changed in the previous switch.
            case FIND_A_PERSON:
                Main.search(data);
                currentState = State.MENU;
                break;
            case PRINT_ALL_PEOPLE:
                Main.print(data);
                currentState = State.MENU;
                break;

        }
    }
}


