package search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class dataPrep {

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
        for (int i = 0; i < Main.data.length; i++) {
            String[] line = Main.data[i].trim().split(" ");
            for (int j = 0; j < line.length; j++) {
                if (Main.map.containsKey(line[j].toLowerCase())) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(i);
                    tmp.addAll(Main.map.get(line[j].toLowerCase()));
                    Main.map.put(line[j].toLowerCase(), tmp);
                } else {
                    Main.map.put(line[j].toLowerCase(), new ArrayList<>(List.of(i)));
                }
            }
        }


    }

    // Used for print all people option.
    public static void print() {
        for (int i = 0; i < Main.data.length; i++) {
            System.out.println(Main.data[i]);
        }

    }
}
