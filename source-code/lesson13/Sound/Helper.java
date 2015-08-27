import javax.sound.sampled.*;

import java.io.*;
import java.util.ArrayList;

public class Helper implements LineListener{

	private static final int DEFAULT_SAMPLE_RATE = 22050;

	private static final int DEFAULT_BITS_PER_SAMPLE = 16;

	private static final boolean DEFAULT_BIG_INDIAN = false;

	private static final boolean DEFAULT_SIGNED = true;

	private static final int DEFAULT_NUM_CHANNELS = 1;

	private byte[] buffer;
	
	private ArrayList<Integer> data = new ArrayList<Integer>();

	private AudioFileFormat audioFileFormat = null;

	private Playback playback = null;

	private Viewer viewer = null;

	public Helper() {
	}

	public Helper(File file) {
		if(!file.isFile()) {
			System.err.println("The audio file is invalid.  Please try again.");  
		}

		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
			if(file.getName().toLowerCase().endsWith(".wav"))
			{
				audioFileFormat = 
					new AudioFileFormat(AudioFileFormat.Type.WAVE,
							audioInputStream.getFormat(),
							(int)audioInputStream.getFrameLength());
			}
			else if(file.getName().toLowerCase().endsWith(".au"))
			{
				audioFileFormat = 
					new AudioFileFormat(AudioFileFormat.Type.AU,
							audioInputStream.getFormat(),
							(int)audioInputStream.getFrameLength());
			}
			else if (file.getName().toLowerCase().endsWith(".aif")||
					file.getName().toLowerCase().endsWith(".aiff"))
			{
				audioFileFormat = 
					new AudioFileFormat(AudioFileFormat.Type.AIFF,
							audioInputStream.getFormat(),
							(int)audioInputStream.getFrameLength());
			}

			if((audioInputStream.getFrameLength() * 
					audioInputStream.getFormat().getFrameSize()) > Integer.MAX_VALUE){
				System.err.println("The sound is too long. Please try using a shorter sound.");
			}
			
			int bufferSize = (int)audioInputStream.getFrameLength() *audioInputStream.getFormat().getFrameSize();
			buffer = new byte[bufferSize];
			
			int numBytesRead = 0;
			int offset = 0;
			while(true) {
				numBytesRead = audioInputStream.read(buffer, offset, bufferSize);
				if(numBytesRead == -1)//no more data
					break;
				else
					offset += numBytesRead;
			}
			
			setData();
		} catch(Exception e) {
			System.err.println("Unable to read from file " + file.getName() + e);
		}
	} 

	public Helper(int numFrames)
	{
		int bytesPerSample = DEFAULT_BITS_PER_SAMPLE / 8; 
		AudioFormat audioFormat = new AudioFormat(DEFAULT_SAMPLE_RATE, DEFAULT_BITS_PER_SAMPLE, DEFAULT_NUM_CHANNELS, 
				DEFAULT_SIGNED, DEFAULT_BIG_INDIAN);

		int lengthInBytes = numFrames * bytesPerSample;
		audioFileFormat = new AudioFileFormat(AudioFileFormat.Type.WAVE, audioFormat, numFrames);
		buffer = new byte[lengthInBytes];
		setData();
	}

	public Helper(Helper sound)
	{
		this.audioFileFormat = sound.audioFileFormat;
		if (sound.buffer != null)
		{
			this.buffer = new byte[sound.buffer.length];
			for (int i = 0; i < sound.buffer.length; i++)
				this.buffer[i] = sound.buffer[i];
			setData();

		}
	}

	public Helper(byte[] buffer, AudioFileFormat audioFileFormat) {
		this.audioFileFormat = audioFileFormat;
		this.buffer = new byte[buffer.length];
		for (int i = 0; i < buffer.length; i++) {
			this.buffer[i] = buffer[i];
		}
		setData();
	}

	/*
	 * Returns the content of the sound as an Integer arraylist.
	 */
	public ArrayList<Integer> getData() {
		return data;
	}

	
	/*
	 * Sets a new ArrayList<integer> as the data source, keeping original audioformat (samplerate, etc)
	 */
	public void setData(ArrayList<Integer> data) {
	   this.data = data;
	   refresh();
	}
	
	
	/*
	 * Returns the converted sampling rate. This may be different to the sampling
	 * rate of the original file
	 */
	public double getSamplingRate() {
		return audioFileFormat.getFormat().getSampleRate();
	}
	
	public int getBitsPerSample() {
	   return audioFileFormat.getFormat().getSampleSizeInBits();
	}

	/*
	 * Returns the maximum value of the samples encoded in this sound.
	 * TODO: really, this should just be 16 for this curriculum.  The students don't know to work
	 * with different sizes!
	 */
	public float getMaxSampleValue() {
		AudioFormat format = audioFileFormat.getFormat();
		float maxValue = -1;

		if(format.getSampleSizeInBits() == 8)
		{
			maxValue = (float)Math.pow(2,7);
		}
		else if(format.getSampleSizeInBits() == 16)
		{
			maxValue = (float)Math.pow(2, 15);
		}
		else if(format.getSampleSizeInBits() == 24)
		{
			maxValue = (float)Math.pow(2, 23);
		}
		else if(format.getSampleSizeInBits() == 32)
		{
			maxValue = (float)Math.pow(2, 31);
		}
		else
		{
			System.err.println("InvalidSampleSize");
		}
		return maxValue;
	}
	/*
	 * Need a reference to the viewer. Viewer listens to sound data line events
	 * such as opening and closing of a line.
	 */
	public void setViewer(Viewer viewer) {
		this.viewer = viewer;
	}

	/*
	 * Calling this method will update the byte array buffer with the content
	 * of the int arraylist. The int arraylist is open to the world 
	 * for modification whereas the byte array is internal to the Helper instance, 
	 * so they may be out of sync through external edits to the sound.
	 * The byte array must be resynched before calling play or saving the content to file. 
	 * Those methods assume the byte array is up to date.
	 */
	public void refresh() {
	   // this is the ratio of the byte buffer length to the arraylist length (basically, how many bytes in each sample).
	   int lengthRatio = getBitsPerSample()/8;
	   // NATE changed this -- so we don't have extra stuff at the end of buffer if it should simply shrink.
	   //   some sort of range determination should avoid care of this -- but it isn't, and who knows how to fix...
	   if (viewer.DEBUG)
	      System.err.println("Data size is " + data.size() +  "...  buffer length is " + buffer.length + "...  length ratio is " + lengthRatio);
	   if (buffer.length != (data.size() * lengthRatio)) {
	      // uh oh, length of array has changed...
			buffer = new byte[data.size() * lengthRatio];
			viewer.zoomReset();
		} 
		for (int i = 0; i < data.size(); i++) {
			setSampleValue(i, data.get(i));
		}
	}
	
	/*
	 * Creates a new audio stream for playing to writing to disk
	 */
	private AudioInputStream createAudioInputStream() {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
		int frameSize = audioFileFormat.getFormat().getFrameSize();

		AudioInputStream audioInputStream = 
			new AudioInputStream(inputStream, audioFileFormat.getFormat(), 
					buffer.length/frameSize);
		return audioInputStream;
	}

	/*
	 * Write the byte array buffer to disk
	 */
	public void writeToFile(String outFileName) {
		AudioInputStream audioInputStream = createAudioInputStream();
		AudioFileFormat.Type type = audioFileFormat.getType();
		try {
			audioInputStream.reset();
			File file = new File(outFileName);
			if(!file.exists()){
				file.createNewFile();
				AudioSystem.write(audioInputStream, type, file);
				audioInputStream.close();
			}
		}catch(IOException e) {
			System.err.println("Error writing to file.\n" + e);
		}

	}

	/*
	 * Play the sound. This maybe called to play a sound from a beginning
	 * or from somewhere in the middle from a previous invocation of play()
	 * followed by pause().
	 */
	public void play()
	{
		if (playback == null) { //if we are playing from the beginning
			playback = new Playback(this, this);
		}
		
		playback.start(); 
	}

	/*
	 * Stop playing. The audio may be playing or paused.
	 */
	public void stop() {
		playback.stop();
		playback = null;
	}

	/*
	 * Pause playing
	 */
	public void pause() {
		playback.pause();
	}
	
	
	/*
	 * Called to play a selected range of the audio. 
	 */
	public void playInRange(int startFrame,  int endFrame) {
		
		//check we are in valid range
		if(startFrame < 0 || endFrame < startFrame || endFrame >= audioFileFormat.getFrameLength()) {
			System.err.println("The range you specified are invalid.");
		}
		
		int frameSize = audioFileFormat.getFormat().getFrameSize();
		int durInFrames = (endFrame - startFrame) + 1;
		int newFrameSize = durInFrames * frameSize;
		byte[] newSoundBuff = new byte[newFrameSize];
		for(int i = 0; i < newFrameSize ; i++) {
			newSoundBuff[i] = buffer[(startFrame*frameSize) + i];
		}

		//create a new helper instance with the data in the selected range
		//for playing. 
		Helper newSound = new Helper();
		newSound.buffer = newSoundBuff;
		newSound.audioFileFormat = new AudioFileFormat(this.audioFileFormat.getType(), this.audioFileFormat.getFormat(), durInFrames);
		
		//IMPORTANT!!! we need to pass the newSound as the first argument so playback gets 
		//the data from the selected range. Must pass "this" helper as the second argument to listen 
		//for play back end events. 
		playback = new Playback(newSound , this);
		playback.start();
	}

	/*
	 * Determines if there is an active playback associated with the sound.
	 */
	public boolean isPlaying() {
		return (playback != null);
	}

	/*
	 * Listens for Line CLOSE event
	 * @see javax.sound.sampled.LineListener#update(javax.sound.sampled.LineEvent)
	 */
	public void update(LineEvent e) {
		if(e.getType().equals(LineEvent.Type.CLOSE)){// && !e.getLine().isOpen()){
			//IMPORTANT! Must set playback to null once the sound has finished playing
			//either through natural completion or the user hitting the stop button.
			//We use nullity of playback to determine if we should create a playback to
			//play from the beginning or continue the playback. A cleaner solution maybe 
			//called for.
			playback = null;
		}
	}

	/*
	 * Converts the data as a byte array into an integer array
	 * for ease of manipulation.
	 */
	private void setData() {
		//data.clear();
		for (int i = 0; i < audioFileFormat.getFrameLength() ; i++) {
			data.add(new Integer(getSampleValue(i)));
		}
	}
	
	/*
	 * Returns the data at the given frame as a byte array
	 */
	private byte[] getFrame(int frameNum)  
	{
		checkFrameNum(frameNum);

		int frameSize = audioFileFormat.getFormat().getFrameSize();
		byte[] theFrame = new byte[frameSize];
		for (int i = 0; i < frameSize; i++) {
			theFrame[i] = buffer[frameNum*frameSize+i];
		}
		return theFrame;
	}

	/*
	 * Sets the data at the given frame number with the data
	 * in the byte array
	 */
	private void setFrame(int frameNum, byte[] theFrame) {
		checkFrameNum(frameNum);
		int frameSize = audioFileFormat.getFormat().getFrameSize();
		for(int i = 0; i < frameSize; i++){
			buffer[frameNum*frameSize+i] = theFrame[i];
		}
	}

	/*
	 * Sets the data at the given frame number with the 
	 * int sample
	 */
	private void setSampleValue(int frameNum, int sample) 
	{
		byte[] theFrame = getFrame(frameNum);
		sampleToFrame(sample, theFrame);
		setFrame(frameNum, theFrame);
	}
	
	/*
	 * Returns the sample at the input frame number as an int
	 */
	private int getSampleValue(int frameNum)  
	{
		checkFrameNum(frameNum);
		byte[] theFrame = getFrame(frameNum);
		return frameToSample(theFrame);
	}
	
	/*
	 * Check the frame number is in range.
	 */
	private void checkFrameNum(int frameNum) {
		if(frameNum < 0 || frameNum >= audioFileFormat.getFrameLength()) {
			System.err.println("The frame number you specified is invalid." + frameNum);
		}
	}
	
	/*
	 * Converts a sample in int to byte array
	 */
	private void sampleToFrame(int sample, byte[] theFrame) {
		AudioFormat format = audioFileFormat.getFormat();
		int sampleSizeInBits = format.getSampleSizeInBits();
		boolean isBigEndian = format.isBigEndian();


		if(format.getEncoding().equals(AudioFormat.Encoding.PCM_SIGNED))
		{
			if(sampleSizeInBits == 8) {
				theFrame[0] = (byte)sample;
			}
			else if(sampleSizeInBits == 16)//2 bytes, first 2 cells in array
			{
				TConversionTool.intToBytes16(sample, theFrame, 0, isBigEndian);
			}
			else if(sampleSizeInBits == 24)
			{
				TConversionTool.intToBytes24(sample, theFrame, 0, isBigEndian);
			}
			else if(sampleSizeInBits == 32)
			{
				TConversionTool.intToBytes32(sample, theFrame, 0, isBigEndian);
			}
			else
			{
				System.err.println("Unsupported audio encoding.  The sample"+
				"size is not recognized as a standard format");
			}
		}//if format == PCM_SIGNED
		else if(format.getEncoding().equals(AudioFormat.Encoding.PCM_UNSIGNED))
		{
			if(sampleSizeInBits == 8)
			{
				theFrame[0] = TConversionTool.intToUnsignedByte(sample);
			}
			else if(sampleSizeInBits == 16)
			{
				TConversionTool.intToUnsignedBytes16(sample, theFrame, 0, isBigEndian);
			}
			else if(sampleSizeInBits == 24)
			{
				TConversionTool.intToUnsignedBytes24(sample, theFrame, 0, isBigEndian);
			}
			else if(sampleSizeInBits == 32)
			{
				TConversionTool.intToUnsignedBytes32(sample, theFrame, 0, isBigEndian);
			}

			else
			{
				System.err.println("Unsupported audio encoding.  The sample"+
						" size is not recognized as a standard "+
				"format.");
			}
		}
		else if(format.getEncoding().equals(AudioFormat.Encoding.ALAW))
		{
			if((sample>Short.MAX_VALUE)||(sample<Short.MIN_VALUE))
				System.err.println("You are trying to set the sample value to: "+
						sample + ", but the maximum value for a sample"+
						" in this format is: "+Short.MAX_VALUE+
						", and the minimum value is: "+Short.MIN_VALUE+
				".  Please choose a value in that range.");
			theFrame[0] = TConversionTool.linear2alaw((short)sample);
		}
		else if(format.getEncoding().equals(AudioFormat.Encoding.ULAW))
		{

			if((sample>Short.MAX_VALUE)||(sample<Short.MIN_VALUE))
				System.err.println("You are trying to set the sample value to: "+
						sample + ", but the maximum value for a sample"+
						" in this format is: "+Short.MAX_VALUE+
						", and the minimum value is: "+Short.MIN_VALUE+
				".  Please choose a value in that range.");
			theFrame[0] = TConversionTool.linear2ulaw((short)sample);
		}
		else
		{
			System.err.println("unsupported audio encoding: " + 
					format.getEncoding() + ".  Currently only PCM, " +
					"ALAW and ULAW are supported.  Please try again" +
			"with a different file.");
		}
	}

	
	/* 
	 * Converts a byte array to an int sample
	 */
	private int frameToSample(byte[] theFrame) {
		AudioFormat format = audioFileFormat.getFormat();
		int sampleSizeInBits = format.getSampleSizeInBits();
		boolean isBigEndian = format.isBigEndian();

		if(format.getEncoding().equals(AudioFormat.Encoding.PCM_SIGNED))
		{
			if(sampleSizeInBits == 8)//8 bits == 1 byte
				return theFrame[0];
			else if(sampleSizeInBits == 16)
				return TConversionTool.bytesToInt16(theFrame, 0, 
						isBigEndian);
			else if(sampleSizeInBits == 24)
				return TConversionTool.bytesToInt24(theFrame, 0, 
						isBigEndian);
			else if(sampleSizeInBits == 32)
				return TConversionTool.bytesToInt32(theFrame, 0, 
						isBigEndian);
			else
			{
				System.err.println("Unsupported audio encoding.  The sample "+
						"size is not recognized as a standard "+ 
				"format.");
				return -1;
			}
		}
		else if(format.getEncoding().equals(AudioFormat.Encoding.PCM_UNSIGNED))
		{
			if(sampleSizeInBits == 8)
				return TConversionTool.unsignedByteToInt(theFrame[0])-
				(int)Math.pow(2,7);
			else if(sampleSizeInBits == 16)
				return TConversionTool.unsignedByteToInt16(theFrame, 0, 
						isBigEndian)-
						(int)Math.pow(2, 15);
			else if(sampleSizeInBits == 24)
				return TConversionTool.unsignedByteToInt24(theFrame, 0, 
						isBigEndian)-
						(int)Math.pow(2, 23);
			else if(sampleSizeInBits == 32)
				return TConversionTool.unsignedByteToInt32(theFrame, 0, 
						isBigEndian)-
						(int)Math.pow(2, 31);
			else
			{
				System.err.println("Unsupported audio encoding.  The sample "+
						"size is not recognized as a standard "+
				"format.");
				return -1;
			}
		}
		else if(format.getEncoding().equals(AudioFormat.Encoding.ALAW))
		{
			return TConversionTool.alaw2linear(buffer[0]);
		}
		else if(format.getEncoding().equals(AudioFormat.Encoding.ULAW))
		{
			return TConversionTool.ulaw2linear(buffer[0]);
		}
		else
		{
			System.err.println("unsupported audio encoding: " + 
					format.getEncoding() + ".  Currently only PCM, " +
					"ALAW and ULAW are supported.  Please try again" +
			"with a different file.");
			return -1;
		}
	}
	
	

	/*
	 * Class that implements the runnable that plays a given range of a sound
	 */
	private class Playback implements Runnable
	{

		private static final int BUFFER_SIZE = 8000;

		private SourceDataLine line = null;

		private Helper sound = null;
		private Helper listener = null;

		AudioInputStream audioInputStream  = null;

		private Thread playThread  = null;


		public Playback(Helper sound, Helper listener)
		{
			this.sound = sound;
			this.listener = listener;
		}

		private void shutDown(String message) 
		{
			if (message != null){
				System.err.println(message);
			} else {
				stop();
				//stop();
			}
		}

		public void start() {
			if (playThread == null) {
				playThread = new Thread(this);
				playThread.start();
			} else {
				line.start();
			}
		}

		public void pause() {
			line.stop();
		}

		public void stop() {
			try {
				audioInputStream.close();
			} catch (IOException e) {
				System.err.println("Error stopping the sound \n" + e);
			}

			if (line.isOpen()) {
				line.stop();
				line.flush();
				line.close();
			}
		}

		public void run()
		{
			AudioFileFormat audioFileFormat = sound.audioFileFormat;

			//get something to play
			audioInputStream = sound.createAudioInputStream();
			if(audioInputStream == null)
			{
				shutDown("There is no audio to play");
				return;
			}

			//reset stream to the beginning
			try {
				audioInputStream.reset();
			} catch(Exception e) {
				shutDown("Unable to reset the stream\n" + e);
				return;
			}

			DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFileFormat.getFormat());
			if(!AudioSystem.isLineSupported(info))
			{
				shutDown("Line " + info + " is not supported.");
				return;
			}
			try {
				line = (SourceDataLine) AudioSystem.getLine(info);
				if(viewer != null) 
					line.addLineListener(viewer);
				line.addLineListener(listener);
				line.open(audioFileFormat.getFormat(), BUFFER_SIZE);
			} catch(LineUnavailableException e) {
				shutDown("Unable to open the line: " + e);
				return;
			}

			//play back the captured data
			int bufferLengthInBytes = line.getBufferSize();
			byte[] data = new byte[bufferLengthInBytes];
			int numBytesRead = 0;

			//start the source data line and begin playing
			line.start();

			while(line.isOpen())
			{
				//System.err.println("here3");

				try {
					if((numBytesRead = audioInputStream.read(data)) == -1)
					{
						//System.err.println(numBytesRead);
						break;
					}
					int numBytesRemaining = numBytesRead;
					//System.err.println("before writing");

					while(numBytesRemaining > 0 && line.isOpen()) {
						numBytesRemaining -= line.write(data, 0, numBytesRemaining);
					}//while
					//System.err.println("after writing");

					//System.out.println(line.isOpen() + " " + line.isRunning() + line.isActive());
				} catch(Exception e) {
					System.err.println("Error during playback: " + e);
					break;
				}//catch
				//System.err.println("here");
			}

			//System.err.println("here2");

			line.drain();
			shutDown(null);
		}//run()
	}

	
} // end of Helper class