/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLHH.BUS;

import QLHH.DAO.NhomHangDAO;
import QLHH.DTO.NhomHangDTO;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class NhomHangBUS {
    private ArrayList <NhomHangDTO> list_Loai;
     /**
     * Xử lý các lệnh trong SQL
     */
    private NhomHangDAO LDAO;
    
    public NhomHangBUS() throws Exception {
        list_Loai = new ArrayList<>();
        LDAO = new NhomHangDAO();
        list_Loai = LDAO.docDB();
    }

    public ArrayList<NhomHangDTO> getList_Loai() {
        return list_Loai;
    }

    public void setList_Loai(ArrayList<NhomHangDTO> list_Loai) {
        this.list_Loai = list_Loai;
    }
    public int getNumbLoai() {
        return list_Loai.size();
    }
}
