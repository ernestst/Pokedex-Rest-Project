package pl.edu.pb.wi.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pb.wi.ws.pokedex.ImageServiceI;


@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageServiceI imageService;

    public ImageController(ImageServiceI imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/add")
    public String uploadImage(@RequestParam(name = "data", required = false) MultipartFile data, @RequestParam("code") String code) {
        return imageService.uploadImage(data, code);
    }

    @GetMapping
    public ResponseEntity<byte[]> downloadImage(@RequestParam String code) {
        return imageService.downloadImage(code);
    }
}
