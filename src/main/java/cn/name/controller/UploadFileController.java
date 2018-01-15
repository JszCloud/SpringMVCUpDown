package cn.name.controller;

import cn.name.model.FileU;
import org.apache.commons.io.FileUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Nominal on 2018/1/15 0015.
 * 微博：@李明易DY
 */
@Controller
public class UploadFileController {

    @GetMapping("to_upload")
    public String to_upload(){
        return "upload";
    }

    /**
     * 单文件上传
     * @param myFile
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("do_upload")
    public ModelAndView do_upload(MultipartFile myFile, HttpServletRequest request) throws Exception{
        //文件夹路径
        String url=request.getSession().getServletContext().getRealPath("/upload");
        if(!new File(url).exists()){
            new File(url).mkdir();
        }
        System.out.println(url);
        //文件名
        String fileName= UUID.randomUUID().toString().replaceAll("-","")+myFile.getOriginalFilename().substring(myFile.getOriginalFilename().lastIndexOf("."),myFile.getOriginalFilename().length());
        System.out.println(fileName);
        FileUtils.copyInputStreamToFile(myFile.getInputStream(),new File(url+ File.separator+fileName));
        ModelAndView mav=new ModelAndView();
        FileU fileU=new FileU();
        fileU.setFileName(fileName);
        mav.addObject("list",fileU);
        mav.setViewName("success");
        return mav;
    }

    /**
     * 多文件上传
     * @param files
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("do_eupload")
    public ModelAndView do_eupload(MultipartFile[] files, HttpServletRequest request) throws Exception{
        ModelAndView mav=new ModelAndView();
        FileU fileU=new FileU();
        //文件夹路径
        String url=request.getSession().getServletContext().getRealPath("/upload");
        if(!new File(url).exists()){
            new File(url).mkdir();
        }
        System.out.println(url);

        for (MultipartFile myFile:files) {
            //文件名
            String fileName=UUID.randomUUID().toString().replaceAll("-","")+myFile.getOriginalFilename().substring(myFile.getOriginalFilename().lastIndexOf("."),myFile.getOriginalFilename().length());
            System.out.println(fileName);
            fileU.setFileName(fileName);
            FileUtils.copyInputStreamToFile(myFile.getInputStream(),new File(url+File.separator+fileName));
        }

        mav.addObject("list",fileU);
        mav.setViewName("success");
        return mav;
    }

    /**
     * 异步上传文件
     * @param ajaxfile
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("ajax_upload")
    @ResponseBody
    public String ajax_upload(MultipartFile ajaxfile, HttpServletRequest request) throws Exception{
        //文件夹路径
        String url=request.getSession().getServletContext().getRealPath("/upload");
        if(!new File(url).exists()){
            new File(url).mkdir();
        }
        System.out.println(url);

        //文件名
        String fileName=UUID.randomUUID().toString().replaceAll("-","")+ajaxfile.getOriginalFilename().substring(ajaxfile.getOriginalFilename().lastIndexOf("."),ajaxfile.getOriginalFilename().length());
        System.out.println(fileName);
        FileUtils.copyInputStreamToFile(ajaxfile.getInputStream(),new File(url+File.separator+fileName));

        return "{'url':'"+fileName+"'}";
    }
}
