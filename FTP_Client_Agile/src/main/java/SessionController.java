import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

import org.apache.commons.net.ftp.FTPClient;

public class SessionController {

    private Session session;
    private ChannelSftp sftp;

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

        }else  if (command.equals("mkdir")) {
            Vector result = execmkdir();

            Iterator myiterator = result.iterator();
            while (myiterator.hasNext()) {
                ChannelSftp.LsEntry current = (ChannelSftp.LsEntry)myiterator.next();
                System.out.println(current.toString());
            }

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

    public boolean execmkdir(String foldername) {
        try {
            sftp.mkdir(foldername);
            return true;
        } catch (SftpException e) {
            e.printStackTrace();
            return false;
        }

    }

    public void closeSession() {
        session.disconnect();
    }



}
