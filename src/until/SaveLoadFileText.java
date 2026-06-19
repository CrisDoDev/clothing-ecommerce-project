package until;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SaveLoadFileText {

// ham doc file van ban
public static String loadText(Component parent) {
    JFileChooser chooser = new JFileChooser();
    int kq = chooser.showOpenDialog(parent);
    if (kq != JFileChooser.APPROVE_OPTION) return null;

    File tep = chooser.getSelectedFile();
    System.out.println("[FileIO] dang doc: " + tep.getAbsolutePath());

    StringBuilder noidung = new StringBuilder();
    BufferedReader br = null;
    try {
        br = new BufferedReader(new FileReader(tep, StandardCharsets.UTF_8));
        String dong = "";
        while ((dong = br.readLine()) != null) {
            noidung.append(dong.trim());
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(parent,
                "Khong doc duoc file !\n" + ex.getMessage(),
                "Loi", JOptionPane.ERROR_MESSAGE);
        return null;
    } finally {
        try { if (br != null) br.close(); } catch (Exception ignore) {}
    }
    return noidung.toString();
}

// ham luu noi dung ra file (mo dialog cho chon noi luu)
public static void saveText(Component parent, String text, String defaultName) {
    // check rong truoc
    if (text == null) {
        JOptionPane.showMessageDialog(parent, "Không có nội dung để lưu");
        return;
    }
    if (text.equals("")) {
        JOptionPane.showMessageDialog(parent, "Không có nội dung để lưu");
        return;
    }

    JFileChooser fc = new JFileChooser();
    fc.setSelectedFile(new File(defaultName));
    int kq = fc.showSaveDialog(parent);
    if (kq != JFileChooser.APPROVE_OPTION) return;

    File tep = fc.getSelectedFile();
    try {
        FileWriter fw = new FileWriter(tep, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(text);
        bw.close();
        fw.close();
        System.out.println("[FileIO] đã lưu: " + tep.getAbsolutePath());
        JOptionPane.showMessageDialog(parent,
                "Đã lưu thành công:\n" + tep.getAbsolutePath());
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(parent,
                "Lỗi khi lưu file: " + ex.getMessage(),
                "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}


    public static void main(String[] args) {
        String textToSave = "Nội dung để lưu vào file.";
        String filePath = "output.txt";

        try (java.io.FileWriter writer = new java.io.FileWriter(filePath)) {
            writer.write(textToSave);
            System.out.println("Đã lưu nội dung vào file " + filePath);
        } catch (java.io.IOException e) {
            System.err.println("Lỗi khi lưu: " + e.getMessage());
        }
    }
}