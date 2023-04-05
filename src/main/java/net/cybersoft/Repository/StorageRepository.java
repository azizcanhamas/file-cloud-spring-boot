package net.cybersoft.Repository;


import net.cybersoft.Entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData,Long>{
    // Dosya ismiyle veritabaninda arama yapmak icin
    // calisacak Hibernate fonksiyonu
    Optional<ImageData> findByName(String fileName);
}
