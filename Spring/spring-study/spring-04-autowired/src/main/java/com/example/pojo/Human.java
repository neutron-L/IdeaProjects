package com.example.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;

public class Human {
    //autowired 直接在属性上使用即可 也可以在set方式上使用 可以不用编写set方法 前提是你这个自动装配属性在ioc(spring)容器中已经存在且符合名字
    //也可以使用@Resource
    //autowired通过byType方式实现,而且要求这个对象存在
    //resource默认通过byName的方式实现,如果找不到名字,则通过byType实现两个都找不到就报错
    @Autowired
    private Cat cat;
    //为false说明这个属性可以为null,否则不可以为空
    //qualifier指定bean名字
    @Autowired(required = false)
    @Qualifier(value = "dog2")
    private Dog dog;
    //说明这个字段可以为null
    @Nullable
    private String name;


    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "cat=" + cat +
                "dog=" + dog +
                "name=" + name + "}";
    }
}
