package ui;

import rsa.RSA;
import until.SaveLoadFileText;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;

// Panel: Nap Private Key, paste ma bam don hang -> sinh chu ky so SHA256withRSA
public class SigningPanel extends JPanel {

    private final JTextArea txtPrivKey = new JTextArea();
    private final JTextArea txtHash = new JTextArea();
    private final JTextArea txtSignature = new JTextArea();
    private final JLabel statusLabel = new JLabel(" ");

    public SigningPanel() {
        setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout(8, 8));
        setBorder(new EmptyBorder(18, 18, 18, 18));

        JPanel north = new JPanel();
        north.setOpaque(false);
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Ký số đơn hàng — SHA256withRSA");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        north.add(title);

        JLabel subtitle = new JLabel("Nạp Private Key, dán mã băm đơn hàng (order_hash) lấy từ Website, nhấn KÝ ĐƠN HÀNG.");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitle.setForeground(new Color(96, 109, 123));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        north.add(Box.createVerticalStrut(4));
        north.add(subtitle);
        north.add(Box.createVerticalStrut(10));

        add(north, BorderLayout.NORTH);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                buildPrivateKeyBox(), buildSignBox());
        split.setResizeWeight(0.42);
        split.setBorder(null);
        add(split, BorderLayout.CENTER);
    }

    // Cot trai: o nhap / nap Private Key
    private JPanel buildPrivateKeyBox() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder("Private Key"));

        txtPrivKey.setLineWrap(true);
        txtPrivKey.setWrapStyleWord(false);
        txtPrivKey.setFont(new Font("Monospaced", Font.PLAIN, 12));
        panel.add(new JScrollPane(txtPrivKey), BorderLayout.CENTER);

        JPanel tools = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 5));
        tools.setOpaque(false);

        JButton btnLoad = new JButton("Nhập file Private Key…");
        JButton btnClear = new JButton("Xóa");

        btnLoad.addActionListener(e -> {
            String content = SaveLoadFileText.loadText(this);
            if (content != null) txtPrivKey.setText(content);
        });
        btnClear.addActionListener(e -> txtPrivKey.setText(""));

        tools.add(btnLoad);
        tools.add(btnClear);
        panel.add(tools, BorderLayout.SOUTH);

        return panel;
    }

    // Cot phai: o nhap hash + nut ky + o hien chu ky
    private JPanel buildSignBox() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder("Ký đơn hàng"));

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(new EmptyBorder(6, 6, 6, 6));

        JLabel lblHash = new JLabel("Mã băm đơn hàng (order_hash):");
        lblHash.setAlignmentX(Component.LEFT_ALIGNMENT);
        center.add(lblHash);
        center.add(Box.createVerticalStrut(4));

        txtHash.setLineWrap(true);
        txtHash.setWrapStyleWord(false);
        txtHash.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane hashScroll = new JScrollPane(txtHash);
        hashScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        hashScroll.setPreferredSize(new Dimension(0, 110));
        hashScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
        center.add(hashScroll);
        center.add(Box.createVerticalStrut(10));

        JButton btnSign = new JButton("KÝ ĐƠN HÀNG");
        btnSign.setFont(new Font("SansSerif", Font.BOLD, 16));

        btnSign.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnSign.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        btnSign.addActionListener(e -> onSign());
        center.add(btnSign);
        center.add(Box.createVerticalStrut(10));

        JLabel lblSig = new JLabel("Chữ ký số (digital_signature):");
        lblSig.setAlignmentX(Component.LEFT_ALIGNMENT);
        center.add(lblSig);
        center.add(Box.createVerticalStrut(4));

        txtSignature.setLineWrap(true);
        txtSignature.setWrapStyleWord(false);
        txtSignature.setEditable(false);
        txtSignature.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane sigScroll = new JScrollPane(txtSignature);
        sigScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        center.add(sigScroll);
        center.add(Box.createVerticalStrut(6));

        JPanel sigTools = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        sigTools.setOpaque(false);
        sigTools.setAlignmentX(Component.LEFT_ALIGNMENT);
        sigTools.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));

        JButton btnCopy = new JButton("Copy");
        JButton btnSave = new JButton("Lưu file");
        btnCopy.addActionListener(e -> copyToClipboard(txtSignature.getText(), "Đã copy chữ ký số!"));
        btnSave.addActionListener(e -> SaveLoadFileText.saveText(this, txtSignature.getText(), "signature.sig"));
        sigTools.add(btnCopy);
        sigTools.add(btnSave);
        center.add(sigTools);
        center.add(Box.createVerticalStrut(4));

        statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        statusLabel.setForeground(new Color(60, 130, 60));
        center.add(statusLabel);

        panel.add(center, BorderLayout.CENTER);
        return panel;
    }

    // Thuc thi ky so: lay private key Base64 + hash, xuat chu ky Base64
    private void onSign() {
        String privKey = txtPrivKey.getText().trim();
        String hash = txtHash.getText().trim();

        if (privKey.isEmpty()) {
            statusFail("Vui lòng nạp Private Key.");
            JOptionPane.showMessageDialog(this, "Vui lòng nạp Private Key trước khi ký.",
                    "Thiếu dữ liệu", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (hash.isEmpty()) {
            statusFail("Vui lòng dán mã băm đơn hàng.");
            JOptionPane.showMessageDialog(this, "Vui lòng dán mã băm đơn hàng (order_hash).",
                    "Thiếu dữ liệu", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String signature = RSA.sign(hash, privKey);
            txtSignature.setText(signature);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(signature), null);
            statusOk("Ký thành công! Chữ ký đã được sao chép vào clipboard.");
        } catch (Exception ex) {
            statusFail("Lỗi: " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "Lỗi khi ký: " + ex.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void statusOk(String msg) {
        statusLabel.setForeground(new Color(60, 130, 60));
        statusLabel.setText(msg);
    }

    private void statusFail(String msg) {
        statusLabel.setForeground(Color.RED);
        statusLabel.setText(msg);
    }

    

    private void copyToClipboard(String text, String successMsg) {
        if (text == null || text.isEmpty()) return;
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
        JOptionPane.showMessageDialog(this, successMsg, "Đã copy", JOptionPane.INFORMATION_MESSAGE);
    }
}