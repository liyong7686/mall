package com.liyong.dao;

import java.util.List;

import com.liyong.model.Company;

public interface CompanyMapper {

	public List<Company> findCompanyList(Company company) ;
}
