package search;
import java.util.*;

public class Main {
    static Scanner g = new Scanner(System.in);
    public static String[] data; // Data to be searched
    public static Map<String, List<Integer>> map = new HashMap<>();

    public static void main(String[] args) {
        if (args[0].equals("--data") && !args[1].equals("")) {
            // the function will take the data from the user and return an array of it.
            data = dataPrep.fillData(args[1]);
        }
        if (data == null) {
            System.out.println("Your file is empty or not found");
        }
        // This function takes care of filling the hashMap "map" to be used later in search.
        dataPrep.hashTable();

        state.start();


    }


    public static void ANY_search() {
        HashSet<Integer> num = new HashSet<>();
        System.out.println("Enter a name or email to search all suitable");
        String[] str = g.nextLine().trim().toLowerCase().split(" ");
        for (String st : str) {
            num.addAll(map.get(st));
        }
        Object[] linesNum = num.toArray();
        System.out.println(num.size() + " persons found:");
        for (Object o : linesNum) {
            System.out.println(data[(int) o]);
        }

    }

    public static void ALL_search() {
        System.out.println("Enter a name or email to search all suitable");
        String[] str = g.nextLine().trim().toLowerCase().split(" ");
        List<String> database = new ArrayList<>(List.of(data));
        List<String> results = new ArrayList<>();

        for (String line : database) {
            if (line.toLowerCase().contains(str[0]) && line.toLowerCase().contains(str[1]) && line.toLowerCase().contains(str[2])) {
                results.add(line);
            }
        }
        if (results.size() == 0) {
            System.out.println("No matching people found.");

        } else {
            System.out.println(results.size() + " persons found:");
            for (String line : results) {
                System.out.println(line);
            }

        }


    }

    public static void NONE_search() {
        System.out.println("Enter a name or email to search all suitable");
        String[] str = g.nextLine().trim().toLowerCase().split(" ");
        HashSet<Integer> linesToDelete = new HashSet<>();
        List<String> results = new ArrayList<>();
        for (String word : str) {
            linesToDelete.addAll(map.get(word));
        }
        for (int i = 0; i < data.length; i++) {
            if (!linesToDelete.contains(i)) {
                results.add(data[i]);
            }
        }
        System.out.println(results.size() + " persons found:");
        for (String line : results) {
            System.out.println(line);
        }


    }


}




