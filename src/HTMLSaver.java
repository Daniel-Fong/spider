import java.io.File;  
import java.io.FileWriter;
import java.io.IOException;  

public class HTMLSaver {
    
    public static void saveHTML(String html, String path) {
        createFile(path);
        writeToFile(html, path);
    }

    private static void createFile(String path) {
    // fix names of paths for protected 
        try {
            File myObj = new File("C:\\Users\\dfong\\projects\\spider\\spider\\files\\" + path + ".txt");
            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
            } else {
              System.out.println("File already exists.");
            }
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

    private static void writeToFile(String html, String path) {
        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\dfong\\projects\\spider\\spider\\files\\" + path + ".txt");
            myWriter.write(html);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
}
