package jetty.sample.apps.keijiban.bean;

import java.util.Date;

/**
 * 掲示板の投稿1件を表す Bean
 * 
 * Created by kawabatahiroto on 2016/08/28.
 */
public class Post {
    private String date;
    private String user;
    private String text;

    public Post() {
        this.date = new Date().toString();
    }
    public Post(String user, String text) {
        this.date = new Date().toString();
        this.user = user;
        this.text = text;
    }

    public String getDate() { return date; }
    public String getUser() { return user; }
    public String getText() { return text; }
}
