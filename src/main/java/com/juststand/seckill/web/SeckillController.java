package com.juststand.seckill.web;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.juststand.seckill.dto.Exposer;
import com.juststand.seckill.dto.SeckillExecution;
import com.juststand.seckill.dto.SeckillResult;
import com.juststand.seckill.enums.SeckillStateEnum;
import com.juststand.seckill.exception.RepeatKillException;
import com.juststand.seckill.exception.SeckillCloseException;
import com.juststand.seckill.exception.SeckillException;
import com.juststand.seckill.model.Seckill;
import com.juststand.seckill.service.SeckillService;

/**
 * 控制器
 * Created By juststand on 2017-3-22
 */
@Controller
@RequestMapping("/") //模块
public class SeckillController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public SeckillService seckillService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model) {
		List<Seckill> list = seckillService.getSeckills();
		log.info(list.toString());
		model.addAttribute("list", list);
		return "list";// /WEB-INF/jsp/list.jsp
	}
	
	@RequestMapping(value="/{seckillId}/detail",method=RequestMethod.GET)
	public String detail (@PathVariable("seckillId")Long seckillId ,Model model) {
		if (seckillId == null) {
			return "redirect:/seckill/list";
		}
		Seckill seckill = seckillService.getById(seckillId);
		if (seckill == null) {
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill",seckill);
		return "detail";
	}
	
	//异步请求数据
	@RequestMapping(value="/{seckillId}/exposer",method=RequestMethod.POST,
			produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable("seckillId")Long seckillId){
		SeckillResult<Exposer> seckillResult;
		try {
			Exposer exposer = seckillService.exportSeckillUrl(seckillId);
			seckillResult = new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			seckillResult = new SeckillResult<Exposer>(false, e.getMessage());
		}
		return seckillResult;
	}
	
	//执行秒杀
	@RequestMapping(value="/{seckillId}/{md5}/execution",method=RequestMethod.POST,
			produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId")Long seckillId ,
			@PathVariable("md5")String md5,@CookieValue(value="userPhone",required=false )Long userPhone) {
		if (userPhone == null) {
			return new SeckillResult<SeckillExecution>(false, "未注册");
		}
		SeckillResult<SeckillExecution> seckillResult;
		try {
			SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
			seckillResult = new SeckillResult<SeckillExecution>(true, seckillExecution);
		} catch (SeckillCloseException e) {
			SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.END);
			seckillResult = new SeckillResult<SeckillExecution>(true, seckillExecution);
		} catch (RepeatKillException e) {
			SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
			seckillResult = new SeckillResult<SeckillExecution>(true, seckillExecution);
		} catch (SeckillException e) {
			SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
			seckillResult = new SeckillResult<SeckillExecution>(true, seckillExecution);
		}
		return seckillResult;
	}
	
	//获取系统时间
	@RequestMapping(value="/time/now",method=RequestMethod.GET,
			produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<Long> getNowTime() {
		Date date = new Date();
		return new SeckillResult<Long>(true, date.getTime());
	}
	
}
