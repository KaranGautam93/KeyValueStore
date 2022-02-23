import store.Pair;
import store.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            String[] commandAndAttribute = command.split(" ");

            if (commandAndAttribute[0].equals("exit")) {
                break;
            } else if (commandAndAttribute[0].equals("get")) {
                if (store.get(commandAndAttribute[1]) == null) {
                    System.out.println("No key found");
                } else {
                    System.out.println(store.get(commandAndAttribute[1]));
                }
            } else if (commandAndAttribute[0].equals("put")) {
                String key = commandAndAttribute[1];

                List<Pair<String, String>> list = new ArrayList<>();
                int i = 2;

                for (i = 2; i + 1 < commandAndAttribute.length; i += 2) {
                    Pair<String, String> pair = new Pair<>(commandAndAttribute[i], commandAndAttribute[i + 1]);
                    ThreadedPut threadedPut = new ThreadedPut(store, key, pair);
                    threadedPut.start();
                }
            } else if (commandAndAttribute[0].equals("keys")) {
                System.out.println(store.keys());
            } else if (commandAndAttribute[0].equals("delete")) {
                store.delete(commandAndAttribute[1]);
            } else {
                break;
            }

        }
    }
}
