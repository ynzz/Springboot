package com.szl.mapper;


import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.szl.entity.Customer;

public interface CustomerMapper extends BaseMapper<Customer>{

	public Customer getCustomerByMobile(@Param("mobile")String mobile);
	
	
}
