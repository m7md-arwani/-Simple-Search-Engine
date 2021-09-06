package search;

import java.io.File;
import java.util.*;

public class Main {
    static Scanner g = new Scanner(System.in);
    public static String[] data; // Data to be searched
    public static Map<String, List<Integer>> map = new HashMap<>();

    public static void main(String[] args) {
        if (args[0].equals("--data") && !args[1].equals("")) {
            // the function will take the data from the user and return an array of it.
            data = fillData(args[1]);
        }
        if (data == null) {
            System.out.println("Your file is empty or not found");
        }
        // This function takes care of filling the hashtable to be used later in search.
        hashTable();

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
    // The hash map will take the form <A word, the lines where it's available>
    public static void hashTable() {
        for (int i = 0; i < data.length; i++) {
            String[] line = data[i].trim().split(" ");
            for (int j = 0; j < line.length; j++) {
                if (map.containsKey(line[j].toLowerCase())) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(i);
                    tmp.addAll(map.get(line[j].toLowerCase()));
                    map.put(line[j].toLowerCase(), tmp);
                } else {
                    map.put(line[j].toLowerCase(), new ArrayList<>(List.of(i)));
                }
            }
        }
    }
    // Any method
    public static void search() {


        System.out.println("Enter a name or email to search all suitable");
        String toSearch = g.nextLine().trim().toLowerCase();
        List<Integer> linesToPrint = map.get(toSearch.toLowerCase());
        if (linesToPrint == null) {
            System.out.println("No matching people found.");
        } else {
            System.out.println(linesToPrint.size() + " persons found:");
            for (Integer LineNumbers : linesToPrint) {
                System.out.println(data[LineNumbers]);
            }
        }


    }

    public static void ANY_search() {
        HashSet<Integer> num = new HashSet<>();
        System.out.println("Enter a name or email to search all suitable");
        String [] str = g.nextLine().trim().toLowerCase().split(" ");
        for (String st: str) {
            num.addAll(map.get(st));
        }
        Object[] linesNum = num.toArray();
        System.out.println(num.size() + " persons found:");
        for(int i = 0 ; i < linesNum.length; i++) {
            System.out.println(data[(int) linesNum[i]]);
        }

    }

    public static void ALL_search() {
        System.out.println("Enter a name or email to search all suitable");
        String [] str = g.nextLine().trim().toLowerCase().split(" ");
        List<String> database = new ArrayList<>(List.of(data));
        List<String> results = new ArrayList<>();

      for (String line : database) {
          if(line.toLowerCase().contains(str[0]) && line.toLowerCase().contains(str[1]) && line.toLowerCase().contains(str[2])) {
            results.add(line);
          }
      }
      if (results.size() == 0) {
          System.out.println("No matching people found.");

      } else {
          System.out.println(results.size() + " persons found:");
          for (String line: results) {
              System.out.println(line);
          }

      }








    }

    public static void NONE_search() {
        System.out.println("Enter a name or email to search all suitable");
        String [] str = g.nextLine().trim().toLowerCase().split(" ");
        HashSet<Integer> linesToDelete = new HashSet<>();
        List<String> results = new ArrayList<>();
        for(String word: str) {
            linesToDelete.addAll(map.get(word));
        }
        for (int i = 0 ; i < data.length; i++) {
            if (!linesToDelete.contains(i)) {
             results.add(data[i]);
            }
        }
        System.out.println(results.size() + " persons found:");
        for (String line: results) {
            System.out.println(line);
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
                System.out.println("Select a matching strategy: ALL, ANY, NONE");
                String str = g.nextLine();
                switch (str) {
                    case "ANY":
                        Main.ANY_search();
                        break;
                    case "ALL":
                        Main.ALL_search();
                        break;

                    case "NONE" :
                         Main.NONE_search();
                        break;
                    default:
                        System.out.println("Wrong");
                }
                currentState = State.MENU;
                break;
            case PRINT_ALL_PEOPLE:
                Main.print(data);
                currentState = State.MENU;
                break;

        }
    }
}


