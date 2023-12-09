/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package QLHH.GUI;

import QLHH.BUS.ChiTietHDBUS;
import QLHH.BUS.ChiTietPNBUS;
import QLHH.BUS.HoaDonBUS;
import QLHH.BUS.PhieuNhapBUS;
import QLHH.DTO.ChiTietHDDTO;
import QLHH.DTO.ChiTietPNDTO;
import QLHH.DTO.HoaDonDTO;
import QLHH.DTO.PhieuNhapDTO;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
/**
 *
 * @author ACER
 */
public class Excel extends javax.swing.JFrame  implements MouseListener {
    private Object[][] data_HD = { 
            { "MaHD", "MaNV", "MaKH","MaKM", "NgayBan", "TongTien" }
        };
    private Object[][] data_CTHD = { 
            { "MaHD", "MaGiay", "SoLuong","GiaBan" }
        };
    
    private Object[][] data_PN = { 
            { "MaPN", "MaNCC", "MaNV", "NgayNhap", "TongTien" }
        };
    private Object[][] data_CTPN = { 
            { "MaPN", "MaGiay", "SoLuong","GiaNhap" }
        };
 
    private HoaDonBUS list_HD;
    private PhieuNhapBUS list_PN;
    private ChiTietHDBUS list_CTHD;
    private ChiTietPNBUS list_CTPN;
    private DefaultTableModel model, modelChiTiet;
    
    private void writeFileExcel_HD() {
        WritableWorkbook workbook;
        // create workbook
        try {
            workbook = Workbook.createWorkbook(new File(Memory.filechoose));
 
            // create sheet
            WritableSheet sheet1 = workbook.createSheet("HoaDon", 0);
            WritableSheet sheet2 = workbook.createSheet("ChiTietHoaDon", 1);
            
            // row begin write data_HD
            int rowBegin = 0;
            int colBegin = 0;
 
            for (int row = rowBegin, i = 0; row < data_HD.length + rowBegin; row++, i++) {
                for (int col = colBegin, j = 0; col < data_HD[0].length + colBegin; col++, j++) {
                    Object obj = data_HD[i][j];
                    sheet1.addCell(new Label(col, row, (String) data_HD[i][j]));
                }
            }
            
            for (int row = rowBegin, i = 0; row < data_CTHD.length + rowBegin; row++, i++) {
                for (int col = colBegin, j = 0; col < data_CTHD[0].length + colBegin; col++, j++) {
                    Object obj = data_CTHD[i][j];
                    sheet2.addCell(new Label(col, row, (String) data_CTHD[i][j]));
                }
            }
            
            rowBegin = 1;
            
            for (int row = rowBegin, i = 0; row < list_HD.getNumbHoaDon() + rowBegin; row++, i++) {
                HoaDonDTO hd = new HoaDonDTO();
                hd = list_HD.getInfor(i);
                
                //string
                sheet1.addCell(new Label(0, row, hd.getStrMaHD() ));
                sheet1.addCell(new Label(1, row, hd.getStrMaNV()));
                sheet1.addCell(new Label(2, row, hd.getStrMaKH() ));
                sheet1.addCell(new Label(4, row, hd.getStrNgayBan() ));
                //double
                sheet1.addCell(new Label(5, row,String.valueOf(hd.getTongTien()) ));
            }
            
            for (int row = rowBegin, i = 0; row < list_CTHD.getNumbChiTietHD()+ rowBegin; row++, i++) {
                ChiTietHDDTO hd = new ChiTietHDDTO();
                hd = list_CTHD.getInfor(i);
                
                //string
                sheet2.addCell(new Label(0, row, hd.getStrMaHD() ));
                sheet2.addCell(new Label(1, row, hd.getStrMaSP()));
                sheet2.addCell(new Label(2, row, String.valueOf(hd.getiSoLuong())));
                sheet2.addCell(new Label(3, row, String.valueOf(hd.getiGiaBan())));
            }
            
            
            
            // write file
            workbook.write();
 
            // close
            workbook.close();
        } catch (IOException e) {
            System.out.println("Error create file\n" + e.toString());
        } catch (RowsExceededException e) {
            System.out.println("Error write file\n" + e.toString());
        } catch (WriteException e) {
            System.out.println("Error write file\n" + e.toString());
        }
        System.out.println("create and write success");
    }
    // open and read file *.xls
    private void readFileExcel_HD() {
        list_HD.deleteAll();
        list_CTHD.deleteAll();
        
        Workbook workbook;
        try {
            // create workbook to open file
            workbook = Workbook.getWorkbook(new File(Memory.filechoose));
            // get sheet want read
            Sheet sheet = workbook.getSheet(0);
            Sheet sheet1 = workbook.getSheet(1);
            
            // hoa don
            int rows = sheet.getRows();
            int cols = sheet.getColumns();
            for (int row = 1; row < rows; row++) {
                String[] arr = new String[cols];
                for (int col = 0; col < cols; col++) {
                    Cell cell = sheet.getCell(col, row);
                    arr[col] = String.valueOf(cell.getContents());
                }
                HoaDonDTO hd = new HoaDonDTO();
                hd.setStrMaHD( arr[0] );
                hd.setStrMaNV(arr[1] );
                hd.setStrMaKH(arr[2] );
                hd.setStrNgayBan(arr[3] );
                hd.setTongTien(Double.parseDouble(arr[4]) );
                list_HD.add(hd);
                System.out.println(hd.toString());
            }
            
            // chi tiet hoa don
            int rows1 = sheet1.getRows();
            int cols1 = sheet1.getColumns();
            for (int row = 1; row < rows1; row++) {
                String[] arr = new String[cols1];
                for (int col = 0; col < cols1; col++) {
                    Cell cell = sheet1.getCell(col, row);
                    arr[col] = String.valueOf(cell.getContents());
                }
                ChiTietHDDTO cthd = new ChiTietHDDTO();
                cthd.setStrMaHD( arr[0] );
                cthd.setStrMaSP( arr[1] );
                cthd.setiSoLuong( Integer.parseInt(arr[2]) );
                cthd.setiGiaBan( Integer.parseInt(arr[3]) );
                list_CTHD.add(cthd);
                System.out.println(cthd.toString());
            }
            
            // close
            workbook.close();
        } catch (BiffException e) {
            System.out.println("File not found\n" + e.toString());
        } catch (IOException e) {
            System.out.println("File not found\n" + e.toString());
        }
    }
 
