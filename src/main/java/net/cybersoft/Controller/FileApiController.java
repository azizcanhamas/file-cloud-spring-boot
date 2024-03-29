package net.cybersoft.Controller;

import net.cybersoft.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

//@RestController
//@RequestMapping("/image")
@Controller
public class FileApiController {

    @Autowired
    private StorageService storageService;

    // Postman ile resimlerin POST yontemiyle gonderilmesi icin
    // http://localhost:9191/uploadImage adresi body kisminda
    // form-data olarak Key:image Value: resim.jpg seklinde
    // istek atilmalidir.
    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        // StorageService servisindeki uploadImage
        // fonksiyonu upload isleminin gerceklesmesi
        // icin dosya ile cagriliyor.
        storageService.uploadImage(file);
        return "redirect:/home";
    }

    // Resimlerin GET yoluyla istenebilmesi http://localhost:9191/image/intel.jpg
    // gibi URL adresi cagrilmalidir.
    @GetMapping("/get/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable Long id){
        byte[] imageData=storageService.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    //Kullaniciya ait bir dosyanin silinmesi icin
    @GetMapping(value = "/delete/{id}")
    public String deleteFile(@PathVariable("id")Long id)
    {
        storageService.deleteImageDataById(id);
        return "redirect:/home";
    }

}
