/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLHH.DAO;

import QLHH.DTO.TaiKhoanDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ACER
 */
public class TaiKhoanDAO {
    MyConnectUnit connect;
     
    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<TaiKhoanDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new MyConnectUnit();
        
        ResultSet result = this.connect.Select("taikhoan", condition, orderBy);
        ArrayList<TaiKhoanDTO> taikhoans = new ArrayList<>();
        while ( result.next() ) {
            TaiKhoanDTO taokhoan = new TaiKhoanDTO();
            taokhoan.setId(result.getInt("id"));
            taokhoan.setStrTenDangNhap(result.getString("tendangnhap"));
            taokhoan.setStrMatKhau(result.getString("matkhau"));
            taikhoans.add(taokhoan);
        }
        connect.Close();
        return taikhoans;
    }
    
    public ArrayList<TaiKhoanDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<TaiKhoanDTO> docDB() throws Exception {
        return docDB(null);
    }
    
    /**
     * Tạo thêm 1 tài khoản dựa theo đã có thông tin trước
     * @return true nếu thành công
     */
    public Boolean them(TaiKhoanDTO tk) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("id", tk.getId());
        insertValues.put("tendangnhap", tk.getStrTenDangNhap());
        insertValues.put("matkhau", tk.getStrMatKhau());
        
        Boolean check = connect.Insert("taikhoan", insertValues);
        
        connect.Close();
        return check;
    }
    
    /** 
     * @param tk chuyền vào dữ liệu tài khoản để xóa
     * @return true nếu thành công
     */
    public Boolean xoa(TaiKhoanDTO tk) throws Exception {
        connect = new MyConnectUnit();
        String condition = " id = '"+tk.getId()+"'";
        
        Boolean check = connect.Delete("taikhoan", condition);
        
        connect.Close();
        return check;
    }
    
    /**
     * @param tk truyền vào dữ liệu tài khoản mới
     * Sửa thông tin đăng nhập hoặc là cấp bậc của 1 tài khoản
     * @return true nếu thành công
     */
    public Boolean sua(TaiKhoanDTO tk) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("matkhau", tk.getStrMatKhau());
        
        String condition = " id = '"+tk.getStrTenDangNhap()+"'";
        
        Boolean check = connect.Update("tbltaikhoan", insertValues, condition);
        
        connect.Close();
        return check;
    }
}
