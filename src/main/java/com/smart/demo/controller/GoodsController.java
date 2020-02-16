package com.smart.demo.controller;

import com.smart.demo.domain.Goods;
import com.smart.demo.service.impl.GoodsServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Description TODO 商品控制层
 * Author Cloudr
 * Date 2020/2/10 12:21
 **/
@Controller
public class GoodsController {
    
    @Resource
    private GoodsServiceImpl goodsService;

//    保存商品信息页面
    @RequestMapping("/saveGoodsPage")
    public String saveGoodsPage(){
        return "saveGoods";
    }

//     保存商品
    @RequestMapping("/saveGoods")
    public ModelAndView saveGoods(Goods goods,@RequestParam(name = "logoFile")MultipartFile logoFile)throws IOException {
        ModelAndView model=new ModelAndView();
        goods.setLogo(logoFile.getBytes());
        goodsService.addGoods(goods);
        model.setViewName("saveGoods");
        return model;
    }

//    显示所有商品信息
    @RequestMapping("/showAllGoods")
    public ModelAndView showAllGoods(){
        ModelAndView model=new ModelAndView();
        List<Goods> goods=goodsService.SelectAllGoods();
        model.addObject("goods",goods);
        model.setViewName("allGoods");
        return model;
    }

//    显示单个商品的图片
    @RequestMapping(value = "/showImg/{goodsId}",produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> showImg(@PathVariable("goodsId")Long goodsId){
        Optional<Goods> optionalGoods=goodsService.findById(goodsId);
        if(optionalGoods.isPresent()){
            Goods goods=optionalGoods.get();
            byte[] imageContent=goods.getLogo();
            HttpHeaders httpHeaders=new HttpHeaders();
            httpHeaders.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(imageContent,httpHeaders, HttpStatus.OK);
        }
        else {
            return null;
        }

    }
}
