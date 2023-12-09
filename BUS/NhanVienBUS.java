/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLHH.BUS;

import QLHH.DAO.NhanVienDAO;
import QLHH.DTO.NhanVienDTO;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class NhanVienBUS {
    private ArrayList<NhanVienDTO> list_NV;
    /**
     * Xử lý các lệnh trong SQL
     */
    private NhanVienDAO nvDAO;
    
    public NhanVienBUS() throws Exception {
        list_NV = new ArrayList<>();
        nvDAO = new NhanVienDAO();
        list_NV = nvDAO.docDB();
    }
    
    public int getNumbNhanVien() {
        return list_NV.size();
    }
    
    public String getDefaultMaHD() {
        if ( list_NV.size() ==0 )
            return "NV01";
        else {
            String s = "NV";
            int iNumb = 0;
            
            for (NhanVienDTO sanpham : list_NV) {
                String[] maSP = sanpham.getStrMaNV().split("NV");
                iNumb = Integer.parseInt( maSP[1] );
                iNumb++;
            }
            s += iNumb;
            return s;
        }
    }
    /**
     * thêm 1 nhân viên vào danh sách và database
     * @return true nếu thành công
     */
    public Boolean them(NhanVienDTO nv) throws Exception{
        if ( nvDAO.them(nv) ) {
            list_NV.add(nv);
        }
        return false;
    }
    
    public NhanVienDTO getInfor(int i){
        int iCount =0;
        for (NhanVienDTO hd : list_NV) {
            if (iCount == i)
                return hd;
            iCount++;
        }
        return null;
    }
    /**
     * xóa 1 nhân viên khỏi danh sách và database
     * @return true nếu thành công
     */
    public Boolean xoa(NhanVienDTO nv) throws Exception {
        if ( nvDAO.xoa(nv) ) {
            
            // duyệt từng phẩn tử
            for ( NhanVienDTO nhanvien : list_NV ) {
                if (nhanvien.getStrMaNV() == nv.getStrMaNV()){
                    list_NV.remove(nhanvien);
                break;
                }
            }
        }
        
        return false;
    }
    
    /**
     * sửa thông tin của 1 nhân viên <br>
     * - Trừ thông tin đăng nhập của nhân viên đó
     * @return true nếu thực hiện thành công
     */
    public Boolean sua(NhanVienDTO nv) throws Exception {
        if ( nvDAO.sua(nv) ) {
            
            // duyệt từng phẩn tử
            for ( NhanVienDTO nhanvien : list_NV ) {
                if (nhanvien.getStrMaNV()== nv.getStrMaNV()){
                break;
                }
            }
        }
        
        return false;
    }
    
    public NhanVienDTO getNhanVien_MaNV(String strMaNV) {
        for ( NhanVienDTO nhanvien : list_NV ) {
            if (nhanvien.getStrMaNV().equals(strMaNV)) {
                return nhanvien;
            }
        }
        return null;
    }
}
