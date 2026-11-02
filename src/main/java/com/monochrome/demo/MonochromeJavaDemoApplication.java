package com.monochrome.demo;

import com.monochrome.api.client.spot.MonochromeSpotApiClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Local entry point that verifies the SDK wiring without calling trading APIs.
 */
public final class MonochromeJavaDemoApplication {

    private static final Logger log = LoggerFactory.getLogger(MonochromeJavaDemoApplication.class);

    private MonochromeJavaDemoApplication() {
    }

    public static void main(String[] args) {
        MonochromeSpotApiClientImpl spotClient = new MonochromeSpotApiClientImpl(null);
        log.info("monochrome-java-demo started");
        log.info("spot client initialized: {}", spotClient.getClass().getSimpleName());
        log.info("run a demo test explicitly, for example: ./gradlew test --tests com.monochrome.demo.SpotApiClientTest.testpostOrder");
    }
}
