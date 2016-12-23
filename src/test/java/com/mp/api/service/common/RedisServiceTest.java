package com.mp.api.service.common;

import org.junit.Test;

import javax.annotation.Resource;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Test case for RedisService
 *
 * create by minpan 09-28-2016
 */
public class RedisServiceTest extends AbstractBaseServiceTest {

    @Resource
    private RedisService redisService;

    @Test
    public void testRemoveKey() {
        assertFalse(redisService.removeKey(null));
        assertTrue(redisService.removeKey("test-key"));
    }

    @Test
    public void testSetKey() {
        assertTrue(redisService.setForString("test-key", "test-value"));
        assertFalse(redisService.setForString("test-key", null));
    }

    @Test
    public void testGetKey() {
        String testKey = "test-key";
        // 1.remove it
        redisService.removeKey(testKey);
        // 2. set it
        redisService.setForString(testKey, "the test value");
        // 3. test get
        assertEquals("the test value", redisService.getForString(testKey));
    }

    @Test
    public void testSetForZSet() {
        assertFalse(redisService.setForZSet(null, null, 0));
        assertFalse(redisService.setForZSet("test-z-key", null, 0));
        assertTrue(redisService.setForZSet("test-z-key-2", "123", 3));
    }

    @Test
    public void testGetForZSet() {
        String testKey = "test-z-key";
        redisService.removeKey(testKey);

        // 1. add some
        redisService.setForZSet(testKey, "1234", 0);
        redisService.setForZSet(testKey, "5678", 1.2);
        redisService.setForZSet(testKey, "5678", -1);
        Set<String> zSet = redisService.getForZSet(testKey);

        assertNotNull(zSet);
        assertEquals(2, zSet.size());
        assertTrue(zSet.contains("5678") && zSet.contains("1234"));

        zSet = redisService.getForZSet(testKey, 0, 0);
        assertNotNull(zSet);
        assertEquals(1, zSet.size());
        assertTrue(zSet.contains("5678") && !zSet.contains("1234"));

        redisService.setForZSet(testKey, "5678", 3);
        zSet = redisService.getForZSet(testKey, 0, 0);
        assertNotNull(zSet);
        assertEquals(1, zSet.size());
        assertTrue(zSet.contains("1234") && !zSet.contains("5678"));

        zSet = redisService.getForZSet(testKey, 1, 0);
        assertNotNull(zSet);
        assertTrue(zSet.isEmpty());

        zSet = redisService.getForZSet("nullkey");
        assertNotNull(zSet);
        assertTrue(zSet.isEmpty());

    }

    @Test
    public void testHash(){

        String key = "redis:test";
        String hashKey = "stuId";
        redisService.putForHash(key,hashKey,"wangming");
    }
}
