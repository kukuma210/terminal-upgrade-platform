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

	@Test
	public void testInsert() throws Exception {


		String data = "{\"access_token\":\"CDjkZB2Kk4IIr_Bv6ADJ2H2Orj8BzMawv-_jpJneu8D" +
				"3NM8eVn7W1eByIJFXsCkRHdarRLx6m0KxOwrwZF2K71DsKPoMAj3BgJEem8oEUyrRNO-3" +
				"MEije3cDiWpT84nKQEDaACAIWV\",\"expires_in\":7200}";

		WxAPIToken token = (WxAPIToken) JSON.parseObject(data,WxAPIToken.class);

		Result<WxAPIToken> res = new Result<WxAPIToken>();

		res.setData(token);

	}

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
