package jetty.sample.apps.keijiban.listener;

import jetty.sample.apps.keijiban.bean.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * アプリケーション開始時・終了時にデータベースに接続して
 * これまでのつぶやきを取得・保存する
 * - データベース名 keijiban
 * - テーブル名 tsubuyaki
 * - ユーザ keijiban
 * - パスワード keijiban
 *
 * create table tsubuyaki(id int, date text, user text, text text);
 *
 * Created by kawabatahiroto on 2016/09/04.
 */
@WebListener
public class DatabaseConnectListener implements ServletContextListener {
    /**
     * アプリケーション開始時、データベースに接続してこれまでのつぶやきを取得
     *
     * @param arg0
     */
    public void contextInitialized(ServletContextEvent arg0) {
        ServletContext context = arg0.getServletContext();

        Connection connection = null;
        try {
            // JDBC ドライバ読み込み
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/keijiban", "keijiban", "keijiban");

            String sql = "SELECT id, date, user, text FROM tsubuyaki ORDER BY id;";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            List<Post> posts = new ArrayList<>();
            while(rs.next()) {
                int id = rs.getInt("id");
                String date = rs.getString("date");
                String user = rs.getString("user");
                String text = rs.getString("text");
                posts.add(0, new Post(id, user, text, date));
            }
            context.setAttribute("posts", posts);

            connection.close();

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                try{
                    connection.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * アプリケーション終了時の実行メソッド
     *
     * @param arg0
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        ServletContext context = arg0.getServletContext();
        List<Post> posts = (List<Post>) context.getAttribute("posts");

        Connection connection = null;
        try {
            // JDBC ドライバ読み込み
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/keijiban", "keijiban", "keijiban");

            String sql = "INSERT INTO tsubuyaki (id, date, user, text) VALUES (?, ?, ?, ?);";
            PreparedStatement pStmt = connection.prepareStatement(sql);
            for(Post post: posts) {
                pStmt.setInt(1, post.getId());
                pStmt.setString(2, post.getDate());
                pStmt.setString(3, post.getUser());
                pStmt.setString(4, post.getText());
                pStmt.addBatch();
            }
            int[] result = pStmt.executeBatch();

            for(int i=0;i<result.length;i++){
                System.out.println("result"+i+": "+result[i]);
            }

            connection.close();

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                try{
                    connection.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
