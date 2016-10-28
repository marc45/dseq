Dseq
---
dseq是基于redis的分布式序列与分布式锁工具  
依赖  
> jedis
> slf4j

###install
要安装使用这个工具十分简单，分为如下两步

1.项目基于maven管理，可以直接通过maven引用下载  
'''
<dependency>
    <groupId>com.ai</groupId>
    <artifactId>dseq</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
'''

2.实例化DSeq对象，注入一个jedisPool对象，可以通过构造函数注入或者构造实例之后再注入。  
3.在需要的地方就可以直接使用了。  
4.如果是spring环境，使用更为简单，可以把DSeq交给spring容器管理  

###通过源码编译
编译打包  
	shell> cd /path/to/project  
	shell> mvn clean package -Dmaven.test.skip=true -s /path/to/settings.xml -P dev  
安装到本地  
	shell> mvn source:jar install -Dmaven.test.skip=true -s /path/to/settings.xml  
安装到私服  
	shell> mvn source:jar deploy -Dmaven.test.skip=true -s /path/to/settings.xml  
