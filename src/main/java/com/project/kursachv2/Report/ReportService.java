package com.project.kursachv2.Report;

import com.project.kursachv2.Group.Group;
import com.project.kursachv2.Group.GroupService;
import com.project.kursachv2.Organization.Organization;
import com.project.kursachv2.Organization.OrganizationService;
import com.project.kursachv2.Practice.Practice;
import com.project.kursachv2.Practice.PracticeService;
import com.project.kursachv2.Student.Student;
import com.project.kursachv2.Student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    private GroupService groupService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private PracticeService practiceService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ReportRepository reportRepository;

    public boolean reportExitsById(long id) {
        return reportRepository.existsById(id);
    }

    public Report getReportById(long id) {
        return reportRepository.getReferenceById(id);
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Report addReport(Report report) {
        return reportRepository.save(report);
    }

    public Report updateReportById(Report report, long id) {
        if (reportExitsById(id)) {
            report.setId(id);
            return reportRepository.save(report);
        } else {
            throw new DataIntegrityViolationException("Отчета с id="+id+" не существует");
        }
    }

    public void deleteReportById(long id) {
        if (reportExitsById(id)) {
            reportRepository.deleteById(id);
        } else {
            throw new DataIntegrityViolationException("Отчета с id="+id+" не существует");
        }
    }

    public List<Report> filterByGroup(Group group) {
        return reportRepository.findAllByGroup(group);
    }
    public List<Report> filterByOrganization(Organization organization) {
        return reportRepository.findAllByOrganization(organization);
    }

    public Report convertDTOtoReport(ReportDTO reportDTO) {
        Report report = new Report();
        Student student = new Student();
        Practice practice = new Practice();
        student.setStuFirstName(reportDTO.getStuFirstName());
        student.setStuSecondName(reportDTO.getStuSecondName());
        student.setStuSurName(reportDTO.getStuSurName());
        report.setStudent(studentService.addStudent(student));
        report.setPractice(practiceService.addPractice(practice));
        report.setGroup(groupService.getGroupById(reportDTO.getGroup_id()));
        return report;
    }

    public Report updateCreatedReport(NewReport newReport) {
        Report report = getReportById(newReport.getId());
        practiceService.updatePracticeById(practiceService.convertDTOtoPractice(newReport.getPractice()),report.getPractice().getId());
        studentService.updateStudentById(studentService.convertDTOtoStudent(newReport.getStudent()),report.getStudent().getId());
        report.setOrganization(organizationService.getOrganizationById(newReport.getOrganization_id()));
        return updateReportById(report, newReport.getId());
    }
}
