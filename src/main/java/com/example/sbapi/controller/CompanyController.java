package com.example.sbapi.controller;

import com.example.sbapi.model.Company;
import com.example.sbapi.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class CompanyController {
    private final CompanyRepository companyRepository;

    // TODO: I feel this can be better, lets look at Spring Boot and some of the freebies they provide.
    /**
     * This is the first attempt at creating an excel file to be made available over the API, will clean this up as I go.
     * @return
     * @throws IOException
     */

    @GetMapping("/companies/excelDownload")
    public ResponseEntity<InputStreamResource> doStuff() throws IOException {

        String[] COLUMNs = {"Id", "Name", "Code"};
        Iterable<Company> companies = this.companyRepository.findAll();
        ByteArrayInputStream stream;
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {

            Sheet sheet = workbook.createSheet("Companies");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowIdx = 1;
            for (Company company : companies) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(company.getId());
                row.createCell(1).setCellValue(company.getName());
                row.createCell(2).setCellValue(company.getCode());
            }

            workbook.write(out);
            stream = new ByteArrayInputStream(out.toByteArray());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=customers.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(stream));

    }
}
