package com.panda;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*给定一个数组A[n], 定义数组的众数 ( Majority Element)
为数组中出现次数超过 n/2 次的元素, 假设数组A[n]非空且一定存在众数, 请设计算法找到该众数并输出.
 一个非空且一定存在众数的整数数组,如: [1,2,2],输出打印该众数,如: 2*/
public class demo1 {
    public static void main(String[] args) {
        Map map=new HashMap();
        int [] arr={1,2,2};
        for (int a:arr) {
            if (map.containsKey(a)){
                map.put(a,((int)map.get(a))+1);
            }else{
                map.put(a,1);
            }
        }
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            int next = (int) iterator.next();
            if (next >arr.length/2){
               System.out.println(next);
           }
        }
    }
}
