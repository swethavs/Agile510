import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class FTPTest {

    private static Properties prop = new Properties();
    private static InputStream input = null;
    SessionController controller = null;
    ByteArrayOutputStream newOut;

    @Before
    public void setUpConnect() {


        controller = new SessionController();
        try {
            input = new FileInputStream("ftp.properties");
            prop.load(input);
            String uname = "pardeexi";
            String password = "wuyn*M45tm";
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
    public void showConnectSuccess() {
        controller = new SessionController();
        assertThat(controller.SetUpConnection(setHost(), setPort(), setUser(), setPass()), equalTo(true));
        //assertThat(out, containsString("test3"));
    }

    @Test
    public void showConnectFailure() {
        controller = new SessionController();
        assertThat(controller.SetUpConnection(setHost(), setPort(), setUser(), "Whatever"), equalTo(false));
    }

    public String setUser() {
        return "pardeexi";
    }

    public String setHost() {
        return "linux.cs.pdx.edu";
    }

    public String setPass() {
        return "";
    }

    public String setPort() {
        return "22";
    }

}
