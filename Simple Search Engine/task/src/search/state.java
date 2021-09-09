package search;

import java.util.Scanner;

class state {
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
                scanner.nextLine();
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
                System.out.println("Select a matching strategy: ALL, ANY, NONE");
                String str = scanner.nextLine();
                switch (str) {
                    case "ANY":
                        Main.ANY_search();
                        break;
                    case "ALL":
                        Main.ALL_search();
                        break;

                    case "NONE":
                        Main.NONE_search();
                        break;
                    default:
                        System.out.println("Wrong");
                }
                currentState = State.MENU;
                break;
            case PRINT_ALL_PEOPLE:
                dataPrep.print();
                currentState = State.MENU;
                break;

        }
    }
}
