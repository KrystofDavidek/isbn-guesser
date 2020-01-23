package cz.muni.kisk.isbnguesser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Krystof Davidek
 */
public class Book {

    private final Object au = new ArrayList<Object>();
    private final Object conspect = new ArrayList<Object>();
    private final Object conspgr = new ArrayList<Object>();
    private final Object series = new ArrayList<Object>();
    private final Object subject = new ArrayList<Object>();
    private final Object form = new ArrayList<Object>();
    private final Object nbn = new ArrayList<Object>();
    private final Object pages = new ArrayList<Object>(); // only duplicates in input
    private final Object date = new ArrayList<Object>();
    private final Object edition = new ArrayList<Object>();
    private final Object isbn = new ArrayList<Object>();
    private final Object title = new ArrayList<Object>();
    private final Object part = new ArrayList<Object>();
    private final Object resp = new ArrayList<Object>();
    private final Object place = new ArrayList<Object>();
    private final Object pub = new ArrayList<Object>();
    private final Object subtitle = new ArrayList<Object>();

    private Integer id;
    private final static String STRING_TYPE = "String";
    public final static List<String> ACTIVE_ATTRIBUTES = List.of(
            "title",
            "subtitle",
            "au",
            "resp",
            "pub",
            "date",
            "place",
            "pages",
            "conspgr",
            "subject"
    );

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return Utils.normalize(fromObjectToString(title));
    }

    public String getSubtitle() {
        return Utils.normalize(fromObjectToString(subtitle));
    }

    public String getAu() {
        return Utils.normalize(fromObjectToString(au));
    }

    public String getResp() {
        return Utils.normalize(fromObjectToString(resp));
    }

    public String getPub() {
        return Utils.normalize(fromObjectToString(pub));
    }

    public String getDate() {
        return Utils.normalize(fromObjectToString(date));
    }

    public String getPlace() {
        return Utils.normalize(fromObjectToString(place));
    }

    public String getPages() {
        return Utils.normalize(fromObjectToString(pages));
    }

    public String getConspect() {
        return Utils.normalize(fromObjectToString(conspect));
    }

    public String getSeries() {
        return Utils.normalize(fromObjectToString(series));
    }

    public String getForm() {
        return Utils.normalize(fromObjectToString(form));
    }

    public String getNbn() {
        return Utils.normalize(fromObjectToString(nbn));
    }

    public String getEdition() {
        return Utils.normalize(fromObjectToString(edition));
    }

    public String getIsbn() {
        return Utils.normalize(fromObjectToString(isbn));
    }

    public String getPart() {
        return Utils.normalize(fromObjectToString(part));
    }

    public String getConspgr() {
        return Utils.normalize(fromObjectToString(conspgr));
    }

    public String getSubject() {
        return Utils.normalize(fromObjectToString(subject));
    }

    private String fromObjectToString(Object inputArray) {
        if (inputArray.getClass().getSimpleName().equals(STRING_TYPE)) {
            return (String) inputArray;
        }
        StringBuilder arrString = new StringBuilder();
        ArrayList<String> stringArray = (ArrayList<String>) inputArray;
        if (stringArray.isEmpty()) {
            return "";
        }
        String auType = (stringArray).get(0).getClass().getSimpleName();
        if (!auType.equals(STRING_TYPE)) {
            return "";
        }
        for (String element : stringArray) {
            arrString.append(element).append(' ');
        }
        return arrString.toString();
    }

    @Override
    public String toString() {
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();
        return gson.toJson(this);
    }
}
