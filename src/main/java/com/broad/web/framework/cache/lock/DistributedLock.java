package com.broad.web.framework.cache.lock;

/**
 * @author broad
 * @date 20200111
 **/
public interface DistributedLock {
    /**
     * 默认超时时间
     * 单位：毫秒
     */
    long TIMEOUT_MILLIS = 5000;

    /**
     * 重试次数
     */
    int RETRY_TIMES = 100;

    /**
     * 每次重试后等待的时间
     * 单位：毫秒
     */
    long SLEEP_MILLIS = 100;

    /**
     * 获取锁
     *
     * @param key key
     * @return 成功/失败
     */
    boolean lock(String key);

    /**
     * 获取锁
     *
     * @param key        key
     * @param retryTimes 重试次数
     * @return 成功/失败
     */
    boolean lock(String key, int retryTimes);

    /**
     * 获取锁
     *
     * @param key         key
     * @param retryTimes  重试次数
     * @param sleepMillis 获取锁失败的重试间隔 单位：毫秒
     * @return 成功/失败
     */
    boolean lock(String key, int retryTimes, long sleepMillis);

    /**
     * 获取锁
     *
     * @param key    key
     * @param expire 获取锁超时时间
     * @return 成功/失败
     */
    boolean lock(String key, long expire);

    /**
     * 获取锁
     *
     * @param key        key
     * @param expire     获取锁超时时间
     * @param retryTimes 重试次数
     * @return 成功/失败
     */
    boolean lock(String key, long expire, int retryTimes);

    /**
     * 获取锁
     *
     * @param key         key
     * @param expire      获取锁超时时间
     * @param retryTimes  重试次数
     * @param sleepMillis 获取锁失败的重试间隔
     * @return 成功/失败
     */
    boolean lock(String key, long expire, int retryTimes, long sleepMillis);

    /**
     * 释放锁
     *
     * @param key key值
     * @return 释放结果
     */
    boolean releaseLock(String key);
}
