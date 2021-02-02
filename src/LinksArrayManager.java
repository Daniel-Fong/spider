import java.util.*;

public class LinksArrayManager {

    public static List<String> createLinksArr(String firstLink) {
        List<String> linksArr = new ArrayList<>();
        linksArr.add(firstLink);
        return linksArr;
    }

    public static Boolean inArray(String link, List<String> linksArr) {
        for (int i = 0; i<linksArr.size(); i++) {
            if (linksArr.get(i).equals(link)) {
                return true;
            }
            else {
            }
        }
        return false;
    }

}
