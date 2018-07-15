package jp.hkawabata.webapp.sample.jersey;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;


/**
 * ZooKeeper に接続し、設定値をウォッチして変更を即時反映する
 */
public class ZooKeeperWatcher implements IZooKeeperWatcher {
    private String zkHost = "node001.hkawabata.jp";
    private String znode = "/zk_test";

    private long createdTimeNano;

    private ZooKeeper zk;
    private String zkData;

    private void connect(String host) throws Exception {
        zk = new ZooKeeper(host, 5000,
                new Watcher() {
                    public void process(WatchedEvent watchedEvent) {
                        System.out.println(watchedEvent);
                        if(watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                            try {
                                zkData = new String(zk.getData(znode, true, null));
                            } catch (Exception e) {
                                System.out.println(e.toString());;
                            }
                        }
                    }
                }
        );
        // つながるまで待機とかした方が良い？？
    }

    public void close() throws InterruptedException {
        zk.close();
    }

    public ZooKeeperWatcher() {
        this.createdTimeNano = System.nanoTime();
        try {
            connect(zkHost);
            zkData = new String(zk.getData(znode, true, null));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public String s() {
        return "ZooKeeperWatcher!!!\n"
                + "watcher instance created: " + createdTimeNano + "\n"
                + "data in znode " + znode + ": " + zkData + "\n";
    }
}
