package pl.stojeckilukasz.upload_file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.stojeckilukasz.upload_file.model.File;

import java.util.Optional;


@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    Optional<File> findByTitle(String filename);
}
