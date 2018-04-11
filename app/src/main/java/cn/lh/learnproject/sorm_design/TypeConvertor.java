package cn.lh.learnproject.sorm_design;

/**
 * 负责java数据类型和数据库数据类型的相互转换
 */
public interface TypeConvertor {

    /**
     * 将数据库数据类型转化为java数据类型
     *
     * @param cloumnType 数据库字段的数据类型
     * @return java的数据类型
     */
    String dateabaseType2JavaType(String cloumnType);

    /**
     * 将java数据类型转化为数据库数据类型
     *
     * @param javaDataType java的数据类型
     * @return 数据库字段的数据类型
     */
    String javaType2DateabaseType(String javaDataType);

}
