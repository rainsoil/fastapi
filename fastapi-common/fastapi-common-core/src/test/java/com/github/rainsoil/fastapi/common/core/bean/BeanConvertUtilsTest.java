package com.github.rainsoil.fastapi.common.core.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;


public class BeanConvertUtilsTest {
    
    @org.junit.jupiter.api.Test
    public   void convertTo() {
        Source source = new Source("张三", 11);
        Target target = BeanConvertUtils.convertTo(source, new Target());
        System.out.println(target);
    }
    
    @org.junit.jupiter.api.Test
    public  void testConvertTo() {
        Source source = new Source("张三", 11);
        Target target = BeanConvertUtils.convertTo(source, () -> new Target());
        System.out.println(target);
    }
    
    @org.junit.jupiter.api.Test
    public  void testConvertTo1() {
        Source source = new Source("张三", 11);
        Target target = BeanConvertUtils.convertTo(source, new Target(), (source1, target1) -> target1.setAge(111));
        System.out.println(target);
    }
    
    @org.junit.jupiter.api.Test
    public void testConvertListTo() {
        Source source1 = new Source("张三", 11);
        Source source2 = new Source("李四", 12);
        List<Source> sources = Arrays.asList(source1, source2);
        List<Target> targets = BeanConvertUtils.convertListTo(sources, () -> new Target(), (source, target) -> {
        
        });
        
        System.out.println(targets);
    }
    
    
    /**
     * 来源类
     *
     * @author luyanan
     * @since 2023/3/26/026
     */
    @AllArgsConstructor
    @Data
    public static class Source {
        
        private String name;
        
        private Integer age;
    }
    
    
    /**
     * 目标类
     *
     * @author luyanan
     * @since 2023/3/26/026
     */
    @Data
    public static class Target {
        
        private String name;
        
        private Integer age;
        
    }
}