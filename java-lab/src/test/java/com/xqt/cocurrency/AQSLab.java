package com.xqt.cocurrency;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author huanruiz
 * @since 2024/7/31
 */
public class AQSLab {

    /**
     * 直接看注释
     */
    private AbstractQueuedSynchronizer synchronizer;

    /**
     * FairSync
     * NonfairSync
     */
    private ReentrantLock reentrantLock;

    public static void main(String[] args) {
    }

}
