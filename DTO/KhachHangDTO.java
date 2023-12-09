/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLHH.DTO;

/**
 *
 * @author ACER
 */
public class KhachHangDTO {
    private String strMaKH, strHoTen, strGioiTinh, strDiaChi,
            strDienThoai;
    private double iTongChiTieu;

    public KhachHangDTO(String strMaKH, String strHoTen, String strGioiTinh, String strDiaChi, String strDienThoai, double TongChiTieu) {
        this.strMaKH = strMaKH;
        this.strHoTen = strHoTen;
        this.strGioiTinh = strGioiTinh;
        this.strDiaChi = strDiaChi;
        this.strDienThoai = strDienThoai;
        this.iTongChiTieu = TongChiTieu;
    }

    public KhachHangDTO() {
    }

    @Override
    public String toString() {
        return "KhachHangDTO{" + "strMaKH=" + strMaKH + ", strHoTen=" + strHoTen + ", strGioiTinh=" + strGioiTinh + ", strDiaChi=" + strDiaChi + ", strDienThoai=" + strDienThoai + ", TongChiTieu=" + iTongChiTieu + '}';
    }

    public String getStrMaKH() {
        return strMaKH;
    }

    public void setStrMaKH(String strMaKH) {
        this.strMaKH = strMaKH;
    }

    public String getStrHoTen() {
        return strHoTen;
    }

    public void setStrHoTen(String strHoTen) {
        this.strHoTen = strHoTen;
    }

    public String getStrGioiTinh() {
        return strGioiTinh;
    }

    public void setStrGioiTinh(String strGioiTinh) {
        this.strGioiTinh = strGioiTinh;
    }

    public String getStrDiaChi() {
        return strDiaChi;
    }

    public void setStrDiaChi(String strDiaChi) {
        this.strDiaChi = strDiaChi;
    }

    public String getStrDienThoai() {
        return strDienThoai;
    }

    public void setStrDienThoai(String strDienThoai) {
        this.strDienThoai = strDienThoai;
    }

    public double getiTongChiTieu() {
        return iTongChiTieu;
    }

    public void setiTongChiTieu(double TongChiTieu) {
        this.iTongChiTieu = TongChiTieu;
    }
    
    
}
