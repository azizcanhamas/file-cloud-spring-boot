package net.cybersoft.Utils;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

// Resim verileri uzerinde sıkıştırma ve cozumleme
// islemleri icin kullanilacak sinif
public class ImageUtils {

    // Resim verisini Deflater sıkıştırma algoritması ile sıkıştırır.
    // Parametre olarak byteArray ve byteArray doner.
    public static byte[] compressImage(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        }catch (Exception ignored){}
        return outputStream.toByteArray();
    }


    // Deflater algoritmasi ile sıkıştırılmış olan veritabanındaki resim
    // verisinin sıkıştırılmamış halini byteArray olarak doner.
    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }

}
