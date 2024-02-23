package com.IT.osahaneat.services;

import com.IT.osahaneat.services.imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService implements FileServiceImp {

    @Value("${fileUpload.rootPath}")
    private String rootPath ;
    private Path root;

//    do t sử dụng value để lấy giá trị nên nó được lấy cùnglaanfnf vì vậy không thể truyền biến làm param
//    vì vậy cần có hàm khởi tạo
    public void init(){
        try {
            root =Paths.get(rootPath);
            if(Files.notExists(root)){
                Files.createDirectories(root);
            }
        }catch(Exception e){
            System.out.println("error create folder upload file "+e.getMessage());
        }

    }
    @Override
    public Boolean saveFile(MultipartFile file) {
        try {
            init();
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            return true;
        }catch (Exception e ){
            System.out.println("error save file "+e.getMessage());
            return false;
        }
    }

    @Override
    public Resource loadFile(String filename) {
        try {
            init();
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
        }catch (Exception e ){
            System.out.println("error is not exists "+e.getMessage());
        }
        return null;
    }
}
