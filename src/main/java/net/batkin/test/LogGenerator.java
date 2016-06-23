package net.batkin.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.keyValue;
import static net.logstash.logback.marker.Markers.append;
import static net.logstash.logback.marker.Markers.appendEntries;
import static net.logstash.logback.marker.Markers.appendFields;

public class LogGenerator {

    private static final Logger logger = LoggerFactory.getLogger(LogGenerator.class);

    public static void main(String[] args) throws Exception {
        MDC.put("mdckey", "mdcvalue");

        logger.info(append("here", "there"), "Hello {}, this is {}", "World", "Fun", keyValue("logging", "fun"));

        Map<String, String> map = new HashMap<>();
        map.put("account", "Absolute Return Fund");
        map.put("strategy", "US Stock Selection");

        logger.info(appendEntries(map), "Processing P&L");

        MyPojo myPojo = new MyPojo("Adam Batkin", "AQR Capital Management");

        logger.info(appendFields(myPojo), "Processing Company");
    }

    public static class MyPojo {
        private String name;
        private String company;

        public MyPojo(String name, String company) {
            this.name = name;
            this.company = company;
        }

        public String getName() {
            return name;
        }

        public String getCompany() {
            return company;
        }
    }
}
