import javax.swing.*;
import java.awt.*;

public class KalkulatorSederhana extends JFrame {

    private final JTextField tfangka1 = new JTextField(10);
    private final JTextField tfangka2 = new JTextField(10);
    private final JLabel lblHasil = new JLabel("Hasil: -", SwingConstants.CENTER);

    // Constructor
    public KalkulatorSederhana() {

        super("Kalkulator Sederhana");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel Input Angka
        JPanel panelInput = new JPanel(new GridLayout(2, 2, 8, 8));
        panelInput.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));
        panelInput.add(new JLabel("Angka Pertama:"));
        panelInput.add(tfangka1);
        panelInput.add(new JLabel("Angka Kedua:"));
        panelInput.add(tfangka2);

        // Panel Tombol Operasi
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 5));
        JButton btnTambah = new JButton("+");
        JButton btnKurang = new JButton("-");
        JButton btnKali = new JButton("x");
        JButton btnBagi = new JButton("/");
        JButton btnReset = new JButton("Reset");

        panelTombol.add(btnTambah);
        panelTombol.add(btnKurang);
        panelTombol.add(btnKali);
        panelTombol.add(btnBagi);
        panelTombol.add(btnReset); 

        // Label Hasil
        lblHasil.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblHasil.setBorder(BorderFactory.createEmptyBorder(5, 15, 15, 15));

        add(panelInput, BorderLayout.NORTH);
        add(panelTombol, BorderLayout.CENTER);
        add(lblHasil, BorderLayout.SOUTH);

        // Event Handler tombol
        btnTambah.addActionListener(e -> hitung('+'));
        btnKurang.addActionListener(e -> hitung('-'));
        btnKali.addActionListener(e -> hitung('x'));
        btnBagi.addActionListener(e -> hitung('/'));
        btnReset.addActionListener(e -> reset());

        pack();
        setLocationRelativeTo(null);
    }

    private void hitung(char Operasi) {
        try {
            double a = Double.parseDouble(tfangka1.getText().trim().replace(',', '.'));
            double b = Double.parseDouble(tfangka2.getText().trim().replace(',', '.'));
            double hasil;

            switch (Operasi) {
                case '+':
                    hasil = a + b;
                    break;
                case '-':
                    hasil = a - b;
                    break;
                case 'x':
                    hasil = a * b;
                    break;
                case '/':
                    if (b == 0) {
                        JOptionPane.showMessageDialog(this, "Pembagian dengan nol tidak diperbolehkan!", "Kesalahan",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    hasil = a / b;
                    break;
                default:
                    return;
            }

            String teks = (hasil == (long) hasil) ? String.valueOf((long) hasil) : String.valueOf(hasil);
            lblHasil.setText("Hasil: " + teks);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid pada kedua kolom!", "Input Tidak",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void reset() {
        tfangka1.setText("");
        tfangka2.setText("");
        lblHasil.setText("Hasil: -");
        tfangka1.requestFocus();
    }

    public static void main(String[] args) {
        // Swing sebaiknya dijalankan di Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new KalkulatorSederhana().setVisible(true));
    }
}