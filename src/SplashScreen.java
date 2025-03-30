public class SplashScreen extends javax.swing.JPanel {
    public SplashScreen() {
        initComponents();
    }

    private void initComponents() {
        menu_bg = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1224, 720));
        setLayout(null);

        menu_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/backgrounds/splash_screen.png")));
        menu_bg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menu_bg.setOpaque(true);
        add(menu_bg);

        menu_bg.setBounds(0, 0, 1224, 720);
    }

    private javax.swing.JLabel menu_bg;
}