package ua.com.mysqlcmd.model;

public class Column {
    private String name;
    private String type;
    private boolean isNull;

    public Column(String name, String type) {
        this.name = name;
        this.type = type;
        this.isNull = isNull;
    }

    public boolean isNull() {
        return isNull;
    }

    public void setNull(boolean aNull) {
        isNull = aNull;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public boolean isNull() {
//        return isNull;
//    }
//
//    public void setNull(boolean isNull) {
//        isNull = isNull;
//    }



//    public Column(String name) {
//        this.name = name;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
