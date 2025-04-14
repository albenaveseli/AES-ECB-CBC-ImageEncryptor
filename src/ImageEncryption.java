import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.util.Arrays;
//Encryption of the image using the Electronic Code Book (ECB) mode and the Cipher Block Chaining (CBC) mode.
public class ImageEncryption {
    public static void main(String[] args) throws Exception {
        String inputPath = "C:\\Users\\CCC\\Desktop\\penguin.bmp";
        String ecbOutputPath = "C:\\Users\\CCC\\Desktop\\encrypted_ecb.bmp";
        String cbcOutputPath = "C:\\Users\\CCC\\Desktop\\encrypted_cbc.bmp";
        String ecbDecryptedPath = "C:\\Users\\CCC\\Desktop\\decrypted_ecb.bmp";
        String cbcDecryptedPath = "C:\\Users\\CCC\\Desktop\\decrypted_cbc.bmp";

        // 1. Lexo imazhin
        byte[] imageBytes = readFile(inputPath);
        byte[] header = Arrays.copyOfRange(imageBytes, 0, 54);
        byte[] data = Arrays.copyOfRange(imageBytes, 54, imageBytes.length);

        // 2. Padding
        int paddingLength = 16 - (data.length % 16);
        byte[] paddedData = Arrays.copyOf(data, data.length + paddingLength);

        // 3. Çelësi dhe IV
        byte[] keyBytes = "1234567890abcdef".getBytes(); // 16-byte key
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec iv = new IvParameterSpec("abcdef9876543210".getBytes()); // CBC mode

        // 4. ECB Encryption
        Cipher ecbCipher = Cipher.getInstance("AES/ECB/NoPadding");
        ecbCipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedECB = ecbCipher.doFinal(paddedData);
        writeFile(ecbOutputPath, header, encryptedECB);

        // 5. CBC Encryption
        Cipher cbcCipher = Cipher.getInstance("AES/CBC/NoPadding");
        cbcCipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] encryptedCBC = cbcCipher.doFinal(paddedData);
        writeFile(cbcOutputPath, header, encryptedCBC);

        // 6. ECB Decryption
        ecbCipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedECB = ecbCipher.doFinal(encryptedECB);
        writeFile(ecbDecryptedPath, header, decryptedECB);

        // 7. CBC Decryption
        cbcCipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] decryptedCBC = cbcCipher.doFinal(encryptedCBC);
        writeFile(cbcDecryptedPath, header, decryptedCBC);

        System.out.println("Enkriptimi dhe Dekriptimi i imazheve përfundoi me sukses!");
    }

    static byte[] readFile(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        byte[] data = fis.readAllBytes();
        fis.close();
        return data;
    }

    static void writeFile(String path, byte[] header, byte[] data) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(header); // header nuk enkriptohet
        fos.write(data);   // vetëm pikselat enkriptohen/dekriptohen
        fos.close();
    }
}
