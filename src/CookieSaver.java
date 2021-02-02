public class CookieSaver {
    public static String getCookies(String html) {
        String cookies = "";
        while (html.contains("Set-Cookie: ")) {
            int index = html.indexOf("Set-Cookie: ");
            int offset = ("Set-Cookie: ").length();
            int endIndex = html.indexOf(";", index) + 1;
            // take cookie from that index
            String cookie = html.substring(index + offset, endIndex);
            cookies += cookie;
            String remove = "Set-Cookie: " + cookie;
            System.out.println(remove);
            // remove cookie from string
            html = html.replace(remove, "");
        }
        return cookies;
    }
}
