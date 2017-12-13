package database;

import java.awt.*;
import java.sql.*;

public class SimpleDb extends Component {
    private String firstName;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SimpleDb m = new SimpleDb();
        m.testDatabase();
    }

    private void testDatabase() throws ClassNotFoundException, SQLException {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/contactdb";
            String login = "postgres";
            String password = "postgres";
            Connection con = DriverManager.getConnection(url, login, password);
            try {
                select(con);
                //selectrange(con, 10,14);
                //insert(con, "Ivan", "Ishchenko", "+380663321456", "asdfs@gmail.com");
                //updatef(con, 8,"Petro");
                //updateall(con, "Eduard", "Babaiko", "+380987415263","babaiko@gmail.com", 3);

                //delete(con, 11);
                //deleteByName(con,"Ivan");
                //deleteAfter(con, 14);

            } finally {
                con.close();
            }
    }

    private void select(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        try {

            ResultSet rs = stmt.executeQuery("SELECT * FROM JC_CONTACT");
            try {
                while (rs.next()) {
                    String str = rs.getString("contact_id") + ":" + rs.getString(2);
                    System.out.println("Contact:" + str);
                }
            } finally {
                rs.close();
            }
        } finally {
            stmt.close();
        }
    }

    private void selectrange(Connection con, int minElement, int maxElement) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM JC_CONTACT WHERE CONTACT_ID BETWEEN '" + minElement + "' AND '" + maxElement + "'");

        while (rs.next()) {
                String str = rs.getString("contact_id") + ":" + rs.getArray(1);
                System.out.println("Contact:" + str);
        }
        rs.close();
        stmt.close();
    }

    private void insert(Connection con, String firstName, String lastName, String phone, String email) throws SQLException {
        Statement stmt = con.createStatement();
        String sql = "INSERT INTO JC_CONTACT " +
                "(FIRST_NAME, LAST_NAME, PHONE, EMAIL) VALUES ('" + firstName + "', '" + lastName + "', '" + phone + "', '" + email + "')";
        stmt.executeUpdate(sql);
        stmt.close();
    }

    private void updatef(Connection con, int contact_id, String firstName) throws SQLException {

        String sql = "UPDATE JC_CONTACT SET" + " FIRST_NAME =? " + ", LAST_NAME='Terot', PHONE='+380997896541', EMAIL='terotr@gmail.com' "
                + " WHERE CONTACT_ID =?";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(2, contact_id);
            preparedStatement.setString(1, firstName);
            preparedStatement.executeUpdate();
            System.out.println("Record is updated to CONTACT_JC table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    private void updateall(Connection con, String firstName, String lastName, String phone, String email, int contact_id) throws SQLException {

        String sql = "UPDATE JC_CONTACT SET FIRST_NAME =?, LAST_NAME=?, PHONE=?, EMAIL=? "
                + " WHERE CONTACT_ID =?";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, email);
            preparedStatement.setInt(5, contact_id);
            preparedStatement.executeUpdate();
            System.out.println("Record is updated to CONTACT_JC table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    private void delete(Connection con, int contact_id) throws SQLException {

        PreparedStatement preparedStatement = null;
        String delete = "DELETE FROM JC_CONTACT WHERE CONTACT_ID = ?";
        try {
            preparedStatement = con.prepareStatement(delete);
            preparedStatement.setInt(1, contact_id);
            preparedStatement.executeUpdate();
            System.out.println("Record " + contact_id + " is deleted!");
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (con != null) {
                con.close();
            }

        }
    }

    private void deleteByName(Connection con, String firstName) throws SQLException {

        PreparedStatement preparedStatement = null;
        String delete = "DELETE FROM JC_CONTACT WHERE FIRST_NAME = ?";
        try {
            preparedStatement = con.prepareStatement(delete);
            preparedStatement.setString(1, firstName);
            preparedStatement.executeUpdate();
            System.out.println("Record " + firstName + " is deleted!");
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (con != null) {
                con.close();
            }

        }
    }

    private void deleteAfter(Connection con, int contact_id) throws SQLException {

        PreparedStatement preparedStatement = null;
        String delete = "DELETE FROM JC_CONTACT WHERE CONTACT_ID > ?";
        try {
            preparedStatement = con.prepareStatement(delete);
            preparedStatement.setInt(1, contact_id);
            preparedStatement.executeUpdate();
            System.out.println("Record " + contact_id + " is deleted!");
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (con != null) {
                con.close();
            }

        }
    }
}
