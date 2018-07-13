package jetty.sample.apps.keijiban.model;

import jetty.sample.apps.keijiban.bean.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 登録済みユーザかどうかを確認してログイン可否を返す
 *
 * Created by kawabatahiroto on 2016/08/28.
 */
public class LoginLogic {

    public static boolean loginCheck(UserInfo userInfo) {
        List<UserInfo> registeredUsers = new ArrayList<>();
        registeredUsers.add(new UserInfo("太郎", "taro"));
        registeredUsers.add(new UserInfo("二郎", "jiro"));
        registeredUsers.add(new UserInfo("三郎", "saburo"));
        return registeredUsers.contains(userInfo);
    }
}
