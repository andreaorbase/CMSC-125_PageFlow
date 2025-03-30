import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.Timer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

class CenterTextRenderer extends DefaultTableCellRenderer {

    public int desiredRow = 0;
    public int desiredColumn = 0;
    int rows = 0;

    public CenterTextRenderer(int desiredRow, int desiredColumn, int rows) {
        this.desiredRow = desiredRow;
        this.desiredColumn = desiredColumn;
        this.rows = rows;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {

        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        // final Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY);
        setHorizontalAlignment(SwingConstants.CENTER);
        // cellComponent.setBackground(Color.gray);
        // ((JComponent) cellComponent).setBorder(border);

        if (desiredColumn == 0 && desiredRow == 0) {
            cellComponent.setBackground(table.getBackground());
        }

        if (row == desiredRow && column == desiredColumn) {
            // Set the background color of the cell
            cellComponent.setBackground(Color.YELLOW);
        } else {
            // Reset the background color to the default
            cellComponent.setBackground(table.getBackground());
        }
        if (row == rows) {
            ((JComponent) cellComponent).setBorder(null);
        }

        return cellComponent;
    }
}

public class IOPanel extends javax.swing.JPanel {

    public int desiredRow;
    public int desiredColumn;
    boolean flag = false;
    public int Selected;
    public double default_speed = 1;
    public double current_speed = default_speed;

    public IOPanel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        initComponents();
    }

