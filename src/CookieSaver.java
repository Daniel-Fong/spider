public class CookieSaver {
    /**
     * gets cookies from html
     * @param String html
     * @return String
     */
    public static String getCookies(String html) {
        String cookies = "";
        // loop until cookies no longer present in html
        while (html.contains("Set-Cookie: ")) {
            // set index of beginning of cookie
            int index = html.indexOf("Set-Cookie: ");
            // set offset for part that we don't need
            int offset = ("Set-Cookie: ").length();
            int endIndex = html.indexOf(";", index) + 1;
            // take cookie from that index
            String cookie = html.substring(index + offset, endIndex);
            cookies += cookie;
            String remove = "Set-Cookie: " + cookie;
            // remove cookie from string
            html = html.replace(remove, "");
            
        }
        return cookies.substring(0, cookies.length()-1);
    }
}
