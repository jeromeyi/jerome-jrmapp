package com.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.jrmapp.common.sort.SortList;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：2011-5-6 下午04:35:52
 * @类说明
 */
public class TestSort {
	public static void main(String[] args)throws Exception{   
        List<UserInfo> list = new ArrayList<UserInfo>();   
           
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");   
           
        list.add(new UserInfo(3,"b",formater.parse("1980-12-01"),11));   
        list.add(new UserInfo(1,"c",formater.parse("1980-10-01"),30));   
        list.add(new UserInfo(2,"a",formater.parse("1973-10-01"),11));   
                       
        System.out.println("-------原来序列-------------------");   
        for(UserInfo user : list){   
            System.out.println(user.toString());   
        }          
           
        //调用排序通用类   
        SortList<UserInfo> sortList = new SortList<UserInfo>();   
           
        //按userId排序   
        sortList.Sort(list, "getUserId", "desc");   
        System.out.println("--------按userId倒序------------------");   
        for(UserInfo user : list){   
            System.out.println(user.toString());   
        }   
           
        //按username排序   
        sortList.Sort(list, "getUsername", null);   
        System.out.println("---------按username排序-----------------");           
        for(UserInfo user : list){   
            System.out.println(user.toString());   
        }   
           
        //按birthDate排序   
        sortList.Sort(list, "getBirthDate", null);   
        System.out.println("---------按birthDate排序-----------------");          
        for(UserInfo user : list){   
            System.out.println(user.toString());   
        }   
           
    }   
}
