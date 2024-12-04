package com.project.kursachv2.Report;

import com.project.kursachv2.Organization.Organization;
import com.project.kursachv2.Organization.OrganizationDTO;
import com.project.kursachv2.Organization.OrganizationService;
import com.project.kursachv2.Practice.PracticeService;
import com.project.kursachv2.Student.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    // Получение студента по индетификатору
    @GetMapping("/{id}")
    public Report getReportById(@PathVariable long id) {
        return reportService.getReportById(id);
    }
    // Получение всех студентов
    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }
    // Запись нового студента
    @PostMapping
    public Report addReport(@Valid @RequestBody ReportDTO reportDTO) {
        return reportService.addReport(reportService.convertDTOtoReport(reportDTO));
    }
    // Обновление студента по индетификатору
    @PutMapping("/{id}")
    public Report updateReportById(@PathVariable long id, @Valid @RequestBody ReportDTO reportDTO) {
        return reportService.updateReportById(reportService.convertDTOtoReport(reportDTO), id);
    }
    // Удаление студента по индетификатору
    @DeleteMapping("/{id}")
    public void deleteReportById(@PathVariable long id) {
        reportService.deleteReportById(id);
    }

    @PutMapping
    public Report updateReport(@Valid @RequestBody NewReport newReport) {
        return reportService.updateCreatedReport(newReport);
    }
}
