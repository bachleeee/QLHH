/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLHH.GUI;

import QLHH.BUS.NhanVienBUS;
import QLHH.DTO.NhanVienDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.MatteBorder;

/**
 *
 * @author ACERp
 */
public class Home extends JFrame{
    private NhanVienBUS list_nv;
    private NhanVienDTO nhanvien;
    private JPanel pnRight;
    private JLabel lblAva,lblMaNV,lblRole,lblHoten,lblLogout;
    private JLabel lblHoadon,lblNhanVien,lblHanghoa,lblNhaphang,lblXuatexcel, lblThongke; 
    private Color colorPanel_Left, colorPanel_Right, colorText;
    private ImageIcon imgAva;
    private Font fontNormal, fontLogout;
    private MatteBorder border;
    private JPanel pnLeft;
    public Home() throws Exception {
        init();
        
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
    }
    
    int x_Mouse, y_Mouse;
    
    private void formMousePressed(java.awt.event.MouseEvent evt) {                                  
        // TODO add your handling code here:
         x_Mouse = evt.getX();
         y_Mouse = evt.getY();
    }                                 

    private void formMouseDragged(java.awt.event.MouseEvent evt) {                                  
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        
        setLocation(x - x_Mouse, y - y_Mouse);
    }  
    
    public void init() throws Exception {
        // JFrame
        this.setUndecorated(true);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 400);
        this.setLayout(new BorderLayout());  
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        list_nv = new NhanVienBUS();
        nhanvien = list_nv.getNhanVien_MaNV(Memory.maNV);
        Memory.nhanvien = nhanvien;
        
        border= new MatteBorder(1, 1, 1, 1, Color.black);
        pnLeft = new JPanel();
        colorText = Memory.colorText;
        colorPanel_Left = Memory.colorThemes;
        colorPanel_Right = Memory.colorThemes_2;
        fontNormal = new Font("Arial", Font.BOLD, 20);
        fontLogout = new Font("Serif", Font.ITALIC, 40);
        lblAva = new JLabel(imgAva);
           
        lblMaNV = new JLabel("ID:" + nhanvien.getStrMaNV());
        lblHoten = new JLabel("Họ Tên:" + nhanvien.getStrHoTen());
        lblRole = new JLabel("Chức vụ:" + nhanvien.getStrMaCV());
        pnRight = new JPanel();
        
        
        
        
      
        //-----------panelLeft
        pnLeft.setBackground(colorPanel_Left);
        pnLeft.setPreferredSize(new Dimension(200,0));
        pnLeft.setLayout(new BoxLayout(pnLeft, BoxLayout.Y_AXIS));
        
        lblMaNV.setFont(fontNormal);
        lblHoten.setFont(fontNormal);
        lblRole.setFont(fontNormal);
       
       
        
        lblMaNV.setForeground(new Color(255,255,255));
        lblRole.setForeground(new Color(255,255,255));
        lblHoten.setForeground(new Color(255,255,255));
        
        lblAva.setBounds(17,50,60,60);
        lblMaNV.setBounds( 17,134,161,33);
        lblRole.setBounds( 17, 200,90 , 33);
        lblHoten.setBounds(17, 266,90 , 33);
        
        
        this.add(pnLeft, BorderLayout.WEST);

        
        
        //------------panelRight------------------
        pnRight = new JPanel();
        pnRight.setBackground(colorPanel_Right);
        pnRight.setLayout(null);
        lblHoadon = new JLabel();
        lblNhanVien = new JLabel();
        lblHanghoa = new JLabel();
        lblNhaphang = new JLabel();
        lblThongke = new JLabel();
        lblXuatexcel = new JLabel();
        //--------------img
        lblHanghoa.setBounds(62 , 47, 200, 70);
        lblHanghoa.setIcon(new ImageIcon("./src/QLHH/img/hanghoa.png"));
        lblNhanVien.setBounds(352, 47, 200, 70);
        lblNhanVien.setIcon(new ImageIcon("./src/QLHH/img/nhanvien.png"));
        lblNhaphang.setBounds(62, 174, 200, 70);
        lblNhaphang.setIcon(new ImageIcon("./src/QLHH/img/phieunhap.png"));
        lblHoadon.setBounds(352, 174, 200, 70);
        lblHoadon.setIcon(new ImageIcon("./src/QLHH/img/hoadon.png"));
        lblThongke.setBounds(62, 301, 200, 70);
        lblThongke.setIcon(new ImageIcon("./src/QLHH/img/thongke.png"));
        lblXuatexcel.setBounds(352, 301,200, 70);
        lblXuatexcel.setIcon(new ImageIcon("./src/QLHH/img/xuatexcel.png"));
        
        
        pnRight.add(lblHanghoa);
        pnRight.add(lblNhaphang);  
        pnRight.add(lblHoadon);
        pnRight.add(lblThongke);
        pnRight.add(lblXuatexcel);
        
        this.add(pnRight, BorderLayout.CENTER);

