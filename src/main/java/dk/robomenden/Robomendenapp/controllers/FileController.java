package dk.robomenden.Robomendenapp.controllers;


import dk.robomenden.Robomendenapp.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    //Метод для просмотра файла
    //Method to view the file
    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws IOException {
        // получаем файл по имени из папки uploads
        // get the file by name from the uploads folder
        Resource fileResource = new FileSystemResource("/root/uploads" + fileName);

        // проверяем, существует ли файл
        // check if the file exists
        if (!fileResource.exists()) {
            throw new FileNotFoundException("File not found: " + fileName);
        }

        // определяем тип файла и его размер
        // determine the file type and size
        String contentType = Files.probeContentType(fileResource.getFile().toPath());
        long contentLength = fileResource.contentLength();

        // формируем заголовки ответа
        // generate response headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        headers.add(HttpHeaders.CONTENT_LENGTH, Long.toString(contentLength));

        // возвращаем ResponseEntity с файлом и заголовками
        // Return ResponseEntity with the file and headers
        return ResponseEntity.ok()
                .headers(headers)
                .body(fileResource);
    }

    //Метод для удаления файла
    //Method for deleting a file
    @PostMapping("/{id}/deleteFile")
    public String delete(@PathVariable("id") int id, @RequestParam("fileName") String fileName,
                                                     @RequestParam("clientId") String clientId) {
        fileService.deleteById(id);
        File file = new File("/root/uploads" + fileName);
        file.delete();
        return "redirect:/buyer/" + Integer.parseInt(clientId) + "/editBuyer";
    }

}

