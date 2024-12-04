package com.project.kursachv2.Excel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {
    @Autowired
    private ExcelService excelService;

    @PostMapping("/export")
    public ResponseEntity<ByteArrayResource> exportExcel(@RequestBody ExcelDTO excelDTO) {
        try {
            System.out.println(excelDTO.getFilterBy());
            ByteArrayOutputStream outputStream = excelService.exportToExcel(excelDTO);
            ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=excel.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping(value = "/import",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty() || !file.getOriginalFilename().endsWith(".xlsx")) {
            return new ResponseEntity<>("File is not valid", HttpStatus.BAD_REQUEST);
        }

        try {
            // Сохранение временного файла
            File tempFile = File.createTempFile("upload-", ".xlsx");
            file.transferTo(tempFile);
            excelService.importFromExcel(tempFile);
            tempFile.delete(); // Удаляем временный файл
            return new ResponseEntity<>("Products imported successfully", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to import products", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
