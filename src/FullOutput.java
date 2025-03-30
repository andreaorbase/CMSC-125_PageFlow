import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;


public class FullOutput extends javax.swing.JPanel {
    public FullOutput() {
        initComponents();
    }

    public void reset() {
        PageFlow.IO.flag = false;
        PageFlow.IO.current_frames = 3;
        PageFlow.IO.main_Array = null;
        PageFlow.IO.io_reference_input.setText("");
        
        removeAll();
        initComponents();
    }

    public void initComponents() {
        results_model = new DefaultTableModel();
        tableScrollPane = new javax.swing.JScrollPane();
        exit = new javax.swing.JButton();
        minimize = new javax.swing.JButton();
        io_save = new javax.swing.JButton();
        io_return = new javax.swing.JButton();
        io_output_scroll = new javax.swing.JScrollPane();
        io_output_bg = new javax.swing.JLabel();
        io_bg = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();

        setLayout(null);
    }
    String[] global_header;
    boolean[] hitMatrix;
    int[][] framesMatrix;
    public void create_tables() {
        mainPanel.setBounds(60, 90, 960, 560);
        mainPanel.setLayout(new BorderLayout());
            int spacing = 20; // Adjust the spacing as desired
            String [] algorithms = {"First in First Out", "Least Recently Used", "Optimal Page Replacement", "Second Chance Algorithm", "Enhanced Second Chance Algorithm", "Least Frequency Used", "Most Frequency Used"};
            global_header = new String [PageFlow.IO.main_Array.length];

            FIFO fifo = new FIFO(PageFlow.IO.main_Array, PageFlow.IO.main_Array.length, PageFlow.IO.current_frames);
            LRU lru = new LRU(PageFlow.IO.main_Array, PageFlow.IO.main_Array.length, PageFlow.IO.current_frames);
            OPT opt = new OPT(PageFlow.IO.main_Array, PageFlow.IO.main_Array.length, PageFlow.IO.current_frames);
            SC sc = new SC(PageFlow.IO.main_Array, PageFlow.IO.main_Array.length, PageFlow.IO.current_frames);
            ESC esc = new ESC(PageFlow.IO.main_Array, PageFlow.IO.main_Array.length, PageFlow.IO.current_frames);    
            LFU lfu = new LFU(PageFlow.IO.main_Array, PageFlow.IO.main_Array.length, PageFlow.IO.current_frames);
            MFU mfu = new MFU(PageFlow.IO.main_Array, PageFlow.IO.main_Array.length, PageFlow.IO.current_frames);

            for(int x = 0; x < global_header.length; x++){
                global_header[x] = String.valueOf(PageFlow.IO.main_Array[x]);
            }
            // char[][] hitMatrix;
            for (int i = 0; i < 7; i++) {
                switch(i){
                    case 0:
                    hitMatrix = fifo.getHitMatrix();
                    framesMatrix = fifo.getFramesMatrix();
                    break;
                    case 1:
                    hitMatrix = lru.getHitMatrix();
                    framesMatrix = lru.getFramesMatrix();
                    break;
                    case 2:
                    hitMatrix = opt.getHitMatrix();
                    framesMatrix = opt.getFramesMatrix();
                    break;
                    case 3:
                    hitMatrix = sc.getHitMatrix();
                    framesMatrix = sc.getFramesMatrix();
                    break;
                    case 4:
                    hitMatrix = esc.getHitMatrix();
                    framesMatrix = esc.getFramesMatrix();
                    break;
                    case 5:
                    hitMatrix = lfu.getHitMatrix();
                    framesMatrix = lfu.getFramesMatrix();
                    break;
                    case 6:
                    hitMatrix = mfu.getHitMatrix();
                    framesMatrix = mfu.getFramesMatrix();
                    break;
                }
                String[] header = global_header;
                Object[][] rowData = {};
              
                JLabel label = new JLabel(algorithms[i], null, SwingConstants.LEFT);
                label.setHorizontalAlignment(SwingConstants.LEADING);
                label.setFont(new java.awt.Font("Poppins ExtraBold", 0, 16));
                label.setAlignmentX(Component.LEFT_ALIGNMENT);

                TableCellRenderer cellRenderer = new CenterTextRenderer(-1, -1, PageFlow.IO.current_frames);
                JTable table = new JTable(rowData, header);
                table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                table.setDefaultRenderer(Object.class, cellRenderer);
                table.setModel(new javax.swing.table.DefaultTableModel(rowData, header));

                DefaultTableModel proxy_model = (DefaultTableModel) table.getModel();
                proxy_model.setColumnCount(results_model.getColumnCount());
                proxy_model.setRowCount(PageFlow.IO.current_frames + 1);

                table.setEnabled(false);
                table.setFillsViewportHeight(true);
                table.setGridColor(new java.awt.Color(255, 255, 255));
                table.setSelectionBackground(new java.awt.Color(211, 211, 211));
                table.setBackground(Color.WHITE);
                table.setInheritsPopupMenu(true);
                table.setIntercellSpacing(new java.awt.Dimension(35, 1));
                table.setRowHeight(40);
                table.getTableHeader().setReorderingAllowed(false);
                table.getTableHeader().setResizingAllowed(false);
                table.getTableHeader().setBackground(Color.WHITE);
                table.getTableHeader().setAlignmentX(Component.LEFT_ALIGNMENT);
                table.setAlignmentX(Component.LEFT_ALIGNMENT);

                JTableHeader results_header = table.getTableHeader();
                TableCellRenderer renderer = results_header.getDefaultRenderer();
                ((JLabel) renderer).setHorizontalAlignment(SwingConstants.CENTER);
                
                // Create a scroll pane for the table
                for(int a = 0; a < PageFlow.IO.main_Array.length; a++){
                    int current_frames = PageFlow.IO.current_frames;
                    if(hitMatrix[a] == false){
                    table.setValueAt("Miss", current_frames, a);
                    }
                    else{
                        table.setValueAt("Hit", current_frames, a);
                    }
            
                    for(int j = 0; j < current_frames; j++){
                        if(framesMatrix[a][j] == -1){
                            table.setValueAt(" ", (current_frames - 1)-j, a);
                        }
                        else{
                            table.setValueAt(framesMatrix[a][j], (current_frames - 1)-j, a);
                        }
                    }
                }
                // Add spacing between the scroll panes
                if (i > 0) {
                    panel.add(Box.createRigidArea(new Dimension(0, spacing)));
                }

                Box box = Box.createVerticalBox();
                box.setAlignmentX(Component.LEFT_ALIGNMENT);
                box.add(label);
                box.add(table.getTableHeader());
                box.add(table);

                panel.add(box);
            }
        // Add an empty border around the panel to create additional spacing
        panel.setBorder(new EmptyBorder(spacing, spacing, spacing, spacing));

        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        // Add the panel to the main panel
        mainPanel.add(panel, BorderLayout.CENTER);
        

        io_output_scroll.setBorder(null);
        io_output_scroll.setBackground(Color.WHITE);
        io_output_scroll.getViewport().setBackground(Color.WHITE);
        io_output_scroll.getVerticalScrollBar().setUnitIncrement(20);
        add(io_output_scroll);
        io_output_scroll.setBounds(60, 90, 960, 540);
        io_output_scroll.setViewportView(mainPanel);

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

        io_output_bg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        io_output_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/algorithm_bg_full.png"))); 
        io_output_bg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(io_output_bg);
        io_output_bg.setBounds(0, 50, 1080, 650);

        io_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/backgrounds/background.png"))); 
        io_bg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        io_bg.setOpaque(true);
        add(io_bg);
        io_bg.setBounds(0, 0, 1224, 720);
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

    private void io_returnMouseEntered(java.awt.event.MouseEvent evt) {
        io_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/return_hover.png")));
    }

    private void io_returnMouseExited(java.awt.event.MouseEvent evt) {
        io_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/return.png")));
    }

    private void io_returnActionPerformed(java.awt.event.ActionEvent evt) {
        Music.sfx();
        reset();
        PageFlow.IO.reset();
        PageFlow.card.show(PageFlow.mainPanel, "2");
    }

    private void io_saveMouseEntered(java.awt.event.MouseEvent evt) {
        io_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/save_hover.png")));
    }

    private void io_saveMouseExited(java.awt.event.MouseEvent evt) {
        io_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/buttons/save.png")));
    }

    private void io_saveActionPerformed(java.awt.event.ActionEvent evt) {
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

                String fileName = "temp.png";
                String pdfName = fileToSave.getAbsolutePath() + ".pdf";
                File filePDF = new File(pdfName);

                // Check if the file already exists
                while (filePDF.exists()) {
                    fileNumber++; // Increment the file number

                    // Generate a new file name
                    pdfName = fileToSave.getAbsolutePath() + String.format("%03d", fileNumber) + ".pdf";
                    filePDF = new File(fileName);
                }
                saveJScrollPaneAsImage(io_output_scroll, fileName);
                try {
                    BufferedImage bufferedImage = ImageIO.read(new File(fileName));
                    int imageWidth = bufferedImage.getWidth() + 70;
                    int imageHeight = bufferedImage.getHeight() + 70;

                    Document document = new Document();
                    document.setPageSize(new com.itextpdf.text.Rectangle(imageWidth, imageHeight));
                    PdfWriter.getInstance(document, new FileOutputStream(pdfName));
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
                    fileNumber++;
                    fileName = fileToSave.getAbsolutePath() + String.format("%03d", fileNumber) + ".png";
                    file = new File(fileName);
                }
                saveJScrollPaneAsImage(io_output_scroll, fileName);
            }
        }
    }

    private static void saveJScrollPaneAsImage(JScrollPane scrollPane, String filename) {
        // Get the panel or component contained in the JScrollPane
        JComponent component = (JComponent) scrollPane.getViewport().getView();

        // Get the size of the panel or component
        Dimension size = component.getSize();

        // Create a BufferedImage with the size of the panel or component
        BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);

        // Create a Graphics2D object from the image
        Graphics2D graphics = image.createGraphics();

        // Paint the panel or component onto the image
        component.paint(graphics);

        // Dispose the graphics object
        graphics.dispose();

        // Save the image to a file
        try {
            ImageIO.write(image, "png", new File(filename));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private javax.swing.JButton exit;
    private javax.swing.JLabel io_bg;
    private javax.swing.JLabel io_output_bg;
    public javax.swing.JScrollPane io_output_scroll;
    private javax.swing.JButton io_return;
    public javax.swing.JButton io_save;
    private javax.swing.JButton minimize;
    public javax.swing.JPanel panel;
    public javax.swing.JPanel mainPanel;
    public javax.swing.JScrollPane tableScrollPane;
    public DefaultTableModel results_model;
    public Timer timer;

    public int[] random_Array;
    public int[] main_Array;
}