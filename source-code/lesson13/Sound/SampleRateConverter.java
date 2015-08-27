import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
 

public class SampleRateConverter {
 
    public static void changeSamplingRate(File waveFile, File targetWaveFile) throws IOException {
        File tempOutputFile = new File(waveFile.getParentFile().getAbsolutePath() + "/.tmp_" + waveFile.getName());
        FileOutputStream tmpFileOutputStream = null;

        AudioFormat newAudioFormat;
        AudioFormat oldAudioFormat;
        AudioInputStream audioInputStream = null;
        FileInputStream fileInputStream = new FileInputStream(waveFile);
        byte[] buffer = new byte[1024];
        try {
            // Determin previous format and setup FileOutputStream:
            audioInputStream = AudioSystem.getAudioInputStream(waveFile);
            // The OutputStream to write to:
            tmpFileOutputStream = new FileOutputStream(tempOutputFile);
            // The previous AudioFormat
            oldAudioFormat = audioInputStream.getFormat();
            // The downsampled AudioFormat
            newAudioFormat = new AudioFormat((int) (oldAudioFormat.getSampleRate() / 2), oldAudioFormat.getSampleSizeInBits(), 1, true, false);
            audioInputStream = new AudioInputStream(fileInputStream, oldAudioFormat, (int) fileInputStream.available() / oldAudioFormat.getFrameSize());
            while (audioInputStream.read(buffer) > -1) {
                byte[] sampledBuffer = changeSampling(buffer, oldAudioFormat);
                tmpFileOutputStream.write(sampledBuffer);
            }
            tmpFileOutputStream.close();
            fileInputStream = new FileInputStream(tempOutputFile);
            audioInputStream = new AudioInputStream(fileInputStream, newAudioFormat, fileInputStream.available() / newAudioFormat.getFrameSize());
            // Skip some bytes at the beginning to prevent crackling noise.
            audioInputStream.skip(30);
            AudioSystem.write(audioInputStream, Type.WAVE, targetWaveFile);
            tempOutputFile.delete();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } finally {
            if (audioInputStream != null) {
                audioInputStream.close();
            }
            if (tmpFileOutputStream != null) {
                tmpFileOutputStream.flush();
            }
        }
    }
 
    private static byte[] changeSampling(byte[] rawData, AudioFormat format) {
        byte[] outputData;
        int currentSample;
        if (format.isBigEndian()) {
            throw new UnsupportedOperationException("Little-Endian files only!");
        }
        // 8-bit Mono
        if (format.getSampleSizeInBits() == 8 && format.getChannels() == 1) {
//            System.out.println("8-bit Mono");
            outputData = new byte[rawData.length / 2];
            for (int i = 0; (i * 2) < rawData.length; i++) {
                currentSample = rawData[(2 * i)];
                outputData[i] = (byte) currentSample;
            }
        // 16-bit Mono
        } else if (format.getSampleSizeInBits() == 16 && format.getChannels() == 1) {
//            System.out.println("16-bit Mono");
            outputData = new byte[rawData.length / 2];
            for (int i = 0; (i * 4) < rawData.length; i++) {
                currentSample = (rawData[4 * i] & 0xFF << 8) + rawData[(4 * i) + 1];
                outputData[(2 * i)] = (byte) (currentSample >>> 8);
                outputData[(2 * i) + 1] = (byte) currentSample;
            }
        // 8-bit Stereo to 8-bit Mono
        } else if (format.getSampleSizeInBits() == 8 && format.getChannels() == 2) {
//            System.out.println("8-bit Stereo");
            outputData = new byte[rawData.length / 4];
            for (int i = 0; (i * 4) < rawData.length; i++) {
                currentSample = rawData[(4 * i)];
                outputData[i] = (byte) currentSample;
            }
        } // 16-bit Stereo to 16-bit Mono
        else if (format.getSampleSizeInBits() == 16 && format.getChannels() == 2) {
//            System.out.println("16-bit Stereo");
            outputData = new byte[rawData.length / 4];
            for (int i = 0; (i * 8) < rawData.length; i++) {
                currentSample = (rawData[8 * i] & 0xFF << 8) + rawData[(8 * i) + 1];
                outputData[2 * i] = (byte) (currentSample >>> 8);
                outputData[(2 * i) + 1] = (byte) currentSample;
            }
        } else {
            throw new UnsupportedOperationException(format.getSampleSizeInBits() + " Bit, " + format.getChannels() + " Channel Audio Files not supported!");
        }
        return outputData;
    }
}
