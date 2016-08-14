package jetty.sample.apps.mvc_trial;

import java.io.Serializable;

/**
 * 処理結果を格納するための JavaBeans
 *
 * Created by kawabatahiroto on 2016/08/14.
 */
public class SampleBean implements Serializable {
    private String afterString;
    private String beforeString;
    public SampleBean() {}

    public void setAfterString(String afterString) { this.afterString = afterString; }
    public String getAfterString() { return afterString; }
    public void setBeforeString(String beforeString) { this.beforeString = beforeString; }
    public String getBeforeString() { return beforeString; }
}
