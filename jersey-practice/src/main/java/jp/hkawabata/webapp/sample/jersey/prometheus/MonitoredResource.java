package jp.hkawabata.webapp.sample.jersey.prometheus;

import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class MonitoredResource {
    @Inject
    private IMetrics metrics;

    @GET
    @Path("/metrics")
    @Produces(MediaType.TEXT_PLAIN)
    public String returnMetrics() {
        Summary.Timer timerForSummary = metrics.startTimerForSummary();
        Histogram.Timer timerForHistogram = metrics.startTimerForHistogram();
        // 処理中リクエスト数カウンタをインクリメント
        metrics.incGauge();
        try {
            // 0-1秒間ランダムに待機
            Thread.sleep((long)(Math.random()*1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 処理中リクエスト数カウンタをデクリメント
        metrics.decGauge();
        // 処理済みリクエスト数カウンタをインクリメント
        metrics.incCounter();
        timerForSummary.observeDuration();
        timerForHistogram.observeDuration();

        return metrics.result();
    }
}
