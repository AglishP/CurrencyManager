package main;

import java.util.List;

public class Output {

    public static void printList(List<Transaction> list) {
        list.stream()
                .forEach(t -> showMessage(t.toString()));
    }


    public static void showMessage(String message) {
        System.out.println(message);
    }
}
