package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.juststand.seckill.dao.SuccessKilledDao;
import com.juststand.seckill.model.SuccessKilled;


/**
 * 配置spring和junit整合
 * Created By juststand on 2017-3-20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {
	
	@Resource
	private SuccessKilledDao successKilledDao;
	
	@Test
	public void testSaveSuccessKilled () {
		int saveSuccessKilled = successKilledDao.saveSuccessKilled(1000L, 18855158810L);
		System.out.println(saveSuccessKilled);
	}
	
	@Test
	public void testGetByIdWithSeckill () {
		SuccessKilled successKilled = successKilledDao.getByIdWithSeckill(1000L,18855158810L);
		System.out.println(successKilled);
	}
}
