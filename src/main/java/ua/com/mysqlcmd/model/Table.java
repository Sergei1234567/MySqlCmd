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
        private String values;

        public Data(String columnName, String values) {
            this.columnName = columnName;
            this.values = values;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public String getValues() {
            return values;
        }

        public void setValue(String values) {
            this.values = values;
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