    // create and write new file *.xls
    private void writeFileExcel_PN() {
        WritableWorkbook workbook;
        // create workbook
        try {
            workbook = Workbook.createWorkbook(new File(Memory.filechoose));
 
            // create sheet
            WritableSheet sheet1 = workbook.createSheet("PhieuNhap", 0);
            WritableSheet sheet2 = workbook.createSheet("ChiTietPhieuNhap", 1);
            
            // row begin write data_HD
            int rowBegin = 0;
            int colBegin = 0;
 
            for (int row = rowBegin, i = 0; row < data_PN.length + rowBegin; row++, i++) {
                for (int col = colBegin, j = 0; col < data_PN[0].length + colBegin; col++, j++) {
                    Object obj = data_PN[i][j];
                    sheet1.addCell(new Label(col, row, (String) data_PN[i][j]));
                }
            }
            
            for (int row = rowBegin, i = 0; row < data_CTPN.length + rowBegin; row++, i++) {
                for (int col = colBegin, j = 0; col < data_CTPN[0].length + colBegin; col++, j++) {
                    Object obj = data_CTPN[i][j];
                    sheet2.addCell(new Label(col, row, (String) data_CTPN[i][j]));
                }
            }
            
            rowBegin = 1;
            
            for (int row = rowBegin, i = 0; row < list_PN.getNumb()+ rowBegin; row++, i++) {
                PhieuNhapDTO hd = new PhieuNhapDTO();
                hd = list_PN.getInfor(i);
                
                //string
                sheet1.addCell(new Label(0, row, hd.getStrMaPN() ));
                sheet1.addCell(new Label(1, row, hd.getStrMaNCC()));
                sheet1.addCell(new Label(2, row, hd.getStrMaNV() ));
                sheet1.addCell(new Label(3, row, hd.getStrNgayNhap()));
                //double
                sheet1.addCell(new Label(4, row,String.valueOf(hd.getTongTien()) ));
            }
            
            for (int row = rowBegin, i = 0; row < list_CTPN.getNumbChiTietPN()+ rowBegin; row++, i++) {
                ChiTietPNDTO hd = new ChiTietPNDTO();
                hd = list_CTPN.getInfor(i);
                
                //string
                sheet2.addCell(new Label(0, row, hd.getStrMaSP()));
                sheet2.addCell(new Label(1, row, hd.getStrMaPN()));
                sheet2.addCell(new Label(2, row, String.valueOf(hd.getiSoLuong())));
                sheet2.addCell(new Label(3, row, String.valueOf(hd.getiGiaNhap())));
            }
            // write file
            workbook.write();
 
            // close
            workbook.close();
        } catch (IOException e) {
            System.out.println("Error create file\n" + e.toString());
        } catch (RowsExceededException e) {
            System.out.println("Error write file\n" + e.toString());
        } catch (WriteException e) {
            System.out.println("Error write file\n" + e.toString());
        }
        System.out.println("create and write success");
    }
 
