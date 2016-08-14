package jetty.sample.apps.mvc_trial;

/**
 * MVC のモデルに相当するクラス
 *
 * Created by kawabatahiroto on 2016/08/14.
 */
public class SampleLogic {
    public static void execute(SampleBean bean) {
        bean.setAfterString("***" + bean.getBeforeString() + "***");
    }
}
