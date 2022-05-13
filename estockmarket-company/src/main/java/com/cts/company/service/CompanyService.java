package com.cts.company.service;

import com.cts.company.model.Company;
import com.cts.company.response.CompanySaveResponse;
import com.cts.company.response.CompanyStockResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

    public CompanySaveResponse register(Company company);
    public CompanyStockResponse getCompanyWithStock(String companyCode);
    public List<CompanyStockResponse> getAllCompaniesWithStocks();
    public void deleteCompanyWithStock(String companyCode);
}
