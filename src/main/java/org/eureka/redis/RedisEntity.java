package org.eureka.redis;

/**
 * @author: 奎
 * @date: 2017/5/15 11:00
 * @description:
 */
public class RedisEntity {
    private String type;
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}