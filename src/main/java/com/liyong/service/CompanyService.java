package com.liyong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyong.dao.CompanyMapper;
import com.liyong.dao.MenuMapper;
import com.liyong.model.Company;

@Service
public class CompanyService {

	@Autowired
	private CompanyMapper companyMapper;
	
	public List<Company> findCompanyList(Company company) throws Exception{
		return companyMapper.findCompanyList(company);
	}
	
}
