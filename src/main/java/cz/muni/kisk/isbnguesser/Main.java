package cz.muni.kisk.isbnguesser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private final static Double THRESHOLD = 0.885;
    private final static boolean IS_SAME = true;
    private final static int MAX_PAIRS = 1;

    public static void main(String[] args) {
        try {
            List<Guesser> guessers = new ArrayList<Guesser>();
            if (args.length != 0) {
                guessers = Reader.read(args[0], MAX_PAIRS);
            } // another else branches only for testing matters
            else if (new File("src/data/czbib-isbn-trn-100k.txt").exists()) {
                guessers = Reader.read("src/data/czbib-isbn-trn-100k.txt", MAX_PAIRS);
            } else {
                try (InputStream is = Main.class.getClassLoader().getResourceAsStream("src/data/czbib-isbn-trn-100k.txt")) {
                    guessers = Reader.read(is, MAX_PAIRS);
                }
            }
            test(guessers);
        } catch (IOException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void test(List<Guesser> guessers) {
        int total = 0;
        int good = 0;
        int bad = 0;
        for (Guesser guesser : guessers) {
            if (guesser.isSame() == IS_SAME && guesser.getGuess() < THRESHOLD) { // < if IS_SAME = true
                bad++;
                total++;
            } else if (guesser.isSame() == IS_SAME && guesser.getGuess() >= THRESHOLD) { // > if IS_SAME = true
                good++;
                total++;
            }
        }
        if (guessers.size() == 1) {
            System.out.println(guessers.get(0));
        } else {
            System.out.println(
                    "Good guesses: " + String.valueOf(good) +
                            "\nBad guesses: " + String.valueOf(bad) +
                            "\nTotal: " + String.valueOf(total) +
                            "\nPercents of bad guesses: " + String.valueOf((bad * 100) / total) + "%"
            );
        }
    }

}
