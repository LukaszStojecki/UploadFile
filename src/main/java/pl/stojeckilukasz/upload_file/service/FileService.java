package pl.stojeckilukasz.upload_file.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.stojeckilukasz.upload_file.model.File;
import pl.stojeckilukasz.upload_file.repository.FileRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FileService {


    private FileRepository fileRepository;

    public void saveFile(MultipartFile multipartFile) throws IOException {
        File file = new File();
        file.setTitle(multipartFile.getOriginalFilename());
        file.setDescription(multipartFile.getContentType());
        file.setFileByte(multipartFile.getBytes());
        fileRepository.save(file);
    }

    public List<File> getAllFiles() {
        return fileRepository.findAll();

    }

    public Optional<File> findByTitle(String name) {
        return fileRepository.findByTitle(name);
    }

}
