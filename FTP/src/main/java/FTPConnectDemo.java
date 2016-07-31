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
    private static String uname = null;
    private static String password = null;

    public static boolean isLoggedIn = false;
    public static SessionController controller = new SessionController();


    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {
        try {
            input = new FileInputStream("ftp.properties");
            // load a properties file
            prop.load(input);
            System.out.println("This program provides remote FTP service for CAT PSU");
            createsession(controller);

            userIO(controller);
            controller.CloseSession();
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static void createsession(SessionController controller) {
        System.out.println("Enter the user name: ");
        uname = scanner.next();
        System.out.println("Enter the password: ");
        password = scanner.next();
        isLoggedIn = controller.login(prop.getProperty("remoteservername"), Integer.parseInt(prop.getProperty("port"))
                , uname, password);
        if (!isLoggedIn) {
            System.out.println("Connection fails");
            System.exit(1);
        }
        System.out.println("Available commands: \nls: List all file and directories\n" +
                "mkdir: Create directory on remote server\n" +
                "rd: Get file from remote server\n" +
                "chmod: Change permissions on remote server\n" +
                "rm: Remove file or directory on remote server\n" +
                "mv: Rename file or directory on remote server\n" +
                "put: Put file on remote server\n" +
                "logout: log out\n");
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
            if (isLoggedIn){

            controller.ExecCommand(command);
            System.out.println("Please enter your command:");
            command = scanner.next();
            }
            else{
                System.out.println("You are not logged in! Please login before proceeding");
                createsession(controller);

            }

        }
    }
}