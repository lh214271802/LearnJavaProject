package cn.lh.learnproject.hightec.orm_reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 手写一个对象关系映射，数据库存储操作
 */
public class TestOrm {
    public static void main(String[] args) {
        //ORM  Object Relationship Mapping 对象关系映射

        Student student1 = new Student(1, 18, "刘德华");
        Student student2 = new Student(2, 22, "陈小春");


        try {
            //获取类的注解，用来创建表
            Class<?> aClass = Class.forName("cn.lh.learnproject.hightec.orm_reflect.Student");
//            Class<?> aClass = Student.class;
            Annotation[] annotations = aClass.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation.toString());
                if (annotation instanceof SxtTable) {
                    System.out.println(((SxtTable) annotation).value());
                }
            }
            SxtTable sxtTable = aClass.getAnnotation(SxtTable.class);
            System.out.println(sxtTable.value());
            System.out.println("==============================");
            //获取成员变量的注解，用来创建列
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                System.out.println(field.toString());
                SxtColumn annotation = field.getAnnotation(SxtColumn.class);
                System.out.println("=columnName=" + annotation.columnName() + "=type=" + annotation.type() + "=length=" + annotation.length());
            }

            //根据上面获取的表名，字段等信息，拼接出SQL语句，进行相关的表的操作
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
