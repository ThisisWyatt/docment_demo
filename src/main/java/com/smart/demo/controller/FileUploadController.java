package com.smart.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * Description TODO
 * Author Cloudr
 * Date 2020/2/9 9:25
 **/
@Controller
public class FileUploadController {

    @RequestMapping("/file")
    public String file(){
        return "file";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file")MultipartFile file){
        if(!file.isEmpty()){
            try {
                BufferedOutputStream outputStream=
                        new BufferedOutputStream(new FileOutputStream(file.getOriginalFilename()));
                outputStream.write(file.getBytes());
                outputStream.flush();
                outputStream.close();
            }catch (FileNotFoundException e){
                    e.printStackTrace();
                    return "上传失败，"+e.getMessage();
            }catch (IOException e){
                e.printStackTrace();
                return "上传失败，"+e.getMessage();
            }
            return "上传成功";
        }
        else {
            return "上传失败，文件为空";
        }
    }

    @RequestMapping("/multiFile")
    public String multiFile(){
        return "multiFile";
    }

    @RequestMapping(value = "/batch/upload",method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(HttpServletRequest request){
        List<MultipartFile> files=((MultipartHttpServletRequest)request).getFiles("file");
        MultipartFile file=null;
        BufferedOutputStream outputStream=null;
        int j;
        for(int i=0;i<files.size();++i){
            file=files.get(i);
            j=i+1;
            if(!file.isEmpty()){
                try {
                    byte[] bytes=file.getBytes();
                    outputStream=new BufferedOutputStream(
                            new FileOutputStream(new File(file.getOriginalFilename())));
                    outputStream.write(bytes);
                    outputStream.close();
                }catch (Exception e){
                    outputStream=null;
                    return "You failed to upload "+j+"=>"+e.getMessage();
                }
            }
            else {
                return "You failed to upload "+j+" because the file was empty.";
            }
        }
        return "upload successful";
    }

}
