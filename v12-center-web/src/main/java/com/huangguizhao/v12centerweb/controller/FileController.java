package com.huangguizhao.v12centerweb.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.huangguizhao.v12.common.pojo.ResultBean;
import com.huangguizhao.v12.common.pojo.WangEditorUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author huangguizhao
 */
@Controller
@RequestMapping("file")
public class FileController {

    @Autowired
    private FastFileStorageClient client;

    @Value("${image.server}")
    private String imageServer;

    @PostMapping("upload")
    @ResponseBody
    public ResultBean upload(MultipartFile file){
        //1.获取文件的扩展名
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        try {
            StorePath storePath = client.uploadFile(file.getInputStream(), file.getSize(), extName, null);
            //
            StringBuilder path = new StringBuilder(imageServer).append(storePath.getFullPath());
            //
            return new ResultBean(200,path);
        } catch (IOException e) {
            e.printStackTrace();
            //TODO 记录异常信息到日志中
            return new ResultBean(500,"文件上传失败");
        }
    }

    @PostMapping("multiUpload")
    @ResponseBody
    public WangEditorUploadResult multiUpload(MultipartFile[] files){
        //1
        String[] data = new String[files.length];
        //2
        try {
            for (int i=0;i<files.length;i++) {
                String originalFilename = files[i].getOriginalFilename();
                String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
                //
                StorePath storePath = client.uploadFile(files[i].getInputStream(), files[i].getSize(), extName, null);
                //
                StringBuilder path = new StringBuilder(imageServer).append(storePath.getFullPath());
                //
                data[i] = path.toString();
            }
        }catch (IOException e){
            e.printStackTrace();
            //TODO 记录异常信息到日志中
            return new WangEditorUploadResult("-1",null);
        }
        return new WangEditorUploadResult("0",data);
    }
}
