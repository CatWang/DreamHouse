package dream.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by lenovo on 2015/6/15.
 */
public class DatabaseConnection {
    private static final String DBDRIVER = "org.postgresql.Driver";
    private static final String DBURL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DBUSER = "postgres";
    private static final String DBPASSWORD = "123698745qqwbway";
    private Connection coon = null;
    public DatabaseConnection() {
        try {
            Class.forName(DBDRIVER);
            this.coon = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
            System.out.println("Opened database successfully");
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }

    }

    public Connection getConnection() {
        return this.coon;
    }

    public void close() throws Exception{
        if(this.coon != null){
            try {
                this.coon.close();
            }catch (Exception e) {
                throw e;
            }
        }
    }
}