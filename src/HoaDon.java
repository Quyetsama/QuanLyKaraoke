import java.io.Serializable;

public class HoaDon implements Serializable {
    private String mhd;
    private int sPhong, lphong, sGio;
    private float thanhTien;

    public HoaDon(){}

    public HoaDon(String mhd, int sPhong, int lphong, int sGio) {
        this.mhd = mhd;
        this.sPhong = sPhong;
        this.lphong = lphong;
        this.sGio = sGio;

        if(lphong == 1){
            this.thanhTien = 70000 * sGio;
        }else {
            this.thanhTien = 50000 * sGio;
        }
    }

    public void Show(){
        System.out.format("%20s %20s %20s %20s %20s \n", this.mhd, this.sPhong, this.lphong, this.sGio, this.thanhTien);
    }

    public String getMhd() {
        return mhd;
    }

    public void setMhd(String mhd) {
        this.mhd = mhd;
    }

    public int getsPhong() {
        return sPhong;
    }

    public void setsPhong(int sPhong) {
        this.sPhong = sPhong;
    }

    public int getLphong() {
        return lphong;
    }

    public void setLphong(int lphong) {
        this.lphong = lphong;
    }

    public int getsGio() {
        return sGio;
    }

    public void setsGio(int sGio) {
        this.sGio = sGio;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien() {
        if(this.lphong == 1){
            this.thanhTien = 70000 * this.sGio;
        }else {
            this.thanhTien = 50000 * this.sGio;
        }
    }
}
