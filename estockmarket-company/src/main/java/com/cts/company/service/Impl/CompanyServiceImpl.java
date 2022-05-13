package com.cts.company.service.Impl;

import com.cts.company.model.Company;
import com.cts.company.repository.CompanyRepository;
import com.cts.company.response.CompanySaveResponse;
import com.cts.company.response.CompanyStockResponse;
import com.cts.company.response.Stock;
import com.cts.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public CompanySaveResponse register(Company company) {
        CompanySaveResponse response = new CompanySaveResponse();
        if (company!=null && company.getCompanyTurnover() >= 100000000) {
            Company savedCompany = companyRepository.save(company);
            if (savedCompany != null) {
                response.setMessage("Company saved successfully!!");
                response.setStatus(HttpStatus.OK.toString());
                response.setHttpstatus(HttpStatus.OK);
                response.setCompany(savedCompany);
            } else {
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
                response.setHttpstatus(HttpStatus.INTERNAL_SERVER_ERROR);
                response.setMessage("Company not saved successfully!!");
            }
        }
        else {
            response.setMessage("Company Turnover must be greater than 10cr");
            response.setHttpstatus(HttpStatus.BAD_REQUEST);
            response.setStatus(HttpStatus.BAD_REQUEST.name());
        }
        return response;
    }
    public CompanyStockResponse getCompanyWithStock(String companyCode) {
        Company company = companyRepository.findByCompanyCode(companyCode);
        CompanyStockResponse companyStock = new CompanyStockResponse();
        if(company!= null) {
            Stock stock = restTemplate.getForObject("http://localhost:8082/api/v1.0/market/stock/" +companyCode , Stock.class);
            companyStock.setCompany(company);
            companyStock.setStock(stock);
            return companyStock;
        }
        return companyStock;
    }

    public List<CompanyStockResponse> getAllCompaniesWithStocks() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyStockResponse> companyStockList = new ArrayList<>();
        for(Company company: companies) {
            CompanyStockResponse companyStock = new CompanyStockResponse();
            companyStock.setCompany(company);
            Stock stock = restTemplate.getForObject("http://localhost:8082/api/v1.0/market/stock/" +company.getCompanyCode() , Stock.class);
            companyStock.setStock(stock);
            companyStockList.add(companyStock);
        }
        return companyStockList;
    }

    public void deleteCompanyWithStock(String companyCode) {
        Company company = companyRepository.findByCompanyCode(companyCode);
        if (company != null) {
            companyRepository.delete(company);
            restTemplate.delete("http://localhost:8082/api/v1.0/market/stock/delete" + companyCode);
        }
    }

}
