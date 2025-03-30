public class MainMenu extends javax.swing.JPanel {
    public MainMenu() {
        initComponents();
    }

    private void initComponents() {
        menu_start = new javax.swing.JButton();
        menu_help = new javax.swing.JButton();
        menu_about = new javax.swing.JButton();
        menu_vol = new javax.swing.JButton();
        menu_exit = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        minimize = new javax.swing.JButton();
        menu_title1 = new javax.swing.JLabel();
        menu_title = new javax.swing.JLabel();
        menu_icon = new javax.swing.JLabel();
        menu_bg = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1224, 720));
        setLayout(null);

        menu_start.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/start.png"))); 
        menu_start.setBorder(null);
        menu_start.setBorderPainted(false);
        menu_start.setContentAreaFilled(false);
        menu_start.setFocusPainted(false);
        menu_start.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menu_startMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menu_startMouseExited(evt);
            }
        });
        menu_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_startActionPerformed(evt);
            }
        });
        add(menu_start);
        menu_start.setBounds(200, 240, 240, 70);

        menu_help.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/help.png"))); 
        menu_help.setBorder(null);
        menu_help.setBorderPainted(false);
        menu_help.setContentAreaFilled(false);
        menu_help.setFocusPainted(false);
        menu_help.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menu_helpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menu_helpMouseExited(evt);
            }
        });
        menu_help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_helpActionPerformed(evt);
            }
        });
        add(menu_help);
        menu_help.setBounds(200, 310, 240, 70);

        menu_about.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/about.png"))); 
        menu_about.setBorder(null);
        menu_about.setBorderPainted(false);
        menu_about.setContentAreaFilled(false);
        menu_about.setFocusPainted(false);
        menu_about.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menu_aboutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menu_aboutMouseExited(evt);
            }
        });
        menu_about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_aboutActionPerformed(evt);
            }
        });
        add(menu_about);
        menu_about.setBounds(200, 380, 240, 70);

        menu_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/mute.png"))); 
        menu_vol.setBorder(null);
        menu_vol.setBorderPainted(false);
        menu_vol.setContentAreaFilled(false);
        menu_vol.setFocusPainted(false);
        menu_vol.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menu_vol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menu_volMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menu_volMouseExited(evt);
            }
        });
        menu_vol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_volActionPerformed(evt);
            }
        });
        add(menu_vol);
        menu_vol.setBounds(30, 630, 70, 70);

        menu_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/exit.png"))); 
        menu_exit.setBorder(null);
        menu_exit.setBorderPainted(false);
        menu_exit.setContentAreaFilled(false);
        menu_exit.setFocusPainted(false);
        menu_exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menu_exitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menu_exitMouseExited(evt);
            }
        });
        menu_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_exitActionPerformed(evt);
            }
        });
        add(menu_exit);
        menu_exit.setBounds(200, 450, 240, 70);

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/close.png"))); 
        exit.setBorder(null);
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        exit.setFocusPainted(false);
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitMouseExited(evt);
            }
        });
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        add(exit);
        exit.setBounds(1175, 10, 40, 30);

        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/min.png"))); 
        minimize.setBorder(null);
        minimize.setBorderPainted(false);
        minimize.setContentAreaFilled(false);
        minimize.setFocusPainted(false);
        minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                minimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                minimizeMouseExited(evt);
            }
        });
        minimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizeActionPerformed(evt);
            }
        });
        add(minimize);
        minimize.setBounds(1135, 10, 40, 30);

        menu_title1.setFont(new java.awt.Font("Poppins ExtraBold", 0, 14)); 
        menu_title1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(menu_title1);
        menu_title1.setBounds(340, 170, 390, 30);

        menu_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/backgrounds/background_menu.gif"))); 
        menu_bg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menu_bg.setOpaque(false);
        add(menu_bg);
        menu_bg.setBounds(0, 0, 1224, 720);
    }

    private void menu_helpMouseEntered(java.awt.event.MouseEvent evt) {
        menu_help.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/help_hover.png")));
    }

    private void menu_helpMouseExited(java.awt.event.MouseEvent evt) {
        menu_help.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/help.png")));
    }

    private void menu_helpActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        PageFlow.card.show(PageFlow.mainPanel, "3");
    }

    private void menu_startMouseEntered(java.awt.event.MouseEvent evt) {
        menu_start.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/start_hover.png")));
    }

    private void menu_startMouseExited(java.awt.event.MouseEvent evt) {
        menu_start.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/start.png")));
    }

    private void menu_startActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        PageFlow.card.show(PageFlow.mainPanel, "5");
    }

    private void menu_aboutMouseEntered(java.awt.event.MouseEvent evt) {
        menu_about.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/about_hover.png")));
    }

    private void menu_aboutMouseExited(java.awt.event.MouseEvent evt) {
        menu_about.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/about.png")));
    }

    private void menu_aboutActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        PageFlow.card.show(PageFlow.mainPanel, "4");
    }

    private void menu_exitMouseEntered(java.awt.event.MouseEvent evt) {
        menu_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/exit_hover.png")));
    }

    private void menu_exitMouseExited(java.awt.event.MouseEvent evt) {
        menu_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/exit.png")));
    }

    private void menu_exitActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        System.exit(0);
    }

    private void exitMouseEntered(java.awt.event.MouseEvent evt) {
        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/close_hover.png")));
    }

    private void exitMouseExited(java.awt.event.MouseEvent evt) {
        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/close.png")));
    }

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        System.exit(0);
    }

    private void minimizeMouseEntered(java.awt.event.MouseEvent evt) {
        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/min_hover.png")));
    }

    private void minimizeMouseExited(java.awt.event.MouseEvent evt) {
        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/min.png")));
    }

    private void minimizeActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        PageFlow.mainFrame.setState(java.awt.Frame.ICONIFIED);
    }

    private void menu_volMouseEntered(java.awt.event.MouseEvent evt) {                                      
        if(PageFlow.sound == true) {
            menu_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/mute_hover.png")));
        } else {
            menu_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/muteX_hover.png")));
        }
    }                                     

    private void menu_volMouseExited(java.awt.event.MouseEvent evt) {                                     
        if(PageFlow.sound == true) {
            menu_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/mute.png")));
        } else {
            menu_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/muteX.png")));
        }
    }                                    

    private void menu_volActionPerformed(java.awt.event.ActionEvent evt) {      
        Music.sfx();                                   
        if(PageFlow.sound == true) {
            PageFlow.sound = false;
            Music.bgMusic.pause();
            menu_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/muteX.png")));
        } else {
            PageFlow.sound = true;
            Music.bgMusic.play();
            menu_vol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/mute.png")));
        }
    }       

    private javax.swing.JLabel menu_bg;
    private javax.swing.JButton exit;
    private javax.swing.JButton menu_about;
    private javax.swing.JButton menu_exit;
    private javax.swing.JButton menu_help;
    private javax.swing.JLabel menu_icon;
    private javax.swing.JButton menu_start;
    private javax.swing.JLabel menu_title;
    private javax.swing.JLabel menu_title1;
    private javax.swing.JButton menu_vol;
    private javax.swing.JButton minimize;
}
