package cn.lh.learnproject.sorm_design;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 负责管理数据库所有表结构和类结构的关系，并可以根据表结构生成类结构
 */
public class TableContext {
    /**
     * 表名为key，表信息为value
     */
    public static Map<String, TableInfo> tables = new HashMap<>();
    /**
     * 将po的class对象和表信息对象关联起来，便于重用
     */
    public static Map<Class, TableInfo> poClassTableMap = new HashMap<>();

    private TableContext() {
    }

    static {
        try {
            Connection connection = DBManager.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, "%", "%", new String[]{"TABLE"});
            while (resultSet.next()) {
                String tableName = (String) resultSet.getObject("TABLE_NAME");
                TableInfo tableInfo = new TableInfo(tableName, new ArrayList<>(), new HashMap<>());
                tables.put(tableName, tableInfo);
                ResultSet columns = metaData.getColumns(null, "%", tableName, "%");
                while (columns.next()) {
                    ColumnInfo columnInfo = tableInfo.getColumns().get(columns.getObject("COLUMN_NAME"));
                    columnInfo.setKeyType(1);//设置为主键类型
                    tableInfo.getPriKeys().add(columnInfo);
                }

                if (tableInfo.getPriKeys().size() > 0) {
                    tableInfo.setOnlyPriKey(tableInfo.getPriKeys().get(0));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, TableInfo> getTableInfos() {
        return tables;
    }

}
