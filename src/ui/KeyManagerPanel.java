package ui;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import rsa.RSA;
import until.SaveLoadFileText;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Savepoint;

// Panel: Sinh va quan ly cap khoa RSA (Public Key + Private Key dang Base64)
public class KeyManagerPanel extends JPanel {

    private final JComboBox<String> cbKeySize = new JComboBox<>(new String[]{"1024", "2048", "4096"});
    private final JTextArea txtPubKey = new JTextArea();
    private final JTextArea txtPrivKey = new JTextArea();

    public KeyManagerPanel() {
        setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(18, 18, 18, 18));

        JPanel north = new JPanel();
        north.setOpaque(false);
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Quản lý khoá RSA");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        north.add(title);

        JLabel subtitle = new JLabel("Tạo cặp khóa Public/Private. Public Key gửi lên Website, Private Key giữ kín tại máy cá nhân.");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitle.setForeground(new Color(96, 109, 123));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        north.add(Box.createVerticalStrut(4));
        north.add(subtitle);
        north.add(Box.createVerticalStrut(12));

        north.add(buildKeySizeSection());
        north.add(Box.createVerticalStrut(8));

        JLabel hint = new JLabel("Gợi ý: 2048 bits là mức keysize khuyên dùng.");
        hint.setFont(new Font("SansSerif", Font.ITALIC, 11));
        hint.setForeground(new Color(120, 130, 150));
        hint.setAlignmentX(Component.LEFT_ALIGNMENT);
        north.add(Box.createVerticalStrut(4));
        north.add(hint);


        
        add(north, BorderLayout.NORTH);
        add(buildKeysPanel(), BorderLayout.CENTER);
    }

    // Section chon do dai khoa va nut sinh khoa
    private JPanel buildKeySizeSection() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        panel.add(new JLabel("RSA Key Size:"));
        cbKeySize.setPreferredSize(new Dimension(80, 28));
        cbKeySize.setSelectedItem("2048");
        panel.add(cbKeySize);
        panel.add(new JLabel("bits"));
        panel.add(Box.createHorizontalStrut(12));

        JButton btnGen = new JButton("Tạo cặp khóa mới");
        btnGen.addActionListener(e -> onGenerateKeyPair());
        panel.add(btnGen);

        return panel;
    }

    // 2 cot Public Key | Private Key, moi cot co Copy / Nhap file / Luu file
    private JPanel buildKeysPanel() {
        JPanel keysPanel = new JPanel(new GridLayout(1, 2, 12, 12));
        keysPanel.setOpaque(false);
        keysPanel.setBorder(new EmptyBorder(8, 0, 0, 0));

        keysPanel.add(buildKeyBox("Public Key", txtPubKey, "public.pub"));
        keysPanel.add(buildKeyBox("Private Key", txtPrivKey, "private.pri"));

        return keysPanel;
    }

    private JPanel buildKeyBox(String title, JTextArea area, String defaultFileName) {
        JPanel box = new JPanel(new BorderLayout(5, 5));
        box.setOpaque(false);
        box.setBorder(BorderFactory.createTitledBorder(title));

        area.setLineWrap(true);
        area.setWrapStyleWord(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        box.add(new JScrollPane(area), BorderLayout.CENTER);

        JPanel tools = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 5));
        tools.setOpaque(false);

        JButton btnCopy = new JButton("Copy Key");
        JButton btnLoad = new JButton("Nhập file");
        JButton btnSave = new JButton("Lưu file");

        btnCopy.addActionListener(e -> copyToClipboard(area.getText(), "Đã copy " + title + "!"));
        btnLoad.addActionListener(e -> {
            String content = SaveLoadFileText.loadText(this);
            if (content != null) area.setText(content);
        });
        btnSave.addActionListener(e -> SaveLoadFileText.saveText(this, area.getText(), defaultFileName));

        tools.add(btnCopy);
        tools.add(btnLoad);
        tools.add(btnSave);
        box.add(tools, BorderLayout.SOUTH);

        return box;
    }

    // Sinh cap khoa RSA va hien thi ra 2 o text
    private void onGenerateKeyPair() {
        
            int keySize = 2048;
        try {
            keySize = Integer.parseInt((String) cbKeySize.getSelectedItem());
        } catch (Exception e) {
            // fallback neu parse loi
            keySize = 2048;
        }
        // System.out.println("KeyManager đang GEn  RSA " + keySize + " bits...");
        // long t1 = System.currentTimeMillis();

        try {
            KeyPair pair = RSA.generateKeyPair(keySize);
            PublicKey pub = pair.getPublic();
            PrivateKey priv = pair.getPrivate();

            String pubB64 = RSA.publicKeyToBase64(pub);
            String privB64 = RSA.privateKeyToBase64(priv);

            txtPubKey.setText(pubB64);
            txtPrivKey.setText(privB64);

            // long t2 = System.currentTimeMillis();
            // System.out.println("KeyManager gen xong, hết " +(t2 - t1) + "ms");

            String tb = "Tạo cặp khóa RSA " + keySize + " bits thành công!\n"
                  + "Hãy lưu Private Key vào máy cá nhân,\n"
                  + "copy Public Key dán lên web.";
            JOptionPane.showMessageDialog(this, tb, "Thành công",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Có lỗi xảy ra: " + ex.getMessage(),"Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    

    private void copyToClipboard(String text, String successMsg) {
        if (text == null || text.isEmpty()) return;
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
        JOptionPane.showMessageDialog(this, successMsg, "Đã copy", JOptionPane.INFORMATION_MESSAGE);
    }
}