import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.*;

public class Helper {

    private JFrame frame;
    private ImagePanel myImagePanel;
    private RGBImage myRGBImage;
    
    private final int defaultWidth = 800;
    private final int defaultHeight = 700;
    
    
    // constructor, no image specified
    public Helper(RGBImage img, String directory) {
        additionalSetup(img);
        load(directory);
        frame.setVisible(true);
    }

    // constructor, image specified
    public Helper(RGBImage img, String dir, String filename) {
        additionalSetup(img);
        addImage(new ImagePanel(new File(dir + "/" + filename)));
        frame.setVisible(true);
    }
    
    private void additionalSetup(RGBImage img) {
        frame = new JFrame();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.black);
        frame.setSize(defaultWidth, defaultHeight);
        myRGBImage = img;
    }
    
       
    public void addImage(ImagePanel anImagePanel) {
        if (myImagePanel != null) 
            frame.getContentPane().remove(myImagePanel);        // remove the current image
        frame.getContentPane().add(BorderLayout.CENTER, anImagePanel);
        frame.setTitle(anImagePanel.getTitle());                // sets the window title .
        frame.setSize(frame.getPreferredSize());    // change the window size to fit the image
        frame.validate();                           // re-layout the components, since they changed.
        frame.repaint();
        myImagePanel = anImagePanel;
    }

    
    public void load(String dir){
        JFileChooser fc = new JFileChooser(new File(dir));
        fc.addChoosableFileFilter(new ImageFilter());
        fc.showOpenDialog(frame);
        addImage(new ImagePanel(fc.getSelectedFile()));
    }

    public void show() {
        frame.setVisible(true);
    }
    
    public void refresh(int[][] red, int[][]green, int[][] blue) {
        myImagePanel.refresh(red, green, blue);
    }

    
    // Inner Class: used in the open file dialog box to show only jpg files
    private class ImageFilter extends FileFilter {
        //Accept all directories and jpg files.
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }
            String fname = f.getName();
            String ext = "";
            // where the last dot is. There may be more than one.
            int dotPlace = fname.lastIndexOf( '.' );
            if ( dotPlace >= 0 )
                ext = fname.substring( dotPlace + 1 );
            if (ext.toLowerCase().equals("jpeg") ||
                    ext.toLowerCase().equals("jpg")) {
                return true;
            } else 
                return false;
        }
        //The description of this filter
        public String getDescription() {
            return "Just Images";
        }
    }

    
    public void save(){
        JFileChooser fc = new JFileChooser(new File("."));
        fc.addChoosableFileFilter(new ImageFilter());
        int returnVal = fc.showSaveDialog(frame);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            // should we force the filename to have .jpg at the end?
            myImagePanel.saveToFile(fc.getSelectedFile());
        }
    }
    
    public void saveAs(String filename) {
        File f = new File(filename);
        myImagePanel.saveToFile(f);
    }

    
    // Inner Class
    public class ImagePanel extends JPanel implements MouseListener{
        
        private Image image;        // the Image object to get an image from a file and display it.
        private int[] pixels;       // the array of pixels used for the image to display
        private String filename;    // the filename of the image
        private String title;       // a title, usually the last part of the filename
        
        // The dimension of the image in pixels.
        private int width;
        private int height;
        
        // Constructor: takes file to read and initialize the internal image.
        public ImagePanel(File f) {
            
            filename = f.getAbsolutePath();     // keep the filename as an instance variable
            title = f.getName();
            this.addMouseListener(this);

            // This loads the image from filename.
            image = new ImageIcon(filename).getImage();
            
            // Determine the image's dimensions.
            width = image.getWidth(this);
            height = image.getHeight(this);
            
            // Get the actual pixels.
            pixels = new int[height*width];

            PixelGrabber pg = new PixelGrabber(image, 0, 0, width, height, pixels, 0, width);
            try {
                pg.grabPixels();
            }
            catch(InterruptedException e) {
                e.printStackTrace();
                System.exit(0);
            }
            if  ((pg.status()  &  ImageObserver.ABORT)  !=  0)  {
                System.err.println("Image  fetch  aborted  or  errored");
                System.err.println("Image  filename = " + filename );
                System.exit(0);
            }
            
            // These temporary arrays are used to update from the RGBImage 
            int[][] red = new int[width][height];
            int[][] green = new int[width][height];
            int[][] blue = new int[width][height];
            
            // Get red, green, blue components.
            for (int h=0; h<height; h++) {
                for (int w=0; w<width; w++) {
                    red[w][h]   = (pixels[h*width+w] >> 16) & 0xff;
                    green[w][h] = (pixels[h*width+w] >>  8) & 0xff;
                    blue[w][h]  = (pixels[h*width+w]      ) & 0xff;
                }
            }
            setPreferredSize(new Dimension(width, height));
            myRGBImage.updateArrays(red, green, blue);
        }
        

        public String getTitle() {
            return title;
        }
        
        
        public void saveToFile(File f) {
            BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            
            bimage.setRGB(0,0,width,height,pixels,0,width);
            try {
                ImageIO.write(bimage, "jpg", f);
            }
            catch (Exception e) {
                System.err.println("Couldn't write to file " + f.getName());
                // don't need to exit here, I guess...
            }
        }
        
        // This method converts the pixels back to an Image object for display or saving to file.  
        private void pixelsToImage(int[][] red, int[][]green, int[][] blue) {
            
            // Convert pixels to image.
            if (pixels.length != height * width)      // maybe the height and width have changed...
                pixels = new int[height*width];
            
            for (int h=0; h<height; h++) {
                for (int w=0; w<width; w++) {
                    pixels[h*width+w]  = 255         << 24;     // Alpha should be 255.
                    pixels[h*width+w] += red[w][h]   << 16;
                    pixels[h*width+w] += green[w][h] << 8;
                    pixels[h*width+w] += blue[w][h];
                }
            }
            ImageProducer ip = new MemoryImageSource(width,height,pixels,0,width);
            image = createImage(ip);
        }

        public void paintComponent(Graphics g) {
            // Set the background as white.
            g.setColor(Color.WHITE);
            Dimension d = getSize();
            g.fillRect(0,0,d.width,d.height);
            // Draw the image.
            g.drawImage(image,0,0,this);
        }
        
        
        // This method should always be called after manipulating the pixels via the 2D arrays.
        // This method updates the Image object and repaints the image.
        private void refresh(int[][] red, int[][]green, int[][] blue) {
            pixelsToImage(red, green, blue);
            repaint();
        }
        
        
        /////// Listener stuff, to help debugging
        // note, this needs to convert to make the RGB values.  Won't be correct if update() not run.
        public void mouseClicked(MouseEvent e) {
            int h = e.getY();
            int w = e.getX();
            int r = (pixels[h*width+w] >> 16) & 0xff;
            int g = (pixels[h*width+w] >>  8) & 0xff;
            int b = (pixels[h*width+w]      ) & 0xff;
            String message = "Click location (h: " + h + ", w: " + w + ")";
            message += "  Pixel values (r: " + r + ", g: " + g + ", b: " + b + ")";
            System.out.println(message);
        }
    
        public void mouseEntered(MouseEvent e) {
        }
    
        public void mouseExited(MouseEvent e) {
        }
    
        public void mousePressed(MouseEvent e) {
        }
    
        public void mouseReleased(MouseEvent e) {
        }
        
    
        
    } // close IEImage class
} // close ImageEditor