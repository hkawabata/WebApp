package jp.hkawabata.webapp.sample.jersey;

/**
 * ZooKeeper に接続し、設定値をウォッチして変更を即時反映する
 */
public class ZooKeeperWatcher implements IZooKeeperWatcher {
    public long createdTimeNano;

    public ZooKeeperWatcher() {
        this.createdTimeNano = System.nanoTime();
    }

    public String s() {
        return "ZooKeeperWatcher!!! created: " + createdTimeNano;
    }
}
