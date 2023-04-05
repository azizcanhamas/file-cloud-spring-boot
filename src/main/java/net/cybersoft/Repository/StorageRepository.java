package net.cybersoft.Repository;


import net.cybersoft.Entity.ImageData;
import net.cybersoft.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData,Long>{
    // Dosya ismiyle image_data tablosunda arama yapmak
    //  icin calisacak Hibernate fonksiyonu
    Optional<ImageData> findByName(String fileName);

    //image_data tablosundaki tum kayitlari liste olarak dondurur.
    List<ImageData> findAll();

    //image_data tablosundaki belirtilen ID'ye sahip kayitlari getir.
    @Query("SELECT f FROM ImageData f WHERE f.fileowner = ?1")
    public List<ImageData> findByFileOwner(String username);
}
