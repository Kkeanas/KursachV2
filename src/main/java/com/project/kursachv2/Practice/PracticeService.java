package com.project.kursachv2.Practice;

import com.project.kursachv2.Organization.Organization;
import com.project.kursachv2.Organization.OrganizationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PracticeService {
    @Autowired
    private PracticeRepository practiceRepository;

    public boolean practiceExitsById(long id) {
        return practiceRepository.existsById(id);
    }

    public Practice getPracticeById(long id) {
        return practiceRepository.getReferenceById(id);
    }

    public List<Practice> getAllPractices() {
        return practiceRepository.findAll();
    }

    public Practice addPractice(Practice practice) {
            return practiceRepository.save(practice);
    }

    public Practice updatePracticeById(Practice practice, long id) {
        if (practiceExitsById(id)) {
            practice.setId(id);
            return practiceRepository.save(practice);
        } else {
            throw new DataIntegrityViolationException("Практики с id="+id+" не существует");
        }
    }

    public void deletePracticeById(long id) {
        if (practiceExitsById(id)) {
            practiceRepository.deleteById(id);
        } else {
            throw new DataIntegrityViolationException("Практики с id="+id+" не существует");
        }
    }

    public Practice convertDTOtoPractice(PracticeDTO practiceDTO) {
        Practice practice = new Practice();
        practice.setPrcIsPayed(practiceDTO.isPrcIsPayed());
        practice.setPrcRelevance(practiceDTO.getPrcRelevance());
        practice.setPrcMaterial(practiceDTO.getPrcMaterial());
        practice.setPrcDescription(practiceDTO.getPrcDescription());
        practice.setPrcDirector(practiceDTO.getPrcDirector());
        practice.setPrcStarting(practiceDTO.getPrcStarting());
        practice.setPrcEnding(practiceDTO.getPrcEnding());
        practice.setPrcFutureWork(practiceDTO.isPrcFutureWork());
        practice.setPrcNextYear(practiceDTO.isPrcNextYear());
        practice.setPrcReportPassed(practiceDTO.isPrcReportPassed());
        practice.setPrcPosition(practiceDTO.getPrcPosition());
        practice.setPrcWork(practiceDTO.getPrcWork());
        return practice;
    }
}
