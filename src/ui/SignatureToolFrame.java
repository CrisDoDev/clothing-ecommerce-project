package ui;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

// Cua so chinh: Sidebar trai + workspace phai (CardLayout chuyen giua welcome / key / sign)
public class SignatureToolFrame extends JFrame {

    private static final String CARD_WELCOME = "welcome";
    private static final String CARD_KEY = "key";
    private static final String CARD_SIGN = "sign";

    private final JPanel workspaceContainer = new JPanel(new CardLayout());

    public SignatureToolFrame() {
        setTitle("Signature Tool — Chữ ký số đơn hàng (SHA256withRSA)");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1180, 760);
        setLocationRelativeTo(null);

        workspaceContainer.add(buildWelcomePanel(), CARD_WELCOME);
        workspaceContainer.add(new KeyManagerPanel(), CARD_KEY);
        workspaceContainer.add(new SigningPanel(), CARD_SIGN);

        List<SidebarItem> items = buildSidebarItems();
        SidebarPanel sidebar = new SidebarPanel(items, this::onSidebarSelected);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebar, workspaceContainer);
        split.setDividerLocation(300);
        split.setEnabled(false);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(split, BorderLayout.CENTER);
    }

    // Danh sach muc o sidebar: gom theo section "TOOL CHỮ KÝ SỐ"
    private List<SidebarItem> buildSidebarItems() {
        List<SidebarItem> items = new ArrayList<>();
        Color sigColor = new Color(180, 40, 100);
        items.add(new SidebarItem("TOOL CHỮ KÝ SỐ", "Tạo cặp khóa RSA", sigColor, CARD_KEY));
        items.add(new SidebarItem("TOOL CHỮ KÝ SỐ", "Ký đơn hàng", sigColor, CARD_SIGN));
        return items;
    }

    // Khi nguoi dung chon muc tren sidebar -> chuyen card tuong ung
    private void onSidebarSelected(SidebarItem item) {
        CardLayout cl = (CardLayout) workspaceContainer.getLayout();
        cl.show(workspaceContainer, item.cardKey());
    }

    // Welcome panel hien thi mac dinh khi vua mo app
    private JPanel buildWelcomePanel() {
        JPanel welcome = new JPanel(new GridBagLayout());
        welcome.setBackground(new Color(238, 238, 238));

        JPanel box = new JPanel();
        box.setOpaque(false);
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Signature Tool");
        title.setFont(new Font("SansSerif", Font.BOLD, 40));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subtitle = new JLabel("Chọn chức năng ở thanh bên trái để bắt đầu");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 22));
        subtitle.setForeground(new Color(96, 109, 123));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        box.add(title);
        box.add(javax.swing.Box.createVerticalStrut(8));
        box.add(subtitle);
        welcome.add(box);
        return welcome;
    }
}