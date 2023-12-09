/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLHH.DAO;

import QLHH.DTO.NhomHangDTO;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class NhomHangDAO {
    MyConnectUnit connect;
    
    /**
     * Lấy thông tin từ Database
     */
    public ArrayList<NhomHangDTO> docDB(String condition, String orderBy) throws Exception {
        // kết nối CSDL
        connect = new MyConnectUnit();
        
        ResultSet result = this.connect.Select("nhomhang", condition, orderBy);
        ArrayList<NhomHangDTO> loais = new ArrayList<>();
        while ( result.next() ) {
            NhomHangDTO loai = new NhomHangDTO();
            loai.setStrMaNH(result.getString("maNH"));
            loai.setStrTenNH(result.getString("tenNH"));
            loais.add(loai);
        }
        connect.Close();
        return loais;
    }
    
    public ArrayList<NhomHangDTO> docDB(String condition) throws Exception {
        return docDB(condition, null);
    }
    
    public ArrayList<NhomHangDTO> docDB() throws Exception {
        return docDB(null);
    }
}
