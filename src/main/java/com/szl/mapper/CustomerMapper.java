package com.szl.mapper;

import org.apache.ibatis.annotations.Param;

import com.szl.entity.Customer;

public interface CustomerMapper {

	public Customer getCustomerByMobile(@Param("mobile")String mobile);
}
