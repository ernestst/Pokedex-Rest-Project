package pl.edu.pb.wi.ws.pokedex;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pb.wi.config.FileStorageProperties;
import pl.edu.pb.wi.pokemon.exception.FileStorageException;

import javax.xml.ws.WebServiceException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageService implements ImageServiceI {

    private final Path fileStorageLocation;

    @Autowired
    public ImageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public String uploadImage(MultipartFile data, String code) {
        Path targetLocation = this.fileStorageLocation.resolve(code + ".png");
        try{
            Files.copy(data.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            throw new WebServiceException("Upload Failed!");
        }

        return "success";
    }

    @Override
    public ResponseEntity<byte[]> downloadImage(String code) {
        Path filePath = this.fileStorageLocation.resolve(code + ".png").normalize();
        try{
            byte[] data = Files.readAllBytes(filePath);
            data = Base64.encode(data);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (IOException e){
            throw new WebServiceException("Download Failed!");
        }
    }
}
