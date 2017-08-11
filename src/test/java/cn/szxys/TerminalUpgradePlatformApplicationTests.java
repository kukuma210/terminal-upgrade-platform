package cn.szxys;

import cn.szxys.Entity.UserEntity;
import cn.szxys.bean.Result;
import cn.szxys.bean.WxAPIToken;
import cn.szxys.mapper.UserMapper;
import cn.szxys.pub.UserSexEnum;
import cn.szxys.tools.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TerminalUpgradePlatformApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void contextLoads() {
	}

	@Autowired(required = false)
	private UserMapper um;

	@Test
	public void testInsert() throws Exception {


		String data = "{\"access_token\":\"CDjkZB2Kk4IIr_Bv6ADJ2H2Orj8BzMawv-_jpJneu8D" +
				"3NM8eVn7W1eByIJFXsCkRHdarRLx6m0KxOwrwZF2K71DsKPoMAj3BgJEem8oEUyrRNO-3" +
				"MEije3cDiWpT84nKQEDaACAIWV\",\"expires_in\":7200}";

		WxAPIToken token = (WxAPIToken) JSON.parseObject(data,WxAPIToken.class);

		Result<WxAPIToken> res = new Result<WxAPIToken>();

		res.setData(token);

	}


		@Test
		public void test() throws Exception {
			//stringRedisTemplate.opsForValue().set("aaa", "111");
			//Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
		}

//		@Test
//		public void testObj() throws Exception {
//			User user=new User("aa@126.com", "aa", "aa123456", "aa","123");
//			ValueOperations<String, User> operations=redisTemplate.opsForValue();
//			operations.set("com.neox", user);
//			operations.set("com.neo.f", user,1,TimeUnit.SECONDS);
//			Thread.sleep(1000);
//			//redisTemplate.delete("com.neo.f");
//			boolean exists=redisTemplate.hasKey("com.neo.f");
//			if(exists){
//				System.out.println("exists is true");
//			}else{
//				System.out.println("exists is false");
//			}
//			// Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
//		}


//	@Test
//	public void testQuery() throws Exception {
//		List<UserEntity> users = um.getAll();
//		System.out.println(users.toString());
//	}
//
//	@Test
//	public void testUpdate() throws Exception {
//		UserEntity user = um.getOne(Long.valueOf(16));
//		System.out.println(user.toString());
//		user.setUserName("neo");
//		um.update(user);
//		Assert.assertTrue(("neo".equals(um.getOne(Long.valueOf(16)).getUserName())));
//	}


}
