package cn.lh.learnproject.hightec.orm_reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
            testReflect(aClass);
//            Class<?> aClass = Student.class;
//            Class<?> aClass = student2.getClass();
            //一个类的Class对象只会加载一次
            System.out.println((aClass == Student.class) + "---" + (Student.class == student1.getClass()));
            System.out.println("aClass.getName()--->" + aClass.getName());
            System.out.println("aClass.getSimpleName()--->" + aClass.getSimpleName());
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
            /**获取成员变量的注解，用来创建列*/
            //获取public的所有成员变量
//            Field[] fields = aClass.getFields();
            //获取所有的成员变量
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                System.out.println("field--->" + field.toString() + "--field.getName()-->" + field.getName());
                SxtColumn annotation = field.getAnnotation(SxtColumn.class);
                System.out.println("=columnName=" + annotation.columnName() + "=type=" + annotation.type() + "=length=" + annotation.length());
            }

            Method[] methods = aClass.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println(method.getModifiers());
                System.out.println(method.toString());
            }
            //根据上面获取的表名，字段等信息，拼接出SQL语句，进行相关的表的操作
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void testReflect(Class<?> aClass) {
        try {
            Student student = (Student) aClass.newInstance();
            Field name = aClass.getDeclaredField("name");
            //这个字段是私有变量，需要调用下面这个方法设置这个属性不需要做安全检查了，可以直接访问
            name.setAccessible(true);
            //通过反射直接写属性
            name.set(student, "周润发");
            System.out.println(student.getName());
            //通过反射直接读属性的值
            System.out.println(name.get(student));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