    public static void saveTableAsImage(JTable table, String filename) {
        Dimension tableSize = table.getSize();
        BufferedImage image = new BufferedImage(tableSize.width, tableSize.height + 40, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        graphics.setColor(table.getBackground());
        graphics.fillRect(0, 0, tableSize.width, tableSize.height + 40);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.print(graphics);

        int headerHeight = tableHeader.getHeight();
        graphics.translate(0, headerHeight);
        table.print(graphics);
        graphics.dispose();

        try {
            ImageIO.write(image, "png", new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reset() {

        if (flag == true) {
            timer.stop();
        }
        default_speed = 1;
        current_speed = default_speed;
        default_frames = 3;
        current_frames = default_frames;
        removeAll();
        initComponents();
        PageFlow.fullOutput.reset();
    }

    public void adjustScrollBar(javax.swing.JComboBox<String> box) {
        if (box.getItemCount() == 0)
            return;
        Object comp = box.getUI().getAccessibleChild(box, 0);
        if (!(comp instanceof JPopupMenu)) {
            return;
        }
        JPopupMenu popup = (JPopupMenu) comp;
        JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);
        scrollPane.setHorizontalScrollBar(new JScrollBar(JScrollBar.HORIZONTAL));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    public void initComponents() {
        length_upper = 40;
        length_lower = 10;
        lower_value = 0;
        upper_value = 20;
        frame_lower = 3;
        frame_upper = 10;

        fault_value = new javax.swing.JLabel();
        results_table = new javax.swing.JTable();
        exit = new javax.swing.JButton();
        minimize = new javax.swing.JButton();
        io_save = new javax.swing.JButton();
        io_return = new javax.swing.JButton();
        io_simulate = new javax.swing.JButton();
        io_simulateAll = new javax.swing.JButton();
        io_frames_add = new javax.swing.JButton();
        io_frames_minus = new javax.swing.JButton();
        io_speed_add = new javax.swing.JButton();
        io_speed_minus = new javax.swing.JButton();
        io_import = new javax.swing.JButton();
        io_random = new javax.swing.JButton();
        io_page_label = new javax.swing.JLabel();
        io_page_bg = new javax.swing.JLabel();
        io_timer_label = new javax.swing.JLabel();
        io_timer_bg = new javax.swing.JLabel();
        io_output_scroll = new javax.swing.JScrollPane();
        io_speed_value = new javax.swing.JLabel();
        io_speed_label = new javax.swing.JLabel();
        io_speed_bg = new javax.swing.JLabel();
        io_reference_input = new javax.swing.JTextField();
        io_reference_label = new javax.swing.JLabel();
        io_reference_bg = new javax.swing.JLabel();
        io_algorithm_select = new javax.swing.JComboBox<>();
        io_algorithm_label = new javax.swing.JLabel();
        io_algorithm_bg = new javax.swing.JLabel();
        io_frames_value = new javax.swing.JLabel();
        io_frames_label = new javax.swing.JLabel();
        io_frames_bg = new javax.swing.JLabel();
        io_output_bg = new javax.swing.JLabel();
        io_bg = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1224, 720));
        setLayout(null);

        fault_value.setFont(new java.awt.Font("Poppins ExtraBold", 0, 16));
        fault_value.setText(" -");
        fault_value.setBounds(1117, 385, 200, 80);
        fault_value.setBackground(Color.cyan);

        add(fault_value);

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

        io_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/save.png")));
        io_save.setBorder(null);
        io_save.setBorderPainted(false);
        io_save.setEnabled(false);
        io_save.setContentAreaFilled(false);
        io_save.setFocusPainted(false);
        io_save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                io_saveMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                io_saveMouseExited(evt);
            }
        });
        io_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                io_saveActionPerformed(evt);
            }
        });
        add(io_save);
        io_save.setBounds(1090, 538, 80, 80);

        io_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/return.png")));
        io_return.setBorder(null);
        io_return.setBorderPainted(false);
        io_return.setContentAreaFilled(false);
        io_return.setFocusPainted(false);
        io_return.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                io_returnMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                io_returnMouseExited(evt);
            }
        });
        io_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                io_returnActionPerformed(evt);
            }
        });
        add(io_return);
        io_return.setBounds(1090, 610, 80, 80);

        io_simulate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/simulate.png")));
        io_simulate.setBorder(null);
        io_simulate.setBorderPainted(false);
        io_simulate.setContentAreaFilled(false);
        io_simulate.setFocusPainted(false);
        io_simulate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                io_simulateMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                io_simulateMouseExited(evt);
            }
        });
        io_simulate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                io_simulateActionPerformed(evt);
            }
        });
        add(io_simulate);
        io_simulate.setBounds(435, 225, 180, 60);

        io_simulateAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/simulateAll.png")));
        io_simulateAll.setBorder(null);
        io_simulateAll.setBorderPainted(false);
        io_simulateAll.setContentAreaFilled(false);
        io_simulateAll.setFocusPainted(false);
        io_simulateAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                io_simulateAllMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                io_simulateAllMouseExited(evt);
            }
        });
        io_simulateAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                io_simulateAllActionPerformed(evt);
            }
        });
        add(io_simulateAll);
        io_simulateAll.setBounds(610, 225, 210, 60);

        io_frames_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/add.png")));
        io_frames_add.setBorder(null);
        io_frames_add.setBorderPainted(false);
        io_frames_add.setContentAreaFilled(false);
        io_frames_add.setFocusPainted(false);
        io_frames_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                io_frames_addMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                io_frames_addMouseExited(evt);
            }
        });
        io_frames_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                io_frames_addActionPerformed(evt);
            }
        });
        add(io_frames_add);
        io_frames_add.setBounds(1115, 60, 40, 50);

        io_frames_minus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/minus.png")));
        io_frames_minus.setBorder(null);
        io_frames_minus.setBorderPainted(false);
        io_frames_minus.setContentAreaFilled(false);
        io_frames_minus.setFocusPainted(false);
        io_frames_minus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                io_frames_minusMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                io_frames_minusMouseExited(evt);
            }
        });
        io_frames_minus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                io_frames_minusActionPerformed(evt);
            }
        });
        add(io_frames_minus);
        io_frames_minus.setBounds(1057, 60, 40, 50);

        io_speed_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/add.png")));
        io_speed_add.setBorder(null);
        io_speed_add.setBorderPainted(false);
        io_speed_add.setContentAreaFilled(false);
        io_speed_add.setFocusPainted(false);
        io_speed_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                io_speed_addMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                io_speed_addMouseExited(evt);
            }
        });
        io_speed_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                io_speed_addActionPerformed(evt);
            }
        });
        add(io_speed_add);
        io_speed_add.setBounds(931, 176, 40, 50);

        io_speed_minus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/minus.png")));
        io_speed_minus.setBorder(null);
        io_speed_minus.setBorderPainted(false);
        io_speed_minus.setContentAreaFilled(false);
        io_speed_minus.setFocusPainted(false);
        io_speed_minus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                io_speed_minusMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                io_speed_minusMouseExited(evt);
            }
        });
        io_speed_minus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                io_speed_minusActionPerformed(evt);
            }
        });
        add(io_speed_minus);
        io_speed_minus.setBounds(870, 176, 40, 50);

        io_import.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/import.png")));
        io_import.setBorder(null);
        io_import.setBorderPainted(false);
        io_import.setContentAreaFilled(false);
        io_import.setFocusPainted(false);
        io_import.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                io_importMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                io_importMouseExited(evt);
            }
        });
        io_import.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                io_importActionPerformed(evt);
            }
        });
        add(io_import);
        io_import.setBounds(490, 105, 120, 40);

        io_random.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/random.png")));
        io_random.setBorder(null);
        io_random.setBorderPainted(false);
        io_random.setContentAreaFilled(false);
        io_random.setFocusPainted(false);
        io_random.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                io_randomMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                io_randomMouseExited(evt);
            }
        });
        io_random.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                io_randomActionPerformed(evt);
            }
        });
        add(io_random);
        io_random.setBounds(610, 105, 130, 40);

        io_page_label.setFont(new java.awt.Font("Poppins ExtraBold", 0, 16)); 
        io_page_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        io_page_label.setText("0");
        add(io_page_label);
        io_page_label.setBounds(1095, 328, 60, 30);

        io_timer_label.setFont(new java.awt.Font("Poppins ExtraBold", 0, 16));
        io_timer_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        io_timer_label.setText("0");
        add(io_timer_label);
        io_timer_label.setBounds(1095, 475, 60, 30);

        io_output_scroll.setBorder(null);
        io_output_scroll.getViewport().setBackground(Color.WHITE);
        io_output_scroll.setOpaque(false);
        add(io_output_scroll);
        io_output_scroll.setBounds(60, 360, 12 * 80, 265);

        io_speed_value.setFont(new java.awt.Font("Poppins ExtraBold", 0, 16));
        io_speed_value.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        io_speed_value.setText(String.valueOf(current_speed) + "x");
        add(io_speed_value);
        io_speed_value.setBounds(900, 186, 40, 30);

        io_speed_label.setFont(new java.awt.Font("Poppins ExtraBold", 0, 16));
        io_speed_label.setForeground(new java.awt.Color(255, 255, 255));
        io_speed_label.setText("Timer Speed");
        add(io_speed_label);
        io_speed_label.setBounds(765, 175, 130, 50);

        io_speed_bg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        io_speed_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/timer_speed.png")));
        io_speed_bg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(io_speed_bg);
        io_speed_bg.setBounds(750, 175, 230, 50);

        io_reference_input.setFont(new java.awt.Font("Poppins SemiBold", 0, 14));
        io_reference_input.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        io_reference_input.setBorder(null);
        io_reference_input.setOpaque(false);
        add(io_reference_input);
        io_reference_input.setBounds(230, 71, 730, 30);

        io_reference_label.setFont(new java.awt.Font("Poppins ExtraBold", 0, 16));
        io_reference_label.setForeground(new java.awt.Color(255, 255, 255));
        io_reference_label.setText("Page Reference");
        add(io_reference_label);
        io_reference_label.setBounds(75, 60, 140, 50);

        io_reference_bg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        io_reference_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/reference.png")));
        io_reference_bg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(io_reference_bg);
        io_reference_bg.setBounds(60, 60, 920, 50);

        io_algorithm_select.setFont(new java.awt.Font("Poppins Regular", 0, 12));
        io_algorithm_select.setAutoscrolls(getAutoscrolls());
        io_algorithm_select.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "First In First Out",
                "Least Recently Used", "Optimal Page Replacement", "Second Chance Algorithm",
                "Enhanced Second Chance Algorithm", "Least Frequently Used", "Most Frequently Used" }));
        io_algorithm_select.setBorder(null);
        io_algorithm_select.setFocusable(false);
        io_algorithm_select.setOpaque(false);
        adjustScrollBar(io_algorithm_select);
        add(io_algorithm_select);
        io_algorithm_select.setBounds(480, 185, 250, 30);

        io_algorithm_label.setFont(new java.awt.Font("Poppins ExtraBold", 0, 16));
        io_algorithm_label.setForeground(new java.awt.Color(255, 255, 255));
        io_algorithm_label.setText("Page Replacement");
        add(io_algorithm_label);
        io_algorithm_label.setBounds(302, 175, 260, 50);

        io_algorithm_bg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        io_algorithm_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm.png")));
        io_algorithm_bg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(io_algorithm_bg);
        io_algorithm_bg.setBounds(280, 175, 460, 50);

        io_frames_value.setFont(new java.awt.Font("Poppins ExtraBold", 0, 16));
        io_frames_value.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        io_frames_value.setText(String.valueOf(default_frames));
        add(io_frames_value);
        io_frames_value.setBounds(1082, 71, 50, 30);

        io_frames_label.setFont(new java.awt.Font("Poppins ExtraBold", 0, 16));
        io_frames_label.setForeground(new java.awt.Color(255, 255, 255));
        io_frames_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        io_frames_label.setText("Frames");
        add(io_frames_label);
        io_frames_label.setBounds(980, 60, 80, 50);

        io_frames_bg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        io_frames_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/frames.png")));
        io_frames_bg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(io_frames_bg);
        io_frames_bg.setBounds(975, 60, 188, 50);

        io_output_bg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        io_output_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_bg.png")));
        io_output_bg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(io_output_bg);
        io_output_bg.setBounds(0, 290, 1080, 430);

        io_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/backgrounds/background.png")));
        io_bg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        io_bg.setOpaque(true);
        add(io_bg);
        io_bg.setBounds(0, 0, 1224, 720);

        results_table.setFont(new java.awt.Font("Poppins Regular", 0, 11)); 
        results_table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null },
                        { null, null, null },
                        { null, null, null }
                },
                new String[] {
                        " ", " ", " "
                }) {
            Class[] types = new Class[] {
                    java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class,
                    java.lang.Integer.class, java.lang.Integer.class,
                    java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class,
                    java.lang.Integer.class, java.lang.Integer.class,
                    java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class,
                    java.lang.Integer.class, java.lang.Integer.class,
                    java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class,
                    java.lang.Integer.class, java.lang.Integer.class,
                    java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class,
                    java.lang.Integer.class, java.lang.Integer.class,
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });

    }

    public void create_table(int[] main_Array, int selected_algo, int current_frames) {
        results_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TableCellRenderer cellRenderer = new CenterTextRenderer(-1, -1, current_frames);
        results_table.setDefaultRenderer(Object.class, cellRenderer);

        Object[][] data = new Object[0][0];
        String[] header = new String[main_Array.length];

        for (int i = 0; i < main_Array.length; i++) {
            header[i] = String.valueOf(main_Array[i]);
        }

        results_table.setModel(new javax.swing.table.DefaultTableModel(data, header));

        DefaultTableModel results_model = (DefaultTableModel) results_table.getModel();
        results_model.setRowCount(current_frames + 1);
        results_model.setColumnCount(main_Array.length);

        results_table.setFillsViewportHeight(true);
        results_table.setGridColor(new java.awt.Color(255, 255, 255));
        results_table.setSelectionBackground(new java.awt.Color(211, 211, 211));
        results_table.setBackground(Color.WHITE);
        results_table.setInheritsPopupMenu(true);
        results_table.setIntercellSpacing(new java.awt.Dimension(35, 1));
        results_table.setRowHeight(40);
        results_table.getTableHeader().setReorderingAllowed(false);
        results_table.getTableHeader().setResizingAllowed(false);
        results_table.getTableHeader().setBackground(Color.WHITE);
        results_table.setBorder(null);

        JTableHeader results_header = results_table.getTableHeader();
        TableCellRenderer renderer = results_header.getDefaultRenderer();
        ((JLabel) renderer).setHorizontalAlignment(SwingConstants.CENTER);

        if (main_Array.length < 12) {
            io_output_scroll.setBounds(60, 360, main_Array.length * 79, 265);
        } else {
            io_output_scroll.setBounds(60, 360, 12 * 79, 265);
        }
        io_output_scroll.setViewportView(results_table);
    }

    public void exitMouseEntered(java.awt.event.MouseEvent evt) {
        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/close_hover.png")));
    }

    public void exitMouseExited(java.awt.event.MouseEvent evt) {
        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/close.png")));
    }

    public void exitActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        System.exit(0);
    }

    public void minimizeMouseEntered(java.awt.event.MouseEvent evt) {
        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/min_hover.png")));
    }

    public void minimizeMouseExited(java.awt.event.MouseEvent evt) {
        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/min.png")));
    }

    public void minimizeActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        PageFlow.mainFrame.setState(java.awt.Frame.ICONIFIED);
    }

    public void io_returnMouseEntered(java.awt.event.MouseEvent evt) {
        io_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/return_hover.png")));
    }

    public void io_returnMouseExited(java.awt.event.MouseEvent evt) {
        io_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/return.png")));
    }

    public void io_returnActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        reset();
        PageFlow.card.show(PageFlow.mainPanel, "2");
    }

    public void io_saveMouseEntered(java.awt.event.MouseEvent evt) {
        io_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/save_hover.png")));
    }

    public void io_saveMouseExited(java.awt.event.MouseEvent evt) {
        io_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/save.png")));
    }

    public void io_saveActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(".png", "png");
        fileChooser.setFileFilter(imageFilter);
        FileNameExtensionFilter pdfFilter = new FileNameExtensionFilter(".pdf", "pdf");
        fileChooser.setFileFilter(pdfFilter);

        int userSelection = fileChooser.showSaveDialog(PageFlow.mainFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            FileNameExtensionFilter selectedFilter = (FileNameExtensionFilter) fileChooser.getFileFilter();

            // Process the selected file filter
            String filterDescription = selectedFilter.getDescription();

            

            if (filterDescription.equals(".pdf")) {
                int fileNumber = 1;

                String fileName = filterDescription + ".png";
                File file = new File(fileName);

                // Check if the file already exists
                while (file.exists()) {
                    fileNumber++; // Increment the file number

                    // Generate a new file name
                    fileName = filterDescription + String.format("%03d", fileNumber) + ".png";
                    file = new File(fileName);
                }
                saveTableAsImage(results_table, fileName);

                String pdfPath = fileToSave.getAbsolutePath() + ".pdf";

                try {
                    BufferedImage bufferedImage = ImageIO.read(new File(fileName));
                    int imageWidth = bufferedImage.getWidth() + 70;
                    int imageHeight = bufferedImage.getHeight() + 70;

                    Document document = new Document();
                    document.setPageSize(new com.itextpdf.text.Rectangle(imageWidth, imageHeight));
                    PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
                    document.open();

                    Image image = Image.getInstance(bufferedImage, null);
                    document.add(image);

                    document.close();
                    
                    File imageFile = new File(fileName);
                    imageFile.delete();

                } catch (IOException | DocumentException e) {
                    e.printStackTrace();
                }

            }
            else if(filterDescription.equals(".png")){

                int fileNumber = 1;

                String fileName = fileToSave.getAbsolutePath() + ".png";
                File file = new File(fileName);

                // Check if the file already exists
                while (file.exists()) {
                    fileNumber++; // Increment the file number

                    // Generate a new file name
                    fileName = fileToSave.getAbsolutePath() + String.format("%03d", fileNumber) + ".png";
                    file = new File(fileName);
                }
                saveTableAsImage(results_table, fileName);

            }
        }
    }

    public void io_randomMouseEntered(java.awt.event.MouseEvent evt) {
        io_random.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/random_hover.png")));
    }

    public void io_randomMouseExited(java.awt.event.MouseEvent evt) {
        io_random.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/random.png")));
    }

    public void io_randomActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        seed = (int) System.currentTimeMillis();
        Random rand = new Random(seed);

        int random_length = rand.nextInt(length_upper - length_lower) + length_lower;
        int random_frame = rand.nextInt(frame_upper - frame_lower) + frame_lower;
        int random_value = rand.nextInt(upper_value - lower_value) + lower_value;

        
        
        

        current_frames = random_frame;

        io_frames_value.setText(String.valueOf(current_frames));

        random_Array = new int[random_length];

        for (int i = 0; i < random_length; i++) {
            random_Array[i] = random_value;
            random_value = rand.nextInt(upper_value - lower_value) + lower_value;

        }
        String array_string = "";
        for (int i = 0; i < random_Array.length; i++) {
            if (i + 1 < random_Array.length) {
                array_string = array_string + String.valueOf(random_Array[i]) + " ";
            } else {
                array_string = array_string + String.valueOf(random_Array[i]) + "";
            }
        }

        // 

        io_reference_input.setText(array_string);
        main_Array = new int[random_Array.length];
        main_Array = random_Array.clone();
    }

    public void io_importMouseEntered(java.awt.event.MouseEvent evt) {
        io_import.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/import_hover.png")));
    }

    public void io_importMouseExited(java.awt.event.MouseEvent evt) {
        io_import.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/import.png")));
    }

    public void io_importActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        

        io_reference_input.setText("");

        final JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(null);
        File file = fc.getSelectedFile();

        try (Scanner read = new Scanner(file)) {
            String frames = " ";
            String algo = " ";

            for (int i = 0; i < 4; i++) {
                if (i < 2) {
                    frames = read.next();
                } else {
                    algo = read.next();
                }
            }
            read.next();
            import_ArrayList = new ArrayList<Integer>();
            while (read.hasNext()) {
                import_ArrayList.add(read.nextInt());
            }

            

            main_Array = new int[import_ArrayList.size()];

            for (int i = 0; i < import_ArrayList.size(); i++) {
                main_Array[i] = import_ArrayList.get(i);
            }

            if (main_Array.length > 40 || Integer.parseInt(frames) > 10) {
                JOptionPane.showMessageDialog(null, "Invalid File.",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            current_frames = Integer.parseInt(frames);
            io_frames_value.setText(String.valueOf(current_frames));

            for (int i = 0; i < main_Array.length; i++) {
                if (i + 1 < main_Array.length) {
                    io_reference_input.setText(io_reference_input.getText() + main_Array[i] + " ");
                } else {
                    io_reference_input.setText(io_reference_input.getText() + main_Array[i] + "");
                }
            }

            Selected = checkSelected(algo);
            

            io_algorithm_select.setSelectedIndex(Selected);
            io_random.setEnabled(false);
            io_frames_add.setEnabled(false);
            io_frames_minus.setEnabled(false);
            io_algorithm_select.setEditable(false);
            io_reference_input.setEditable(false);
        } catch (Exception e) {
            
        }
    }

    private int checkSelected(String algo) {
        int selected = 0;
        if (algo.equals("FIFO")) {
            selected = 0;
        } else if (algo.equals("LRU")) {
            selected = 1;
        } else if (algo.equals("OPT")) {
            selected = 2;
        } else if (algo.equals("SC")) {
            selected = 3;
        } else if (algo.equals("ESCA")) {
            selected = 4;
        } else if (algo.equals("LFU")) {
            selected = 5;
        } else if (algo.equals("MFU")) {
            selected = 6;
        }
        return selected;
    }

    public void io_simulateAllMouseEntered(java.awt.event.MouseEvent evt) {
        io_simulateAll
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/simulateAll_hover.png")));
    }

    public void io_simulateAllMouseExited(java.awt.event.MouseEvent evt) {
        io_simulateAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/simulateAll.png")));
    }

    public void io_simulateAllActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();

        int int_values = 0;
        String values = io_reference_input.getText();
        values.trim();
        

        if (io_reference_input.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Page Reference Cannot be Empty!",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (values.charAt(values.length() - 1) == ' ') {
            
            JOptionPane.showMessageDialog(null, "Page Reference contains invalid input/s.",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (int i = 0; i < values.length(); i++) {
            if (values.charAt(i) == ' ') {
                int_values++;
            } else {
                continue;
            }
        }
        int_values = int_values + 1;

        
        try (Scanner read = new Scanner(values)) {
            flag = true;
            // main_array loading - contains arrays
            main_Array = new int[int_values];
            for (int i = 0; i < int_values; i++) {
                main_Array[i] = read.nextInt();
                if (main_Array[i] > 20) {
                    JOptionPane.showMessageDialog(null, "Page Reference contains value/s exceeding 20.",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            flag = true;
            PageFlow.fullOutput.results_model.setColumnCount(main_Array.length);
            PageFlow.fullOutput.create_tables();
            PageFlow.card.show(PageFlow.mainPanel, "6");
        }
        catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Page Reference input contains invalid characters.",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void io_simulateMouseEntered(java.awt.event.MouseEvent evt) {
        io_simulate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/simulate_hover.png")));
    }

    public void io_simulateMouseExited(java.awt.event.MouseEvent evt) {
        io_simulate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/simulate.png")));
    }

    public void io_simulateActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();

        io_import.setEnabled(false);
        io_random.setEnabled(false);
        io_frames_add.setEnabled(false);
        io_frames_minus.setEnabled(false);
        io_reference_input.setEditable(false);
        io_speed_add.setEnabled(false);
        io_speed_minus.setEnabled(false);
        io_algorithm_select.setEnabled(false);
        io_simulate.setEnabled(false);
        io_simulateAll.setEnabled(false);
        results_table.setEnabled(false);

        int int_values = 0;
        String values = io_reference_input.getText();
        values.trim();

        if (io_reference_input.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Page Reference Cannot be Empty!",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (values.charAt(values.length() - 1) == ' ') {
            JOptionPane.showMessageDialog(null, "Page Reference contains invalid input/s.",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (int i = 0; i < values.length(); i++) {
            if (values.charAt(i) == ' ') {
                int_values++;
            } else {
                continue;
            }
        }
        int_values = int_values + 1;
        
        try (Scanner read = new Scanner(values)) {
            flag = true;
            // main_array loading - contains arrays
            main_Array = new int[int_values];
            for (int i = 0; i < int_values; i++) {
                main_Array[i] = read.nextInt();
                if (main_Array[i] > 20) {
                    JOptionPane.showMessageDialog(null, "Page Reference contains value/s exceeding 20.",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            // selected algo
            Selected = io_algorithm_select.getSelectedIndex();

            // table creation
            create_table(main_Array, Selected, current_frames);

            boolean[] hitMatrix;
            int[][] framesMatrix;
            
            switch (Selected) {
                case 0:
                    FIFO fifo = new FIFO(main_Array, main_Array.length, current_frames);
                    hitMatrix = fifo.getHitMatrix();
                    framesMatrix = fifo.getFramesMatrix();
                    // framesMatrix[numofpages][framesize]
                    

                    initiate_print(hitMatrix, framesMatrix);

                    break;
                case 1:
                    LRU lru = new LRU(main_Array, main_Array.length, current_frames);
                    hitMatrix = lru.getHitMatrix();
                    framesMatrix = lru.getFramesMatrix();
                    // framesMatrix[numofpages][framesize]
                    initiate_print(hitMatrix, framesMatrix);
                    break;
                case 2:
                    OPT opt = new OPT(main_Array, main_Array.length, current_frames);
                    hitMatrix = opt.getHitMatrix();
                    framesMatrix = opt.getFramesMatrix();
                    // framesMatrix[numofpages][framesize]
                    initiate_print(hitMatrix, framesMatrix);
                    break;
                case 3:
                    SC sc = new SC(main_Array, main_Array.length, current_frames);
                    hitMatrix = sc.getHitMatrix();
                    framesMatrix = sc.getFramesMatrix();
                    // framesMatrix[numofpages][framesize]
                    initiate_print(hitMatrix, framesMatrix);
                    break;
                case 4:
                    ESC esc = new ESC(main_Array, main_Array.length, current_frames);
                    hitMatrix = esc.getHitMatrix();
                    framesMatrix = esc.getFramesMatrix();
                    // framesMatrix[numofpages][framesize]
                    initiate_print(hitMatrix, framesMatrix);
                    break;
                case 5:
                    LFU lfu = new LFU(main_Array, main_Array.length, current_frames);
                    hitMatrix = lfu.getHitMatrix();
                    framesMatrix = lfu.getFramesMatrix();
                    // framesMatrix[numofpages][framesize]
                    initiate_print(hitMatrix, framesMatrix);
                    break;
                case 6:
                    MFU mfu = new MFU(main_Array, main_Array.length, current_frames);
                    hitMatrix = mfu.getHitMatrix();
                    framesMatrix = mfu.getFramesMatrix();
                    // framesMatrix[numofpages][framesize]
                    initiate_print(hitMatrix, framesMatrix);
                    break;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Page Reference input contains invalid characters.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initiate_print(boolean[] hitMatrix, int[][] framesMatrix) {
        timer = new Timer((int) (500 / current_speed), new ActionListener() {
            // matrix + hits
            int faults = 0;
            int col = 0;

            public void actionPerformed(ActionEvent evt) {
                io_timer_label.setText(String.valueOf(col));
                io_page_label.setText(String.valueOf(main_Array[col]));
                if (col < main_Array.length) {

                    if (hitMatrix[col] == false) {
                        results_table.setValueAt("Miss", current_frames, col);
                        faults++;
                        fault_value.setText(" " + faults);
                    } else {
                        results_table.setValueAt("Hit", current_frames, col);
                    }

                    for (int row = 0; row < current_frames; row++) {
                        if (framesMatrix[col][row] == -1) {
                            results_table.setValueAt(" ", (current_frames - 1) - row, col);
                        } else {
                            if (main_Array[col] == framesMatrix[col][row]) {
                                // edit table highlight here
                                
                                desiredRow = (current_frames - 1) - row;
                                desiredColumn = col;

                                TableCellRenderer cellRenderer = new CenterTextRenderer(desiredRow, desiredColumn,
                                        current_frames);
                                results_table.setDefaultRenderer(Object.class, cellRenderer);
                                io_output_scroll.setViewportView(results_table);
                            }
                            results_table.setValueAt(framesMatrix[col][row], (current_frames - 1) - row, col);
                        }
                    }
                    col++;
                }
                if (col == main_Array.length) {
                    io_import.setEnabled(true);
                    io_random.setEnabled(true);
                    io_save.setEnabled(true);
                    io_frames_add.setEnabled(true);
                    io_frames_minus.setEnabled(true);
                    io_reference_input.setEditable(true);
                    io_speed_add.setEnabled(true);
                    io_speed_minus.setEnabled(true);
                    io_algorithm_select.setEnabled(true);
                    io_simulate.setEnabled(true);
                    io_simulateAll.setEnabled(true);
                    timer.stop();
                    TableCellRenderer cellRenderer = new CenterTextRenderer(-1, desiredColumn,
                    current_frames);
                    results_table.setDefaultRenderer(Object.class, cellRenderer);
                    io_output_scroll.setViewportView(results_table);
                }
            }
        });
        if (!timer.isRunning()) {
            timer.start();
        }
    }

    public void io_speed_minusMouseEntered(java.awt.event.MouseEvent evt) {
        io_speed_minus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/minus_hover.png")));
    }

    public void io_speed_minusMouseExited(java.awt.event.MouseEvent evt) {
        io_speed_minus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/minus.png")));
    }

    public void io_speed_minusActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        if (current_speed > 0.5) {
            current_speed = current_speed - 0.5;
            io_speed_value.setText(String.valueOf(current_speed) + "x");
        } else {
            
        }
    }

    public void io_speed_addMouseEntered(java.awt.event.MouseEvent evt) {
        io_speed_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/add_hover.png")));
    }

    public void io_speed_addMouseExited(java.awt.event.MouseEvent evt) {
        io_speed_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/add.png")));
    }

    public void io_speed_addActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        if (current_speed < 2.0) {
            current_speed = current_speed + 0.5;
            io_speed_value.setText(String.valueOf(current_speed) + "x");
        } else {
            
        }
    }

    public void io_frames_addMouseEntered(java.awt.event.MouseEvent evt) {
        io_frames_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/add_hover.png")));
    }

    public void io_frames_addMouseExited(java.awt.event.MouseEvent evt) {
        io_frames_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/add.png")));
    }

    public void io_frames_addActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        
        if (current_frames >= 3 && current_frames < 10) {
            current_frames++;
            io_frames_value.setText(String.valueOf(current_frames));
            
        } else {
            
            
        }
    }

    public void io_frames_minusMouseEntered(java.awt.event.MouseEvent evt) {
        io_frames_minus
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/minus_hover.png")));
    }

    public void io_frames_minusMouseExited(java.awt.event.MouseEvent evt) {
        io_frames_minus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/minus.png")));
    }

    public void io_frames_minusActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        if (current_frames <= 3) {
            
            
        } else {
            current_frames--;
            io_frames_value.setText(String.valueOf(current_frames));
            
        }
    }

    int default_frames = 3;
    int current_frames = default_frames;
    int length_upper, length_lower, seed, frame_lower, frame_upper, lower_value, upper_value;

    public javax.swing.JButton exit;
    public javax.swing.JLabel io_algorithm_bg;
    public javax.swing.JLabel io_algorithm_label;
    public javax.swing.JComboBox<String> io_algorithm_select;
    public javax.swing.JLabel io_bg;
    public javax.swing.JButton io_frames_add;
    public javax.swing.JLabel io_frames_bg;
    public javax.swing.JLabel io_frames_label;
    public javax.swing.JButton io_frames_minus;
    public javax.swing.JLabel io_frames_value;
    public javax.swing.JButton io_import;
    public javax.swing.JLabel io_output_bg;
    public javax.swing.JScrollPane io_output_scroll;
    public javax.swing.JLabel io_page_bg;
    public javax.swing.JLabel io_page_label;
    public javax.swing.JButton io_random;
    public javax.swing.JLabel io_reference_bg;
    public javax.swing.JTextField io_reference_input;
    public javax.swing.JLabel io_reference_label;
    public javax.swing.JButton io_return;
    public javax.swing.JButton io_save;
    public javax.swing.JButton io_simulate;
    public javax.swing.JButton io_simulateAll;
    public javax.swing.JButton io_speed_add;
    public javax.swing.JLabel io_speed_bg;
    public javax.swing.JLabel io_speed_label;
    public javax.swing.JButton io_speed_minus;
    public javax.swing.JLabel io_speed_value;
    public javax.swing.JLabel io_timer_bg;
    public javax.swing.JLabel io_timer_label;
    public javax.swing.JLabel fault_value;
    public javax.swing.JButton minimize;
    public javax.swing.JTable results_table;
    public Timer timer;

    public int[] random_Array;
    public int[] main_Array;
    public ArrayList<Integer> import_ArrayList;
}