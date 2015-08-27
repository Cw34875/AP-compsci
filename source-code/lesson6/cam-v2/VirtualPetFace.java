import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.Border;


public class VirtualPetFace extends JFrame implements ActionListener{

    private final int WIDTH = 400;
    private final int HEIGHT = 400;
    private ImagePanel imagePanel;
    private JTextPane textArea;
    private String base;
    private int loopslot = 0;
    private String[] files;
    private Image[] allPics;
    private ArrayList<Image> pics;
    private Timer timer;
    

    private static final String imageBase = "./pet_images/";
    
    public static void main(String args[]) {
        VirtualPet newPet = new VirtualPet();   
    }
    
    public VirtualPetFace() {
        try {
            javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    createGUI();
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't successfully complete");
        }
        
        init();
    }
    
    public void init() {
        String curDir = System.getProperty("user.dir");
        
        base = curDir + "/" + imageBase;
        pics = new ArrayList<Image>();
        timer = new Timer(400, this);
        //timer.setInitialDelay(1000);

        getAllImages();
        
        setBackground();
        //setImage("angel");      
        //setMessage("Hello, and Welcome!");
    }
    
    public void createGUI() {
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBackground(Color.white);
       
        
        imagePanel = new ImagePanel();
        int width = 200;
        int height = 200;
        imagePanel.setPreferredSize(new Dimension(width, height));
        imagePanel.setMinimumSize(new Dimension(width, height));
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        contentPane.add(imagePanel,c);
        
        textArea = new JTextPane();
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(width, height/2));
        scroll.setSize(new Dimension(width, height/2));
        textArea.setPreferredSize(new Dimension(width, height/2));
        textArea.setSize(new Dimension(width, height/2));


        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        c.ipady = 20;
        contentPane.add(scroll, c);
    
        setLocationRelativeTo(null);
        setVisible(true);
        //toFront();
        setAlwaysOnTop(true);
        setAlwaysOnTop(false);
    }
    
    private void setBackground() {
        Image backImage = createImage(base+"background.png", "");
        Border bkgrnd = new CentredBackgroundBorder(backImage);
        ((JComponent) getContentPane()).setBorder(bkgrnd);
    }
    
    protected Image createImage(String path, String description) {
           return new ImageIcon(path, description).getImage();
    }

    public void setImage(String mood) {
        timer.stop();
        pics.clear();
        getImages(mood);
        timer.start(); 
    }

     public void actionPerformed(ActionEvent e) {
            loopslot++;

            if (loopslot >= pics.size()) {
                loopslot = 0;
            }

            imagePanel.repaint();

            if (loopslot == pics.size()) {
                timer.restart();
            }
        }

    public void getAllImages() {
        File dir = new File(base);   
        files  = dir.list();
        allPics = new Image[files.length];
        for (int i = 0; i < files.length; i++) {
            //System.err.println(files[i]);
            allPics[i]=createImage(base + files[i],"");
            
        }
        //System.err.println(pics.size());
    }       
        
        
    public void getImages(final String mood) {
    
        for (int i = 0; i < files.length; i++) {
            if (files[i].contains(mood)) {
                pics.add(allPics[i]);
            }
        }
        //System.err.println(pics.size());
    }
    
    public void setMessage(String message) {
        String current = textArea.getText();
        textArea.setText(current + "\n" + message);
        textArea.select(current.length(), (current.length() + message.length() + 1));
    }
    
    
    public class ImagePanel extends JPanel {
        public ImagePanel( ) {
            super();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (pics.size() > 0) {
                g.drawImage(pics.get(loopslot), 0, 0, this.getWidth(), this.getHeight(), null);
            }
        }

    }

    public class CentredBackgroundBorder implements Border {
        private final Image image;
     
        public CentredBackgroundBorder(Image image) {
            this.image = image;
        }
     
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g. drawImage(image, 0, 0, VirtualPetFace.this.getWidth(), VirtualPetFace.this.getHeight(),null);
        }
     
        public Insets getBorderInsets(Component c) {
            return new Insets(0,0,0,0);
        }
     
        public boolean isBorderOpaque() {
            return true;
        }
    }
}

 


