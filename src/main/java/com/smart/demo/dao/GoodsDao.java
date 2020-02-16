package com.smart.demo.dao;

import com.smart.demo.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description TODO
 * Author Cloudr
 * Date 2020/2/15 15:26
 **/
public interface GoodsDao extends JpaRepository<Goods,Long> {

}
