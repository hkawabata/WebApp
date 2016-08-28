package jetty.sample.apps.keijiban.bean;

import java.io.Serializable;

/**
 * ユーザ情報を表す Bean
 *
 * Created by kawabatahiroto on 2016/08/28.
 */
public class UserInfo implements Serializable {
    private String name;
    private String pass;
    public UserInfo() {}
    public UserInfo(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }
    public String getName() { return name; }
    public String getPass() { return pass; }

    @Override
    public boolean equals(Object other) {
        if(other == null) return false;
        if(getClass() != other.getClass()) {
            return false;
        } else {
            UserInfo castedOther = (UserInfo) other;
            return (name.equals(castedOther.name) && pass.equals(castedOther.pass));
        }
    }

    @Override
    public int hashCode() {
        final int seed = 31;
        int result = 1;
        result = seed * result + name.hashCode();
        result = seed * result + pass.hashCode();
        return result;
    }

}
