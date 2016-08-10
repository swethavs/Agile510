import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class ITtest {

    private static Properties prop = new Properties();
    private static InputStream input = null;
    SessionController controller = null;
    String out;
    ByteArrayOutputStream newOut;

    @Before
    public void setUpConnect() {
        controller = new SessionController();
        try {
            input = new FileInputStream("ftp.properties");
            prop.load(input);
            String uname = "pardeexi";
            String password = "";
            controller.SetUpConnection(prop.getProperty("remoteservername"), prop.getProperty("port"), uname, password);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        newOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOut));
        //out = newOut.toString();


    }

    @Test
    public void showWorkFlow() {
        FTPConnectDemo.isLoggedIn = true;
        controller.ExecCommand("mkdir directoryfortest");
        assertThat(controller.CheckFileExist("directoryfortest"), equalTo(true));
        controller.ExecCommand("ls");
        controller.ExecCommand("mv directoryfortest directoryfortest2");
        assertThat(controller.CheckFileExist("directoryfortest2"), equalTo(true));
        controller.ExecCommand("rmdir directoryfortest2");
        assertThat(controller.CheckFileExist("directoryfortest2"), equalTo(false));
        controller.ExecCommand("logout");
        out = newOut.toString();
        assertThat(out, containsString("test3"));
        assertThat(out, containsString("You have been logged out!"));

    }



}
