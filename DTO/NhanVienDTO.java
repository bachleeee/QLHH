/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLHH.DTO;

/**
 *
 * @author ACER
 */
public class NhanVienDTO {
    private String strMaNV;
    private String strHoTen;
    private String strMaCV;    
    private String strGioiTinh;
    private String strNgaySinh;
    private String strDiaChi;
    private String strDienThoai;
    private String iID;

    public NhanVienDTO() {
    }

    public NhanVienDTO(String strMaNV, String strHoTen, String strMaCV, String strGioiTinh, String strNgaySinh, String strDiaChi, String strDienThoai, String strAnh) {
        this.strMaNV = strMaNV;
        this.strHoTen = strHoTen;
        this.strMaCV = strMaCV;
        this.strGioiTinh = strGioiTinh;
        this.strNgaySinh = strNgaySinh;
        this.strDiaChi = strDiaChi;
        this.strDienThoai = strDienThoai;
        this.iID = iID;
    }

    public String getStrMaNV() {
        return strMaNV;
    }

    public void setStrMaNV(String strMaNV) {
        this.strMaNV = strMaNV;
    }

    public String getStrHoTen() {
        return strHoTen;
    }

    public void setStrHoTen(String strHoTen) {
        this.strHoTen = strHoTen;
    }

    public String getStrMaCV() {
        return strMaCV;
    }

    public void setStrMaCV(String strMaCV) {
        this.strMaCV = strMaCV;
    }

    public String getStrGioiTinh() {
        return strGioiTinh;
    }

    public void setStrGioiTinh(String strGioiTinh) {
        this.strGioiTinh = strGioiTinh;
    }

    public String getStrNgaySinh() {
        return strNgaySinh;
    }

    public void setStrNgaySinh(String strNgaySinh) {
        this.strNgaySinh = strNgaySinh;
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

    public String getiID() {
        return iID;
    }

    public void setiID(String iID) {
        this.iID = iID;
    }


    @Override
    public String toString() {
        return "NhanVienDTO{" + "strMaNV=" + strMaNV + ", strHoTen=" + strHoTen + ", strMaCV=" + strMaCV + ", strGioiTinh=" + strGioiTinh + ", strNgaySinh=" + strNgaySinh + ", strDiaChi=" + strDiaChi + ", strDienThoai=" + strDienThoai + ", strAnh=" + iID + '}';
    }
    
    
}
