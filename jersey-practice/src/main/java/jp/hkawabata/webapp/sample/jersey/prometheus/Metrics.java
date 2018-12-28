package jp.hkawabata.webapp.sample.jersey.prometheus;

import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;
import io.prometheus.client.exporter.common.TextFormat;

import java.io.StringWriter;

public class Metrics implements IMetrics {
    public String result() {
        StringWriter writer = new StringWriter();
        try {
            TextFormat.write004(writer, registry.metricFamilySamples());
        } catch (Exception e) {
            return e.getMessage();
        }
        return writer.toString();
    }

    public void incCounter() {
        counter.inc();
    }
    public void incGauge() {
        gauge.inc();
    }
    public void decGauge() {
        gauge.dec();
    }
    public Summary.Timer startTimerForSummary() {
        return latencySummary.startTimer();
    }
    public Histogram.Timer startTimerForHistogram() {
        return latencyHistogram.startTimer();
    }
}
