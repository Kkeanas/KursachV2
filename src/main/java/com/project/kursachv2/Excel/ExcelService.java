package com.project.kursachv2.Excel;

import com.project.kursachv2.Group.Group;
import com.project.kursachv2.Group.GroupDTO;
import com.project.kursachv2.Group.GroupService;
import com.project.kursachv2.Organization.OrganizationService;
import com.project.kursachv2.Report.Report;
import com.project.kursachv2.Report.ReportDTO;
import com.project.kursachv2.Report.ReportService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    private ReportService reportService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private GroupService groupService;
    public ByteArrayOutputStream exportToExcel(ExcelDTO excelDTO) throws Exception{
        System.out.println(excelDTO.getFilterBy().equals("group"));

        List<Report> reports = new ArrayList<>();
        if (excelDTO.getFilterBy().equals("group")) {
            System.out.println("Filter by group");
            reports = reportService.filterByGroup(groupService.getGroupById(excelDTO.getFilterId()));
        }
        if (excelDTO.getFilterBy().equals("organization")) {
            reports = reportService.filterByOrganization(organizationService.getOrganizationById(excelDTO.getFilterId()));
        }

        Workbook workbook =  new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Reports");

        // Создаем заголовки
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Название");
        headerRow.createCell(2).setCellValue("Цена");

        // Заполняем данными
        int rowCount = 1;
        for (Report report : reports) {
            Row row = sheet.createRow(rowCount++);
            List<String> info = report.getAllinfo();
            for (int i = 0; i < info.size(); i++) {
                System.out.print(info.get(i));
                row.createCell(i).setCellValue(info.get(i));
            }
        }

        // Записываем в ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream;
    }
    public void importFromExcel(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        Row groupN = rowIterator.next();
        String groupName = groupN.getCell(0).getStringCellValue();
        List<ReportDTO> reports = new ArrayList<>();
        while (rowIterator.hasNext()) {
            ReportDTO report = new ReportDTO();
            Row row = rowIterator.next();
            report.setStuSecondName(row.getCell(0).getStringCellValue());
            report.setStuFirstName(row.getCell(1).getStringCellValue());
            report.setStuSurName(row.getCell(2).getStringCellValue());
            reports.add(report);
        }
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setGrpName(groupName);
        Group group = groupService.addGroup(groupService.convertDTOtoGroup(groupDTO));
        for (ReportDTO report : reports) {
            report.setGroup_id(group.getId());
            reportService.addReport(reportService.convertDTOtoReport(report));
        }
        workbook.close();
        fis.close();
    }
}
