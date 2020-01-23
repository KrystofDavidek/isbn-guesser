package cz.muni.kisk.isbnguesser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static cz.muni.kisk.isbnguesser.Book.ACTIVE_ATTRIBUTES;

/**
 * @author Krystof Davidek
 */
public class Guesser {

    private final boolean isSame;
    private final Map<String, Double> attrSimilarities;
    private final Double guess;
    private final Book book1;
    private final Book book2;

    public Guesser(int isSame, Book book1, Book book2)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        this.isSame = isSame == 1;
        this.book1 = book1;
        this.book2 = book2;
        this.attrSimilarities = getAttrSimilarities(book1, book2);
        this.guess = guess();
    }

    public Map<String, Double> getAttrSimilarities() {
        return new HashMap<String, Double>(attrSimilarities);
    }

    public Double getGuess() {
        return guess;
    }

    public boolean isSame() {
        return isSame;
    }

    public boolean getIsSame() {
        return isSame;
    }

    public Book getBook1() {
        return book1;
    }

    public Book getBook2() {
        return book2;
    }

    private Map<String, Double> getAttrSimilarities(Book book1, Book book2)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Double> similarities = new HashMap<String, Double>();
        for (String attr : ACTIVE_ATTRIBUTES) {
            String method = attr.substring(0, 1).toUpperCase() + attr.substring(1);
            String getMethodName = "get" + method;
            Method getMethod = book1.getClass().getMethod(getMethodName);
            String getResult1 = (String) getMethod.invoke(book1);
            String getResult2 = (String) getMethod.invoke(book2);
            if (!(getResult1.equals("") || getResult2.equals(""))) {
                similarities.put(attr, Utils.getStringSimilarity(getResult1, getResult2));
            }
        }
        return similarities;
    }

    private Double guess() {
        Double guess = 0.0;
        int count = attrSimilarities.size();
        for (Double similarity : attrSimilarities.values()) {
            guess += similarity;
        }
        return guess / count;
    }

    @Override
    public String toString() {
        String string = String.valueOf(isSame);
        String guess = String.valueOf(this.guess);
        return "Are same: " + string + ",  guess: " + guess + "\n";
    }
}
