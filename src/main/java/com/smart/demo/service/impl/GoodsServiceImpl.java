package com.smart.demo.service.impl;

import com.smart.demo.dao.GoodsDao;
import com.smart.demo.domain.Goods;
import com.smart.demo.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * Description TODO
 * Author Cloudr
 * Date 2020/2/15 15:33
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    @Override
    public List<Goods> SelectAllGoods() {
        return goodsDao.findAll();
    }

    @Override
    public void addGoods(Goods goods) {
        goodsDao.save(goods);
    }

    @Override
    public Optional<Goods> findById(Long id) {
        return goodsDao.findById(id);
    }
}
