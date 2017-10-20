package com.nowcoder.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

    private String REDIS_IP;

    private String REDIS_PORT;

    private String REDIS_PASSWORD;

    private String EMAIL_ADDRESS;

    private String EMAIL_PASSWORD;

    private volatile static ConfigUtil config;

    private final static String CONFIG_FILENAME = "config.properties";

    private static final Logger logger = LoggerFactory.getLogger(ConfigUtil.class);

    private static Properties props;

    private static Properties loadConfig(){
        props = new Properties();
        InputStream in = null;
        try {
            in = ConfigUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILENAME);
            props.load(in);
        } catch (FileNotFoundException e) {
            logger.error("jdbc.properties文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("jdbc.properties文件流关闭出现异常");
            }
        }
        return props;
    }

    private ConfigUtil() {
        this.setREDIS_IP(props.getProperty("redis.ip"));
        this.setREDIS_PORT(props.getProperty("redis.port"));
        this.setREDIS_PASSWORD(props.getProperty("redis.password"));
        this.setEMAIL_ADDRESS(props.getProperty("email.address"));
        this.setEMAIL_ADDRESS(props.getProperty("email.password"));
    }

    public static ConfigUtil getConfig() {
        if (config == null) {
            synchronized (ConfigUtil.class) {
                if (config == null) {
                    loadConfig();//加载配置文件
                    config = new ConfigUtil();
                }
            }
        }
        return config;
    }

    public String getREDIS_IP() {
        return REDIS_IP;
    }

    public void setREDIS_IP(String REDIS_IP) {
        this.REDIS_IP = REDIS_IP;
    }

    public String getREDIS_PORT() {
        return REDIS_PORT;
    }

    public void setREDIS_PORT(String REDIS_PORT) {
        this.REDIS_PORT = REDIS_PORT;
    }



    public String getREDIS_PASSWORD() {
        return REDIS_PASSWORD;
    }

    public void setREDIS_PASSWORD(String REDIS_PASSWORD) {
        this.REDIS_PASSWORD = REDIS_PASSWORD;
    }

    public String getEMAIL_ADDRESS() {
        return EMAIL_ADDRESS;
    }

    public void setEMAIL_ADDRESS(String EMAIL_ADDRESS) {
        this.EMAIL_ADDRESS = EMAIL_ADDRESS;
    }

    public String getEMAIL_PASSWORD() {
        return EMAIL_PASSWORD;
    }

    public void setEMAIL_PASSWORD(String EMAIL_PASSWORD) {
        this.EMAIL_PASSWORD = EMAIL_PASSWORD;
    }

}
