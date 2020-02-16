package com.smart.demo.service;

import com.smart.demo.domain.Goods;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Description TODO
 * Author Cloudr
 * Date 2020/2/15 15:27
 **/
@Service
public interface GoodsService {

    List<Goods> SelectAllGoods();

    void addGoods(Goods goods);

    Optional<Goods> findById(Long id);
}
