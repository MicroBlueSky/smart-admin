package cn.newcapec.city.smart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot方式启动类
 *
 * @author stylefeng
 * @Date 2017/5/21 12:06
 */
@SpringBootApplication
public class SmartAdminApplication {

    private final static Logger logger = LoggerFactory.getLogger(SmartAdminApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SmartAdminApplication.class, args);
        logger.info("SmartAdminApplication is success!");
    }
}
