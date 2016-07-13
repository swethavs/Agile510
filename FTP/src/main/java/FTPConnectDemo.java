import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTPClient;

public class FTPConnectDemo {

    /*private static final int FTP_TIMEOUT = 3000;
    private static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
*/
  /*  public static void main(String[] args) {

        //String host = "linuxlab.cs.pdx.edu";
        String host = args[0];

        int port = Integer.parseInt(args[1]);

        //String user = "pmudgal";
        String user = args[2];

        //String password;
        String pass = args[3];

        SessionController controller = new SessionController();

        if (!controller.setUpConnection(host, port, user, pass)) {
            System.out.println("Connection fails");
            System.exit(1);
        }

        userIO(controller);
        controller.closeSession();

    }

    //FTPClient ftpClient = new FTPClient();

    public static void userIO(SessionController controller) {
        System.out.println("Please enter your command:");
        String command = scanner.next();
        while (!command.equalsIgnoreCase("q")) {

            controller.execCommand(command);
            System.out.println("Please enter your command:");
            command = scanner.next();

        }
    }*/

    private static final int FTP_TIMEOUT = 3000;
    private static Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    private static Properties prop = new Properties();
    private static InputStream input = null;



    public static void main(String[] args) {


        try {
            input = new FileInputStream("ftp.properties");


            // load a properties file

            prop.load(input);


            SessionController controller = new SessionController();

            if (!controller.setUpConnection(prop.getProperty("remoteservername"), Integer.parseInt(prop.getProperty("port"))
                    , prop.getProperty("uname"), prop.getProperty("password"))) {
               /* if (!controller.setUpConnection("linuxlab.cs.pdx.edu", 22,"pmudgal","vyzX6$2xbf")){
//                    , prop.getProperty("uname"), prop.getProperty("password"))) {*/

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


    //FTPClient ftpClient = new FTPClient();

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