package com.yh.ydd.common.basedo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 实现区域管理的查询方式
 */
public class AreaSearchImp implements Search {
    @Override
    public List<HashMap> toSearch(String msg) {
        //查询区域的信息
        ArrayList arrayList = new ArrayList();

        HashMap hashMap = new HashMap();

        hashMap.put("name",msg);

        arrayList.add(hashMap);


        return  arrayList;
    }
}
