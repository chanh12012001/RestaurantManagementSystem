package DTO;

public class TableModelItemUI {
    private int iD;
    private String name;
    private String status;

    public TableModelItemUI(int iD, String name, String status) {
        this.iD = iD;
        this.name = name;
        this.status = status;
    }    
    
    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}