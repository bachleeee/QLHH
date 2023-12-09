/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLHH.DTO;

/**
 *
 * @author ACER
 */
public class ChiTietPNDTO {
    String strMaPN, strMaSP;
    int iSoLuong, iGiaNhap;

    public ChiTietPNDTO(String strMaPN, String strMaSP, int iSoLuong, int iGiaNhap) {
        this.strMaPN = strMaPN;
        this.strMaSP = strMaSP;
        this.iSoLuong = iSoLuong;
        this.iGiaNhap = iGiaNhap;
    }

    public ChiTietPNDTO() {
    }

    public String getStrMaPN() {
        return strMaPN;
    }

    public void setStrMaPN(String strMaPN) {
        this.strMaPN = strMaPN;
    }

    public String getStrMaSP() {
        return strMaSP;
    }

    public void setStrMaSP(String strMaSP) {
        this.strMaSP = strMaSP;
    }

    public int getiSoLuong() {
        return iSoLuong;
    }

    public void setiSoLuong(int iSoLuong) {
        this.iSoLuong = iSoLuong;
    }

    public int getiGiaNhap() {
        return iGiaNhap;
    }

    public void setiGiaNhap(int iGiaNhap) {
        this.iGiaNhap = iGiaNhap;
    }

    @Override
    public String toString() {
        return "ChiTietPNDTO{" + "strMaPN=" + strMaPN + ", strMaSP=" + strMaSP + ", iSoLuong=" + iSoLuong + ", iGiaNhap=" + iGiaNhap + '}';
    }

    
    
    public static int maSPTangdan(ChiTietPNDTO a, ChiTietPNDTO b){
        return a.getStrMaSP().compareTo(b.getStrMaSP());
    }
}
