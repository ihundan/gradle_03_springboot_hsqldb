package ihundan.wukonglin.hsqldb.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.hsqldb.server.Server;

public class UtilHsqlDB {
	public static final int PORT = 9002;
    public static final String DB_NAME = "wukonglin";       //数据库文件名，同时也是本类中的数据库名
    public static final String DB_PATH = "./db/";
    public static final String USER_NAME = "sa";
    public static final String PASSWORD = "";
    public static final int SERVER_MODE = 0;
    public static final int STAND_ALONE_MODE = 1;   //In-Process
    public static int mode = SERVER_MODE;          //记录当前用什么模式，开发时用Server，发布时用standalone

    /**
     * 启动数据库服务
     */
    public static boolean startHSQL() {
        if (mode == SERVER_MODE) {
            Server server = new Server();//它可是hsqldb.jar里面的类啊。
            server.setDatabaseName(0, DB_NAME);
            server.setDatabasePath(0, DB_PATH + DB_NAME);
            server.setPort(PORT);
            server.setSilent(true);
            server.start();         //自动多线程运行
            System.out.println("hsqldb started...");
        } else if (mode == STAND_ALONE_MODE) {
            //standalone模式，打开连接就同时启动数据库，所以这里可以什么都不做
        }

        try {
            Thread.sleep(800);        // 等待Server启动
        } catch (InterruptedException e) {
        }
        return true;
    }

    /**
     * 关闭数据库服务
     */
    public static boolean stopHSQL() {
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate("SHUTDOWN;");
            return true;
        } catch (SQLException ex) {
        	ex.printStackTrace();
            return false;
        }
    }

    /**
     * 获取连接
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            if (mode == SERVER_MODE) {
                conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:" + PORT + "/" + DB_NAME, USER_NAME, PASSWORD);
            } else if (mode == STAND_ALONE_MODE) {
                conn = DriverManager.getConnection("jdbc:hsqldb:file:" + DB_PATH + DB_NAME, USER_NAME, PASSWORD);
            }
        } catch (ClassNotFoundException ex) {
        	ex.printStackTrace();
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
    	UtilHsqlDB.mode = UtilHsqlDB.STAND_ALONE_MODE;
    	UtilHsqlDB.startHSQL();
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate("create table tb_user(objid int not null primary key,username VARCHAR(40),password VARCHAR(40))");
            for (int i = 10; i < 20; i++) {
                statement.executeUpdate("insert into tb_user values(" + i + ",'liu','zhaoyang')");
            }
            statement.close();
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }
        UtilHsqlDB.stopHSQL();
    }
}
