package jp.hkawabata.webapp.sample.jersey.prometheus;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;

public interface IMetrics {
    CollectorRegistry registry = new CollectorRegistry();

    Counter counter = Counter.build()
            .name("requests_total").help("counter").register(registry);

    Gauge gauge = Gauge.build()
            .name("inprogress_requests").help("Inprogress requests.").register(registry);

    Summary latencySummary = Summary.build()
            .name("requests_latency_seconds_summary").help("Request latency in seconds (Summary).").register(registry);

    Histogram latencyHistogram = Histogram.build()
            .name("requests_latency_seconds_histogram").help("Request latency in seconds (Histogram).").register(registry);

    String result();

    void incCounter();
    void incGauge();
    void decGauge();
    Summary.Timer startTimerForSummary();
    Histogram.Timer startTimerForHistogram();
}
