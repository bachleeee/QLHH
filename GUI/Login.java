/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLHH.GUI;

import java.sql.Connection;
import QLHH.BUS.TaiKhoanBUS;
import QLHH.DTO.TaiKhoanDTO;
import com.mysql.cj.jdbc.CallableStatement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author ACER
 */
public class Login extends JFrame implements ActionListener {

    public Login() throws Exception {
        initComponents();

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

    public void initComponents() throws Exception {
        list_TK = new TaiKhoanBUS();

        Arial = new Font("Arial", Font.PLAIN, 15);
        Memory.colorText = new Color(0, 0, 0);
        Memory.colorThemes_2 = new Color(255, 255, 255);
        colorPanel_L = Memory.colorThemes_2;
        Memory.colorThemes = new Color(57, 110, 140);
        colorPanel_R = Memory.colorThemes;

        iconClose = new ImageIcon("./src/QLHH/img/cross.png");
        iconLogo = new ImageIcon("./src/QLHH/img/logo.png");
        pLeft = new JPanel();
        pRight = new JPanel();
        btnExit = new JButton(iconClose);
        lblLogo = new JLabel(iconLogo);
        lblName = new JLabel(new ImageIcon("./src/QLHH/img/user.png"));
        lblPass = new JLabel(new ImageIcon("./src/QLHH/img/password.png"));
        tfName = new JTextField();
        pfPass = new JPasswordField();
        lblLogin = new JLabel("Đăng nhập", SwingConstants.CENTER);
        lblDangKy = new JLabel("Đăng ký", SwingConstants.CENTER);

        // Jframe
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // chia theo tỉ lệ 4:3 x 187
        this.setSize(392, 561);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);

        this.add(pRight, BorderLayout.CENTER);

        //---------------------------- Panel Right------------------------------
        pRight.setLayout(null);
        pRight.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.black));
        pRight.setBackground(colorPanel_R);

        pRight.add(btnExit);
        pRight.add(lblLogo);
        pRight.add(tfName);
        pRight.add(pfPass);
        pRight.add(lblName);
        pRight.add(lblPass);
        pRight.add(lblLogin);
        pRight.add(lblDangKy);

        btnExit.setBounds(350, 1, 24, 24);
        btnExit.setBackground(colorPanel_R);
        btnExit.setForeground(colorPanel_R);
        btnExit.setOpaque(false);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                actionBtnClose();
            }
        });

        lblName.setBounds(90, 285, 30, 30);
        lblPass.setBounds(90, 335, 30, 30);

        tfName.setBounds(130, 285, 150, 30);
        tfName.setFont(Arial);
        tfName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
        tfName.setOpaque(false);
        tfName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {
                if (key.getKeyCode() == KeyEvent.VK_ENTER) {
                    pfPass.requestFocus();
                }
            }
        });

        pfPass.setBounds(130, 335, 150, 30);
        pfPass.setFont(Arial);
        pfPass.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
        pfPass.setOpaque(false);
        pfPass.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent key) {
                if (key.getKeyCode() == KeyEvent.VK_ENTER) {
                    actionButton_Login();
                }
            }
        });

        lblLogin.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
        lblLogin.setBounds(60, 415, 260, 40);
        lblLogin.setBackground(new java.awt.Color(241, 196, 15));
        lblLogin.setOpaque(true);
        lblLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                actionButton_Login();
            }

            @Override
            public void mousePressed(MouseEvent me) {
                lblLogin.setBackground(colorPanel_R);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                lblLogin.setBackground(new java.awt.Color(241, 196, 15));
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                lblLogin.setBackground(new java.awt.Color(243, 156, 18));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                lblLogin.setBackground(new java.awt.Color(241, 196, 15));
            }
        });
        lblDangKy.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
        lblDangKy.setBounds(60, 455, 260, 40);
        lblDangKy.setBackground(new java.awt.Color(153, 255, 255));
        lblDangKy.setOpaque(true);
        lblDangKy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                dispose();
                DangKy formdn = null;
                try {
                    formdn = new DangKy();
                } catch (Exception ex) {
                    Logger.getLogger(DangKy.class.getName()).log(Level.SEVERE, null, ex);
                }
                formdn.show();
            }

            @Override
            public void mousePressed(MouseEvent me) {
                lblDangKy.setBackground(colorPanel_R);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                lblDangKy.setBackground(new java.awt.Color(173, 255, 255));
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                lblDangKy.setBackground(new java.awt.Color(173, 255, 255));
            }

            @Override
            public void mouseExited(MouseEvent me) {

            }
        });

        lblLogo.setBounds(90, 60, 200, 200);
        lblLogo.setBackground(Color.BLACK);

        time = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("thời gian chạy");
                for (int i = 0; i < radiobuttons.length; i++) {
                    if (radiobuttons[i].isSelected()) {
                        if (i == radiobuttons.length - 1) {
                            i = -1;
                        }
                        setButtonImageIcon(i + 1);
                        radiobuttons[i + 1].setSelected(true);
                        break;
                    }
                }
            }
        });
        time.start();
        //-----------------------End---------------------------------------------
    }

    public void setButtonImageIcon(int iNumb) {

    }

    /**
     * @param iNumbRadioBtn truyền số lượng các Radio button vào
     * @return JPanel với 1 list RadioButton
     */
    /**
     * Xử lý đăng nhập nếu đăng nhập thành công sẽ chuyển sang trang home
     */
    public Boolean actionButton_Login() {
        TaiKhoanDTO tk = new TaiKhoanDTO();

        Connection con = null;
        CallableStatement stmt = null;

        tk.setStrTenDangNhap(tfName.getText());
        tk.setStrMatKhau(new String(pfPass.getPassword()));

        if (list_TK.kiemTraDangNhap(tk)) {
            System.out.println("Đăng nhập thành công");
            String result = null;
            try {
                con = MyConnection.getConnection();
                stmt = (CallableStatement) con.prepareCall("{call selectmanv(?)}");

                stmt.setString(1, tk.getStrTenDangNhap());

                stmt.executeUpdate();

                //read the OUT parameter now
                result = stmt.getString(1);
                System.out.println("Employee Record Save Success::"+result);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    stmt.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            
            Memory.maNV = result;

            try {

                Home home = new Home();
                home.setVisible(true);
                time.stop();
                this.setVisible(false);
            } catch (Exception e) {
                System.out.println("Lỗi " + e.getMessage());
            }
            return true;
        }

        // nếu đăng nhập sai thì xóa mật khẩu cũ 
        // và điền lại mật khẩu mới
        System.out.println("Đăng nhập thất bại");
        pfPass.setText("");
        pfPass.requestFocus();
        return false;
    }

    /**
     * xừ lý phần thay đổi hình ảnh khi click vào radio button
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        JRadioButton src = (JRadioButton) ae.getSource();
//        System.out.println(src.getName());
        String url = "./src/ShoesManager/images/Poster/Poster_";

        // cắt chuỗi để lấy số dằng sau: radiobutton Number
        String[] srcName = src.getName().split(" ");
        // lấy Number
        int iNumber = Integer.parseInt(srcName[1]) + 1;

        // thêm đuôi vào url
        url += String.valueOf(iNumber) + ".png";

        // hiển thị ảnh theo Number đã lấy
        lblImage.setIcon(new ImageIcon(url));
        System.out.println(url);
    }

    private void actionBtnClose() {
        System.exit(0);
    }

    public static void main(String[] args) throws Exception {
        new Login().setVisible(true);
    }

//    private
    private Font Arial;
    private TaiKhoanBUS list_TK;
    private Color colorPanel_L, colorPanel_R;
    private JPanel pLeft, pRight, pRadioButtons;
    private JButton btnExit;
    private ImageIcon iconClose, iconLogo;
    private JLabel lblLogo, lblName, lblPass, lblLogin, lblDangKy;
    private JLabel lblImage;
    private JTextField tfName;
    private JPasswordField pfPass;
    private JRadioButton[] radiobuttons;
    private ButtonGroup buttongroup;
    private Timer time;
//    private TaiKhoanBUS list_TK;
//    End.

}
