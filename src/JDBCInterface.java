import java.sql.*;
import java.util.Scanner;
import java.lang.*;
import java.sql.PreparedStatement;
import com.mysql.jdbc.Driver;

/**
 * Class JDBCInterface functions to initialize a connection to a
 * local MySQL server and provide SQL queries, constructed
 * through prepared statements, fulfilled through user input, to be returned
 * as output.
 **/

public class JDBCInterface {

    static final String DB_URL = "jdbc:mysql://localhost/foodmart";
    static final String USER = "root";
    static final String PASS = "bmth2345";

    /**
     * Main Method creates a connection to the local MySQL database
     * and takes user input into a prepared statement, through error checked
     * stages. This is sent as a pre-structured SQL query to the database and returns
     * the stored data formatted.
     * @param args
     */
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            System.out.println("Select a department...");
            System.out.format("+---------------+---------------------------+%n");
            System.out.format("| department_id | department_description    |%n");
            System.out.format("+---------------+---------------------------+%n");
            System.out.format("|             1 | HQ General Management     |%n");
            System.out.format("|             2 | HQ Information Systems    |%n");
            System.out.format("|             3 | HQ Marketing              |%n");
            System.out.format("|             4 | HQ Human Resources        |%n");
            System.out.format("|             5 | HQ Finance and Accounting |%n");
            System.out.format("|            11 | Store Management          |%n");
            System.out.format("|            14 | Store Information Systems |%n");
            System.out.format("|            15 | Permanent Checkers        |%n");
            System.out.format("|            16 | Temp Checkers             |%n");
            System.out.format("|            17 | Permanent Stockers        |%n");
            System.out.format("|            18 | Temp Stockers             |%n");
            System.out.format("|            19 | Store Permanent Butchers  |%n");
            System.out.format("+---------------+---------------------------+%n");

            Scanner statementInput = new Scanner(System.in);
            String departmentDesc = statementInput.nextLine();

            while(!departmentDesc.equals("HQ General Management") && !departmentDesc.equals("HQ Information Systems") && !departmentDesc.equals("HQ Marketing") && !departmentDesc.equals("HQ Human Resources") && !departmentDesc.equals("HQ Finance and accounting") && !departmentDesc.equals("Store Management") &&
            !departmentDesc.equals("Store Information Systems") && !departmentDesc.equals("Permanent Checkers") && !departmentDesc.equals("Temp Checkers") && !departmentDesc.equals("Permanent Stockers") && !departmentDesc.equals("Temp Stockers") && !departmentDesc.equals("Store Permanent Butchers")) {

              System.out.println("Please ensure case sensitivity and a valid department...");
              System.out.println('\n');
              departmentDesc = statementInput.nextLine();

            }

            System.out.println("Select a pay type...");
            System.out.format("+----------+%n");
            System.out.format("| pay_type |%n");
            System.out.format("+----------+%n");
            System.out.format("| Monthly  |%n");
            System.out.format("| Hourly   |%n");
            System.out.format("+----------+%n");

            Scanner payMethodInput = new Scanner(System.in);
            String payMethod = payMethodInput.nextLine();

            while(!payMethod.equals("Monthly") && !payMethod.equals("Hourly")) {

                System.out.println("Please ensure case sensitivity and a valid pay type...");
                System.out.println('\n');
                payMethod = statementInput.nextLine();

            }


            System.out.println("Select an education level...");
            System.out.format("+---------------------+%n");
            System.out.format("|   education_level   |%n");
            System.out.format("+---------------------+%n");
            System.out.format("| Graduate Degree     |%n");
            System.out.format("| Bachelors Degree    |%n");
            System.out.format("| Partial College     |%n");
            System.out.format("| High School Degree  |%n");
            System.out.format("+---------------------+%n");

            Scanner educationLevelInput = new Scanner(System.in);
            String educationLevel = educationLevelInput.nextLine();

            while(!educationLevel.equals("Graduate Degree") && !educationLevel.equals("Bachelors Degree") && !educationLevel.equals("Partial College") && !educationLevel.equals("High School Degree")) {

                System.out.println("Please ensure case sensitivity and a valid graduate degree...");
                System.out.println('\n');
                educationLevel = statementInput.nextLine();

            }

            String updateString = "SELECT DISTINCT education_level, department_description, pay_type, full_name FROM employee INNER JOIN department INNER JOIN position WHERE education_level = ? AND department_description = ? AND pay_type = ?";
            stmt = conn.prepareStatement(updateString);
            stmt.setString(1, educationLevel);
            stmt.setString(2, departmentDesc);
            stmt.setString(3, payMethod);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {

                System.out.print("full_name: " + rs.getString("full_name") + " ");
                System.out.print("education_level: " + rs.getString("education_level") + " ");
                System.out.print("department_description: " + rs.getString("department_description") + " ");
                System.out.print("pay_type: " + rs.getString("pay_type") + " " + '\n');

            }

            rs.close();
            stmt.close();
            conn.close();

        } catch(SQLException se) {

            se.printStackTrace();
        } catch(Exception e) {

            e.printStackTrace();
        } finally {

          try{

            if(stmt!=null)
            stmt.close();

            } catch(SQLException se2) {
            }
            try {
                if(conn!=null)
                    conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }

        System.out.println("Goodbye!");
    }
}





