import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBDogs {

    private static final String SERVER = "remotemysql.com";
    private static final String PORT = "3306";
    private static final String USER_NAME = "LlehZ42XUm";
    private static final String PASSWORD = "8w8CJwq8p3";
    private static final String DATABASE_NAME = "LlehZ42XUm";

    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://"+SERVER+":"+PORT, USER_NAME, PASSWORD);

//        createTable(con);
//        insertDog(con, 1, "Brian", 3, "Siberian Husky");
//        insertDog(con, 2, "Snoopy", 5, "Australian Shepherd");
//        insertDog(con, 3, "Rexi", 7, "Jack Russell Terrier");
//        getTableContent(con);
//        updateDogAge(con, 2, 9);
//        getTableContent(con);
//        deleteDogByID(con, 3);
        getTableContent(con);
        con.close();
    }

    private static void createTable(Connection con) throws SQLException {
        String statementToExecute = "CREATE TABLE " + DATABASE_NAME + ".`dogs`(`id` INT NOT NULL, `name` VARCHAR(40) NOT NULL, `age` INT NOT NULL, `breed` VARCHAR(30) NOT NULL, PRIMARY KEY(`id`));";
        con.createStatement().execute(statementToExecute);
    }

    private static void insertDog(Connection con, int id, String name, int age, String breed) throws SQLException {
        String statementToExecute = "INSERT INTO " + DATABASE_NAME + ".dogs (`id`, `name`, `age`, `breed`) VALUES ('" + id + "', '" + name + "', '" + age + "', '" + breed + "');";
        con.createStatement().execute(statementToExecute);
    }

    private static void updateDogAge(Connection con, int id, int age) throws SQLException {
        String statementToExecute = "UPDATE `" + DATABASE_NAME + "`.`dogs` SET `age`='"+age+"' WHERE `id`='"+id+"';";
        con.createStatement().execute(statementToExecute);
    }

    private static void deleteDogByID(Connection con, int id) throws SQLException {
        String statementToExecute = "DELETE FROM `" + DATABASE_NAME + "`.`dogs` WHERE `id`='"+id+"';";
        con.createStatement().execute(statementToExecute);
    }

    private static void getTableContent(Connection con) throws SQLException {
        String statementToExecute = "SELECT * FROM " + DATABASE_NAME + ".dogs;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(statementToExecute);
        while(rs.next()) {
            //Retrieve by column name
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String breed = rs.getString("breed");

            //Display values
            System.out.println("ID: " + id);
            System.out.println(", Name: " + name);
            System.out.println(", Age: " + age);
            System.out.println(", Breed: " + breed);
        }
        rs.close();
    }
}
