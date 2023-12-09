/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLHH.DAO;

import QLHH.DTO.KhachHangDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ACER
 */
public class KhachHangDAO {
    MyConnectUnit connect;
    
    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<KhachHangDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new MyConnectUnit();
        
        ResultSet result = this.connect.Select("khachhang", condition, orderBy);
        ArrayList<KhachHangDTO> khachhangs = new ArrayList<>();
        while ( result.next() ) {
            KhachHangDTO khachhang = new KhachHangDTO();
            khachhang.setStrMaKH(result.getString("MaKH"));
            khachhang.setStrHoTen(result.getString("HoTen"));
            khachhang.setStrDiaChi(result.getString("DiaChi"));
            khachhang.setStrDienThoai(result.getString("DienThoai"));
            khachhang.setStrGioiTinh(result.getString("GioiTinh"));
            khachhang.setiTongChiTieu(result.getDouble("TongChiTieu"));
            khachhangs.add(khachhang);
        }
        connect.Close();
        return khachhangs;
    }
    
    public ArrayList<KhachHangDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<KhachHangDTO> docDB() throws Exception {
        return docDB(null);
    }
    
    /**
     * Tạo thêm 1 khách hàng dựa theo đã có thông tin trước
     * @return true nếu thành công
     */
    public Boolean them(KhachHangDTO kh) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("diachi", kh.getStrDiaChi());
        insertValues.put("dienthoai", kh.getStrDienThoai());
        insertValues.put("GioiTinh", kh.getStrGioiTinh());
        insertValues.put("makh", kh.getStrMaKH());
        insertValues.put("hoten", kh.getStrHoTen());
        insertValues.put("tongchitieu", kh.getiTongChiTieu());
        
        Boolean check = connect.Insert("khachhang", insertValues);
        
        connect.Close();
        return check;
    }
    
    /** 
     * @param kh chuyền vào dữ liệu khách hàng để xóa
     * @return true nếu thành công
     */
    public Boolean xoa(KhachHangDTO kh) throws Exception {
        connect = new MyConnectUnit();
        String condition = " makh = '"+kh.getStrMaKH()+"'";
        
        Boolean check = connect.Delete("khachhang", condition);
        
        connect.Close();
        return check;
    }
    
    /**
     * @param kh truyền vào dữ liệu khách hàng mới
     * Sửa thông tin đăng nhập hoặc là cấp bậc của 1 khách hàng
     * @return true nếu thành công
     */
    public Boolean sua(KhachHangDTO kh) throws Exception {
        connect = new MyConnectUnit();
        
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("diachi", kh.getStrDiaChi());
        insertValues.put("dienthoai", kh.getStrDienThoai());
        insertValues.put("GioiTinh", kh.getStrGioiTinh());
        insertValues.put("makh", kh.getStrMaKH());
        insertValues.put("hoten", kh.getStrHoTen());
        insertValues.put("tongchitieu", kh.getiTongChiTieu());
        
        String condition = " MaKH = '"+kh.getStrMaKH()+"'";
        
        Boolean check = connect.Update("khachhang", insertValues, condition);
        
        connect.Close();
        return check;
    }
}
