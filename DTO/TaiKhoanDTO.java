/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLHH.DTO;

/**
 *
 * @author ACER
 */
public class TaiKhoanDTO {
    private int id;
    private String strTenDangNhap = "";
    private String strMatKhau = "";

    public TaiKhoanDTO() {
    }

    public TaiKhoanDTO(int id, String strTenDangNhap, String strMatKhau) {
        this.id = id;
        this.strTenDangNhap = strTenDangNhap;
        this.strMatKhau = strMatKhau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrTenDangNhap() {
        return strTenDangNhap;
    }

    public void setStrTenDangNhap(String strTenDangNhap) {
        this.strTenDangNhap = strTenDangNhap;
    }

    public String getStrMatKhau() {
        return strMatKhau;
    }

    public void setStrMatKhau(String strMatKhau) {
        this.strMatKhau = strMatKhau;
    }
    
}
