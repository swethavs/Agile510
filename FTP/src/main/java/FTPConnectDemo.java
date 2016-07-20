import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

/**
 * This class loads the property file containing login credentials
 * establishes the connection to FTP server by calling SessionController
 * and asks for the command to input.
 */
public class FTPConnectDemo {

    private static final int FTP_TIMEOUT = 3000;
    private static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    private static Properties prop = new Properties();
    private static InputStream input = null;


    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {
        try {
            input = new FileInputStream("ftp.properties");
            // load a properties file
            prop.load(input);
            SessionController controller = new SessionController();
            if (!controller.setUpConnection(prop.getProperty("remoteservername"), Integer.parseInt(prop.getProperty("port"))
                    , prop.getProperty("uname"), prop.getProperty("password"))) {
                System.out.println("Connection fails");
                System.exit(1);
            }
            userIO(controller);
            controller.closeSession();
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    /**
     *This method asks the user to provide the desired command
     * and perform the operation specific to command.
     * @param controller
     */
    public static void userIO(SessionController controller) {
        System.out.println("Please enter your command:");
        String command = scanner.next();
        while (!command.equalsIgnoreCase("q")) {

            controller.execCommand(command);
            System.out.println("Please enter your command:");
            command = scanner.next();

        }
    }
}