import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.sound.sampled.*;

import java.io.File;
import java.lang.Math;
import java.awt.geom.*;

public class Viewer implements LineListener
{
	public boolean DEBUG = false;

	private Helper myHelper;
	private boolean isPlaying = false;
	private JFrame mainFrame;
	private OverViewPanel overViewPanel;
	private WavePanel wavePanel;       // the Helper needs to reset the zoom during refresh
	private JPanel controlPanel;  
	private JButton playBtn;
	private JButton playSelectionBtn;
	private JButton stopBtn;
	private JButton pauseBtn;
	private JButton zoomInBtn;
	private JButton zoomOutBtn;
	private int playSelClicked = 0;
	private double samplePerPixel;
	private ArrayList<Integer>data;



	private static int frameWidth = 800;
	private static int overViewHeight = 50;
	private static int controlHeight = 80;
	private static int waveHeight = 400;
	private static int frameHeight = overViewHeight + controlHeight + waveHeight;


	private int waveLocY = overViewHeight;
	private int controlLocY =  overViewHeight + waveHeight;

	///CONSTANTS///
	private static final Color selectionColor = Color.gray;
	private static final Color backgroundColor = Color.white;
	private static final Color overViewBarColor = new Color(0f, 0f, 1f, 0.8f);
	private static final Color overViewSelectionColor = new Color(0f, 1f, 0f, 0.5f);
	private static final Color waveColor = Color.blue;
	private static final Color waveLiteColor = new Color(0.4f, 0.5f, 1f, 1f);
	private static final Color barColor = Color.red;
	private int zoomThresh = 40;
	private static final int zoomStop = 2;
	private int stemThresh = 100;
	private Font textFont = new Font("sansserif", Font.BOLD, 8);
	private Font textFont2 = new Font("sansserif", Font.BOLD, 12);

	final BasicStroke stroke = new BasicStroke(0.2f);
	final BasicStroke thickerStroke = new BasicStroke(2.0f);
	final BasicStroke wideStroke = new BasicStroke(5.0f);

	/*
	 * Do nothing constructor. We'll initialize everything after load
	 */
	public Viewer() {
	}

	
	/*
	 * Return the sampling rate of the sound.  Only works if the sound has been
	 * created (i.e., the helper)
	 */
	public int getSamplingRate() {
	    return ((int) myHelper.getSamplingRate());
	}
	
	/*
	 * Create a new empty sound
	 */
	public ArrayList<Integer> newSound(int soundLength) {
		myHelper = new Helper(soundLength);
		myHelper.setViewer(this);
		data = myHelper.getData();
		createMainFrame();
		return data;
	}

