package cn.szxys;

import cn.szxys.Entity.UserEntity;
import cn.szxys.mapper.UserMapper;
import cn.szxys.pub.UserSexEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TerminalUpgradePlatformApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired(required = false)
	private UserMapper um;

//	@Test
//	public void testInsert() throws Exception {
//		um.insert(new UserEntity("aa", "a123456", UserSexEnum.MAN));
//		um.insert(new UserEntity("bb", "b123456", UserSexEnum.WOMAN));
//		um.insert(new UserEntity("cc", "b123456", UserSexEnum.WOMAN));
//
//		Assert.assertEquals(3, um.getAll().size());
//	}
//
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
