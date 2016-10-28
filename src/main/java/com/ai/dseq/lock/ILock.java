package com.ai.dseq.lock;
/**
 * 分布式锁接口
 * @author Administrator
 *
 */
public interface ILock {
	/**
	 * 解锁
	 */
	void unlock();
}