	/*
	 * Load some sound data from through a file chooser
	 * We restrict the allowed file formats to .wav, .au .aif .aiff.
	 * We only check agains the file name. The file may actually be encoded
	 * differently.
	 */
	public ArrayList<Integer> load() {
		JFileChooser fileChooser = new JFileChooser();
		File thisFile = new File(".");
		try {
			fileChooser = new JFileChooser(thisFile.getCanonicalPath());
		} catch (Exception e) {
			System.out.println("Cannot find current directory.");
			// Will simply load from default path instead.
		}
		fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter () {
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				}
				String name = f.getName();
				if (name.endsWith(".au") || name.endsWith(".wav") || name.endsWith(".aiff") || name.endsWith(".aif")) {
					return true;
				}
				return false;
			}
			public String getDescription() {
				return ".au, .wav, .aif";
			}
		});

		int returnVal = fileChooser.showOpenDialog(new JFrame());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			myHelper =  new Helper(file);
			myHelper.setViewer(this);
			data =  myHelper.getData();
			createMainFrame();
			return data;
		}
		return null;
	}

	/*
	 * Load a sound given a specific file name
	 */
	public ArrayList<Integer> load(String filename) {
		if (filename == null) {
			System.err.println("Please specify a valid file name");
			return null;
		}

		File file = new File(filename);
		if (!file.isFile()) {
			System.err.println("File not found");
			return null;
		}

		String name = file.getName();
		if (!(name.endsWith(".au") || name.endsWith(".wav") || name.endsWith(".aiff") || name.endsWith(".aif"))) {
			System.err.println("Audio format not supported. Please use either files ending with '.au', '.wav', '.aiff', '.aif'");
			return null;
		}

		myHelper =  new Helper(file);
		myHelper.setViewer(this);
		createMainFrame();
		data = myHelper.getData();
		return data;
	}

	/* 
	 * Takes a new ArrayList to use as the data for the sound.  
	 * Will keep the same audio format (sampling rate, depth (which should be 16!), filename, etc).
	 */
	public void setData(ArrayList<Integer> data) {
	   this.data = data;
	   myHelper.setData(data);  
	   refresh(false);     // byte array is updated in the Helper.setData(), no need to do it again
	   zoomReset();
	} 

	/*
	 * This method is called to refresh the visualization and optionally update
	 * the byte array in helper with the current content of the integer arraylist.
	 */
	public void refresh(boolean updateData) {
		wavePanel.refresh(updateData);
		overViewPanel.refresh();

	}


	/*
	 * Play the sound
	 */
	public void play() {
		if (myHelper != null)
			myHelper.play();
		else
			System.err.println("No sound has been loaded. Please load a new sound");
	}

	/*
	 * zoom to a range
	 */
	public void zoomTo(int begin, int end) {
		wavePanel.zoomTo(begin, end);
	}

	// When length of data changes, etc.
	public void zoomReset() {
	   wavePanel.zoomReset();
	}
	
	
	/*
	 * Write the sound to a specific file
	 */
	public void writeToFile(String fileName) {
		if (myHelper != null)
			myHelper.writeToFile(fileName);
		else
			System.err.println("No sound has been loaded. Please load a new sound");
	}



	////////////// methods to create the interactive visualizer ////////////////////////
	private void createMainFrame() {
		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Container contentPane = mainFrame.getContentPane();
		//contentPane.setLayout(new BorderLayout());

		//creates the control panel
		controlPanel = new JPanel(null);
		controlPanel.setLayout(new FlowLayout());
		makeSoundVis();
		mainFrame.addComponentListener(new ResizeHandler());


		//setting up the play sound button
		playBtn = new JButton("Play");
		playBtn.setEnabled(true);
		playBtn.setToolTipText("Play the  sound");
		playBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				stopBtn.setEnabled(true);
				pauseBtn.setEnabled(true);
				playBtn.setEnabled(false);
				playSelectionBtn.setEnabled(false);
				zoomInBtn.setEnabled(false);
				zoomOutBtn.setEnabled(false);
				isPlaying = true;
				myHelper.play();
			}
		});
		controlPanel.add(playBtn);


		//setting up the play selection button
		playSelectionBtn = new JButton("Play Selection");
		playSelectionBtn.setEnabled(false);
		playSelectionBtn.setToolTipText("Play sound between start and stop index");
		playSelectionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				playSelClicked++;
				stopBtn.setEnabled(true);
				pauseBtn.setEnabled(true);
				playSelectionBtn.setEnabled(false);
				playBtn.setEnabled(false);
				zoomInBtn.setEnabled(false);
				zoomOutBtn.setEnabled(false);
				isPlaying = false;
				if (playSelClicked == 1){ 
					myHelper.playInRange(wavePanel.getSelectionStartSample(), wavePanel.getSelectionEndSample());
				} else {
					myHelper.play();
				}
			}
		});
		controlPanel.add(playSelectionBtn);

		//setting up the stop button
		stopBtn = new JButton("Stop");
		stopBtn.setEnabled(false);
		stopBtn.setToolTipText("Stop playing the sound");
		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				myHelper.stop();
				stopBtn.setEnabled(false);
				pauseBtn.setEnabled(false);
				playBtn.setEnabled(true);
				if (wavePanel.moreZoomInScope()){
					zoomInBtn.setEnabled(true);
					playSelectionBtn.setEnabled(true);
				}

				if (wavePanel.moreZoomOutScope()) {
					zoomOutBtn.setEnabled(true);
				}
				playSelClicked = 0;
				isPlaying = false;

			}
		});
		controlPanel.add(stopBtn);


		//setting up the stop button
		pauseBtn = new JButton("Pause");
		pauseBtn.setEnabled(false);
		pauseBtn.setToolTipText("Pause the sound");
		pauseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				myHelper.pause();
				if (isPlaying) {
					playBtn.setEnabled(true);
				} else {
					playSelectionBtn.setEnabled(true);
				}
			}
		});
		controlPanel.add(pauseBtn);

		//setting up the zoom  button
		zoomInBtn = new JButton("Zoom In");
		zoomInBtn.setEnabled(false);
		zoomInBtn.setToolTipText("Click to see the samples within your selection");
		zoomInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				handleZoomIn();
			}
		});
		controlPanel.add(zoomInBtn);


		//setting up the zoom  button
		zoomOutBtn = new JButton("Zoom Out");
		zoomOutBtn.setEnabled(false);
		zoomOutBtn.setToolTipText("Click to zoom out");
		zoomOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				handleZoomOut();
			}
		});
		controlPanel.add(zoomOutBtn);


		controlPanel.setBounds(0, controlLocY, frameWidth, controlHeight);
		contentPane.add(controlPanel);

		contentPane.add(wavePanel);
		contentPane.add(overViewPanel);
		wavePanel.setBounds(0, waveLocY, frameWidth, waveHeight);

		//controlPanel.removeAll();
		//controlPanel.setBackground(Color.CYAN);

		mainFrame.setSize(frameWidth, frameHeight);

		overViewPanel.init();
		wavePanel.init();
		//
		//mainFrame.pack();
		//mainFrame.setResizable(false);
		//mainFrame.validate();

		mainFrame.setVisible(true);
		overViewPanel.setBounds(0, 0, frameWidth, overViewHeight);

		//mainFrame.validate();

	}

	private void makeSoundVis()
	{
		wavePanel = new WavePanel();
		wavePanel.setPreferredSize(new Dimension(frameWidth, waveHeight));
		wavePanel.setSize(wavePanel.getPreferredSize());
		makeOverView();
	}

	private void makeOverView() {
		overViewPanel = new OverViewPanel();
		overViewPanel.setPreferredSize(new Dimension(frameWidth, overViewHeight));
		overViewPanel.setSize(overViewPanel.getPreferredSize());
	}

	/**
	 * Method to handle the line event update
	 * @param e the line event
	 */
	public void update(LineEvent e)
	{

		if(e.getType().equals(LineEvent.Type.CLOSE)){
			playBtn.setEnabled(true);

			stopBtn.setEnabled(false);
			pauseBtn.setEnabled(false);
			if (wavePanel.moreZoomInScope()) {
				playSelectionBtn.setEnabled(true);

				zoomInBtn.setEnabled(true);
			}
			if (wavePanel.moreZoomOutScope())
				zoomOutBtn.setEnabled(true);
			isPlaying = false;
			playSelClicked = 0;
		}

	}

	/**
	 * Method to handle a zoom in
	 */
	private void handleZoomIn()
	{
		wavePanel.zoomIn();
	}

	private void handleZoomOut(){
		wavePanel.zoomOut();
	}
	

	private ArrayList<SoundSample> getSampleAsArrayList(Selection selection, Component c) {
		ArrayList<SoundSample> points = new ArrayList<SoundSample>();

		//System.err.println(c.getHeight());
		int sampleBeginIndex = selection.getBegin();
		int sampleEndIndex = selection.getEnd();

		if (!checkRange(sampleBeginIndex, sampleEndIndex)) {
			System.err.println("The range of sample (" + sampleBeginIndex + " , " + sampleEndIndex +
			") you are trying to visualize is invalid");
		} else {
			int selectionLength = sampleEndIndex - sampleBeginIndex + 1;
			float maxValue = myHelper.getMaxSampleValue();
			samplePerPixel = selectionLength*1.0/frameWidth;
			if (DEBUG)
			   System.err.println("getSamples: sample per pixel " + samplePerPixel );

			if (selectionLength < frameWidth) { //if every sample can be displayed (i.e at least 1 pixel per sample)
				if (DEBUG)
					System.err.println("getSamples: Everything can be displayed " + selectionLength);
				int ratio = (int)Math.floor(frameWidth / selectionLength);
				if (DEBUG)
					System.err.println("getSamples: ratio " + ratio);
				int occupyPixels = ratio * selectionLength;
				selectionLength = selectionLength + (frameWidth - occupyPixels) / ratio; //fill up the display space with a few more samples

				if (DEBUG)
					System.err.println("getSamples: selection length " + selectionLength);

				if (selectionLength + sampleBeginIndex - 1 > data.size()) {
					selectionLength -= selectionLength + sampleBeginIndex - 1 - data.size();
				}
				if (DEBUG)
					System.err.println("getSamples: Final selection length " + selectionLength);
				for(int i = 0; i < selectionLength; i++) {
					float sampleValue = data.get(sampleBeginIndex + i - 1);
					float y = ((float)Math.floor(c.getHeight()/2) * (1 - sampleValue / maxValue));
					points.add(new SoundSample(new Point2D.Float(i*ratio, y), sampleValue, sampleBeginIndex+i));
				}
			} else if (selectionLength / 2 < frameWidth) { //we can sample at Nyquist rate
				//System.err.println("Sample begin " + sampleBeginIndex );

				int start = sampleBeginIndex;
				for(int pixel = 0; pixel<frameWidth; pixel++) {
					//System.err.println("Sample index " + (start+ samplePerPixel * pixel) );

					int sampleValue = data.get((int)Math.floor(start+ samplePerPixel * pixel));
					//int sampleValue = findMax(start, end);
					float y = ((float)Math.floor(c.getHeight()/2) - 
							(sampleValue  * ((float)Math.floor(c.getHeight()/2) / 
									maxValue)));
					points.add(new SoundSample(new Point2D.Float(pixel, y), sampleValue, pixel));
				}
			} else {//show general contour
				int start = sampleBeginIndex;
				int end = (int)(sampleBeginIndex + this.samplePerPixel);
				int baseLine = c.getHeight()/2;
				for(int pixel = 0; pixel<frameWidth; pixel++) {
					ArrayList<Integer> minMax = findMinMax(start, end);
					//System.err.println(minMax);
					float y = baseLine - (minMax.get(0)*0.9f/maxValue * baseLine);
					points.add(new SoundSample(new Point2D.Float(pixel, y), minMax.get(0), pixel));
					 
					y = baseLine - (minMax.get(0) * 0.7f /maxValue * baseLine);
					points.add(new SoundSample(new Point2D.Float(pixel, y), minMax.get(0)/2, pixel));

					
					//System.err.print("Min y: " + y + " ");

					y = baseLine - (minMax.get(1)*0.9f/maxValue * baseLine);
					points.add(new SoundSample(new Point2D.Float(pixel, y), minMax.get(1)/2, pixel));
					y = baseLine - (minMax.get(1) * 0.7f /maxValue * baseLine);
					points.add(new SoundSample(new Point2D.Float(pixel, y), minMax.get(1), pixel));

//					if (y > c.getHeight())
//						System.err.println("not right");
					start = end + 1;
					end = (int)(end + samplePerPixel);
				}
			}
		}
		return points;
	}


	private ArrayList<Integer> findMinMax(int beginFrame, int endFrame) {
		//System.err.println(beginFrame + " " + endFrame);
		//System.err.println(data);

		ArrayList<Integer> minMax = new ArrayList<Integer>();
		int length = endFrame - beginFrame + 1;
		float maxValue = myHelper.getMaxSampleValue();

		float max = (-1) * maxValue;;
		float min = maxValue;
		for (int sampleIndex = beginFrame; sampleIndex < endFrame; sampleIndex++ ) {
			int sampleValue = data.get(sampleIndex);
			if (max < sampleValue) {
				max = sampleValue;
			} 
			if (min > sampleValue) {
				min = sampleValue;
			}
		}
		minMax.add((int)min);
		minMax.add((int)max);
		return minMax;
	}


	private boolean checkRange(int begin, int end) {
		return begin >= 0 && end <= data.size();
	}

	private class ResizeHandler extends ComponentAdapter {
		public void componentResized(ComponentEvent e) {
			Component c = e.getComponent();
			if (c == mainFrame) {
				JFrame theMainFrame = (JFrame)c;
				//update the range of samples to visualize
				//				
				//				
				//				
				//				int newSampleCount = (int)Math.round((theMainFrame.getWidth() - frameWidth) * samplePerPixel / 2);
				//
				//				Selection currSel = zoomStack.get(zoomStack.size() - 1);
				//				int newLeftSamples = Math.min(currSel.beginSelInSamples, newSampleCount);
				//				int leftRemainder = newSampleCount - newLeftSamples;
				//				int newRightSamples = Math.min(myHelper.getLengthInFrames() - currSel.endSelInSamples +1 , newSampleCount);
				//				int rightRemainder = newSampleCount - newRightSamples;
				//				currSel.beginSelInSamples -= newLeftSamples;
				//				currSel.endSelInSamples += newRightSamples;
				//				
				//				
				//				if (leftRemainder > 0  && rightRemainder == 0) {
				//					newRightSamples = Math.min(myHelper.getLengthInFrames() - currSel.endSelInSamples + 1, leftRemainder);
				//					currSel.endSelInSamples += newRightSamples;
				//				} else if (leftRemainder == 0 && rightRemainder > 0) {
				//					newLeftSamples = Math.min(currSel.beginSelInSamples, rightRemainder);
				//					currSel.beginSelInSamples -= newLeftSamples;
				//				}
				//				
				//				System.err.println(currSel.beginSelInSamples + " " + currSel.endSelInSamples);
				//				selBeginSample = currSel.beginSelInSamples;
				//				selEndSample = currSel.endSelInSamples;

				//update the visualization area
				frameHeight = theMainFrame.getHeight();
				frameWidth = theMainFrame.getWidth();
				//his.overViewHeight = frameHeight / 10;
				waveHeight = frameHeight - overViewHeight - controlHeight;
				controlLocY = waveHeight + overViewHeight;
				zoomThresh = frameWidth / 20;
				stemThresh = frameWidth / 8;
				wavePanel.setSize(frameWidth, waveHeight);
				controlPanel.setBounds(0, controlLocY, frameWidth, controlHeight);
				overViewPanel.setSize(frameWidth, overViewHeight);
				wavePanel.refresh(false); //just refresh the view. wait till user calls refresh explicitly to update data
				overViewPanel.refresh();

				//samplePerPixel = (selEndSample - selBeginSample + 1) /frameWidth;

				mainFrame.validate();
			}
		}

	}


	private class OverViewPanel extends JPanel {

		private int viewBeginX;
		private int viewEndX;
		private ArrayList<SoundSample> points;

		private final int baseLineLocY = overViewHeight - 10;
		private static final int mainTickHeight = 8;
		private static final int smallTickHeight = 5;
		private static final int textHeightFromBase = 10;
		private static final int numOfSmallTicks = 4;
		private static final int smallTickTextThresh = 100;

		public OverViewPanel()
		{
			super(null);
			//setBackground(backgroundColor);
			//setPreferredSize(new Dimension(frameWidth, overViewHeight));
			//setSize(getPreferredSize());
		}

		public void setView(final Selection selection) {
			setView(selection.beginSelInSamples, selection.endSelInSamples);
		}

		/*
		 * Show all the points in the sound. Since the overview never changes
		 * zoom scope we always display all the points
		 */
		public void init() {
			//System.err.println("here2 " + this.getHeight());
			//System.err.println(this);
			this.points = getSampleAsArrayList(new Selection(1, data.size()), this);
			repaint();
		}

		/*
		 * Refresh the data 
		 */
		public void refresh() {
			//System.err.println("here " + this.getHeight());
			init();
			//repaint();
		}

		public void paintComponent(final Graphics g)
		{
			final Graphics2D g2 = (Graphics2D)g;
			g2.setBackground(backgroundColor);
			g2.clearRect(0, 0, frameWidth, overViewHeight);

			if (this.points.size() > 0) {
				g2.setColor(Color.blue);
				//draw the lines
				g2.setStroke(stroke);
				if (points.size() > frameWidth) { //draw contour
					//g2.setStroke(thickerStroke);
					for(int i = 0; i < this.points.size() - 1; i+=4){
						Point2D.Float pt1  = points.get(i).getDispPoint();
						//Point2D.Float pt2  = new Point2D.Float(pt1.x, pt1.y/2);
						Point2D.Float pt2  = points.get(i+1).getDispPoint();
						Point2D.Float pt3 = points.get(i+2).getDispPoint();
						Point2D.Float pt4 = points.get(i+3).getDispPoint();

						//						//Point2D.Float pt4  = new Point2D.Float(pt2.x, pt2.y/2);
						g2.draw(new Line2D.Float(pt1, pt2));
						//g2.setColor(waveLiteColor);
						g.setColor(waveLiteColor);
						g2.draw(new Line2D.Float(pt2, pt3));
						g2.setColor(waveColor);
						g2.draw(new Line2D.Float(pt3, pt4));
						//						g2.draw(new Line2D.Float(this.points.get(i).getDispPoint(),
						//								this.points.get(i+1).getDispPoint()));
						//						g2.draw(new Line2D.Float(this.points.get(i).getDispPoint(),
						//								this.points.get(i+1).getDispPoint()));
						//						g2.draw(new Line2D.Float(this.points.get(i).getDispPoint(),
						//								this.points.get(i+1).getDispPoint()));
					}

				} else { 
					for(int i = 0; i < this.points.size() - 1; i++)
					{
						g2.draw(new Line2D.Float(this.points.get(i).getDispPoint(),
								this.points.get(i+1).getDispPoint()));
					}
				}
			}

			//draw the selection if it exists
			if(viewBeginX != 0 && viewEndX!= 0)
			{
				g2.setColor(overViewSelectionColor);
				g2.fillRect(viewBeginX, 0,  viewEndX - viewBeginX + 1, overViewHeight);
			}


			final double totalTimeInSec = data.size() / myHelper.getSamplingRate();
			final int mainTickDist = (int) Math.floor(frameWidth / totalTimeInSec);
			final int smallTickDist = (int) Math.floor(mainTickDist / (numOfSmallTicks+1));

			//draw the base line;
			g2.setColor(Color.black);
			g2.setStroke(thickerStroke);
			final Point2D base = new Point2D.Double(0, this.baseLineLocY);
			Point2D pt = new Point2D.Double(frameWidth, this.baseLineLocY);
			g2.draw(new Line2D.Double(base, pt));

			//draw the ticks
			pt = new Point2D.Double(0, baseLineLocY - this.mainTickHeight);
			//show text for main ticks
			g2.setFont(textFont2);
			for (int i = 0; i <= totalTimeInSec; i++) {
				g2.setStroke(thickerStroke);
				g2.draw(new Line2D.Double(base, pt));

			
				
				g2.drawString(i + "", (float)base.getX(), (float)base.getY() + textHeightFromBase);

				for (int j = 0; j < numOfSmallTicks; j++) {
					g2.setStroke(stroke);
					base.setLocation(base.getX() + smallTickDist, baseLineLocY);
					pt.setLocation(pt.getX() + smallTickDist, baseLineLocY - this.smallTickHeight);

					if (smallTickDist > 100) {
						g2.drawString((j+1)*0.2 + 1 + "", (float)base.getX(), (float)base.getY() + textHeightFromBase);
					}

					if (base.getX() > frameWidth) {
						break;
					}
					g2.draw(new Line2D.Double(base, pt));
				}
				base.setLocation(base.getX() + smallTickDist, baseLineLocY);
				pt.setLocation(pt.getX() + smallTickDist, baseLineLocY - mainTickHeight);			
			}

		}

		public void setView(final int viewBeginSamples, final int viewEndSamples) {
			//System.out.println(viewBeginSamples + " " + viewEndSamples);
			this.viewBeginX =  (int)((double)(viewBeginSamples) / data.size() * frameWidth);
			this.viewEndX = (int)((double)(viewEndSamples) / data.size() * frameWidth);
			//System.out.println(viewBeginX + " " + viewEndX);
			repaint();
		}
	}//end class OverViewPanel

	/**
	 * Class to display the sound wave
	 */
	private class WavePanel extends JPanel 
	{
		private int selBeginPixel;
		private int selEndPixel;
		private int selBeginSample;
		private int selEndSample;
		private ArrayList<Selection> zoomStack;
		private ArrayList<SoundSample> points;
		private NumberFormat formatter;

		public WavePanel()
		{
			super(null);
			setBackground(backgroundColor);
			setPreferredSize(new Dimension(frameWidth, frameHeight));
			setSize(getPreferredSize());
			zoomStack = new ArrayList<Selection>();
			Selection selection = new Selection(1, data.size());
			zoomStack.add(selection);

			this.points = new ArrayList<SoundSample>();
			SelectionListener listener = new SelectionListener();
			addMouseListener(listener);
			addMouseMotionListener(listener);
			formatter = new DecimalFormat("#.##");
			//selBeginSample = 1;
			//selEndSample = data.size();
		}

		public void refresh(boolean updateData) {
			if (updateData) {
				myHelper.refresh();
			}
			this.points = getSamples(zoomStack.get(zoomStack.size() -1));
			repaint();
		}

		public boolean moreZoomOutScope() {
			return zoomStack.size() > 1;
		}

		public void zoomIn() {
			//System.out.println(selBeginPixel + " " + selEndPixel);  
			selBeginPixel = selEndPixel = -1;
			playSelectionBtn.setEnabled(false);
			Selection prevSel = zoomStack.get(zoomStack.size()-1);
			Selection currentSel = new Selection(prevSel.beginSelInSamples + selBeginSample - 1, prevSel.beginSelInSamples + selEndSample - 1);
			zoomStack.add(currentSel);

			this.points = getSamples(currentSel);
			zoomInBtn.setEnabled(false);
			zoomOutBtn.setEnabled(true);
			if(DEBUG) {
				System.out.println("samples per pixel: " + samplePerPixel);
				System.out.println("number of points: " + points.size());
			}
			repaint();

			//update the overview panel
			overViewPanel.setView(currentSel.beginSelInSamples, currentSel.endSelInSamples);
		}

		public void zoomTo(int begin, int end) {
			Selection selection = new Selection(begin, end);
			this.points = getSamples(selection);
			repaint();
			overViewPanel.setView(selection.beginSelInSamples, selection.endSelInSamples);
		}

		public void zoomOut() {
			selBeginPixel = selEndPixel = -1;
			playSelectionBtn.setEnabled(false);
			int lastIndex = zoomStack.size()-1;
			//System.out.println(lastIndex);
			if (lastIndex >= 1 ) {
				zoomStack.remove(lastIndex);
				Selection prevSelection = zoomStack.get(lastIndex-1);
				this.points = getSamples(prevSelection);
				if(DEBUG) {
					System.out.println("samples per pixel: " + samplePerPixel);
					System.out.println("number of points: " + points.size());
				}
				repaint();
				if (lastIndex == 1)
					zoomOutBtn.setEnabled(false);
				//update the overview panel
				overViewPanel.setView(prevSelection.beginSelInSamples, prevSelection.endSelInSamples);
				clearSelection();
			} 
		}
		
		// Reset the zoom level to maximum view, clear all stored zooms, etc...  Nate's guessing here...
		public void zoomReset() {
		   zoomStack.clear();
	      Selection selection = new Selection(1, data.size());
	      this.points = getSamples(selection);
	      zoomStack.add(selection);
	      zoomOutBtn.setEnabled(false);
	      clearSelection();
	      repaint();
	      overViewPanel.setView(selection.beginSelInSamples, selection.endSelInSamples);
		}

		public ArrayList<SoundSample> getSamples(Selection selection) {
			return getSampleAsArrayList(selection, this);
		}

		public void init() {
			this.points = getSamples(zoomStack.get(zoomStack.size() -1));

			if(DEBUG) {
				System.out.println("samples per pixel: " + samplePerPixel);
				System.out.println("number of points: " + points.size());
			}
			repaint();
		}


		public void paintComponent(Graphics g)
		{
			Rectangle selection = g.getClipBounds();

			//clear out the image
			Graphics2D g2 = (Graphics2D)g;
			g2.setBackground(backgroundColor);
			g2.clearRect((int)selection.getX(), (int)selection.getY(), 
					(int)selection.getWidth(), (int)selection.getHeight());

			//draw the selection if it exists
			if(selBeginPixel!=-1 && selEndPixel!=-1)
			{
				g2.setBackground(selectionColor);
				g2.clearRect(selBeginPixel, 0, 
						selEndPixel-selBeginPixel+1, this.getHeight());

			}

			if (this.points.size() > 0) {
				g2.setColor(waveColor);
				//draw the lines
				if (this.points.size() > stemThresh) {
					if (points.size() > frameWidth) { //draw contour
						//g2.setStroke(thickerStroke);
						for(int i = 0; i < this.points.size() - 1; i+=4){
							Point2D.Float pt1  = points.get(i).getDispPoint();
							//Point2D.Float pt2  = new Point2D.Float(pt1.x, pt1.y/2);
							Point2D.Float pt2  = points.get(i+1).getDispPoint();
							Point2D.Float pt3 = points.get(i+2).getDispPoint();
							Point2D.Float pt4 = points.get(i+3).getDispPoint();

							//							//Point2D.Float pt4  = new Point2D.Float(pt2.x, pt2.y/2);
							g2.draw(new Line2D.Float(pt1, pt2));
							//g2.setColor(waveLiteColor);
							g.setColor(waveLiteColor);
							g2.draw(new Line2D.Float(pt2, pt3));
							g2.setColor(waveColor);
							g2.draw(new Line2D.Float(pt3, pt4));
							//							g2.draw(new Line2D.Float(this.points.get(i).getDispPoint(),
							//									this.points.get(i+1).getDispPoint()));
							//							g2.draw(new Line2D.Float(this.points.get(i).getDispPoint(),
							//									this.points.get(i+1).getDispPoint()));
							//							g2.draw(new Line2D.Float(this.points.get(i).getDispPoint(),
							//									this.points.get(i+1).getDispPoint()));
						}

					} else { //draw line
						g2.setStroke(stroke);
						for(int i = 0; i < this.points.size() - 1; i++){
							g2.draw(new Line2D.Float(this.points.get(i).getDispPoint(),
									this.points.get(i+1).getDispPoint()));
						}
					}
				} else {// stem plot
					for(int i = 0; i < this.points.size(); i++)
					{
						//draw the stem
						SoundSample sample = this.points.get(i);
						g2.setStroke(thickerStroke);
						Point2D.Double base = new Point2D.Double(sample.getDispPoint().getX() + 10, Math.floor(this.getHeight())/2);
						Point2D.Double pt = new Point2D.Double(sample.getDispPoint().getX() + 10, sample.getDispPoint().getY());
						g2.draw(new Line2D.Float(base, pt));


						if (this.points.size() < zoomThresh) { 
							//draw the sample value
							float sampleValue = (float)sample.getSampleValue();
							float y = (float)sample.getDispPoint().getY();
							g2.setFont(textFont);	
							if (y < Math.floor(this.getHeight())/2) 
								y = y - 10;
							else 
								y = y + 10;
							//if (sample.getDispPoint().getX() > 0)
							g2.drawString(formatter.format(sampleValue), (float)sample.getDispPoint().getX(), y);

						}

						//draw the vertical bar in the array
						//						g2.setStroke(thickerStroke);
						//						base = new Point2D.Double(base.getX()-8, this.getHeight() - 10);
						//						pt = new Point2D.Double(base.getX(), this.getHeight() - 50);
						//						g2.draw(new Line2D.Double(base, pt));
						//						g2.drawString(formatter.format(points.get(i).getSampleIndex()), (float)base.getX(), (float)base.getY() - 10 );
					}
					//
					//					g2.setStroke(wideStroke);
					//					//draw the array cells
					//					g2.draw(new Line2D.Double(selection.getX(), 
					//							Math.floor(this.getHeight() - 30), 
					//							selection.getX()+selection.getWidth()-1,
					//							Math.floor(this.getHeight() - 30)));
					//					//draw the array cells
					//					g2.draw(new Line2D.Double(selection.getX(), 
					//							Math.floor(this.getHeight() - 10), 
					//							selection.getX()+selection.getWidth()-1,
					//							Math.floor(this.getHeight() - 10)));
				}

			}

			//draw the center line
			g2.setColor(barColor);
			g2.setStroke(new BasicStroke(1));
			g2.draw(new Line2D.Double(selection.getX(), 
					Math.floor(this.getHeight()/2), 
					selection.getX()+selection.getWidth()-1,
					Math.floor(this.getHeight()/2)));

			//			//draw the current position
			//			if (selection.getX()<currentPixelPosition && 
			//					currentPixelPosition<(selection.getX()+selection.getWidth()-1))
			//			{
			//				g2.setColor(barColor);
			//				g2.setStroke(new BasicStroke(1));
			//				g2.draw(new Line2D.Double(currentPixelPosition, 0, 
			//						currentPixelPosition, frameHeight));  
			//			}
		}

		public boolean moreZoomInScope() {
			return selBeginPixel > 0 && selEndPixel > 0;
		}
		public int getSelectionStartSample() {
			//System.err.println((int)(mousePressed * samplePerPixel) + zoomStack.get(zoomStack.size()-1).beginSelInSamples);
			return (int)(selBeginPixel * samplePerPixel) + zoomStack.get(zoomStack.size()-1).beginSelInSamples;
		}

		public int getSelectionEndSample() {
			//System.err.println((int)(mouseReleased * samplePerPixel) + zoomStack.get(zoomStack.size()-1).beginSelInSamples);
			return (int)(selEndPixel * samplePerPixel) + zoomStack.get(zoomStack.size()-1).beginSelInSamples;
		}



		private class SelectionListener extends MouseInputAdapter {
			int mousePressed;
			int mouseReleased;

			public void mousePressed(MouseEvent e) {
				mousePressed = e.getX();
			}

			public void mouseDragged(MouseEvent e) {
				update(e);
			}

			public void mouseReleased(MouseEvent e) {
				update(e);
			}


			public void update(MouseEvent e)
			{
				mouseReleased = e.getX();
				Selection currSelection = zoomStack.get(zoomStack.size()-1);

				if (mousePressed > mouseReleased) {
					selBeginPixel = mouseReleased;
					selEndPixel = mousePressed;
				} else {
					selBeginPixel = mousePressed;
					selEndPixel = mouseReleased;
				}


				if (selBeginPixel < 1) 
					selBeginPixel = 1;

				if (selEndPixel > frameWidth) {
					selEndPixel = frameWidth;
				}

				selBeginSample = (int)Math.floor(selBeginPixel * samplePerPixel);
				selEndSample = (int)Math.floor(selEndPixel * samplePerPixel);

				//				//stopped dragging outside the window.
				//				if(selEndSample > currSelection.endSelInSamples) {
				//				
				//				//System.err.println(selEndSample + " " + data.size());
				//					selEndSample = currSelection.endSelInSamples;
				//				}
				//
				//				//stopped dragging outside the window
				//				if(selBeginSample < currSelection.beginSelInSamples)
				//					selBeginSample = currSelection.beginSelInSamples;

				wavePanel.repaint();

				if (!myHelper.isPlaying()) {
					playSelectionBtn.setEnabled(true);

					if (points.size() > zoomStop) {
						zoomInBtn.setEnabled(true);
					}
				}
				if (DEBUG)
				   System.err.println(selBeginSample + " "  + selEndSample);
			}


		}

		private void clearSelection() {
			selBeginSample = 0;
			selEndSample = 0;
			zoomInBtn.setEnabled(false);
		}


	}//end class SamplingPanel



	private class Selection {

		int beginSelInSamples;
		int endSelInSamples;

		public Selection(int begin, int end) {
			this.beginSelInSamples = begin;
			this.endSelInSamples  = end;
		}

		public int getBegin() {
			return beginSelInSamples;
		}

		public int getEnd() {
			return endSelInSamples;
		}

	} //end class Selection

	private class SoundSample {

		private Point2D.Float dispPoint;
		private float sampleValue;
		private int sampleIndex;

		public SoundSample(Point2D.Float dispPoint, float sampleValue, int sampleIndex) {
			this.dispPoint = dispPoint;
			this.sampleValue = sampleValue;
			this.sampleIndex = sampleIndex;
		}

		public Point2D.Float getDispPoint() {
			return dispPoint;
		}

		public float getSampleValue() {
			return sampleValue;
		}

		public int getSampleIndex() {
			return sampleIndex;
		}
	}//end class SoundSample


}//end class Viewer


