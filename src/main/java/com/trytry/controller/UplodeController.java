package com.trytry.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;

import com.trytry.common.lang.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/files")
public class UplodeController {
    @Value("${server.port}")
    private String port;

    private static final String ip = "http://localhost";

    @PostMapping("/uplode")
    public Result<?> uplode(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();//获取源文件名称
        //定义文件唯一标识（前缀）
        String uuid = IdUtil.fastSimpleUUID();
        String rootFilePath = System.getProperty("user.dir") + "/src/main/resources/files/" + uuid + "_" + originalFilename;//获取上传路径
        FileUtil.writeBytes(file.getBytes(),rootFilePath);//把文件写入上传路径
        return Result.success(ip + ":" + port + "/files/" + uuid);//返回url

    }

    @GetMapping("/{uuid}")
    public void getFiles(HttpServletResponse response, @PathVariable String uuid){
        OutputStream os;//新建输出流对象
        String basePath = System.getProperty("user.dir") + "/src/main/resources/files/";
        List<String> fileNames = FileUtil.listFileNames(basePath);//获取所有文件名
        String fileName = fileNames.stream().filter(name -> name.contains(uuid)).findAny().orElse("");//找到跟参数一致的文件
        try {
            if (StrUtil.isNotEmpty(fileName)){
                response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + fileName);//通过文件的路径读取文件字节流
                os = response.getOutputStream();//通过输出流返回文件
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (IOException e) {
            System.out.println("文件下载失败");
        }

    }
}
