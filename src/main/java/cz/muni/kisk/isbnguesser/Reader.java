package cz.muni.kisk.isbnguesser;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Krystof Davidek
 */
public class Reader {

    public static List<Guesser> read(String path, int maxPairs) throws
            IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        try (InputStream is = new FileInputStream(path)) {
            return read(is, maxPairs);
        }
    }

    public static List<Guesser> read(InputStream is, int maxPairs) throws
            IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Book[] pair = new Book[2];
        int next = 0;
        int pairs = 0;
        Guesser guesser = null;
        List<Guesser> guessers = new ArrayList<Guesser>();
        String[] firstLine = new String[3];
        Gson g = new Gson();
        BufferedReader br = new BufferedReader((new InputStreamReader(is)));

        while (br.ready() && pairs < maxPairs) {
            if (next == 0) {
                firstLine = br.readLine().split(" ");
            } else if (next == 1) {
                pair[0] = g.fromJson(br.readLine(), Book.class);
                pair[0].setId(Integer.parseInt(firstLine[1]));
            } else if (next == 2) {
                pair[1] = g.fromJson(br.readLine(), Book.class);
                pair[1].setId(Integer.parseInt(firstLine[2]));
                guesser = new Guesser(Integer.parseInt(firstLine[0]), pair[0], pair[1]);
                guessers.add(guesser);
                pairs += 1;
                next = -1;
            }
            next++;
        }
        return guessers;
    }
}
