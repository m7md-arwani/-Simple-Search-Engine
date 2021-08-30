package search;

import java.util.Scanner;

public class Main {
    static Scanner g = new Scanner(System.in);
    public static String[] data;

    public static void main(String[] args) {
        // the function will take the data from the user and return an array of it.
        data = fillData();
        state.start();


    }

    public static String[] fillData() {
        int N;
        System.out.println("Enter the number of people:");
        N = g.nextInt();
        g.nextLine();
        System.out.println("Enter all people:");
        String[] people = new String[N];
        int count = 0;
        while (count != N) {
            people[count] = g.nextLine();
            count++;
        }

        return people.clone();
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


