package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

// Sidebar trai: tieu de app + danh sach muc nhom theo section
public class SidebarPanel extends JPanel {
    private final List<JButton> itemButtons = new ArrayList<>();

    public SidebarPanel(List<SidebarItem> items, Consumer<SidebarItem> onSelect) {
        setLayout(new java.awt.BorderLayout());
        setBackground(new Color(242, 242, 242));
        setPreferredSize(new Dimension(280, 600));

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);
        content.setBorder(new EmptyBorder(18, 12, 18, 12));

        JLabel title = new JLabel("Signature Tool");
        title.setFont(new Font("Monospaced", Font.BOLD, 30));
        title.setForeground(new Color(30, 30, 30));
        title.setBorder(new EmptyBorder(0, 8, 16, 8));
        content.add(title);

        JSeparator topSep = new JSeparator();
        topSep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        topSep.setForeground(new Color(220, 220, 220));
        content.add(topSep);
        content.add(Box.createVerticalStrut(8));


        JLabel subT = new JLabel("Chữ ký số đơn hàng");
        subT.setFont(new Font("SansSerif", Font.ITALIC, 12));
        subT.setForeground(new Color(130, 130, 130));
        subT.setBorder(new EmptyBorder(0, 10, 14, 8));
        content.add(subT);

        String currentSection = "";
        for (SidebarItem item : items) {
            if (!item.section().equals(currentSection)) {
                if (!currentSection.isEmpty()) {
                    content.add(Box.createVerticalStrut(8));
                    JSeparator sep = new JSeparator();
                    sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
                    content.add(sep);
                    content.add(Box.createVerticalStrut(10));
                }
                currentSection = item.section();
                JLabel sectionLabel = new JLabel(currentSection);
                sectionLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
                sectionLabel.setForeground(new Color(145, 117, 90));
                sectionLabel.setBorder(new EmptyBorder(4, 0, 8, 0));
                sectionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                content.add(sectionLabel);
            }

            JButton btn = createItemButton(item);
            btn.addActionListener(e -> {
                highlightButton(btn);
                onSelect.accept(item);
            });
            itemButtons.add(btn);
            content.add(btn);
            content.add(Box.createVerticalStrut(6));
        }

        content.add(Box.createVerticalGlue());

        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(null);
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, java.awt.BorderLayout.CENTER);
    }

    // Nut cho 1 muc, co cham tron mau de phan biet nhom
    private JButton createItemButton(SidebarItem item) {
        JButton btn = new JButton();
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(8, 8, 8, 8));
        btn.setOpaque(true);
        btn.setBackground(new Color(242, 242, 242));
        btn.setForeground(new Color(60, 60, 60));
        btn.setFont(new Font("SansSerif", Font.PLAIN, 16));

        Color c = item.bulletColor();
        btn.setText("<html><span style='color:rgb(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ")'>&#9679;</span> "
                + item.displayName() + "</html>");
        return btn;
    }

    private void highlightButton(JButton selected) {
        for (JButton btn : itemButtons) {
            btn.setBackground(new Color(242, 242, 242));
        }
        selected.setBackground(new Color(227, 224, 248));
    }
}