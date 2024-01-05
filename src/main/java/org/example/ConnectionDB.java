package org.example;

import javax.crypto.spec.PSource;
import java.sql.*;

public class ConnectionDB {

    private final Connection connection = connection();


    private static final String url = "jdbc:postgresql://localhost:5454/postgres";
    private static final String user = "postgres";
    private static final String password = "root";

    public static Connection connection() {
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(url, user, password);
            System.out.println("Connection✔");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connect;
    }





    public static int getUserCount()  {
        String SQL = "Select count(*) from person";
        int count = 0;
        try (Connection conn = connection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
//            System.out.println("ASD");
            System.out.println(e.getMessage());

        }
        return count;

    }

    public static void addUser(String name,int age) throws SQLException {
        String SQL = "insert into person (name, age)values (?,?)";
        Connection conn = connection();
            PreparedStatement statement = conn
                    .prepareStatement(SQL);
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.executeUpdate();
    }

    public static void printUsers()throws SQLException{
        ConnectionDB connectionDB = new ConnectionDB();
        Connection conn = connectionDB.connection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select  * from person");
        while (rs.next()){
            System.out.println(
                    rs.getInt("id")+" "
                    +rs.getString("name")+" "
                    +rs.getInt("age"));
        }
    }
    private final String INSERT_INTO = "insert into person(name,age)values(?,?)";

    public void save(Person person) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
        preparedStatement.setString(1, person.getName());
        preparedStatement.setInt(2, person.getAge());
        preparedStatement.executeUpdate();
        System.out.println("save");
    }

}

