package cn.lh.learnproject.hightec.orm_reflect;

@SxtTable(value = "student")
public class Student {

    @SxtColumn(columnName = "id", type = "int", length = 10)
    private int id;

    @SxtColumn(columnName = "age", type = "int", length = 3)
    private int age;

    @SxtColumn(columnName = "name", type = "varchar", length = 10)
    private String name;

    public Student() {
    }

    public Student(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
