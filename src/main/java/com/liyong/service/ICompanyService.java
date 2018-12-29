package com.liyong.service;

import java.util.List;

import com.liyong.model.Company;

public interface ICompanyService {

	public List<Company> findCompanyList(Company company) throws Exception;
}
