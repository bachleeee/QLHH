/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLHH.DTO;

/**
 *
 * @author ACER
 */
public class NhomHangDTO {
    private String strMaNH;
    private String strTenNH;

    public NhomHangDTO() {
    }
    
    
    public NhomHangDTO(String strMaloai, String strTenloai) {
        this.strMaNH = strMaloai;
        this.strTenNH = strTenloai;
    }

    public String getStrMaNH() {
        return strMaNH;
    }

    public void setStrMaNH(String strMaNH) {
        this.strMaNH = strMaNH;
    }

    public String getStrTenNH() {
        return strTenNH;
    }

    public void setStrTenNH(String strTenNH) {
        this.strTenNH = strTenNH;
    }

    
    
}
