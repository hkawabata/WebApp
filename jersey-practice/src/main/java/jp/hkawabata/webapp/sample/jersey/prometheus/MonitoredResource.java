package jp.hkawabata.webapp.sample.jersey.prometheus;

import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/prometheus")
public class MonitoredResource {
    @Inject
    private IPrometheusMetrics metrics;

    private static long maxWaitMS = 1000;

    /**
     * ユーザのリクエストを処理する
     * ここでは実際の何らかの処理の代わりにランダムな時間 sleep してからレスポンスを返す
     *
     * @return
     */
    @GET
    @Path("/process")
    @Produces(MediaType.APPLICATION_JSON)
    public String processRequest() {
        Summary.Timer timerForSummary = metrics.startTimerForSummary();
        Histogram.Timer timerForHistogram = metrics.startTimerForHistogram();
        // 処理中リクエスト数カウンタをインクリメント
        metrics.incRequestsInProgress();
        // リクエストを処理する
        process();
        // 処理中リクエスト数カウンタをデクリメント
        metrics.decRequestsInProgress();
        // 処理済みリクエスト数カウンタをインクリメント
        metrics.incRequestsTotal();
        timerForSummary.observeDuration();
        timerForHistogram.observeDuration();
        return "{\"result\": \"SUCCESS\"}";
    }

    private void process() {
        try {
            // 0-1秒間ランダムに待機
            Thread.sleep((long)(Math.random() * maxWaitMS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 待ち時間の最大値を変更する
     *
     * @param value
     * @return
     */
    @GET
    @Path("/set_max_wait_ms")
    @Produces(MediaType.APPLICATION_JSON)
    public String setMaxWaitMS(@QueryParam("value") long value) {
        if (value != 0) {
            maxWaitMS = value;
            return "{\"result\": \"SUCCESS\"}";
        } else {
            return "{\"result\": \"FAILURE\"}";
        }
    }

    /**
     * Prometheus サーバが参照するためのメトリクス情報を返す
     *
     * @return
     */
    @GET
    @Path("/metrics")
    @Produces(MediaType.TEXT_PLAIN)
    public String returnMetrics() {
        return metrics.result();
    }
}
