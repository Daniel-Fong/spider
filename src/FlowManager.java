import java.util.*;

/**
 * Controls flow of spider app
 */
public class FlowManager {
    public static void runSpider() {
        // instantiate HTTPConnection class
        HTTPConnection connection = new HTTPConnection();

        // create queue for links
        Queue<String> queue = new LinkedList<>();
        queue.add("/");

        // add root link to queue
        List<String> linksArr = LinksArrayManager.createLinksArr("/");

        // instantiate HTMLParser class
        HTMLParser parser = new HTMLParser();

        // Loop through queue until empty
        while (queue.size() > 0) {
            // grab first link in queue
            String link = queue.poll();
            // send get request to link and store as html variable
            String html = connection.getWebPage(link);
            // Save html
            HTMLSaver.saveHTML(html, link);
            // get links from current page
            List<String> links = parser.getLinks(html);
            
            // Loop through links
            for(int i = 0; i < links.size(); i++) {
                // if links are not already in the visited links array
                if (LinksArrayManager.inArray(links.get(i), linksArr) == false) {
                    // add link to visited links array
                    linksArr.add(links.get(i));
                    // add link to queue
                    queue.add(links.get(i));
                } 
            }
        }
        // post login, get post response
        String postResponse = connection.postLogin();
        // get cookies
        String cookies = CookieSaver.getCookies(postResponse);

        // add links to queue
        queue.add("/sb/admintool?cPage=index&amp;actionId=FLUSH_CACHE");
        queue.add("/sb/admintool?cPage=stats&amp;actionId=FLUSH_CACHE");
        queue.add("/sb/admintool?cPage=index&amp;actionId=SCHEDULE_JOB_INSTANCE&amp;organizationId=SMT");
        queue.add("/sb/admintool?cPage=index&amp;actionId=WEB_SOCKET&amp;organizationId=SMT");
        queue.add("/sb/admintool?cPage=index&amp;actionId=ERROR_LOG&amp;organizationId=SMT");

        int count = 1;
        // Loop through links in queue until empty
        while (queue.size() > 0) {
                // grab link
                String protectedLink = queue.poll();
                String html = connection.getProtectedWebPage(protectedLink, cookies);
                HTMLSaver.saveHTML(html, "protectedLink" + count);
                count++;
        }
    }
}
