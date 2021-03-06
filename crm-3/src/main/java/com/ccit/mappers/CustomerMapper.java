package com.ccit.mappers;


import com.ccit.pojo.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerMapper {
    List<Customer> findByParams(Map<String, Object> params);

    Long countByParams(Map<String, Object> params);

    Long count();

    Customer findById(Integer id);

    void insert(Customer customer);

    List<Customer> findAllCompany(Integer userid);
    
    List<Customer> findByCompanyId(Integer companyid);

    void update(Customer customer);

    void delete(Integer id);

    void updateUser(Customer customer);

    List<Customer> findAll(@Param("userid") Integer userid);
}
