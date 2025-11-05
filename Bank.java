import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank implements HasMenu {
    private Admin admin;
    private CustomerList customers;
    private static final String DATA_FILE = "customers.dat";

    public Bank() {
        admin = new Admin();
        customers = new CustomerList();

        loadCustomers();
        start();
        saveCustomers();
    }//End public Bank

    public static void main(String[] args) {
        new Bank();
    }//End public static void main(

    public String menu() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nBank Menu\n\n");
        sb.append("0) Exit system\n");
        sb.append("1) Login as admin\n");
        sb.append("2) Login as customer\n\n");
        sb.append("Action: ");
        return sb.toString();
    }//End public String menu

    public void start() {
        Scanner sc = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            System.out.print(menu());
            int choice = getInt(sc);
            switch (choice) {
                case 0:
                    System.out.println("Exiting system.");
                    done = true;
                    break;
                case 1:
                    if (admin.login()) {
                        startAdmin(sc);
                    } else {
                        System.out.println("Admin login failed.");
                    }//End  if else
                    break;
                case 2:
                    loginCustomer(sc);
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }//End Switch

        }//End while 

    }//End public void start

    private void startAdmin(Scanner sc) {
        boolean done = false;
        while (!done) {
            System.out.print(admin.menu());
            int choice = getInt(sc);
            switch (choice) {
                case 0:
                    done = true;
                    break;
                case 1:
                    fullCustomerReport();
                    break;
                case 2:
                    addUser(sc);
                    break;
                case 3:
                    applyInterest();
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }//End switch

        }//End while
        System.out.println("Exiting admin menu.");
    }//End private void startAdmin Scanner sc

    private void fullCustomerReport() {
        System.out.println("Full customer report");
        if (customers.isEmpty()) {
            System.out.println("(no customers)");
            return;
        }//End if

        for (Customer c : customers) {
            System.out.println(c.getReport());
        }//End for

    }//End private void fullCustomerReport

    private void addUser(Scanner sc) {
        System.out.println("Add User");
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        System.out.print("PIN: ");
        String pin = sc.nextLine().trim();
        if (name.length() == 0) {
            System.out.println("Name cannot be blank. Aborting.");
            return;
        }//End if

        if (pin.length() == 0) {
            System.out.println("PIN cannot be blank. Aborting.");
            return;
        }//End if
        Customer newCustomer = new Customer(name, pin);
        customers.add(newCustomer);
        System.out.println("Customer added: " + name);
        saveCustomers(); 
    }//End private void addUser Scanner sc

    private void applyInterest() {
        System.out.println("Apply interest");
        if (customers.isEmpty()) {
            System.out.println("(no customers)");
            return;
        }//End if

        for (Customer c : customers) {
            try {
                c.applySavingsInterest();
            } catch (NoSuchMethodError e) {
                System.out.println("ERROR: Customer.applySavingsInterest() not found. Please add it to Customer.");
                return;
            } catch (Exception ex) {
                System.out.println("Failed to apply interest for " + c.getUserName() + ": " + ex.getMessage());
            }//End try catch catch

        }//End for
        System.out.println("Interest applied to all customers.");
        saveCustomers();
    }//End private void applyInterest

    private void loginCustomer(Scanner sc) {
        System.out.print("User name: ");
        String u = sc.nextLine().trim();
        System.out.print("PIN: ");
        String p = sc.nextLine().trim();

        Customer current = null;
        for (Customer c : customers) {
            if (c.login(u, p)) {
                current = c;
                break;
            }//End for

        }//End private void loginCustomer Scanner sc

        if (current != null) {
            System.out.println("Login Successful\n");
            current.start();
            saveCustomers(); // save after customer finishes (PIN changes, deposits, etc.)
        } else {
            System.out.println("Login failed. No matching user.");
        }//End if else

    }//End private void applyInterest

    public void saveCustomers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(customers);
        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }//End try catch

    }//End public void saveCustomers

    public void loadCustomers() {
        File f = new File(DATA_FILE);
        if (!f.exists()) {
            System.out.println("No data file found (" + DATA_FILE + "). Starting with empty customer list.");
            return;
        }//End if

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            Object obj = ois.readObject();
            if (obj instanceof CustomerList) {
                customers = (CustomerList) obj;
            } else if (obj instanceof ArrayList) {
                customers = new CustomerList();
                customers.addAll((ArrayList<Customer>) obj);
            } else {
                System.out.println("Data file did not contain expected object. Starting with empty list.");
                customers = new CustomerList();
            }//End if else if else
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading customers: " + e.getMessage());
            System.out.println("Starting with empty customer list. Consider refreshing sample data.");
            customers = new CustomerList();
        }//End try catch

    }//End public void loadCustomers

    public void loadSampleCustomers() {
        customers = new CustomerList();
 
        customers.add(new Customer("Alice", "1111", 1000.00, 1000.00, 0.05));
        customers.add(new Customer("Bob", "2222", 0.00, 0.00, 0.02));
        customers.add(new Customer("Cindy", "3333", 0.00, 0.00, 0.02));
        System.out.println("Sample customers loaded.");
    }//End public void loadSampleCustomers

    private int getInt(Scanner sc) {
        try {
            String token = sc.nextLine().trim();
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            return -1;
        }//End try catch

    }//End private int getInt Scanner sc

}//End public class Bank implements HasMenu

