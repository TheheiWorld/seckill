package test;

import java.util.Date;
import java.util.List;

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
	public void testReduceNumber() {
		long seckillId = 1000;
		int number = seckillDao.reduceNumber(seckillId, new Date());
		System.out.println(number);
	}
	
	@Test
	public void testGetById (){
		long seckillId = 1000;
		Seckill seckill = seckillDao.getById(seckillId);
		System.out.println(seckill);
	}
	
	@Test
	public void getAll (){
		List<Seckill> list = seckillDao.getAll(0, 5);
		for (Seckill seckill : list) {
			System.out.println(seckill);
		}
	}
}
