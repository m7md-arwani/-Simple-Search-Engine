package search;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner g = new Scanner(System.in);
        String[] str = g.nextLine().trim().split(" ");
        String search = g.nextLine().trim();
        boolean isFound = false;
        for (int i = 0; i < str.length; i++) {
            if (str[i].equals(search)) {
                System.out.println(i + 1);
                isFound = true;
            }

        }
        if (!isFound) {
            System.out.println("Not found");
        }
    }
}
