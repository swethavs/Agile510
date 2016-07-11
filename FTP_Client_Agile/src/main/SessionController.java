package FTP_Client.sample;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
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

    public void execCommand(String command) {
        if (command.equals("ls")) {
            Vector result = execls();

            Iterator myiterator = result.iterator();
            while (myiterator.hasNext()) {
                ChannelSftp.LsEntry current = (ChannelSftp.LsEntry)myiterator.next();
                System.out.println(current.toString());
            }

        }
        else if (command.equals("rd"))
        {
            try{
                GetSingleFileRemotely();
            }
            catch(Exception e)
            {
                System.out.println("Error while getting the file remotely" +e);
            }
        }else if(command.startsWith("mkdir")){
            String[] splittedString=command.split(" ");
            if(splittedString.length>1) {
                execmkdir(splittedString[1]);
            }else{
                System.out.println("Please provide the folder name");
            }
        }
    }

    public void execmkdir(String foldername) {
        if(foldername!=null){
            try {
                sftp.mkdir(foldername);
                System.out.println("Folder is created");
            } catch (SftpException e) {
                System.out.println( " It seems folder already present");
            }
        } else{
            System.out.println("Please provide the folder name");
        }
    }
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

        if (fileToDownload.trim().isEmpty()) {
            System.out.println("Filename cannot be blank.\n");
            return;
        }

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
    public void closeSession() {
        session.disconnect();
    }








}