    // open and read file *.xls
    private void readFileExcel_PN() {
        list_PN.deleteAll();
        list_CTPN.deleteAll();
        
        Workbook workbook;
        try {
            // create workbook to open file
            workbook = Workbook.getWorkbook(new File(Memory.filechoose));
            // get sheet want read
            Sheet sheet = workbook.getSheet(0);
            Sheet sheet1 = workbook.getSheet(1);
            
            // hoa don
            int rows = sheet.getRows();
            int cols = sheet.getColumns();
            for (int row = 1; row < rows; row++) {
                String[] arr = new String[cols];
                for (int col = 0; col < cols; col++) {
                    Cell cell = sheet.getCell(col, row);
                    arr[col] = String.valueOf(cell.getContents());
                }
                PhieuNhapDTO hd = new PhieuNhapDTO();
                hd.setStrMaPN( arr[0] );
                hd.setStrMaNCC(arr[1] );
                hd.setStrMaNV(arr[2] );
                hd.setStrNgayNhap(arr[3] );
                hd.setTongTien(Double.parseDouble(arr[4]) );
                list_PN.add(hd);
                System.out.println(hd.toString());
            }
            
            // chi tiet hoa don
            int rows1 = sheet1.getRows();
            int cols1 = sheet1.getColumns();
            for (int row = 1; row < rows1; row++) {
                String[] arr = new String[cols1];
                for (int col = 0; col < cols1; col++) {
                    Cell cell = sheet1.getCell(col, row);
                    arr[col] = String.valueOf(cell.getContents());
                }
                ChiTietPNDTO cthd = new ChiTietPNDTO();
                cthd.setStrMaPN( arr[0] );
                cthd.setStrMaSP( arr[1] );
                cthd.setiSoLuong( Integer.parseInt(arr[2]) );
                cthd.setiGiaNhap(Integer.parseInt(arr[3]) );
                list_CTPN.add(cthd);
                System.out.println(cthd.toString());
            }
            
            // close
            workbook.close();
        } catch (BiffException e) {
            System.out.println("File not found\n" + e.toString());
        } catch (IOException e) {
            System.out.println("File not found\n" + e.toString());
        }
    }
 

