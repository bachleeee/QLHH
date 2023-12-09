/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLHH.DAO;

import QLHH.DTO.NhanVienDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ACER
 */
public class NhanVienDAO {
    MyConnectUnit connect;
     
    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<NhanVienDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new MyConnectUnit();
        
        ResultSet result = this.connect.Select("nhanvien", condition, orderBy);
        ArrayList<NhanVienDTO> nhanviens = new ArrayList<>();
        while ( result.next() ) {
            NhanVienDTO nhanvien = new NhanVienDTO();
            nhanvien.setStrMaNV(result.getString("MaNV") );
            nhanvien.setStrHoTen(result.getString("HoTen"));
            nhanvien.setStrMaCV(result.getString("MaCV"));            
            nhanvien.setStrGioiTinh(result.getString("GioiTinh"));
            nhanvien.setStrNgaySinh(result.getString("NgaySinh"));
            nhanvien.setStrDiaChi(result.getString("DiaChi"));
            nhanvien.setStrDienThoai(result.getString("DienThoai"));
            nhanvien.setiID(result.getString("userid"));
            
            nhanviens.add(nhanvien);
        }
        connect.Close();
        return nhanviens;
    }
    
    public ArrayList<NhanVienDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<NhanVienDTO> docDB() throws Exception {
        return docDB(null);
    }
    
    /**
     * Tạo thêm 1 nhanvien dựa theo đã có thông tin trước
     * @return true nếu thành công
     */
    public Boolean them(NhanVienDTO nv) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("MaNV", nv.getStrMaNV());
        insertValues.put("HoTen", nv.getStrHoTen());
        insertValues.put("MaCV", nv.getStrMaCV());
        insertValues.put("GioiTinh", nv.getStrGioiTinh());
        insertValues.put("NgaySinh", nv.getStrNgaySinh());
        insertValues.put("DiaChi", nv.getStrDiaChi());
        insertValues.put("DienThoai", nv.getStrDienThoai());
        insertValues.put("userid", nv.getiID());
        
        Boolean check = connect.Insert("nhanvien", insertValues);
        
        connect.Close();
        return check;
    }
    
    /** 
     * @param nv chuyền vào dữ liệu tài khoản để xóa
     * @return true nếu thành công
     */
    public Boolean xoa(NhanVienDTO nv) throws Exception {
        connect = new MyConnectUnit();
        String condition = " tendangnhap = '"+nv.getStrMaNV()+"'";
        
        Boolean check = connect.Delete("nhanvien", condition);
        
        connect.Close();
        return check;
    }
    
    /**
     * @param nv truyền vào dữ liệu tài khoản mới
     * Sửa thông tin đăng nhập hoặc là cấp bậc của 1 tài khoản
     * @return true nếu thành công
     */
    public Boolean sua(NhanVienDTO nv) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("MaNV", nv.getStrMaNV());
        insertValues.put("HoTen", nv.getStrHoTen());
        insertValues.put("MaCV", nv.getStrMaCV());
        insertValues.put("GioiTinh", nv.getStrGioiTinh());
        insertValues.put("DiaChi", nv.getStrNgaySinh());
        insertValues.put("DiaChi", nv.getStrDiaChi());
        insertValues.put("DienThoai", nv.getStrDienThoai());
        insertValues.put("userid", nv.getiID());
        
        String condition = " id = '"+nv.getStrMaNV()+"'";
        
        Boolean check = connect.Update("nhanvien", insertValues, condition);
        
        connect.Close();
        return check;
    }
}
