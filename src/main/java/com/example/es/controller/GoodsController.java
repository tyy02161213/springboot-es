package com.example.es.controller;

import com.example.es.entity.GoodsInfo;
import com.example.es.mapper.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
public class GoodsController {

    @Autowired
    private GoodsRepository goodsRepository;
    private Integer PAGESIZE=10;

    @GetMapping("save")
    public String save(){
        GoodsInfo goodsInfo = new GoodsInfo(System.currentTimeMillis(),
                "商品"+System.currentTimeMillis(),"这是一个测试商品");
        goodsRepository.save(goodsInfo);
        return "success";
    }


    @GetMapping("delete")
    public String delete(GoodsInfo goodsInfo){
        goodsRepository.delete(goodsInfo);
        return "success";
    }

    @GetMapping("update")
    public String update(long id,String name,String description){
        GoodsInfo goodsInfo = new GoodsInfo(id,
                name,description);
        goodsRepository.save(goodsInfo);
        return "success";
    }

    @GetMapping("getOne")
    public Optional<GoodsInfo> getOne(long id){
        Optional<GoodsInfo> goodsInfos =  goodsRepository.findById(id);
        return goodsInfos;
    }

    @GetMapping("getGoodsList")
    public List<GoodsInfo> getList(){
        //es搜索默认第一页页码是0
       Iterator<GoodsInfo> iterator = goodsRepository.findAll().iterator();
       List<GoodsInfo> goodsInfos = new ArrayList<>();
       while(iterator.hasNext()) goodsInfos.add(iterator.next());
       return goodsInfos;
    }

}
