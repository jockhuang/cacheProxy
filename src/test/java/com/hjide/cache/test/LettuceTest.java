package com.hjide.cache.test;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.sync.RedisCommands;
import org.junit.Assert;
import org.junit.Test;

/**
 * cache com.hjide.cache.test
 * Created by jock on 2017/10/17.
 */
public class LettuceTest extends BaseTest
{
    @Test
    public void testString(){
        RedisClient client = RedisClient.create("redis://localhost");

        StatefulRedisConnection<String, String> connection = client.connect();

        RedisCommands<String, String> commands = connection.sync();

        commands.set("foo","test");
        String value = commands.get("foo");
        Assert.assertEquals(value,"test");
        connection.close();

        client.shutdown();
    }
}
