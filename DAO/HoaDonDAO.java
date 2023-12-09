/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLHH.DAO;

import QLHH.DTO.HoaDonDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ACER
 */
public class HoaDonDAO {
    MyConnectUnit connect;
    
    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<HoaDonDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new MyConnectUnit();
        
        ResultSet result = this.connect.Select("hoadon", condition, orderBy);
        ArrayList<HoaDonDTO> hoadons = new ArrayList<>();
        while ( result.next() ) {
            HoaDonDTO hoadon = new HoaDonDTO();
            hoadon.setStrMaHD(result.getString("mahd"));
            hoadon.setStrMaNV(result.getString("manv"));            
            hoadon.setStrMaKH(result.getString("makh"));
            hoadon.setStrNgayBan(result.getString("ngayban"));
            hoadon.setTongTien(result.getDouble("tongtien"));
            hoadons.add(hoadon);
        }
        connect.Close();
        return hoadons;
    }
    
    public ArrayList<HoaDonDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<HoaDonDTO> docDB() throws Exception {
        return docDB(null);
    }
    
    /**
     * Tạo thêm 1 hdách hàng dựa theo đã có thông tin trước
     * @return true nếu thành công
     */
    public Boolean them(HoaDonDTO hd) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("mahd", hd.getStrMaHD());
        insertValues.put("manv", hd.getStrMaNV());        
        insertValues.put("makh", hd.getStrMaKH());
        insertValues.put("ngayban", hd.getStrNgayBan());
        insertValues.put("tongtien", hd.getTongTien());
        
        Boolean check = connect.Insert("hoadon", insertValues);
        
        connect.Close();
        return check;
    }
    
    /** 
     * @param hd chuyền vào dữ liệu hdách hàng để xóa
     * @return true nếu thành công
     */
    public Boolean xoa(HoaDonDTO hd) throws Exception {
        connect = new MyConnectUnit();
        String condition = " mahd = '"+hd.getStrMaHD()+"'";
        
        Boolean check = connect.Delete("hoadon", condition);
        
        connect.Close();
        return check;
    }
    
    /**
     * @param hd truyền vào dữ liệu hdách hàng mới
     * Sửa thông tin đăng nhập hoặc là cấp bậc của 1 hdách hàng
     * @return true nếu thành công
     */
    public Boolean sua(HoaDonDTO hd) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("makh", hd.getStrMaKH());
        insertValues.put("manv", hd.getStrMaNV());
        insertValues.put("ngayban", hd.getStrNgayBan());
        insertValues.put("tongtien", hd.getTongTien());
        
        String condition = " MaHD = '"+hd.getStrMaHD()+"'";
        
        Boolean check = connect.Update("hoadon", insertValues, condition);
        
        connect.Close();
        return check;
    }
}
