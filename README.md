# CS121Lab9Half

> Bank

> Import java io.*, util.ArrayList, and util.Scanner
> public class Bank implements HasMenu
> > 2 normal privates for other java files  and a static final string data file
> > public bank
> > > 2 variables for both normal privates
> > > load start and save customer
> > public static void main
> > > new Bank
> > public String menu()
> > > variable for a new StringBuilder
> > > give user options
> > > return toString
> > public void start
> > > Scanner is a new scanner in system.in and boolean false
> > > > while
> > > > > print menu 
> > > > > getInt
> > > > > case 0
> > > > > > print exiting system 
> > > > > > done true
> > > > > case 1
> > > > > > if admin log
> > > > > > > startAdmin sc
> > > > > > else
> > > > > > > print Admin failed
> > > > > > break
> > > > > case 2
> > > > > > loginCustomer and break
> > > > > default
> > > > > > print invalid selection and to try again
> > startAdmin Scanner sc
> > > boolean false and while not that boolean
> > > > print adminmenu 
> > > > getInt as choice
> > > > switch choice
> > > > >  case 0
> > > > > > boolean true and break
> > > > > case 1
> > > > > > fullCustomerReport and break
> > > > > case 2
> > > > > > addUser sc and break
> > > > > case 3
> > > > > > applyInterest and break
> > > > > default
> > > > > > print invalid try again
> > > > print exit admin menu
> > private void fullCustomerReport
> > > if customer isEmpty
> > > > print no customer 
> > > > return
> > > for customer c customers
> > > > print get report
> > private void addUser
> > > ask for Users name and PIN
> > > if name length is 0
> > > > print blank error 
> > > > return
> > > if pin length is 0
> > > > print pin blank error
> > > > return
> > > make and add a new customer using given info and then saveCustomer
> > private void applyInterest
> > > if customers isEmpty
> > > > print no customers and return
> > > for customers c is customers
> > > > try
> > > > > c applySavingsInterest
> > > > catch NoSuchMethodError e
> > > > >  print Customer.applySavingsInterest not found 
> > > > > return
> > > > catch Exception ex
> > > > > print failed applying interest
> > > print applied Intrest
> > > saveCustomers
> > private void loginCustomer Scanner sc
> > > ask for name and pin and current to null
> > > for Customer c customers
> > > > if c.login u and p
> > > > > current to c and break
> > > if current not null
> > > > start Current and saveCustomer
> > > else
> > > > print failed
> > public void saveCustomers
> > > try ObjectOutputStream oos equal new ObjectOutputStream new FileOutputStream DATA_FILE
> > > > oos write object customer
> > > catch new FileOutputStream e
> > > > print e getmessage
> > public void loadCustomers
> > > File f equal new File(DATA_FILE);
> > > if not f exists
> > > > print no data found
> > > > return
> > > try ectInputStream ois equals new ObjectInputStream new FileInputStream f
> > > > obj equals ois.readObject
> > > > if instanceof CustomerList
> > > > > customers equal CustomerList obj
> > > > else if obj instanceof ArrayList
> > > > > new CustomerList and addAll ArrayList Customer obj
> > > > else
> > > > > print not expected object 
> > > > > new customers list
> > > catch IOException ClassNotFoundException e
> > > > print get message with empty container list 
> > > > new Customer list
> > public void loadSampleCustomers
> > > new CustomerList
> > > add new Customer "Alice", "1111", 1000.00, 1000.00, 0.05
> > > add new Customer "Bob", "2222", 0.00, 0.00, 0.02
> > > add new Customer "Cindy", "3333", 0.00, 0.00, 0.02
> > > print sample customers loaded
> > private int getInt Scanner sc
> > > try 
> > > > token sc nextLine trim
> > > > return Integer parseInt token
> > > catch NumberFormatException  e
> > > > return -1



> CustomerList

> import java io Serializable and util ArrayList
> public class CustomerList extends ArrayList<Customer> implements Serializable
> > private static final long serialVersionUID equals 1L
> > public CustomerList
> > > super



> Admin

> import java io Serializable
> public class Admin extends User implements Serializable
> > private static final long serialVersionUID equal 1L
> > public Admin
> > > super "admin" and "0000"
> > public String menu
> > > sb is new StringBuilder
> > > give options for menu, exit menu, customer report, add user, and apply interest to saving account
> > >  return sb toString
> > public void start
> > > leave blank
> > public String getReport
> > > return String format "Admin: %s | PIN: %s", getUserName(), and getPIN()
