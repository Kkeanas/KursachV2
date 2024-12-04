package com.project.kursachv2.Practice;

import com.project.kursachv2.Organization.Organization;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/practice")
public class PracticeController {
    @Autowired
    private PracticeService practiceService;
    // Получение студента по индетификатору
    @GetMapping("/{id}")
    public Practice getPracticeById(@PathVariable long id) {
        return practiceService.getPracticeById(id);
    }
    // Получение всех студентов
    @GetMapping
    public List<Practice> getAllPractices() {
        return practiceService.getAllPractices();
    }
    // Запись нового студента
    @PostMapping
    public Practice addPractice(@Valid @RequestBody PracticeDTO practiceDTO) {
        return practiceService.addPractice(practiceService.convertDTOtoPractice(practiceDTO));
    }
    // Обновление студента по индетификатору
    @PutMapping("/{id}")
    public Practice updatePracticeById(@PathVariable long id, @Valid @RequestBody PracticeDTO practiceDTO) {
        return practiceService.updatePracticeById(practiceService.convertDTOtoPractice(practiceDTO), id);
    }
    // Удаление студента по индетификатору
    @DeleteMapping("/{id}")
    public void deletePracticeById(@PathVariable long id) {
        practiceService.deletePracticeById(id);
    }
}
