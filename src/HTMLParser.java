import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParser {

    /**
     * parses links from html
     * @param String html
     * @return List<String> linksArr
     */
    public List<String> getLinks (String html) {
        // jsoup parse on html
        Document document = Jsoup.parse(html);
        // select hrefs from parsed html
        Elements links = document.select("a[href]"); 
        List<String> linksArr = new ArrayList<>();
        // loop through links
        for (Element link : links) {
            // stringify link
            String linkStr = link.attr("href").toString();
            // if absolute link
            if (linkStr.contains("www.siliconmtn.com")) {
                // format link
                linkStr = linkStr.replaceFirst("http://", "");
                linkStr = linkStr.replaceFirst("www.siliconmtn.com", "");
                // add to linksArr
                linksArr.add(linkStr);
            }
            // if relative link
            else if (linkStr.charAt(0) == '/' && linkStr.length() > 1) {
                // add to linksArr
                linksArr.add(linkStr);
            }
        }
        return linksArr;
    }
    
}
