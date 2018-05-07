package d14cqcp01.group5.carservices;

import java.io.Serializable;

public class NhaXe implements Serializable{

    private String tenNhaXe;

    private DanhGia danhGiaChung;
    private DanhGia danhGiaDungGio;
    private DanhGia danhGiaChatLuongPhucVu;
    private DanhGia danhGiaChatLuongXe;

    public NhaXe(){}

    public NhaXe(String tenNhaXe) {
        this.tenNhaXe = tenNhaXe;
        danhGiaChung = new DanhGia(0,0,0,0,0);
        danhGiaDungGio = new DanhGia(0,0,0,0,0);
        danhGiaChatLuongPhucVu = new DanhGia(0,0,0,0,0);
        danhGiaChatLuongXe = new DanhGia(0,0,0,0,0);
    }

    public String getTenNhaXe() {
        return tenNhaXe;
    }

    public void setTenNhaXe(String tenNhaXe) {
        this.tenNhaXe = tenNhaXe;
    }

    public float getDanhGiaChung(){
        return danhGiaChung.getTongDanhGia();
    }

    public float getDanhGiaChatLuongXe(){
        return danhGiaChatLuongXe.getTongDanhGia();
    }

    public float getDanhGiaDungGio(){
        return danhGiaDungGio.getTongDanhGia();
    }

    public float getDanhGiaChatLuongPhucVu(){
        return danhGiaChatLuongPhucVu.getTongDanhGia();
    }

    public void setDanhGiaChung(int soSao){
        danhGiaChung.danhGia(soSao);
    }

    public void setDanhGiaDungGio(int soSao){
        danhGiaDungGio.danhGia(soSao);
    }

    public  void setDanhGiaChatLuongPhucVu(int soSao){
        danhGiaChatLuongPhucVu.danhGia(soSao);
    }

    public void setDanhGiaChatLuongXe(int soSao){
        danhGiaChatLuongXe.danhGia(soSao);
    }
}

