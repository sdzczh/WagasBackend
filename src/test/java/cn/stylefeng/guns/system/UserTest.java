package cn.stylefeng.guns.system;

import cn.stylefeng.guns.base.BaseJunit;
import cn.stylefeng.guns.modular.system.dao.UserMapper;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * 用户测试
 *
 * @author fengshuonan
 * @date 2017-04-27 17:05
 */
public class UserTest{
    private ArrayList<Integer> list;

    private void deal(){
        //向list容器中顺序添加指定数量num的整数
        if (list==null) {
            list = new ArrayList<Integer>();
            for (int i = 1; i < 1000; i++) {
                list.add(i);
            }
        }
        //打乱list中元素顺序
        Collections.shuffle(list);
    }

    //抽奖的方法：抽出指定数量的奖项
    public void draw(){
        Random rdom = new Random();

        int index = rdom.nextInt(list.size());
        System.out.println("一等奖："+list.get(index));
        list.remove(index);
        Collections.shuffle(list);


        for (int i = 0; i < 2; i++) {
            int index2 = rdom.nextInt(list.size());
            System.out.println("二等奖："+list .get(index2));
            list.remove(index2);
        }
        Collections.shuffle(list);


        for (int i = 0; i < 3; i++) {
            int index3 = rdom.nextInt(list.size());
            System.out.println("三等奖："+list .get(index3));
            list.remove(index3);
        }
        Collections.shuffle(list);
    }


    public static void main(String[] args) {
        UserTest rf = new UserTest();
        rf.deal();
        rf.draw();

    }

}
