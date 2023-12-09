/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLHH.DAO;

import QLHH.DTO.SanPhamDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ACER
 */
public class SanPhamDAO {
    MyConnectUnit connect;
    
    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<SanPhamDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new MyConnectUnit();
        
        ResultSet result = this.connect.Select("sanpham", condition, orderBy);
        ArrayList<SanPhamDTO> sanphams = new ArrayList<>();
        while ( result.next() ) {
            SanPhamDTO sanpham = new SanPhamDTO();
            sanpham.setStrMaSP(result.getString("MaSP"));
            sanpham.setStrTenSP(result.getString("TenSP"));
            sanpham.setStrMaNH(result.getString("MaNH"));
            sanpham.setiGia(result.getInt("GiaBan"));
            sanpham.setiSoLuong(result.getInt("SoLuong"));
            
            sanphams.add(sanpham);
        }
        connect.Close();
        return sanphams;
    }
    
    public ArrayList<SanPhamDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<SanPhamDTO> docDB() throws Exception {
        return docDB(null);
    }
    
    
    public Boolean them(SanPhamDTO hd) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("masp", hd.getStrMaSP());
        insertValues.put("soluong", hd.getiSoLuong());
        insertValues.put("giaban", hd.getiGia());
        insertValues.put("manh", hd.getStrMaNH());
        insertValues.put("tensp", hd.getStrTenSP());
        
        Boolean check = connect.Insert("sanpham", insertValues);
        
        connect.Close();
        return check;
    }
    
    /** 
     * @param hd chuyền vào dữ liệu hdách hàng để xóa
     * @return true nếu thành công
     */
    public Boolean xoa(SanPhamDTO hd) throws Exception {
        connect = new MyConnectUnit();
        String condition = " MaSP = '"+hd.getStrMaSP()+"'";
        
        Boolean check = connect.Delete("sanpham", condition);
        
        connect.Close();
        return check;
    }
    
    /**
     * @param hd truyền vào dữ liệu hdách hàng mới
     * Sửa thông tin đăng nhập hoặc là cấp bậc của 1 hdách hàng
     * @return true nếu thành công
     */
    public Boolean sua(SanPhamDTO hd) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("MaSP", hd.getStrMaSP());
        insertValues.put("TenSP", hd.getStrTenSP());
        insertValues.put("MaNH", hd.getStrMaNH());
        insertValues.put("SoLuong", hd.getiSoLuong());
        insertValues.put("GiaBan", hd.getiGia());
        
        
        System.out.println(hd.toString());
        
        String condition = "MaSP = '"+hd.getStrMaSP()+"'";
        
        Boolean check = connect.Update("sanpham", insertValues, condition);
        
        connect.Close();
        return check;
    }
}
