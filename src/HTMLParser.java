import java.util.*;
import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class HTMLParser {

    public static List<String> getLinks (String html) {
        Document document = Jsoup.parse(html);
        Elements links = document.select("a[href]"); 
        List<String> linksArr = new ArrayList<>();
        for (Element link : links) {
            String linkStr = link.attr("href").toString();
            if (linkStr.contains("www.siliconmtn.com")) {
                linkStr = linkStr.replaceFirst("http://", "");
                linkStr = linkStr.replaceFirst("www.siliconmtn.com", "");
                linksArr.add(linkStr);
            }
            else if (linkStr.charAt(0) == '/' && linkStr.length() > 1) {
                linksArr.add(linkStr);
            }
        }
        return linksArr;
    }
    
}
