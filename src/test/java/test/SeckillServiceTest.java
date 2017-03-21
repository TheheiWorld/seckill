package test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.juststand.seckill.dto.Exposer;
import com.juststand.seckill.dto.SeckillExecution;
import com.juststand.seckill.model.Seckill;
import com.juststand.seckill.service.SeckillService;


/**
 * 配置spring和junit整合
 * Created By juststand on 2017-3-20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-*.xml"})
public class SeckillServiceTest {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private SeckillService seckillService;
	
	@Test
	public void testGetSeckills (){
		List<Seckill> seckills = seckillService.getSeckills();
		logger.info("list={}",seckills);
	}
	
	@Test
	public void testExportSeckillUrl (){
		Exposer exposer = seckillService.exportSeckillUrl(1000L);
		logger.info("exposer={}",exposer);
	}
	
	@Test
	public void testExecuteSeckill (){
		SeckillExecution executeSeckill = seckillService.executeSeckill(1000L, 18854158810L, "86152fbfaaf4786a43d20cef308a157e");
		logger.info(executeSeckill.toString());
	}
	
}
