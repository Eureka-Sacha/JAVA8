package org.eureka.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * The type Redis pool utils.
 *
 * @author Eureka.
 * @date 2017 -05-15 15:47:31
 * @author: 奎
 * @date: 2017 /5/15 14:56
 * @description:
 */
public class RedisPoolUtils {
    //redis 服务ID
    private static String IP = "";
    //端口
    private static int PORT = 6379;

    private static String AUTH = "1234";

    private static int MAX_TOTAL = 1024;

    private static int MAX_IDLE = 200;

    private static int MAX_WAIT = 10000;

    private static int TIMEOUT = 10000;

    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;

    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_TOTAL);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, IP, PORT, TIMEOUT, AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get jedis jedis.
     *
     * @return the jedis
     */
    public synchronized static Jedis getJedis(){
        try {
            if(jedisPool!=null){
                Jedis jedis=jedisPool.getResource();
                return jedis;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Return resource.
     *
     * @param jedis the jedis
     */
    public static void returnResource(Jedis jedis){
        if(jedis!=null){
//            jedisPool.returnResource(jedis);
            jedis.close();
        }

    }

}
