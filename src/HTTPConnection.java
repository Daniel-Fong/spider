import java.util.*;

import javax.net.ssl.SSLSocketFactory;

import java.net.*;
import java.net.Socket;
import java.io.*;

public class HTTPConnection {
// 52.37.173.54:443
    
    public String getWebPage(String link) {

        StringBuilder html = new StringBuilder();

        try (Socket socket = SSLSocketFactory.getDefault().createSocket("www.siliconmtn.com", 443)) {
            
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.writeBytes("GET " + link + " HTTP/1.1 \n");
            out.writeBytes("Host: " + "www.siliconmtn.com" + "\n\n");
            String inData;
            String newLine = System.lineSeparator();
            while((inData = in.readLine()) != null && (in.ready()) ) {
                html.append(inData + newLine);
            }
        } catch (Exception e) {
            System.out.println("Unable to connect to Server" + e);
        }
        return html.toString();
    }

    public String postLogin() {
        StringBuilder html = new StringBuilder();

        try (Socket socket = SSLSocketFactory.getDefault().createSocket("www.siliconmtn.com", 443)) {
            
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String requestBody = "requestType=reqBuild&pmid=ADMIN_LOGIN&emailAddress=daniel.fong%40siliconmtn.com&password=SMTRul3s%21&l=";
            int contentLength = requestBody.length();

            out.writeBytes("POST /sb/admintool HTTP/1.1 \n");
            out.writeBytes("Content-Type: application/x-www-form-urlencoded\r\n");
            out.writeBytes("Content-Length: " + contentLength + "\r\n");
            out.writeBytes("Host: " + "www.siliconmtn.com" + "\n\n");

            out.writeBytes(requestBody);
            String inData;
            String newLine = System.lineSeparator();
            while((inData = in.readLine()) != null && (in.ready()) ) {
                html.append(inData + newLine);
            }
        } catch (Exception e) {
            System.out.println("Unable to connect to Server" + e);
        }
        System.out.println(html);
        return html.toString();
    }

    public String getProtectedWebPage(String link, String cookies) {

        StringBuilder html = new StringBuilder();

        try (Socket socket = SSLSocketFactory.getDefault().createSocket("www.siliconmtn.com", 443)) {
            
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.writeBytes("GET " + link + " HTTP/1.1 \n");
            out.writeBytes("Host: " + "www.siliconmtn.com" + "\n\n");
            out.writeBytes("cookie: " + cookies);
            String inData;
            String newLine = System.lineSeparator();
            while((inData = in.readLine()) != null && (in.ready()) ) {
                html.append(inData + newLine);
            }
        } catch (Exception e) {
            System.out.println("Unable to connect to Server" + e);
        }
        System.out.println(html.toString());
        return html.toString();
    }
}


    
