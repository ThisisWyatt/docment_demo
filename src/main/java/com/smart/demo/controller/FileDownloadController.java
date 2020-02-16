package com.smart.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Description TODO
 * Author Cloudr
 * Date 2020/2/9 22:14
 **/
@Controller
public class FileDownloadController {

    @RequestMapping("/downloadFile")
    public String downloadFile(){
        return "downloadFile";
    }

    @RequestMapping(value = "/testDownload",method = RequestMethod.GET)
    public void Download(HttpServletResponse response)throws IOException{
        String fileName="test.mp3";
        String pathName="X:/";
        File file=new File(pathName+fileName);
        response.setHeader("content-type","application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        byte[] buff=new byte[1024];
        BufferedInputStream bis=null;
        OutputStream os=null;
        try{
            os=response.getOutputStream();
            bis=new BufferedInputStream(new FileInputStream(file));
            int i=bis.read(buff);
            while (i!=-1){
                os.write(buff,0,buff.length);
                os.flush();
                i=bis.read(buff);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if(bis!=null){
                try {
                    bis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
