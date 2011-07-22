package com.jrmapp.study.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：2011-7-22 下午01:17:18
 * @类说明 计算List中所有整数的和测试类 
 */
public class CountListIntegerSumMain {
	 /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        List<Integer> list = new ArrayList<Integer>();  
        int threadCounts = 10;//采用的线程数  
        //生成的List数据  
        for (int i = 1; i <= 1000000; i++) {  
            list.add(i);  
        }  
        CountListIntegerSum countListIntegerSum=new CountListIntegerSum(list,threadCounts);  
        long sum=countListIntegerSum.getIntegerSum();  
        System.out.println("List中所有整数的和为:"+sum);  
    }  
}
