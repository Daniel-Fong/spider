import java.util.*;

public class FlowManager {
    public static void runSpider() {
        HTTPConnection connection = new HTTPConnection();

        Queue<String> queue = new LinkedList<>();
        queue.add("/");

        List<String> linksArr = LinksArrayManager.createLinksArr("/");

        HTMLParser parser = new HTMLParser();

        while (queue.size() > 0) {
            String link = queue.poll();
            String html = connection.getWebPage(link);
            HTMLSaver.saveHTML(html, link);
            List<String> links = parser.getLinks(html);
            
            for(int i = 0; i < links.size(); i++) {
                if (LinksArrayManager.inArray(links.get(i), linksArr) == false) {
                    linksArr.add(links.get(i));
                    queue.add(links.get(i));
                } 
            }
        }
        String stuff = connection.postLogin();
        String cookies = CookieSaver.getCookies(stuff);

        queue.add("/sb/admintool?cPage=index&amp;actionId=FLUSH_CACHE");
        queue.add("/sb/admintool?cPage=stats&amp;actionId=FLUSH_CACHE");
        queue.add("/sb/admintool?cPage=index&amp;actionId=SCHEDULE_JOB_INSTANCE&amp;organizationId=SMT");
        queue.add("/sb/admintool?cPage=index&amp;actionId=WEB_SOCKET&amp;organizationId=SMT");
        queue.add("/sb/admintool?cPage=index&amp;actionId=ERROR_LOG&amp;organizationId=SMT");

        int count = 1;
        while (queue.size() > 0) {
                String protectedLink = queue.poll();
                String html = connection.getProtectedWebPage(protectedLink, cookies);
                HTMLSaver.saveHTML(html, "protectedLink" + count);
                count++;
        }
    }
}
