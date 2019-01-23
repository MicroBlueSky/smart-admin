package cn.newcapec.city.smart.core.util;

import cn.newcapec.city.smart.config.properties.GunsProperties;
import cn.newcapec.city.smart.core.core.util.SpringContextHolder;

/**
 * 验证码工具类
 */
public class KaptchaUtil {

    /**
     * 获取验证码开关
     */
    public static Boolean getKaptchaOnOff() {
        return SpringContextHolder.getBean(GunsProperties.class).getKaptchaOpen();
    }
}