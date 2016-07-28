import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class FTPTest {

    @Test
    public void showConnectSuccess() {
        SessionController controller = new SessionController();
        assertThat(controller.setUpConnection(setHost(), setPort(), setUser(), setPass()), equalTo(true));
    }

    @Test
    public void showConnectFailure() {
        SessionController controller = new SessionController();
        assertThat(controller.setUpConnection(setHost(), setPort(), setUser(), "Whatever"), equalTo(false));
    }

    public String setUser() {
        return "pardeexi";
    }

    public String setHost() {
        return "linux.cs.pdx.edu";
    }

    public String setPass() {
        return "pvBze+7z2g";
    }

    public int setPort() {
        return 22;
    }

}
