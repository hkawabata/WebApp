package jetty.sample.apps.keijiban.bean;

import java.util.Date;

/**
 * 掲示板の投稿1件を表す Bean
 * 
 * Created by kawabatahiroto on 2016/08/28.
 */
public class Post {
    private int id;
    private String date;
    private String user;
    private String text;

    public Post() {
        this.date = new Date().toString();
    }
    public Post(int id, String user, String text) {
        this.id = id;
        this.date = new Date().toString();
        this.user = user;
        this.text = text;
    }
    public Post(int id, String user, String text, String date) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.text = text;
    }

    public int getId() { return id; }
    public String getDate() { return date; }
    public String getUser() { return user; }
    public String getText() { return text; }
}
