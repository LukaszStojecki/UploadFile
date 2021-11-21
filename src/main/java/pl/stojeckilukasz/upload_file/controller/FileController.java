package pl.stojeckilukasz.upload_file.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.stojeckilukasz.upload_file.model.File;
import pl.stojeckilukasz.upload_file.service.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class FileController {

    private FileService fileService;

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("myfile") MultipartFile myfile) throws IOException {
        fileService.saveFile(myfile);
        return "redirect:/";
    }

    @GetMapping("/images/{filename}")
    public void getImage(@PathVariable("filename") String filename, HttpServletResponse response) throws Exception {
        Optional<File> img = fileService.findByTitle(filename);
        if (img.isPresent()) {
            byte[] bytes = img.get().getFileByte();
            InputStream is = new BufferedInputStream(new ByteArrayInputStream(bytes));
            String mimeType = URLConnection.guessContentTypeFromStream(is);
            response.setContentType(mimeType);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        }
    }

    @GetMapping("/")
    public String home(Model model) {
        List<File> files = fileService.getAllFiles();
        model.addAttribute("files", files);
        return "index";
    }
}
