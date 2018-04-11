package cn.lh.learnproject.sorm_design;

import java.util.List;

public interface Query {

    /**
     * 执行一个DML语句
     *
     * @param sql
     * @param params
     * @return 执行sql语句影响的行数
     */
    int executeDML(String sql, Object[] params);

    /**
     * 存储一个数据到数据库
     *
     * @param obj
     */
    void insert(Object obj);

    /**
     * 删除clazz表示类对应的表中的记录（指定主键值id的记录）
     *
     * @param clazz 跟表对应的类的Class对象
     * @param id    逐渐的值
     */
    void delete(Class clazz, int id);

    /**
     * 删除对象在数据库中对应的记录
     *
     * @param object
     */
    void delete(Object object);


    /**
     * 更新对象对应的记录，并且只更新指定的字段的值
     *
     * @param object     要更新的对象
     * @param fieldNames 更新的属性列表
     * @return 执行后影响记录的行数
     */
    int update(Object object, String[] fieldNames);


    /**
     * 查询返回多行记录，并将每行记录封装到clazz指定类的对象中
     *
     * @param sql    查询语句
     * @param clazz  javabean的class对象
     * @param params sql参数
     * @return 查询结果
     */
    List queryRows(String sql, Class clazz, Object[] params);

    /**
     * 返回一行记录，并将该记录封装到clazz指定的类的对象中
     *
     * @param sql    查询语句
     * @param clazz  javabean的class对象
     * @param params sql参数
     * @return 查询结果
     */

    Object queryUniqueRow(String sql, Class clazz, Object[] params);


    /**
     * 返回一个值（一行一列），并将该值返回
     *
     * @param sql    查询语句
     * @param params sql参数
     * @return 查询结果
     */

    Object queryValue(String sql, Object[] params);

    /**
     * 返回一个值（一行一列），并将该值返回
     *
     * @param sql    查询语句
     * @param params sql参数
     * @return 查询的数字
     */

    Number queryNumber(String sql, Object[] params);


}
