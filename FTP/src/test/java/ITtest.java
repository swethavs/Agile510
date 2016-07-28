import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class ITtest {
    /*
    private static Properties prop = new Properties();
    private static InputStream input = null;
    SessionController controller = null;
    String out;

    @Before
    public void setUpConnect() {
        controller = new SessionController();
        try {
            input = new FileInputStream("ftp.properties");
            prop.load(input);
            controller.SetUpConnection(prop.getProperty("remoteservername"), Integer.parseInt(prop.getProperty("port"))
                    , prop.getProperty("uname"), prop.getProperty("password"));
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();

        System.setOut(new PrintStream(newOut));
        out = newOut.toString();

    }

    @Test
    public void showWorkFlow() {
        controller.ExecCommand("ls");
        assertThat(out, containsString("test3"));
    }


*/
}
