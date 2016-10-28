package com.ai.dseq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.ai.dseq.lock.Lock;
/**
 * 工具对外接口
 * @author Administrator
 *
 */
public class DSeq {
	private final Logger LOG=LoggerFactory.getLogger(DSeq.class);
	private JedisPool pool;
	
	public void setPool(JedisPool pool) {
		this.pool = pool;
	}
	
	//constructor
	public DSeq(){
		
	}
	public DSeq(JedisPool pool){
		this.pool=pool;
	}
	
	/**
	 * 获取一个锁对象，在使用完成后必须手动释放，否则将造成连接资源无法回收
	 * jedis连接不会被释放，直到释放锁
	 * @param name
	 * @return
	 */
	public Lock lock(String name){
		Jedis jedis=pool.getResource();
		String lockName="lock:"+name;
		long result=jedis.setnx(lockName, "1");
		
		if(result==0){
			LOG.debug("fail to lock(获取锁失败):"+name);
			return null;
		}
		
		Lock lock=new Lock(jedis,lockName);
		return lock;
	}
	/**
	 * 获取一个序列的值，范围64 位有符号整数
	 * 会默认在seqName前加"seq:",已方便在redis中的管理
	 * @param seqName
	 * @return
	 */
	public long nextval(String seqName){
		Jedis jedis=null;
		try{
			jedis=pool.getResource();
			
			//return new value of key after the increment
			return jedis.incr("seq:"+seqName);
		}finally{
			if(jedis!=null){
				jedis.close();
			}
		}
	}
	

}
