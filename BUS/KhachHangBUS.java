/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLHH.BUS;

import QLHH.DAO.KhachHangDAO;
import QLHH.DTO.KhachHangDTO;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class KhachHangBUS {
    private ArrayList<KhachHangDTO> list_KH;
    /**
     * Xử lý các lệnh trong SQL
     */
    private KhachHangDAO khDAO;
    
    public KhachHangBUS() throws Exception {
        list_KH = new ArrayList<>();
        khDAO = new KhachHangDAO();
        list_KH = khDAO.docDB();
    }

    public int getNumbKH(){
        return list_KH.size();
    }
    public ArrayList<KhachHangDTO> getList_KH() {
        return list_KH;
    }

    public void setList_KH(ArrayList<KhachHangDTO> list_KH) {
        this.list_KH = list_KH;
    }
    
    public KhachHangDTO getInfor (String strMaKH) {
        for (KhachHangDTO kh : list_KH) {
            if (kh.getStrMaKH().equals(strMaKH)) {
                return kh;
            }
        }
        
        return null;
    }
    
    /**
     * thêm 1 khách hàng vào danh sách và database
     * @return true nếu thành công
     */
    public Boolean them(KhachHangDTO kh) throws Exception{
        if ( khDAO.them(kh) ) {
            list_KH.add(kh);
        }
        return false;
    }
    
    /**
     * xóa 1 khách hàng khỏi danh sách và database
     * @return true nếu thành công
     */
    public Boolean xoa(KhachHangDTO kh) throws Exception {
        if ( khDAO.xoa(kh) ) {
            
            // duyệt từng phẩn tử
            for ( KhachHangDTO taikhoan : list_KH ) {
                if (taikhoan.getStrMaKH().equals(kh.getStrMaKH())){
                    list_KH.remove(taikhoan);
                break;
                }
            }
        }
        
        return false;
    }
    
    /**
     * sửa thông tin của 1 khách hàng <br>
     * - Trừ thông tin đăng nhập của khách hàng đó
     * @return true nếu thực hiện thành công
     */
    public Boolean sua(KhachHangDTO kh) throws Exception {
        if ( khDAO.sua(kh) ) {
            
            // duyệt từng phẩn tử
            for ( KhachHangDTO taikhoan : list_KH ) {
                if (taikhoan.getStrMaKH().equals(kh.getStrMaKH())){
                    taikhoan.setStrDiaChi(kh.getStrDiaChi());
                    taikhoan.setStrGioiTinh(kh.getStrGioiTinh());
                    taikhoan.setStrDienThoai(kh.getStrDienThoai());
                    taikhoan.setStrHoTen(kh.getStrHoTen());
                    taikhoan.setiTongChiTieu(kh.getiTongChiTieu());
                    return true;
                }
            }
        }
        
        return false;
    }
}
