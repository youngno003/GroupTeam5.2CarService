package d14cqcp01.group5.carservices;

public class PhanHoi {
    private String UserName;
    private String DanhGia;

    public PhanHoi(String userName, String danhGia) {
        UserName = userName;
        DanhGia = danhGia;
    }
    public PhanHoi(){}

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getDanhGia() {
        return DanhGia;
    }

    public void setDanhGia(String danhGia) {
        DanhGia = danhGia;
    }
}
