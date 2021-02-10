import javax.net.ssl.SSLSocketFactory;
import java.net.Socket;
import java.io.*;

public class HTTPConnection {
    
    /**
     * Connect and get page
     * @param String link
     * @return String
     */
    public String getWebPage(String link) {
        // string builder for html
        StringBuilder html = new StringBuilder();

        try (Socket socket = SSLSocketFactory.getDefault().createSocket("www.siliconmtn.com", 443)) {
            // set up streams
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // write http request
            out.writeBytes("GET " + link + " HTTP/1.1 \n");
            out.writeBytes("Host: " + "www.siliconmtn.com" + "\n\n");
            String inData;
            String newLine = System.lineSeparator();
            // loop while reader still has data to read
            while((inData = in.readLine()) != null && (in.ready()) ) {
                // add html to string builder
                html.append(inData + newLine);
            }
        } catch (Exception e) {
            System.out.println("Unable to connect to Server" + e);
        }
        return html.toString();
    }

    public String postLogin() {
        // string builder for html
        StringBuilder html = new StringBuilder();

        try (Socket socket = SSLSocketFactory.getDefault().createSocket("www.siliconmtn.com", 443)) {
            // set up streams
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // set request body
            String requestBody = "requestType=reqBuild&pmid=ADMIN_LOGIN&emailAddress=daniel.fong%40siliconmtn.com&password=SMTRul3s%21&l=";
            // set content length header
            int contentLength = requestBody.length();

            // write post http request with headers and body
            out.writeBytes("POST /sb/admintool HTTP/1.1 \n");
            out.writeBytes("Content-Type: application/x-www-form-urlencoded\r\n");
            out.writeBytes("Content-Length: " + contentLength + "\r\n");
            out.writeBytes("Host: " + "www.siliconmtn.com" + "\n\n");
            out.writeBytes(requestBody);
            
            String inData;
            String newLine = System.lineSeparator();
             // loop while reader still has data to read
            while((inData = in.readLine()) != null && (in.ready()) ) {
                // add html to string builder
                html.append(inData + newLine);
            }
        } catch (Exception e) {
            System.out.println("Unable to connect to Server" + e);
        }
        return html.toString();
    }

    public String getProtectedWebPage(String link, String cookies) {
        // string builder for html
        StringBuilder html = new StringBuilder();

        try (Socket socket = SSLSocketFactory.getDefault().createSocket("www.siliconmtn.com", 443)) {
            // set up streams
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // write http get request with cookies to persist session
            out.writeBytes("GET " + link + " HTTP/1.1 \n");
            out.writeBytes("Host: " + "www.siliconmtn.com" + "\n");
            out.writeBytes("Cookie: " + cookies + "\n\n");
            String inData;
            String newLine = System.lineSeparator();
             // loop while reader still has data to read
            while((inData = in.readLine()) != null && (in.ready()) ) {
                // add html to string builder
                html.append(inData + newLine);
            }
        } catch (Exception e) {
            System.out.println("Unable to connect to Server" + e);
        }
        return html.toString();
    }
}


    
