/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLHH.DTO;

/**
 *
 * @author ACER
 */
public class ChiTietHDDTO {
    private String strMaSP, strMaHD;
    private int iSoLuong, iGiaBan;

    public ChiTietHDDTO() {
    }

    public ChiTietHDDTO(String strMaSP, String strMaHD, int iSoLuong, int iGiaBan) {
        this.strMaSP = strMaSP;
        this.strMaHD = strMaHD;
        this.iSoLuong = iSoLuong;
        this.iGiaBan = iGiaBan;
    }

    @Override
    public String toString() {
        return "ChiTietHDDTO{" + "strMaSP=" + strMaSP + ", strMaHD=" + strMaHD + ", iSoLuong=" + iSoLuong + ", iGiaBan=" + iGiaBan + '}';
    }

    public String getStrMaSP() {
        return strMaSP;
    }

    public void setStrMaSP(String strMaSP) {
        this.strMaSP = strMaSP;
    }

    public String getStrMaHD() {
        return strMaHD;
    }

    public void setStrMaHD(String strMaHD) {
        this.strMaHD = strMaHD;
    }

    public int getiSoLuong() {
        return iSoLuong;
    }

    public void setiSoLuong(int iSoLuong) {
        this.iSoLuong = iSoLuong;
    }

    public int getiGiaBan() {
        return iGiaBan;
    }

    public void setiGiaBan(int iGiaBan) {
        this.iGiaBan = iGiaBan;
    }
    
    
    
    public static int maSPTangdan(ChiTietHDDTO a, ChiTietHDDTO b){
        return a.getStrMaSP().compareTo(b.getStrMaSP());
    }
    public static int maSPGiamdan(ChiTietHDDTO a, ChiTietHDDTO b){
        return b.getStrMaSP().compareTo(a.getStrMaSP());
    }
    
    public static int soLuongTangdan(ChiTietHDDTO a, ChiTietHDDTO b){
        if (a.getiSoLuong()< b.getiSoLuong()) {
            return -1;
        } 
        else {
            if (a.getiSoLuong() == a.getiSoLuong()) {
                return 0;
            }
            else {
                return 1;
            }
        }
    }
    public static int soLuongGiamdan(ChiTietHDDTO a, ChiTietHDDTO b){
        if (a.getiSoLuong() > b.getiSoLuong()) {
            return -1;
        } 
        else {
            if (a.getiSoLuong() == a.getiSoLuong()) {
                return 0;
            }
            else {
                return 1;
            }
        }
    }
}
