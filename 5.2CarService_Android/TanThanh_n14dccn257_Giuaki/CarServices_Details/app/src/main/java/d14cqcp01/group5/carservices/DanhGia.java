package d14cqcp01.group5.carservices;

import java.io.Serializable;

public class DanhGia implements Serializable{

    public final static int MOT_SAO = 1;
    public final static int HAI_SAO = 2;
    public final static int BA_SAO = 3;
    public final static int BON_SAO = 4;
    public final static int NAM_SAO = 5;


    private float tongDanhGia;

    private int motSao;
    private int haiSao;
    private int baSao;
    private int bonSao;
    private int namSao;

    public DanhGia(){
    }

    public DanhGia(int motSao, int haiSao, int baSao, int bonSao, int namSao) {
        this.motSao = motSao;
        this.haiSao = haiSao;
        this.baSao = baSao;
        this.bonSao = bonSao;
        this.namSao = namSao;
        tinhTrungBinh();
    }

    public void danhGia(int soSao){
        switch (soSao){
            case MOT_SAO:{
                motSao+=1;
                tinhTrungBinh();
                break;
            }
            case HAI_SAO:{
                haiSao+=1;
                tinhTrungBinh();
                break;
            }
            case BA_SAO:{
                baSao+=1;
                tinhTrungBinh();
                break;
            }
            case BON_SAO:{
                bonSao+=1;
                tinhTrungBinh();
                break;
            }
            case NAM_SAO:{
                namSao+=1;
                tinhTrungBinh();
                break;
            }
        }
    }

    private float tinhTrungBinh(){
        long soPhieu = motSao + haiSao + baSao + bonSao + namSao + 1;
        long soSao = motSao + haiSao*2 + baSao*3 + bonSao*4 + namSao*5;

        tongDanhGia = soSao/soPhieu;

        return tongDanhGia;
    }

    public float getTongDanhGia() {
        return tongDanhGia;
    }


    public int getMotSao() {
        return motSao;
    }

    public void setMotSao(int motSao) {
        this.motSao = motSao;
    }

    public int getHaiSao() {
        return haiSao;
    }

    public void setHaiSao(int haiSao) {
        this.haiSao = haiSao;
    }

    public int getBaSao() {
        return baSao;
    }

    public void setBaSao(int baSao) {
        this.baSao = baSao;
    }

    public int getBonSao() {
        return bonSao;
    }

    public void setBonSao(int bonSao) {
        this.bonSao = bonSao;
    }

    public int getNamSao() {
        return namSao;
    }

    public void setNamSao(int namSao) {
        this.namSao = namSao;
    }
}
