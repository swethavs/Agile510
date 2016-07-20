import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;
import java.util.Vector;


public class SessionController {

    private Session session;
    private static ChannelSftp sftp;
    static Scanner inp = new Scanner(System.in);
    private static Properties prop = new Properties();
    private static InputStream input = null;

    /**
     * This method establishes the connection to FTP server
     * @param host
     * @param port
     * @param user
     * @param password
     * @return
     */
    public boolean setUpConnection(String host, int port, String user, String password) {
        JSch jsch = new JSch();
        try {
            Session session = jsch.getSession(user, host, port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect(3000);
            Channel channel = session.openChannel("sftp");
            sftp = (ChannelSftp) channel;
            sftp.connect(3000);
        } catch (JSchException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * This method is executing the commands asked by user.
     * @param command
     */
    public void execCommand(String command) {
        if (command.equals("ls")) {
            Vector result = execls();
            Iterator myiterator = result.iterator();
            while (myiterator.hasNext()) {
                ChannelSftp.LsEntry current = (ChannelSftp.LsEntry)myiterator.next();
                System.out.println(current.toString());
            }
        }else if (command.startsWith("mkdir")) {
            execmkdir(command);
        } else if (command.equals("rd")) {
            try{
                GetSingleFileRemotely();
            }
            catch(Exception e) {
                System.out.println("Error while getting the file remotely" +e);
            }
        }else if (command.startsWith("chmod")){
            try {
                execchmod(command);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }else if(command.equals("quit") || command.equals("Quit") ){
            try{
                execQuit(command);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     *
     * @throws Exception
     */
    public static void GetSingleFileRemotely() throws Exception  {
        try {
            input = new FileInputStream("ftp.properties");
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // load a properties file
        prop.load(input);
        System.out.println("Enter file name to download:");
        String fileToDownload = inp.nextLine();
        OutputStream os;
        String pwd = null;
        try {
            pwd = sftp.pwd();
            System.out.println("\nThe present working directory in the remote server is" +pwd);
            System.out.println("fileToDownload is"+fileToDownload);
            SessionController ctr = new SessionController();
            ctr.execCommand("ls");
        } catch (SftpException e) {
            e.printStackTrace();
        }
        /*if (fileToDownload.trim().isEmpty()) {
            System.out.println("Filename cannot be blank.\n");
            return;
        }*/
        try {
            //String fdest = "/Users/madusudanan/Downloads/scheduler.txt";
            String fDestDir = prop.getProperty("directorytodownload");
            sftp.get(fileToDownload,fDestDir);
            System.out.println("file successfully downloaded and saved in the path" +fDestDir);


        } catch (SftpException e)
        {
            System.out.println("There was an exception in downloading the file stacktrace:" +e);
        }
        catch(Exception e)
        {
            System.out.println("\nThere was an error in downloading the file" +e);
        }
        finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     *
     * @return
     */
    public Vector execls() {
        Vector vector = null;
        try {
            vector = sftp.ls(".");
        } catch (SftpException e) {
            System.out.println("ls exception");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return vector;
    }

    /**
     * This method is making the directory on remote
     * @param command
     * @return
     */
    public boolean execmkdir(String command) {
        String[] commandArgs = command.split(" ");
        try {
            if (commandArgs.length > 1) {
                sftp.mkdir(commandArgs[1]);
                return true;
            } else {
                System.out.println("Please provide the directory name");
                return false;
            }
        } catch (SftpException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method handles the change permission commands.
     * @param command
     * @return
     */
    public boolean execchmod(String command){
        String[] commandArgs = command.split(" ");
        try {
            if (commandArgs.length > 2) {
                int permissionType = Integer.parseInt(commandArgs[1]);
                String filename = commandArgs[2];
                sftp.chmod(permissionType,filename);
                return true;
            } else {
                System.out.println("Please provide the command properly");
                return false;
            }
        } catch (SftpException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean execQuit(String command){
        if(command.equals("quit") || command.equals("Quit") ){
            sftp.quit();
        }
        return true;
    }
    /**
     * This method is closing the session
     */
    public void closeSession() {
        session.disconnect();
    }



}