        //--------------mouseAction
        lblHanghoa.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                lblHanghoa.setIcon(new ImageIcon("./src/QLHH/img/hanghoa.png"));
                HangHoa hd;
                try {
                    hd = new HangHoa();
                    hd.setVisible(true);
                    setVisible(false);
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            @Override
            public void mousePressed(MouseEvent me) {
                lblHanghoa.setIcon(new ImageIcon("./src/QLHH/img/hanghoa.png"));
            }
            @Override
            public void mouseReleased(MouseEvent me) {
                lblHanghoa.setIcon(new ImageIcon("./src/QLHH/img/hanghoa1.png"));
            }
            @Override
            public void mouseEntered(MouseEvent me) {
                lblHanghoa.setIcon(new ImageIcon("./src/QLHH/img/hanghoa1.png"));
            }
            @Override
            public void mouseExited(MouseEvent me) {
                lblHanghoa.setIcon(new ImageIcon("./src/QLHH/img/hanghoa.png"));
            }
        });
        lblNhaphang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                try {
                    PhieuNhap dm = new PhieuNhap();
                    dm.setVisible(true);
                    setVisible(false);
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            @Override
            public void mousePressed(MouseEvent me) {
                lblNhaphang.setIcon(new ImageIcon("./src/QLHH/img/phieunhap.png"));
            }
            @Override
            public void mouseReleased(MouseEvent me) {
                lblNhaphang.setIcon(new ImageIcon("./src/QLHH/img/phieunhap1.png"));
            }
            @Override
            public void mouseEntered(MouseEvent me) {
                lblNhaphang.setIcon(new ImageIcon("./src/QLHH/img/phieunhap1.png"));
            }
            @Override
            public void mouseExited(MouseEvent me) {
                lblNhaphang.setIcon(new ImageIcon("./src/QLHH/img/phieunhap.png"));
            }
        });
        lblNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                try {
                    NhanVien dm = new NhanVien();
                    dm.setVisible(true);
                    setVisible(false);
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            @Override
            public void mousePressed(MouseEvent me) {
                lblNhanVien.setIcon(new ImageIcon("./src/QLHH/img/nhanvien.png"));
            }
            @Override
            public void mouseReleased(MouseEvent me) {
                lblNhanVien.setIcon(new ImageIcon("./src/QLHH/img/nhanvien1.png"));
            }
            @Override
            public void mouseEntered(MouseEvent me) {
                lblNhanVien.setIcon(new ImageIcon("./src/QLHH/img/nhanvien1.png"));
            }
            @Override
            public void mouseExited(MouseEvent me) {
                lblNhanVien.setIcon(new ImageIcon("./src/QLHH/img/nhanvien.png"));
            }
        });
        lblHoadon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                try {
                    HoaDon dm = new HoaDon();
                    dm.setVisible(true);
                    setVisible(false);
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            @Override
            public void mousePressed(MouseEvent me) {
                lblHoadon.setIcon(new ImageIcon("./src/QLHH/img/hoadon.png"));
            }
            @Override
            public void mouseReleased(MouseEvent me) {
                lblHoadon.setIcon(new ImageIcon("./src/QLHH/img/hoadon1.png"));
            }
            @Override
            public void mouseEntered(MouseEvent me) {
                lblHoadon.setIcon(new ImageIcon("./src/QLHH/img/hoadon1.png"));
            }
            @Override
            public void mouseExited(MouseEvent me) {
                lblHoadon.setIcon(new ImageIcon("./src/QLHH/img/hoadon.png"));
            }
        });
        lblThongke.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                try {
                    ThongKe dm = new ThongKe();
                    dm.setVisible(true);
                    setVisible(false);
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            @Override
            public void mousePressed(MouseEvent me) {
                lblThongke.setIcon(new ImageIcon("./src/QLHH/img/thongke.png"));
            }
            @Override
            public void mouseReleased(MouseEvent me) {
                lblThongke.setIcon(new ImageIcon("./src/QLHH/img/thongke1.png"));
            }
            @Override
            public void mouseEntered(MouseEvent me) {
                lblThongke.setIcon(new ImageIcon("./src/QLHH/img/thongke1.png"));
            }
            @Override
            public void mouseExited(MouseEvent me) {
                lblThongke.setIcon(new ImageIcon("./src/QLHH/img/thongke.png"));
            }
        });
        lblXuatexcel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                try {
                    Excel dm = new Excel();
                    dm.setVisible(true);
                    setVisible(false);
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            @Override
            public void mousePressed(MouseEvent me) {
                lblXuatexcel.setIcon(new ImageIcon("./src/QLHH/img/xuatexcel.png"));
            }
            @Override
            public void mouseReleased(MouseEvent me) {
                lblXuatexcel.setIcon(new ImageIcon("./src/QLHH/img/xuatexcel1.png"));
            }
            @Override
            public void mouseEntered(MouseEvent me) {
                lblXuatexcel.setIcon(new ImageIcon("./src/QLHH/img/xuatexcel1.png"));
            }
            @Override
            public void mouseExited(MouseEvent me) {
                lblXuatexcel.setIcon(new ImageIcon("./src/QLHH/img/xuatexcel.png"));
            }
        });
        
       
        
        
        //------------------End-----------------------------
    }
    
    /**
     * Hàm kiểm tra xem cập bậc của nó 
     * 1 : Admin FULL POWER
     * 2 : Nhập xuất file Excel và Thống Kê
     * 3 : Kiểm tra hàng hóa
     * 4 : Nhập Hàng
     * 5 : Khuyến mãi
     * 6 : Hóa đơn
     */
    
    
    public static void main(String[] args) throws Exception {
        new Home().setVisible(true);
    }
    

}
