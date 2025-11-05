import java.io.Serializable;

public class Admin extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    public Admin() {
        super("admin", "0000");
    }//End public Admin

    public String menu() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nAdmin Menu\n\n");
        sb.append("0) Exit this menu\n");
        sb.append("1) Full customer report\n");
        sb.append("2) Add user\n");
        sb.append("3) Apply interest to savings accounts\n\n");
        sb.append("Action: ");
        return sb.toString();
    }//End public String menu

    public void start() {
        // intentionally left blank
    }//End public void start

    public String getReport() {
        return String.format("Admin: %s | PIN: %s", getUserName(), getPIN());
    }//End public String getReport

}//End public class Admin extends User implements Serializable

