package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.juststand.seckill.dao.SeckillDao;
import com.juststand.seckill.model.Seckill;


/**
 * 配置spring和junit整合
 * Created By juststand on 2017-3-20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
	
	@Resource
	private SeckillDao seckillDao;
	
	@Test
	public void testTeduceNumber() {
		long seckillId = 1000;
		Seckill seckill = seckillDao.getById(seckillId);
		System.out.println(seckill);
	}
}
