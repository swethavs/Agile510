
/**
 * Created by Priyanka on 8/1/2016.
 */
public enum FTPCommandsEnum {
    LS("ls"),
    MKDIR("mkdir"),
    RD("rd"),
    CHMOD("chmod"),
    RM("rm"),
    RMDIR("rmdir"),
    MV("mv"),
    PUT("put"),
    LOGOUT("logout");

    private String command;

    FTPCommandsEnum(String command){
        this.command=command;
    }

    public String command(){
        return command;
    }

}
