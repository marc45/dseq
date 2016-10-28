package com.ai.dseq.lock;

import redis.clients.jedis.Jedis;

public class Lock implements ILock{
	private Jedis jedis;
	private String name;
	
	public Lock(Jedis jedis, String name){
		this.jedis=jedis;
		this.name=name;
	}

	@Override
	public void unlock() {
		jedis.del(this.name);
		jedis.close();
	}

}
