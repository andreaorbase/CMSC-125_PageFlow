public class Help extends javax.swing.JPanel {
    public Help() {
        initComponents();
    }

    private void initComponents() {
        exit = new javax.swing.JButton();
        minimize = new javax.swing.JButton();
        help_return = new javax.swing.JButton();
        help_bg = new javax.swing.JLabel();

        help_fifo = new javax.swing.JButton();
        help_lru = new javax.swing.JButton();
        help_opt = new javax.swing.JButton();
        help_sc = new javax.swing.JButton();
        help_esc = new javax.swing.JButton();
        help_lfu = new javax.swing.JButton();
        help_mfu = new javax.swing.JButton();
        
        algo_info = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1224, 720));
        setMinimumSize(new java.awt.Dimension(1224, 720));
        setPreferredSize(new java.awt.Dimension(1224, 720));
        setLayout(null);

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

        help_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/return.png"))); 
        help_return.setBorder(null);
        help_return.setBorderPainted(false);
        help_return.setContentAreaFilled(false);
        help_return.setFocusPainted(false);
        help_return.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                help_returnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                help_returnMouseExited(evt);
            }
        });
        help_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                help_returnActionPerformed(evt);
            }
        });
        add(help_return);
        help_return.setBounds(1090, 600, 80, 80);


        help_fifo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/FIFO.png"))); 
        help_fifo.setBorder(null);
        help_fifo.setBorderPainted(false);
        help_fifo.setContentAreaFilled(false);
        help_fifo.setFocusPainted(false);
        help_fifo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        help_fifo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                help_fifoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                help_fifoMouseExited(evt);
            }
        });
        add(help_fifo);
        help_fifo.setBounds(50, 500, 282, 33);

        help_lru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/LRU.png"))); 
        help_lru.setBorder(null);
        help_lru.setBorderPainted(false);
        help_lru.setContentAreaFilled(false);
        help_lru.setFocusPainted(false);
        help_lru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        help_lru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                help_lruMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                help_lruMouseExited(evt);
            }
        });
        add(help_lru);
        help_lru.setBounds(50, 540, 282, 33);

        help_opt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/OPT.png"))); 
        help_opt.setBorder(null);
        help_opt.setBorderPainted(false);
        help_opt.setContentAreaFilled(false);
        help_opt.setFocusPainted(false);
        help_opt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        help_opt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                help_optMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                help_optMouseExited(evt);
            }
        });
        add(help_opt);
        help_opt.setBounds(50, 580, 282, 33);


        help_sc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/SC.png"))); 
        help_sc.setBorder(null);
        help_sc.setBorderPainted(false);
        help_sc.setContentAreaFilled(false);
        help_sc.setFocusPainted(false);
        help_sc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        help_sc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                help_scMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                help_scMouseExited(evt);
            }
        });
        add(help_sc);
        help_sc.setBounds(50, 620, 282, 33);


        help_esc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/ESC.png"))); 
        help_esc.setBorder(null);
        help_esc.setBorderPainted(false);
        help_esc.setContentAreaFilled(false);
        help_esc.setFocusPainted(false);
        help_esc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        help_esc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                help_escMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                help_escMouseExited(evt);
            }
        });
        add(help_esc);
        help_esc.setBounds(350, 500, 282, 33);


        help_lfu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/LFU.png"))); 
        help_lfu.setBorder(null);
        help_lfu.setBorderPainted(false);
        help_lfu.setContentAreaFilled(false);
        help_lfu.setFocusPainted(false);
        help_lfu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        help_lfu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                help_lfuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                help_lfuMouseExited(evt);
            }
        });
        add(help_lfu);
        help_lfu.setBounds(350, 540, 282, 33);


        help_mfu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/MFU.png"))); 
        help_mfu.setBorder(null);
        help_mfu.setBorderPainted(false);
        help_mfu.setContentAreaFilled(false);
        help_mfu.setFocusPainted(false);
        help_mfu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        help_mfu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                help_mfuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                help_mfuMouseExited(evt);
            }
        });
        add(help_mfu);
        help_mfu.setBounds(350, 580, 282, 33);


        // INFORMATION
        algo_info.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        algo_info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/algo_description.png"))); 
        algo_info.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(algo_info);
        algo_info.setBounds(700, 500, 383, 165);

        help_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/backgrounds/help.png"))); 
        help_bg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        help_bg.setOpaque(true);
        add(help_bg);
        help_bg.setBounds(0, 0, 1224, 720);
    }

    private void help_returnMouseEntered(java.awt.event.MouseEvent evt) {
        help_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/return_hover.png")));
    }

    private void help_returnMouseExited(java.awt.event.MouseEvent evt) {
        help_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/return.png")));
    }

    private void help_returnActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        PageFlow.card.show(PageFlow.mainPanel, "2");
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

    // ALGORITHMS
    private void help_fifoMouseEntered(java.awt.event.MouseEvent evt) {
        help_fifo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/FIFO_hover.png")));
        algo_info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/FIFO_info.png")));
    }

    private void help_fifoMouseExited(java.awt.event.MouseEvent evt) {
        help_fifo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/FIFO.png")));
        algo_info.setIcon(null); 
    }


    private void help_lruMouseEntered(java.awt.event.MouseEvent evt) {
        help_lru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/LRU_hover.png")));
        algo_info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/LRU_info.png")));
    }

    private void help_lruMouseExited(java.awt.event.MouseEvent evt) {
        help_lru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/LRU.png")));
        algo_info.setIcon(null); 
    }


    private void help_optMouseEntered(java.awt.event.MouseEvent evt) {
        help_opt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/OPT_hover.png")));
        algo_info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/OPT_info.png")));
    }

    private void help_optMouseExited(java.awt.event.MouseEvent evt) {
        help_opt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/OPT.png")));
        algo_info.setIcon(null); 
    }


    private void help_scMouseEntered(java.awt.event.MouseEvent evt) {
        help_sc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/SC_hover.png")));
        algo_info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/SC_info.png")));
    }

    private void help_scMouseExited(java.awt.event.MouseEvent evt) {
        help_sc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/SC.png")));
        algo_info.setIcon(null); 
    }

    private void help_escMouseEntered(java.awt.event.MouseEvent evt) {
        help_esc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/ESC_hover.png")));
        algo_info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/ESC_info.png")));
    }

    private void help_escMouseExited(java.awt.event.MouseEvent evt) {
        help_esc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/ESC.png")));
        algo_info.setIcon(null); 
    }

    private void help_lfuMouseEntered(java.awt.event.MouseEvent evt) {
        help_lfu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/LFU_hover.png")));
        algo_info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/LFU_info.png")));
    }

    private void help_lfuMouseExited(java.awt.event.MouseEvent evt) {
        help_lfu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/LFU.png")));
        algo_info.setIcon(null); 
    }

    private void help_mfuMouseEntered(java.awt.event.MouseEvent evt) {
        help_mfu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/MFU_hover.png")));
        algo_info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/MFU_info.png")));
    }

    private void help_mfuMouseExited(java.awt.event.MouseEvent evt) {
        help_mfu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_info/MFU.png")));
        algo_info.setIcon(null); 
    }
    



    private javax.swing.JButton exit;
    private javax.swing.JLabel help_bg;
    private javax.swing.JButton help_return;
    private javax.swing.JButton minimize;

    private javax.swing.JButton help_fifo;
    private javax.swing.JButton help_lru;
    private javax.swing.JButton help_opt;
    private javax.swing.JButton help_sc;
    private javax.swing.JButton help_esc;
    private javax.swing.JButton help_lfu;
    private javax.swing.JButton help_mfu;
    private javax.swing.JLabel algo_info;
}
