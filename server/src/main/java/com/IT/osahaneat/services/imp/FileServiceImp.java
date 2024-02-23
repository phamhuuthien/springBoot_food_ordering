package com.IT.osahaneat.services.imp;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileServiceImp {
//    MultipartFile sử dụng cơ chế stream để tiết kiệm ta nguyên của hệ thống
//    không sử dụng base 64 do tốn tài nguyên
    Boolean saveFile(MultipartFile file);
    Resource loadFile(String filename);
}
