/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLHH.DTO;

/**
 *
 * @author ACER
 */
public class HoaDonDTO {
    private String strMaHD, strMaNV, strMaKH, strNgayBan;
    private double tongTien;

    public HoaDonDTO() {
    }

    public HoaDonDTO(String strMaHD, String strMaNV, String strMaKH, String strNgayBan, double tongTien) {
        this.strMaHD = strMaHD;
        this.strMaNV = strMaNV;
        this.strMaKH = strMaKH;
        this.strNgayBan = strNgayBan;
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "HoaDonDTO{" + "strMaHD=" + strMaHD + ", strMaNV=" + strMaNV + ", strMaKH=" + strMaKH + ", strNgayBan=" + strNgayBan + ", tongTien=" + tongTien + '}';
    }

    public String getStrMaHD() {
        return strMaHD;
    }

    public void setStrMaHD(String strMaHD) {
        this.strMaHD = strMaHD;
    }

    public String getStrMaNV() {
        return strMaNV;
    }

    public void setStrMaNV(String strMaNV) {
        this.strMaNV = strMaNV;
    }

    public String getStrMaKH() {
        return strMaKH;
    }

    public void setStrMaKH(String strMaKH) {
        this.strMaKH = strMaKH;
    }

    public String getStrNgayBan() {
        return strNgayBan;
    }

    public void setStrNgayBan(String strNgayBan) {
        this.strNgayBan = strNgayBan;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
    
    public static int maHDTangdan(HoaDonDTO a, HoaDonDTO b){
        return a.getStrMaHD().compareTo(b.getStrMaHD());
    }
    public static int maHDGiamdan(HoaDonDTO a, HoaDonDTO b){
        return b.getStrMaHD().compareTo(a.getStrMaHD());
    }
    
    public static int maNVTangdan(HoaDonDTO a, HoaDonDTO b){
        return a.getStrMaNV().compareTo(b.getStrMaNV());
    }
    
    public static int maNVGiamdan(HoaDonDTO a, HoaDonDTO b){
        return b.getStrMaNV().compareTo(a.getStrMaNV());
    }
    
    public static int maKHTangdan(HoaDonDTO a, HoaDonDTO b){
        return a.getStrMaKH().compareTo(b.getStrMaKH());
    }
    public static int maKHGiamdan(HoaDonDTO a, HoaDonDTO b){
        return b.getStrMaKH().compareTo(a.getStrMaKH());
    }
    
    
}
