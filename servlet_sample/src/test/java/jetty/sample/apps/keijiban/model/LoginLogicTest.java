package jetty.sample.apps.keijiban.model;

import jetty.sample.apps.keijiban.bean.UserInfo;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * LoginLogic のテスト
 *
 * Created by kawabatahiroto on 2016/08/28.
 */
public class LoginLogicTest {
    @Test
    public void testLoginSuccess() {
        UserInfo userInfo = new UserInfo("太郎", "taro");
        assertTrue(LoginLogic.loginCheck(userInfo));
    }

    @Test
    public void testLoginFailure1() {
        UserInfo userInfo = new UserInfo("二郎", "taro");
        assertFalse(LoginLogic.loginCheck(userInfo));
    }

    @Test
    public void testLoginFailure2() {
        UserInfo userInfo = new UserInfo("太郎", "saburo");
        assertFalse(LoginLogic.loginCheck(userInfo));
    }
}
