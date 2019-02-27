redis cache使用：

一	 在项目中config/app/下配置cache.properties
cache.properties 内容如下：
######cache######
spring.cache.type=redis
spring.cache.cache-names=myredis

######Redis######
#Redis数据库索引（默认为0）
spring.redis.database=0
#Redis服务器地址
spring.redis.host=192.168.226.129
#Redis服务器连接端口
spring.redis.port=6379
# Redis服务器连接密码（默认为空）
spring.redis.password=
#连接池最大连接数（使用负值表示没有限制）
spring.redis.pool.max-active=8
#连接池最大阻塞等待时间（使用负值表示没有限制）
spring.redis.pool.max-wait=-1
#连接池中的最大空闲连接
spring.redis.pool.max-idle=8
#连接池中的最小空闲连接
spring.redis.pool.min-idle=0
#连接超时时间（毫秒）
spring.redis.timeout=0



二 	项目中引入cache模块
在项目中pom.xml添加
<!-- cache -->
	  	<dependency>
	  		<groupId>com.cloud.frame.cache</groupId>
  			<artifactId>frame-cache</artifactId>
	  	</dependency>



三 	在项目的ApplicationConfig中配置
@Import(value={
		RedisCacheConfig.class
})



四	 使用cache注解 或者 RedisUtil 工具操作redis

1 @Cacheable
Spring在每次执行前都会检查Cache中是否存在相同key的缓存元素，如果存在就不再执行该方法，而是直接从缓存中获取结果进行返回，否则才会执行并将返回结果存入指定的缓存中。
例：
@Cacheable(value="users", key="#user.id", condition="#user.id%2==0")

------------------------------------------------------

2 @CachePut
@CachePut标注的方法在执行前不会去检查缓存中是否存在之前执行过的结果，而是每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中。
例：
@CachePut(value={"myredis,myredis2"},key="'userGroup_' + #p0.id")

------------------------------------------------------

3 @CacheEvict
标注在需要清除缓存元素的方法或类上的。当标记在一个类上时表示其中所有的方法的执行都会触发缓存的清除操作。@CacheEvict可以指定的属性有value、key、condition、allEntries和beforeInvocation。
其中value、key和condition的语义与@Cacheable对应的属性类似。
例：
@CacheEvict(value="myredis",key="'userGroup_' + #p0.id")


allEntries属性
	allEntries是boolean类型，表示是否需要清除缓存中的所有元素。默认为false，表示不需要。当指定了allEntries为true时，Spring Cache将忽略指定的key。有的时候我们需要Cache一下清除所有的元素，这比一个一个清除元素更有效率。
 
beforeInvocation属性
	清除操作默认是在对应方法成功执行之后触发的，即方法如果因为抛出异常而未能成功返回时也不会触发清除操作。使用beforeInvocation可以改变触发清除操作的时间，当我们指定该属性值为true时，Spring会在调用该方法之前清除缓存中的指定元素
	
	
	
4 @Caching
@Caching注解可以让我们在一个方法或者类上同时指定多个Spring Cache相关的注解。其拥有三个属性：cacheable、put和evict，分别用于指定@Cacheable、@CachePut和@CacheEvict。
例：
@Caching(cacheable = @Cacheable("users"), evict = { @CacheEvict("cache2"),@CacheEvict(value = "cache3", allEntries = true) })
	

------------------------------------------------------
	
三个属性，value、key和condition

1 value属性是必须指定的，其表示当前方法的返回值是会被缓存在哪个Cache上的，对应Cache的名称,可以是多个，写法：value={"myredis,myredis2"}
2 key属性是用来指定Spring缓存方法的返回结果时对应的key的。该属性支持SpringEL表达式。使用方法参数时我们可以直接使用“#参数名”或者“#p参数index”
3 condition 缓存的条件，默认为空，使用 SpEL 编写，返回 true 或者 false，只有为 true 才进行缓存


