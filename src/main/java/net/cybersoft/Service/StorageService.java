package net.cybersoft.Service;

import net.cybersoft.Details.CustomUserDetails;
import net.cybersoft.Entity.ImageData;
import net.cybersoft.Repository.StorageRepository;
import net.cybersoft.Utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageRepository storageRepo;

    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = storageRepo.save(ImageData.builder()
                        .fileowner(SecurityContextHolder.getContext().getAuthentication().getName())
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "Dosya yukleme islemi basarili : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName){
        // Resmin veritabaninda olma durumu sorgulaniyor.
        // Eger dosya varsa decompress (cozumlenip) return ediliyor.
        Optional<ImageData> dbImageData = storageRepo.findByName(fileName);

        // "No Value Present" hatasi icin cozum
        // Eger gecerli bir deger varsa
        if(dbImageData.isPresent()){
            byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
            return images;
        }
        else return null;
    }

    // Dosyaya ait ID bilgisi ile dosya silme
    public void deleteImageDataById(Long id)
    {
        storageRepo.deleteById(id);
    }

}
