import java.io.Serializable;

public class SanPham implements Serializable {
    private String msp;
    private String tenSP;
    private int soLuong;
    private float donGia;

    public SanPham() {
    }

    public SanPham(String msp, String tenSP, int soLuong, float donGia) {
        this.msp = msp;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public void Show(){
        System.out.format("%20s %20s %20s %20s \n", this.msp, this.tenSP, this.soLuong, this.donGia);
    }

    public String getMsp() {
        return msp;
    }

    public void setMsp(String msp) {
        this.msp = msp;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }
}
