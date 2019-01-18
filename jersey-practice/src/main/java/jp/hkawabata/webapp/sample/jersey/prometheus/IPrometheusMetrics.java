package jp.hkawabata.webapp.sample.jersey.prometheus;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;

public interface IPrometheusMetrics {
    CollectorRegistry registry = new CollectorRegistry();

    Counter requestsTotal = Counter.build()
            .name("requests_total").help("requestsTotal").register(registry);

    Gauge requestsInProgress = Gauge.build()
            .name("inprogress_requests").help("Inprogress requests.").register(registry);

    Summary latencySummary = Summary.build()
            .quantile(0.1, 0.01)
            .quantile(0.3, 0.01)
            .quantile(0.5, 0.01)
            .quantile(0.7, 0.01)
            .quantile(0.9, 0.01)
            .maxAgeSeconds(60)
            .name("requests_latency_seconds_summary").help("Request latency in seconds (Summary).").register(registry);

    Histogram latencyHistogram = Histogram.build()
            .name("requests_latency_seconds_histogram").help("Request latency in seconds (Histogram).").register(registry);

    String result();

    void incRequestsTotal();
    void incRequestsInProgress();
    void decRequestsInProgress();
    Summary.Timer startTimerForSummary();
    Histogram.Timer startTimerForHistogram();
}
