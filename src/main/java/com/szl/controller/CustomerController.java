package com.szl.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.szl.common.ResourceUtil;
import com.szl.common.ResultEnum;
import com.szl.entity.Customer;
import com.szl.service.CustomerService;
import com.szl.vo.ResultVo;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Resource
	private CustomerService customerService;
	
	@Autowired
	private ResourceUtil resourceUtil;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DataSource dataSource;
	
	/**
	 * hello world
	 * @return
	 */
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String sayHello(){
		return "hello springBoot!" + resourceUtil.getName();
	}
	/**
	 * mybatis
	 * 根据手机号查询用户信息
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/getCustomer", method = RequestMethod.GET)
	public ResultVo getCustomer(@RequestParam(value = "mobile") String mobile){
		Customer customer = customerService.getCustomerByMobile(mobile);
		return ResultVo.getData(ResultEnum.SUCCESS, customer);
	}
	
	/**
	 * mybatis-plus
	 * @param mobile
	 * @return
	 */
	@RequestMapping("/getByMobile")
	public ResultVo getByMobile(@RequestParam(value = "mobile") String mobile){
		return ResultVo.getData(ResultEnum.SUCCESS, customerService.selectList(new EntityWrapper<Customer>().where("mobile", mobile)));
	}
	
	
	/**
	 * jdbcTemplates
	 * @param mobile
	 * @return
	 */
	@RequestMapping("/testJdbcTemplate")
	public ResultVo testJdbcTemplate(@RequestParam(value = "mobile") String mobile){
		ResultVo resultVo = new ResultVo();
		String sql = "select * from customer where mobile = ?";
		Object[] params = {mobile};
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, params);
		resultVo.setCode("0");
		resultVo.setMsg("成功");
		resultVo.setData(list);
		return resultVo;
	}
	
	/**
	 * 查询链接池名称
	 * @return
	 */
	@RequestMapping("/getDataSource")
	public ResultVo getDataSource() {
		ResultVo resultVo = ResultVo.get("0", "成功");
		String name = dataSource.getClass().getName();
		resultVo.setData(name);
		return resultVo;
	}
	
}
