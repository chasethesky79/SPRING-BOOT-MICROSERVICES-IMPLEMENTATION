package se.magnus.util.path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

@Component
public class ServiceUtil {

    private static final Logger logger = LoggerFactory.getLogger(ServiceUtil.class);
    private final String port;
    private String serviceAddress;

    @Autowired
    public ServiceUtil(@Value("${server.port}") String port) {
        this.port = port;
    }

    public String getServiceAddress() {
        if (Objects.isNull(serviceAddress)) {
            serviceAddress = String.format("%s/%s:%s", findMyHostName(), findMyIpAddress(), port);
        }
        return serviceAddress;
    }
    private String findMyHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            logger.error("Unknown host name");
            return "Unknown host name";
        }
    }

    private String findMyIpAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            logger.error("Unknown IP address");
            return "Unknown IP address";
        }
    }
}
