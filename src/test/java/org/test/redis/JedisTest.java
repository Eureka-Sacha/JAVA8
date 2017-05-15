package org.test.redis;

import org.apache.log4j.Logger;
import org.eureka.redis.RedisPoolUtils;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author: 奎
 * @date: 2017/5/15 11:04
 * @description:
 */
public class JedisTest {
    private Jedis jedis;
    private static Logger logger=Logger.getLogger(Jedis.class);
    @Before
    public void setup(){
        jedis= RedisPoolUtils.getJedis();
//        jedis.auth("1234");
    }

    @Test
    public void strTest(){
        System.out.println(jedis.get("a"));
        jedis.set("a","1");
        jedis.append("a"," is 1");
        System.out.println(jedis.get("a"));
        jedis.del("a");
    }
    @Test
    public void loggerTest(){
        logger.debug("this is debug");
        logger.error("this is error");
        logger.info("this is info");
    }

}
