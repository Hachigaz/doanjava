package Panel.NhanVien;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuButtonExample extends JFrame {
    private JPopupMenu popupMenu;

    public MenuButtonExample() {
        setTitle("Menu Button Example");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton menuButton = new JButton("Menu");
        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                popupMenu.show(menuButton, 0, menuButton.getHeight());
            }
        });

        JMenuItem logoutItem = new JMenuItem("Đăng xuất");
        logoutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Thực hiện các thao tác khi nhấn "Đăng xuất" ở đây
                JOptionPane.showMessageDialog(null, "Bạn đã đăng xuất");
            }
        });

        JMenuItem changePasswordItem = new JMenuItem("Đổi mật khẩu");
        changePasswordItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Thực hiện các thao tác khi nhấn "Đổi mật khẩu" ở đây
                JOptionPane.showMessageDialog(null, "Bạn muốn đổi mật khẩu");
            }
        });

        popupMenu = new JPopupMenu();
        popupMenu.add(logoutItem);
        popupMenu.add(changePasswordItem);

        setLayout(new FlowLayout());
        add(menuButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MenuButtonExample().setVisible(true);
            }
        });
    }
}