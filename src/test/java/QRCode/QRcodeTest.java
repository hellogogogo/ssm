package QRCode; /**
 * @Author: liuyu
 * @Date: 2021/5/25
 */

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QRcodeTest {

    private static String content = "https://baike.baidu.com/item/%E5%97%B7%E5%A4%A7%E5%96%B5/19817560?fr=aladdin";


    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    /**
     * 二维码
     */
    @Test
    public void methods() {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //设置白边
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300, hints);
            BufferedImage image = toBufferedImage(bitMatrix);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            //输出二维码图片流
            try {
                ImageIO.write(image, "png", outputStream);
                System.out.println("data:image/jpeg;base64,"+ org.apache.commons.codec.binary.Base64.encodeBase64String(outputStream.toByteArray()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (WriterException e1) {
            e1.printStackTrace();
        }
    }

    private BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }
}