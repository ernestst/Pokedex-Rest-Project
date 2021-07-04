package pl.edu.pb.wi.ws.pokedex;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.awt.*;

public interface ImageServiceI {
    String uploadImage(MultipartFile data, String code);

    ResponseEntity<byte[]> downloadImage(String code);
}
