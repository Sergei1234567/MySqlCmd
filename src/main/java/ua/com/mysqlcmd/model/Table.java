package ua.com.mysqlcmd.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Table {
    private String name;
    private Map<Column, List<Object>> rows;

    public Table(String name, Map<Column, List<Object>> rows) {
        this.name = name;
        this.rows = rows;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Column> getColumns() {
        return rows.keySet();
    }

    public void setRows(Map<Column, List<Object>> rows) {
        this.rows = rows;
    }

    public Map<Column, List<Object>> getRows() {
        return rows;
    }
}
