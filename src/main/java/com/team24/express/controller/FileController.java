package com.team24.express.controller;

import com.team24.express.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @className: FileController
 * @author: Bobby
 * @date: 11/9/2023
 * 文件处理相关
 **/
@Tag(name = "FileController", description = "文件处理相关控制器，上传")
@RestController
@CrossOrigin
public class FileController {

    /**
     * 在D盘里
     */
    private final static String AVATAR_DIR = "D:\\GraduateLearning\\Courses\\Engineer Trainning\\292309\\projects\\ExpressProject\\express-vue\\src\\assets\\upload\\";

    public Result handleFileUpload(MultipartFile file, String uploadDir) {
        if (!file.isEmpty()) {
//                byte[] bytes = file.getBytes();
//                File dirFile = new File(uploadDir);
//                if (!dirFile.exists()) {
//                    dirFile.mkdir();
//                }
//                Path path = Paths.get(uploadDir + file.getOriginalFilename());
////                if (!Files.exists(path))
////                    Files.createDirectories(path);
//                Files.write(path, bytes);


            String requestName = file.getOriginalFilename();
            //1.2、设置上传目录
            File dirFile = new File(uploadDir);
            if (!dirFile.exists()) {
                //如果不存在，创建对应文件夹
                dirFile.mkdirs();
            }

            //2.利用uuid获取加密字符
            String nameOne = UUID.randomUUID().toString().replace("-", "");
            String fileName = nameOne.concat("-" + requestName);
            String filePath = dirFile.getPath() + "/" + fileName;
            //3、利用multipartFile自带方法将JSON数据转化为对应的
            try {
                file.transferTo(new File(filePath));
            } catch (IOException e) {
                e.printStackTrace();
                return Result.error("上传失败");
            }

            Result result = Result.success("上传成功");
            Map<String, Object> data = new HashMap<>();
            data.put("filename", file.getOriginalFilename());
            data.put("contentType", file.getContentType());
            data.put("name", file.getName());
            data.put("path", filePath);
            result.setData(data);
            return result;
        } else {
            return Result.error("上传失败：请选择要上传的文件！");
        }
    }

    @Operation(summary = "上传文件", description = "通用的上传文件接口",
            parameters = {
                    @Parameter(name = "file", schema = @Schema(implementation = MultipartFile.class))
            })
    @PostMapping("/upload/avatar")
    public Result uploadAvatar(@RequestPart("file") MultipartFile file) {
        return handleFileUpload(file, AVATAR_DIR);
    }
}
