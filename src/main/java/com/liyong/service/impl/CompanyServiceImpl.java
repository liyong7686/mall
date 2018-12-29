package com.liyong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyong.dao.CompanyMapper;
import com.liyong.dao.MenuMapper;
import com.liyong.model.Company;
import com.liyong.service.ICompanyService;

@Service
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private CompanyMapper companyMapper;
	
	public List<Company> findCompanyList(Company company) throws Exception{
		return companyMapper.findCompanyList(company);
	}
	
}
