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
                execchmod(command);
        }
    }

    /**
     *This method downloads the remote file asked by the user
     * It first displays the list of files currently in the remote directory and then downloads them
     *
     */
    public static void GetSingleFileRemotely() {
        String pwd = null;
        String pathToDownload = null, YOrNo = "n";
        SessionController ctr = new SessionController();

        OutputStream os;
        try {
            pwd = sftp.pwd();
            System.out.println("The present working directory in the remote server is" +pwd);
            System.out.println("Files/directory in current working directory include");
            ctr.execCommand("ls");

            System.out.println("Do you want to navigate to a different directory? [y/n]");
            YOrNo = inp.nextLine();
            while(!YOrNo.toLowerCase().equals("y") && !YOrNo.toLowerCase().equals("n"))
            {
                System.out.println("Enter valid option");

                YOrNo = inp.nextLine();
            }

            while(YOrNo.toLowerCase().equals("y")) {
                try {
                    YOrNo = "n";
                    System.out.println("Enter path to download");
                    pathToDownload = inp.nextLine();
                    sftp.cd(pathToDownload);
                    System.out.println("you are now in path" + sftp.pwd());
                    System.out.println("Files/directory in current working directory include");
                    ctr.execCommand("ls");
                    System.out.println("Do you want to navigate to a different directory? [y/n]");
                    YOrNo = inp.nextLine();
                    while(!YOrNo.toLowerCase().equals("y") && !YOrNo.toLowerCase().equals("n"))
                    {
                        System.out.println("Enter valid option");

                        YOrNo = inp.nextLine();
                    }

                }
                catch (Exception e)
                {
                    System.out.println("There was an error in navigating" +e.getMessage());
                    System.out.println("Please enter a valid path");
                    System.out.println("Do you want to continue navigating to a different directory? [y/n]");
                    YOrNo = inp.nextLine();

                    while(!YOrNo.toLowerCase().equals("y") && !YOrNo.toLowerCase().equals("n"))
                    {
                        System.out.println("Enter valid option");

                        YOrNo = inp.nextLine();
                    }

                }

            }
            System.out.println("Enter file name to download:");
            String fileToDownload = inp.nextLine();
            System.out.println("fileToDownload is"+fileToDownload);

            input = new FileInputStream("ftp.properties");

            // load  properties file
            prop.load(input);



            if (fileToDownload == null || fileToDownload.equals("")) {
                System.out.println("Filename cannot be blank.\n");
                return;
            }

            String fDestDir = prop.getProperty("directorytodownload");
            sftp.get(fileToDownload,fDestDir);
            System.out.println("file successfully downloaded and saved in the path" +fDestDir);


        } catch (SftpException e)
        {
            System.out.println("There was an exception in downloading the file :" +e);
        }
        catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
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
            System.out.println("Can not create directory. Either directory already exist or name contains invalid symbol. Please enter correct Directory name and try again ! ");
            return false;
        }
    }

    /**
     * Change Permission
     * @param command
     * @return
     */
    public boolean execchmod(String command){
        String[] commandArgs = command.split(" ");
        String filename =null;
        int permissionType=0;
        try {
            if (commandArgs.length > 2) {
                permissionType = Integer.parseInt(commandArgs[1],8);
               filename = commandArgs[2];
                sftp.chmod(permissionType,filename);
                return true;
            } else {
                System.out.println("Please provide the command properly");
                return false;
            }
        } catch (SftpException e) {
            System.out.println("Either the file "+ filename+" does not exist" + " or permissionType "+ permissionType+ " is wrong. \n" +
                    "Please try again with correct command." );
            return false;
        }catch (NumberFormatException e) {
            System.out.println("Either the file "+ filename+" does not exist" + " or permissionType "+ permissionType+ " is wrong. \n" +
                    "Please try again with correct command." );
            return false;
        }
    }

    /**
     * This method is closing the session
     */
    public void closeSession() {
        session.disconnect();
    }



}