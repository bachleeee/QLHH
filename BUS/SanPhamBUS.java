/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLHH.BUS;

import QLHH.DAO.SanPhamDAO;
import QLHH.DTO.SanPhamDTO;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class SanPhamBUS {
    private ArrayList<SanPhamDTO> list_SP;
    /**
     * Xử lý các lệnh trong SQL
     */
    private SanPhamDAO spDAO;
    
    public void docDB() throws Exception {
        list_SP = new ArrayList<>();
        spDAO = new SanPhamDAO();
        list_SP = spDAO.docDB();
    }

    
    public SanPhamBUS() throws Exception {
        list_SP = new ArrayList<>();
        spDAO = new SanPhamDAO();
        list_SP = spDAO.docDB();
    }
    
    public ArrayList<SanPhamDTO> getList_SP() {
        return list_SP;
    }

    public void setList_SP(ArrayList<SanPhamDTO> list_SP) {
        this.list_SP = list_SP;
    }
    
    public int getNumbSanPham() {
        return list_SP.size();
    }
    
    public String getDefaultMaHD() {
        if ( list_SP.size() ==0 )
            return "SUP01";
        else {
            String s = "SUP";
            int iNumb = 0;
            
            for (SanPhamDTO sanpham : list_SP) {
                String[] maSP = sanpham.getStrMaSP().split("SUP");
                iNumb = Integer.parseInt( maSP[1] );
                iNumb++;
            }
            s += iNumb;
            return s;
        }
    }
    
    public int getSoLuongCuaSanPham(String strMaSP) {
        for (SanPhamDTO sanpham : list_SP) {
            if (strMaSP.equals(sanpham.getStrMaSP())) {
                return sanpham.getiSoLuong();
            }
        }
        return -1;
    }
    
    public SanPhamDTO getInfor(String strMaSP){
        for (SanPhamDTO hd : list_SP) {
            if (hd.getStrMaSP().equals(strMaSP))
                return hd;
        }
        SanPhamDTO hd = new SanPhamDTO();
        hd.setStrMaSP("null");
        return hd;
    }
    
    public SanPhamDTO getInfor(int i){
        int iCount =0;
        for (SanPhamDTO hd : list_SP) {
            if (iCount == i)
                return hd;
            iCount++;
        }
        return null;
    }
    
    public ArrayList<SanPhamDTO> timKiem_MaSP(String strMaSP1, String strMaSP2, int sapxep) {
        ArrayList<SanPhamDTO> arr = new ArrayList<>();
        
//        Collections.sort(this.list_SP, SanPhamDTO::maSPTangdan);

        int flag = 0;
        for (SanPhamDTO sanpham : list_SP) {
            if ( sanpham.getStrMaSP().indexOf(strMaSP1) != -1 ) {
                flag = 1;
//                System.out.println("Tim thay SP1" + sanpham.getStrMaSP() );
            }
            if ( sanpham.getStrMaSP().indexOf(strMaSP2) != -1 ) {
                flag = 2;
//                System.out.println("Tim thay SP2" + sanpham.getStrMaSP() );
            }
            if ( flag == 1 || flag == 2 )
                arr.add(sanpham);
            if ( flag == 2 )
                break;
        }
        
        if(sapxep == 1){
//            Collections.sort(arr, SanPhamDTO::maSPTangdan);
        }
        else if(sapxep == -1) {
//            Collections.sort(arr, SanPhamDTO::maSPGiamdan);
        }
        return arr;
    }
    
    public ArrayList<SanPhamDTO> timKiem_SoLuong(int SL1, int SL2,int sapxep) {
        ArrayList<SanPhamDTO> arr = new ArrayList<>();
        for (SanPhamDTO sanpham : list_SP) {
            if ( sanpham.getiSoLuong() >= SL1 && sanpham.getiSoLuong() <= SL2) {
//                System.out.println("Tim thay " + sanpham.getStrMaSP() );
                arr.add(sanpham);
            }
        }
        if(sapxep == 1){
//            Collections.sort(arr, SanPhamDTO::soLuongTangdan);
        }
        else if(sapxep == -1){
//            Collections.sort(arr, SanPhamDTO::soLuongGiamdan);
        }
        
        return arr;
    }
    
    /**
     * @return true nếu tìm thấy 
     */
    public boolean kiemTraKhoachinh(SanPhamDTO hd) {
        for (SanPhamDTO sanpham : list_SP) {
            if ( sanpham.getStrMaSP().equals(hd.getStrMaSP())){
                System.out.println("Bị trùng");
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<SanPhamDTO> timKiem_TenSanPham(String strTenSP) {
        ArrayList<SanPhamDTO> arr = new ArrayList<>();
        for (SanPhamDTO sanpham : list_SP) {
            if ( sanpham.getStrTenSP().indexOf(strTenSP) != -1 ) {
                arr.add(sanpham);
            }
        }
        
        
        return arr;
    }
    public ArrayList<SanPhamDTO> timKiem_MaNhomHang(String strMaLoai) {
        ArrayList<SanPhamDTO> arr = new ArrayList<>();
        for (SanPhamDTO sanpham : list_SP) {
            if ( sanpham.getStrMaNH().indexOf(strMaLoai) != -1 ) {
                arr.add(sanpham);
            }
        }
        
        
        return arr;
    }
    
    //-----------------------------------------------------------------------------------------------
    /**
     * thêm 1sản phẩm vào danh sách và database
     * @return true nếu thành công
     */
    public Boolean them(SanPhamDTO hd) throws Exception{
        if ( !kiemTraKhoachinh(hd) ) {
            if ( spDAO.them(hd) ) {
                list_SP.add(hd);
                System.out.println("thêm sản phẩm");
            }
        }
        return false;
    }
    
    /**
     * xóa 1sản phẩm hdỏi danh sách và database
     * @return true nếu thành công
     */
    public Boolean xoa(SanPhamDTO hd) throws Exception {
        if ( spDAO.xoa(hd) ) {
            
            // duyệt từng phẩn tử
            for ( SanPhamDTO sanpham : list_SP ) {
                if (sanpham.getStrMaSP().equals(hd.getStrMaSP())){
                    list_SP.remove(sanpham);
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * sửa thông tin của 1sản phẩm <br>
     * - Trừ thông tin đăng nhập củasản phẩm đó
     * @return true nếu thực hiện thành công
     */
    public Boolean sua(SanPhamDTO hd) throws Exception {
        if ( spDAO.sua(hd) ) {
            
            // duyệt từng phẩn tử
            for ( SanPhamDTO sanpham : list_SP ) {
                if (sanpham.getStrMaSP().equals(hd.getStrMaSP())){
                    sanpham.setStrMaSP(hd.getStrMaSP());
                    sanpham.setStrMaNH(hd.getStrMaNH());
                    sanpham.setStrTenSP(hd.getStrTenSP());
                    sanpham.setiGia(hd.getiGia());
                    sanpham.setiSoLuong(hd.getiSoLuong());
                    return true;
                }
            }
        }
        
        return false;
    }
    
}