    public Excel() {
        try {
        list_CTPN = new ChiTietPNBUS();
        } catch (Exception ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            list_PN = new PhieuNhapBUS();
        } catch (Exception ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            list_CTPN.docDB();
        } catch (Exception ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            list_PN.docDB();
        } catch (Exception ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            list_HD = new HoaDonBUS();
        } catch (Exception ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            list_HD.docDB();
        } catch (Exception ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            list_CTHD = new ChiTietHDBUS();
        } catch (Exception ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            list_CTHD.docDB();
        } catch (Exception ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.setUndecorated(true);
        init();
        initComponents();

        System.out.println("");
        // các hàm xử lý sau khi vào
        this.setLocationRelativeTo(null);
        this.setSize(1005, 607);
        this.setTitle("QUẢN LÝ HÀNG HÓA SIÊU THỊ");
        actionButtondisplayHD();
        actionButtondisplayChiTietHD();
    }
    
    private Color Color_pnLeft, colorPanel_Center, colorText;
    private JLabel[] listLblLeft;
    private String[] strArr_Left;
    
    public void init() {
        Color_pnLeft = Memory.colorThemes;
        colorPanel_Center = Memory.colorThemes_2;
        colorText = Memory.colorText;
        
        strArr_Left = new String[10];
        strArr_Left[0] = "hanghoa";
        strArr_Left[1] = "nhanvien";
        strArr_Left[2] = "phieunhap";
        strArr_Left[3] = "hoadon";
        strArr_Left[4] = "thongke";
        strArr_Left[5] = "xuatexcel";
    }
    /**
     * Creates new form Excel
     */
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnLeft = createPanel_LblLeft(strArr_Left);
        pCenter = new javax.swing.JPanel();
        pChiTiet = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblThongTin = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblThongTin1 = new javax.swing.JTable();
        lblChiTiet_ChiTietHoaDon = new javax.swing.JLabel();
        lblChiTiet_ChiTietHoaDon1 = new javax.swing.JLabel();
        pThongTin = new javax.swing.JPanel();
        lblChiTiet_ChiTietHoaDon2 = new javax.swing.JLabel();
        lblChiTiet_ChiTietHoaDon3 = new javax.swing.JLabel();
        btnExcel_3 = new javax.swing.JToggleButton();
        btnExcel_5 = new javax.swing.JToggleButton();
        btnExcel_6 = new javax.swing.JToggleButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuTaiKhoan = new javax.swing.JMenu();
        menuTaiKhoan_ThongTin = new javax.swing.JMenuItem();
        menuTaiKhoan_DangXuat = new javax.swing.JMenuItem();
        menuTroVe = new javax.swing.JMenu();
        menuThoat = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1005, 601));

        pnLeft.setBackground(Color_pnLeft);
        pnLeft.setPreferredSize(new java.awt.Dimension(584, 70));

        pCenter.setBackground(colorPanel_Center);
        pCenter.setPreferredSize(new java.awt.Dimension(1280, 600));

        pChiTiet.setBackground(colorPanel_Center);
        pChiTiet.setPreferredSize(new java.awt.Dimension(880, 580));

        tblThongTin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblThongTin);

        tblThongTin1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblThongTin1);

        lblChiTiet_ChiTietHoaDon.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lblChiTiet_ChiTietHoaDon.setForeground(colorText);
        lblChiTiet_ChiTietHoaDon.setText("Chi Tiết");

        lblChiTiet_ChiTietHoaDon1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lblChiTiet_ChiTietHoaDon1.setForeground(colorText);
        lblChiTiet_ChiTietHoaDon1.setText("Thông Tin");

        pThongTin.setBackground(colorPanel_Center);
        pThongTin.setPreferredSize(new java.awt.Dimension(340, 580));
        pThongTin.setVerifyInputWhenFocusTarget(false);

        lblChiTiet_ChiTietHoaDon2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lblChiTiet_ChiTietHoaDon2.setForeground(colorText);

        lblChiTiet_ChiTietHoaDon3.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lblChiTiet_ChiTietHoaDon3.setForeground(colorText);

        btnExcel_3.setText("Ghi File");
        btnExcel_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcel_3ActionPerformed(evt);
            }
        });

        btnExcel_5.setText("Đọc từ Database");
        btnExcel_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcel_5ActionPerformed(evt);
            }
        });

        btnExcel_6.setText("Đọc File");
        btnExcel_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcel_6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pThongTinLayout = new javax.swing.GroupLayout(pThongTin);
        pThongTin.setLayout(pThongTinLayout);
        pThongTinLayout.setHorizontalGroup(
            pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pThongTinLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pThongTinLayout.createSequentialGroup()
                        .addComponent(lblChiTiet_ChiTietHoaDon3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pThongTinLayout.createSequentialGroup()
                        .addComponent(lblChiTiet_ChiTietHoaDon2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                        .addGroup(pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnExcel_3)
                            .addComponent(btnExcel_6)
                            .addComponent(btnExcel_5))
                        .addGap(16, 16, 16))))
        );
        pThongTinLayout.setVerticalGroup(
            pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pThongTinLayout.createSequentialGroup()
                .addGroup(pThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pThongTinLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblChiTiet_ChiTietHoaDon2)
                        .addGap(79, 79, 79))
                    .addGroup(pThongTinLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnExcel_3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcel_6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcel_5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)))
                .addComponent(lblChiTiet_ChiTietHoaDon3))
        );

        javax.swing.GroupLayout pChiTietLayout = new javax.swing.GroupLayout(pChiTiet);
        pChiTiet.setLayout(pChiTietLayout);
        pChiTietLayout.setHorizontalGroup(
            pChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pChiTietLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pChiTietLayout.createSequentialGroup()
                        .addComponent(pThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(lblChiTiet_ChiTietHoaDon1)
                        .addContainerGap(282, Short.MAX_VALUE))
                    .addGroup(pChiTietLayout.createSequentialGroup()
                        .addGroup(pChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pChiTietLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblChiTiet_ChiTietHoaDon)
                .addGap(295, 295, 295))
        );
        pChiTietLayout.setVerticalGroup(
            pChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pChiTietLayout.createSequentialGroup()
                .addGroup(pChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pChiTietLayout.createSequentialGroup()
                        .addContainerGap(70, Short.MAX_VALUE)
                        .addComponent(lblChiTiet_ChiTietHoaDon1)
                        .addGap(28, 28, 28))
                    .addGroup(pChiTietLayout.createSequentialGroup()
                        .addComponent(pThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblChiTiet_ChiTietHoaDon)
                .addGap(23, 23, 23)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pCenterLayout = new javax.swing.GroupLayout(pCenter);
        pCenter.setLayout(pCenterLayout);
        pCenterLayout.setHorizontalGroup(
            pCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCenterLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187))
        );
        pCenterLayout.setVerticalGroup(
            pCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE))
        );

        menuTaiKhoan.setText("Tài khoản");

        menuTaiKhoan_ThongTin.setText("Thông tin");
        menuTaiKhoan_ThongTin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                menuTaiKhoan_ThongTinMouseReleased(evt);
            }
        });
        menuTaiKhoan_ThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTaiKhoan_ThongTinActionPerformed(evt);
            }
        });
        menuTaiKhoan.add(menuTaiKhoan_ThongTin);

        menuTaiKhoan_DangXuat.setText("Đăng xuất");
        menuTaiKhoan_DangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                menuTaiKhoan_DangXuatMouseReleased(evt);
            }
        });
        menuTaiKhoan_DangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTaiKhoan_DangXuatActionPerformed(evt);
            }
        });
        menuTaiKhoan.add(menuTaiKhoan_DangXuat);

        jMenuBar1.add(menuTaiKhoan);

        menuTroVe.setText("Trở về");
        menuTroVe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuTroVeMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuTroVe);

        menuThoat.setText("Thoát");
        menuThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuThoatMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuThoatMouseEntered(evt);
            }
        });
        jMenuBar1.add(menuThoat);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(805, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(213, Short.MAX_VALUE)
                    .addComponent(pCenter, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pCenter, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuTaiKhoan_ThongTinMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTaiKhoan_ThongTinMouseReleased
        // TODO add your handling code here:
        User ui = new User();
        ui.setVisible(true);
    }//GEN-LAST:event_menuTaiKhoan_ThongTinMouseReleased

    private void menuTaiKhoan_ThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTaiKhoan_ThongTinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuTaiKhoan_ThongTinActionPerformed

    private void menuTaiKhoan_DangXuatMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTaiKhoan_DangXuatMouseReleased
        // TODO add your handling code here:
        Login lg;
        try {
            lg = new Login();
            lg.setVisible(true);
            this.setVisible(false);
        } catch (Exception ex) {
            Logger.getLogger(HangHoa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuTaiKhoan_DangXuatMouseReleased

    private void menuTaiKhoan_DangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTaiKhoan_DangXuatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuTaiKhoan_DangXuatActionPerformed

    private void menuTroVeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTroVeMouseClicked
        // TODO add your handling code here:
        Home home;
        try {
            home = new Home();
            home.setVisible(true);
            setVisible(false);
        } catch (Exception e) {
            System.out.println("Lỗi");
        }
    }//GEN-LAST:event_menuTroVeMouseClicked

    private void menuThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuThoatMouseClicked
        // TODO add your handling code here:
        Question_YesNO Q = new Question_YesNO("Bạn có muốn thoát không?");
        Q.setVisible(true);
    }//GEN-LAST:event_menuThoatMouseClicked

    private void menuThoatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuThoatMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_menuThoatMouseEntered

    private void btnExcel_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcel_3ActionPerformed
        // TODO add your handling code here:
        ChonFile cf = new ChonFile(this, true);
        cf.setVisible(true);
        writeFileExcel_PN();
    }//GEN-LAST:event_btnExcel_3ActionPerformed

    private void btnExcel_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcel_5ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            list_PN.docDB();
        } catch (Exception ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            list_CTPN.docDB();
        } catch (Exception ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
        actionButtondisplayChiTietPN();
        actionButtondisplayPN();
    }//GEN-LAST:event_btnExcel_5ActionPerformed

    private void btnExcel_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcel_6ActionPerformed
        // TODO add your handling code here:
        ChonFile cf = new ChonFile(this, true);
        cf.setVisible(true);
        String[] s = Memory.filechoose.split("\\.");
        if (s[1].equals("xls")) {
            readFileExcel_PN();
            actionButtondisplayPN();
            actionButtondisplayChiTietPN();
        }
        else {
            JOptionPane.showConfirmDialog(null, "File Không đúng hãy chọn file excel\nvd:abd.xls", "404", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnExcel_6ActionPerformed
    private JPanel createPanel_LblLeft(String[] strArr_Left) {
        JPanel Panel = new JPanel();
        listLblLeft = new JLabel[10];
        
        Panel.setLayout(new BoxLayout(Panel,BoxLayout.Y_AXIS));
        Panel.setBackground(Color_pnLeft);
        
        for (int i=0 ; i < listLblLeft.length ; i++) {
            listLblLeft[i] = new JLabel(new ImageIcon("./src/QLHH/img/Menu/"+strArr_Left[i]+".png"));
            listLblLeft[i].setName(strArr_Left[i]);
            listLblLeft[i].setOpaque(true);
            listLblLeft[i].addMouseListener(this);
            listLblLeft[i].setBackground(Color_pnLeft);
            
            Panel.add(listLblLeft[i]);
        }
        
        return Panel;
    }
    
    private void createVectorHeaderHD() {
        if (model.getRowCount()==0) {
            Vector<String> header = new Vector();
            header.add("Mã HĐ");
            header.add("Mã KH");
            header.add("Mã NV");
            header.add("Mã KM");
            header.add("Ngày Bán");
            header.add("Tổng Tiền");
            
            model = new DefaultTableModel(header, 0){
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };
        }
    }
    
    private void actionButtondisplayHD() {
        model = new DefaultTableModel();
        
        createVectorHeaderHD();
        for (int i=0 ; i < list_HD.getNumbHoaDon() ; i++) {
            HoaDonDTO hd = new HoaDonDTO();
            
            hd = list_HD.getInfor(i);
            
            createVectorHeaderHD();
            
            Vector row=new Vector();
            row.add(hd.getStrMaHD());
            row.add(hd.getStrMaKH());
            row.add(hd.getStrMaNV());
            row.add(hd.getStrNgayBan());
            row.add(hd.getTongTien());
            
            model.addRow(row);
        }
        
        tblThongTin.setModel(model);
        tblThongTin.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblThongTin.getColumnModel().getColumn(3).setPreferredWidth(50);
    }
    private void createVectorHeaderChiTietHD() {
        if (modelChiTiet.getRowCount()==0) {
            Vector<String> header = new Vector();
            header.add("Mã Sản Phẩm");
            header.add("Mã Hóa Đơn");
            header.add("Số Lượng");
            header.add("Giá Bán");
            header.add("Mã Khuyến Mãi");
            header.add("Tỉ Lệ KM");
            modelChiTiet = new DefaultTableModel(header, 0) {
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };
        }
    }
    
    private void actionButtondisplayChiTietHD() {
        actionButtondisplayChiTietHD("null");
    }
    
    private void actionButtondisplayChiTietHD(String strMaHD) {
        modelChiTiet = new DefaultTableModel();
        
        createVectorHeaderChiTietHD();
        for (int i=0 ; i < list_CTHD.getNumbChiTietHD() ; i++) {
            ChiTietHDDTO hd = new ChiTietHDDTO();
            
            hd = list_CTHD.getInfor(i);
            
            createVectorHeaderChiTietHD();
            
            if ( hd.getStrMaHD().equalsIgnoreCase(strMaHD) || 
                    strMaHD.equals("null")) {
                
                Vector row=new Vector();
                row.add(hd.getStrMaSP());
                row.add(hd.getStrMaHD());
                row.add(hd.getiSoLuong());
                row.add(hd.getiGiaBan());
                row.add(hd.getStrMaSP());
                modelChiTiet.addRow(row);
            }
        }
        
        tblThongTin1.setModel(modelChiTiet);
    }
    
    
    //phieu nhap=================================================================================
    private void createVectorHeaderPN() {
        if (model.getRowCount()==0) {
            Vector<String> header = new Vector();
            header.add("Mã PN");
            header.add("Mã NCC");
            header.add("Mã NV");
            header.add("Ngày Nhập");
            header.add("Tổng Tiền");
            
            model = new DefaultTableModel(header, 0){
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };;
        }
    }
    
    private void actionButtondisplayPN() {
        model = new DefaultTableModel();
        
        createVectorHeaderPN();
        for (int i=0 ; i < list_PN.getNumb() ; i++) {
            PhieuNhapDTO hd = new PhieuNhapDTO();
            
            hd = list_PN.getInfor(i);
            
            createVectorHeaderPN();
            
            Vector row=new Vector();
            row.add(hd.getStrMaPN());
            row.add(hd.getStrMaNCC());
            row.add(hd.getStrMaNV());
            row.add(hd.getStrNgayNhap());
            row.add(hd.getTongTien());
            
            model.addRow(row);
        }
       
        
        tblThongTin.setModel(model);
        
    }
    private void createVectorHeaderChiTietPN() {
        if (modelChiTiet.getRowCount()==0) {
            Vector<String> header = new Vector();
            header.add("Mã Sản Phẩm");
            header.add("Mã Phiếu Nhập");
            header.add("Số Lượng");
            header.add("Giá Nhập");
            modelChiTiet = new DefaultTableModel(header, 0) {
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };
        }
    }
    
    private void actionButtondisplayChiTietPN() {
        actionButtondisplayChiTietPN("null");
    }
    
    private void actionButtondisplayChiTietPN(String strMaHD) {
        modelChiTiet = new DefaultTableModel();
        
        createVectorHeaderChiTietPN();
        for (int i=0 ; i < list_CTPN.getNumbChiTietPN() ; i++) {
            ChiTietPNDTO hd = new ChiTietPNDTO();
            
            hd = list_CTPN.getInfor(i);
            
            createVectorHeaderChiTietPN();
            
            if ( hd.getStrMaPN().equalsIgnoreCase(strMaHD) || 
                    strMaHD.equals("null")) {
                
                Vector row=new Vector();
                row.add(hd.getStrMaSP());
                row.add(hd.getStrMaPN());
                row.add(hd.getiSoLuong());
                row.add(hd.getiGiaNhap());
                modelChiTiet.addRow(row);
            }
        }
        
        tblThongTin1.setModel(modelChiTiet);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Excel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Excel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Excel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Excel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Excel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnExcel_3;
    private javax.swing.JToggleButton btnExcel_5;
    private javax.swing.JToggleButton btnExcel_6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblChiTiet_ChiTietHoaDon;
    private javax.swing.JLabel lblChiTiet_ChiTietHoaDon1;
    private javax.swing.JLabel lblChiTiet_ChiTietHoaDon2;
    private javax.swing.JLabel lblChiTiet_ChiTietHoaDon3;
    private javax.swing.JMenu menuTaiKhoan;
    private javax.swing.JMenuItem menuTaiKhoan_DangXuat;
    private javax.swing.JMenuItem menuTaiKhoan_ThongTin;
    private javax.swing.JMenu menuThoat;
    private javax.swing.JMenu menuTroVe;
    private javax.swing.JPanel pCenter;
    private javax.swing.JPanel pChiTiet;
    private javax.swing.JPanel pThongTin;
    private javax.swing.JPanel pnLeft;
    private javax.swing.JTable tblThongTin;
    private javax.swing.JTable tblThongTin1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent me) {
        JLabel src = (JLabel) me.getSource();
        System.out.println(src.getName() + "is Clicked");
    }

    @Override
    public void mousePressed(MouseEvent me) {
        JLabel src = (JLabel) me.getSource();
        System.out.println(src.getName() + "is Pressed");
        src.setIcon(new ImageIcon("./src/QLHH/img/Menu/"+src.getName()+" click.png"));
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        JLabel src = (JLabel) me.getSource();
        System.out.println(src.getName() + "is Entered");
        src.setIcon(new ImageIcon("./src/QLHH/img/Menu/"+src.getName()+" hover.png"));
    
        // xử lý nút trở về
        if ( src.getName().equals("back") ) {
            Home home;
            try {
                home = new Home();
                home.setVisible(true);
                setVisible(false);
            } catch (Exception e) {
                System.out.println("Lỗi");
            }
        }
        
        if ( src.getName().equals("nhanvien") ) 
                try {
                    NhanVien dm;
                    dm = new NhanVien();
                    dm.setVisible(true);
                    setVisible(false);
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                } 
        
        if ( src.getName().equals("phieunhap") ) 
                try {
                    PhieuNhap hd = new PhieuNhap();
                    hd.setVisible(true);
                    setVisible(false);
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        if ( src.getName().equals("hoadon") ) 
                try {
                    HoaDon hd;
                    hd = new HoaDon();
                    hd.setVisible(true);
                    setVisible(false);
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        if ( src.getName().equals("thongke") ) 
                try {
                    ThongKe hd;
                    hd = new ThongKe();
                    hd.setVisible(true);
                    setVisible(false);
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        if ( src.getName().equals("hanghoa") ) 
                try {
                    HangHoa hd;
                    hd = new HangHoa();
                    hd.setVisible(true);
                    setVisible(false);
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        JLabel src = (JLabel) me.getSource();
        System.out.println(src.getName() + "is Entered");
        src.setIcon(new ImageIcon("./src/QLHH/img/Menu/"+src.getName()+" hover.png"));

    }

    @Override
    public void mouseExited(MouseEvent me) {
        JLabel src = (JLabel) me.getSource();
        System.out.println(src.getName() + "is Exited");
        src.setIcon(new ImageIcon("./src/QLHH/img/Menu/"+src.getName()+".png"));
    }
}
