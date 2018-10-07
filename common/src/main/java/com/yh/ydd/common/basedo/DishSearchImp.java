package com.yh.ydd.common.basedo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 实现菜品的查询方式
 */
public class DishSearchImp implements Search {
    @Override
    public List<HashMap> toSearch(String msg) {

        //查询区域的信息
        ArrayList arrayList = new ArrayList();


        HashMap hashMap = new HashMap();

        hashMap.put("name","大肠");

        arrayList.add(hashMap);


        return  arrayList;
    }
}
