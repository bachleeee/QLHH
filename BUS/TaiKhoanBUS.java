/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLHH.BUS;

import QLHH.DAO.TaiKhoanDAO;
import QLHH.DTO.TaiKhoanDTO;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class TaiKhoanBUS {
    private ArrayList<TaiKhoanDTO> list_TK;
    /**
     * Xử lý các lệnh trong SQL
     */
    private TaiKhoanDAO tkDAO;
    
    public TaiKhoanBUS() throws Exception {
        list_TK = new ArrayList<>();
        tkDAO = new TaiKhoanDAO();
        list_TK = tkDAO.docDB();
    }
    
    /**
     * thêm 1 tài khoản vào danh sách và database
     * @return true nếu thành công
     */
    public Boolean them(TaiKhoanDTO tk) throws Exception{
        if ( tkDAO.them(tk) ) {
            list_TK.add(tk);
        }
        return false;
    }
    
    /**
     * xóa 1 tài khoản khỏi danh sách và database
     * @return true nếu thành công
     */
    public Boolean xoa(TaiKhoanDTO tk) throws Exception {
        if ( tkDAO.xoa(tk) ) {
            
            // duyệt từng phẩn tử
            for ( TaiKhoanDTO taikhoan : list_TK ) {
                if (taikhoan.getStrTenDangNhap().equals(tk.getStrTenDangNhap())){
                    list_TK.remove(taikhoan);
                break;
                }
            }
        }
        
        return false;
    }
    
    /**
     * sửa thông tin của 1 tài khoản <br>
     * - Trừ thông tin đăng nhập của tài khoản đó
     * @return true nếu thực hiện thành công
     */
    public Boolean sua(TaiKhoanDTO tk) throws Exception {
        if ( tkDAO.sua(tk) ) {
            
            // duyệt từng phẩn tử
            for ( TaiKhoanDTO taikhoan : list_TK ) {
                if (taikhoan.getStrTenDangNhap().equals(tk.getStrTenDangNhap())){
                break;
                }
            }
        }
        
        return false;
    }

    /**
     * Kiêm tra xem tài khoản đó có trong arraylist hay chưa <br>
     * <h3> Không phân biệt hoa thường </h3>
     * @return true nếu thành công
     */
    public Boolean kiemTraDangNhap(TaiKhoanDTO tk) {
        for ( TaiKhoanDTO taikhoan : list_TK ) {
            // kiểm tra mật khảu và tên đăng nhập
            if (taikhoan.getStrTenDangNhap().equalsIgnoreCase(tk.getStrTenDangNhap())
                    && taikhoan.getStrMatKhau().equalsIgnoreCase(tk.getStrMatKhau())
                    
                    )
                   
                     {
                return true;
            }
        }
        return false;
    }
    
}
