package cn.littlemotor.web.test;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class test {
    public static void main(String[] args){
        //测试结果add会继续添加到key对应的vulue后面形成一个list
        //set会直接将原来的数据清空后放入新的
        MultiValueMap<String, String> test = new LinkedMultiValueMap<>();
        test.add("test", "test1");
        test.add("test", "test2");
        test.set("test", "test3");
        test.set("test", "test4");
    }
}
