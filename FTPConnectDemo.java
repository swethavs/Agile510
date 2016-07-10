/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.jcraft.jsch.Channel
 *  com.jcraft.jsch.ChannelSftp
 *  com.jcraft.jsch.JSch
 *  com.jcraft.jsch.JSchException
 *  com.jcraft.jsch.Session
 *  com.jcraft.jsch.SftpException
 *  org.apache.commons.net.ftp.FTPClient
 */
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.PrintStream;
import org.apache.commons.net.ftp.FTPClient;

public class FTPConnectDemo {
    private static final int FTP_TIMEOUT = 3000;

    public static void main(String[] args) {
        FTPClient ftpClient = new FTPClient();
        String user = "pmudgal";
        String host = "linuxlab.cs.pdx.edu";
        int port = 22;
        String pass = ""; //Pass the password here
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(pass);
            session.connect(3000);
            Channel channel = session.openChannel("sftp");
            ChannelSftp sftp = (ChannelSftp)channel;
            sftp.connect(3000);
            sftp.mkdir("testing");
            System.out.println("Connected");
        }
        catch (JSchException e) {
            e.printStackTrace();
        }
        catch (SftpException e) {
            e.printStackTrace();
        }
    }
}