package jp.hkawabata.webapp.sample.jersey;

import jp.hkawabata.webapp.sample.jersey.prometheus.IPrometheusMetrics;
import jp.hkawabata.webapp.sample.jersey.prometheus.PrometheusMetrics;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        packages(getClass().getPackage().getName());
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(ZooKeeperWatcher.class).to(IZooKeeperWatcher.class).in(Singleton.class);
                bind(PrometheusMetrics.class).to(IPrometheusMetrics.class).in(Singleton.class);
            }
        });
    }
}
