import java.util.*;

public class LinksArrayManager {

    /**
     * creates queue
     * @param String firstLink
     * @return List<String> linksArr
     */
    public static List<String> createLinksArr(String firstLink) {
        List<String> linksArr = new ArrayList<>();
        // add first link to linksArr
        linksArr.add(firstLink);
        return linksArr;
    }

    /**
     * 
     * @param String link
     * @param List<String> linksArr
     * @return
     */
    public static Boolean inArray(String link, List<String> linksArr) {
        // loop through links arr
        for (int i = 0; i<linksArr.size(); i++) {
            // if in links arr, return true
            if (linksArr.get(i).equals(link)) {
                return true;
            }
            else {
            }
        }
        // if loop through entire array and not there, return false
        return false;
    }

}
