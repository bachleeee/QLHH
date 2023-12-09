/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLHH.DTO;

/**
 *
 * @author ACER
 */
public class SanPhamDTO {
    String strMaSP, strTenSP, strMaNH;
    int iSoLuong, iGia;
    
    public SanPhamDTO() {
    }

    public SanPhamDTO(String strMaSP, String strTenSP, String strMaNH, int iSoLuong, int iGia) {
        this.strMaSP = strMaSP;
        this.strTenSP = strTenSP;
        this.strMaNH = strMaNH;
        this.iSoLuong = iSoLuong;
        this.iGia = iGia;
    }

    public String getStrMaSP() {
        return strMaSP;
    }

    public void setStrMaSP(String strMaSP) {
        this.strMaSP = strMaSP;
    }

    public String getStrTenSP() {
        return strTenSP;
    }

    public void setStrTenSP(String strTenSP) {
        this.strTenSP = strTenSP;
    }

    public String getStrMaNH() {
        return strMaNH;
    }

    public void setStrMaNH(String strMaNH) {
        this.strMaNH = strMaNH;
    }

    public int getiSoLuong() {
        return iSoLuong;
    }

    public void setiSoLuong(int iSoLuong) {
        this.iSoLuong = iSoLuong;
    }

    public int getiGia() {
        return iGia;
    }

    public void setiGia(int iGia) {
        this.iGia = iGia;
    }

    @Override
    public String toString() {
        return "SanPhamDTO{" + "strMaSP=" + strMaSP + ", strTenSP=" + strTenSP + ", strMaNH=" + strMaNH + ", iSoLuong=" + iSoLuong + ", iGia=" + iGia + '}';
    }
    
    
}
