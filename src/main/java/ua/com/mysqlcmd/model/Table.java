package ua.com.mysqlcmd.model;

import java.util.List;

public class Table {
    private String name;
    private List<Column> columns;
    private List<List<Data>> rows;

    public Table(String name, List<Column> columns, List<List<Data>> rows) {
        this.name = name;
        this.columns = columns;
        this.rows = rows;
    }

    public static final class Data {
        private String columnName;
        private Object value;

        public Data(String columnName, Object value) {
            this.columnName = columnName;
            this.value = value;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<List<Data>> getRows() {
        return rows;
    }

    public void setRows(List<List<Data>> rows) {
        this.rows = rows;
    }
}
