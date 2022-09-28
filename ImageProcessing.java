import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;

public class ImageProcessing {
    public static void main(String[] args) {
 
        String fileName = "py_logo.png";
        int[][] imageData = imgToTwoD(
                "./python_logo.png");
        viewImageData(imageData);
        //
        // int[][] trimmed = trimBorders(imageData, 60);
        // int[][] negative = negativeColor(imageData);
        // int[][] horizontalStrech = stretchHorizontally(imageData);
        int[][] verticalStrech = stretchVertically(imageData);
        //
        // twoDToImage(trimmed, "trimmed_" + fileName);
        // twoDToImage(negative, "negative_" + fileName);
        // twoDToImage(horizontalStrech, "horizontalStretch_" + fileName);
        twoDToImage(verticalStrech, "verticalStretch_" + fileName);
        //
        // int[][] allFilters =
        // stretchHorizontally(shrinkVertically(colorFilter(negativeColor(trimBorders(invertImage(imageData),
        // 50)), 200, 20, 40)));
        // Painting with pixels
    }

    // Image Processing Methods
    // ================================================================================================
    public static int[][] trimBorders(int[][] imageTwoD, int pixelCount) {
        // 
        if (imageTwoD.length > pixelCount * 2 && imageTwoD[0].length > pixelCount * 2) {
            //
            int[][] trimmedImg = new int[imageTwoD.length - pixelCount * 2][imageTwoD[0].length - pixelCount * 2];
            //
            for (int i = 0; i < trimmedImg.length; i++) {
                //
                for (int j = 0; j < trimmedImg[i].length; j++) {
                    trimmedImg[i][j] = imageTwoD[i + pixelCount][j + pixelCount];
                }
                //-------
            }
            //-----------------------
            return trimmedImg;
        } else {
            System.out.println("Cannot trim that many pixels from the given image.");
            return imageTwoD;
        }
    }

    // ================================================================================================
    public static int[][] negativeColor(int[][] imageTwoD) {
        // scoped vars
        int[][] negativeImg = new int[imageTwoD.length][imageTwoD[0].length];
        // ----------------
        for (int i = 0; i < imageTwoD.length; i++) {
            //
            for (int u = 1; u < imageTwoD[i].length; u++) {
                //
                int[] rgba = getRGBAFromPixel(imageTwoD[i][u]);
                rgba[0] = 255 - rgba[0];
                rgba[1] = 255 - rgba[1];
                rgba[2] = 255 - rgba[2];
                negativeImg[i][u] = getColorIntValFromRGBA(rgba);
            }
            // -------
        }
        // --------------------
    return negativeImg;
    }

    // ================================================================================================
    public static int[][] stretchHorizontally(int[][] imageTwoD) {
        // doubles each pixel horizonally
        int[][] horizontalStrech = new int[imageTwoD.length][imageTwoD[0].length*2];
        //
        for (int i = 0; i < imageTwoD.length; i++) {
            //
            int pointerOne = 0;
            // 
            for (int u = 0; u < imageTwoD[0].length; u++) {
                // doubles each pixel horizontally
                horizontalStrech[i][pointerOne] = imageTwoD[i][u];
                horizontalStrech[i][pointerOne+1] = imageTwoD[i][u];
                pointerOne+=2;
            }

        }

        return horizontalStrech;
    }

    public static int[][] stretchVertically(int[][] imageTwoD) {
        // double each pixel vertically
        int[][] verticalStrech = new int[imageTwoD.length*2][imageTwoD[0].length];
         //
        int pointer=0;
        for (int i = 0; i < imageTwoD.length; i++) {
            //
            for (int u = 0; u < imageTwoD[0].length; u++) {
                //
                verticalStrech[pointer][u] = imageTwoD[i][u];
                verticalStrech[pointer+1][u] = imageTwoD[i][u];
                
            }
            pointer+=2;
        }
         return verticalStrech;
    }

    public static int[][] shrinkVertically(int[][] imageTwoD) {
        int[][]verticalShrink = new int[imageTwoD.length/2][imageTwoD[0].length];

        return verticalShrink;
    }

    public static int[][] invertImage(int[][] imageTwoD) {
        // TODO: Fill in the code for this method
        return null;
    }

    public static int[][] colorFilter(int[][] imageTwoD, int redChangeValue, int greenChangeValue,
            int blueChangeValue) {
        // TODO: Fill in the code for this method
        return null;
    }

    // Painting Methods
    public static int[][] paintRandomImage(int[][] canvas) {
        // TODO: Fill in the code for this method
        return null;
    }

    public static int[][] paintRectangle(int[][] canvas, int width, int height, int rowPosition, int colPosition,
            int color) {
        // TODO: Fill in the code for this method
        return null;
    }

    public static int[][] generateRectangles(int[][] canvas, int numRectangles) {
        // TODO: Fill in the code for this method
        return null;
    }

    // Utility Methods
    public static int[][] imgToTwoD(String inputFileOrLink) {
        try {
            BufferedImage image = null;
            //
            if (inputFileOrLink.substring(0, 4).toLowerCase().equals("http")) {
                URL imageUrl = new URL(inputFileOrLink);
                image = ImageIO.read(imageUrl);
                if (image == null) {
                    System.out.println("Failed to get image from provided URL.");
                }
            } else {
                image = ImageIO.read(new File(inputFileOrLink));
            }
            //
            int imgRows = image.getHeight();
            int imgCols = image.getWidth();
            int[][] pixelData = new int[imgRows][imgCols];
            for (int i = 0; i < imgRows; i++) {
                for (int j = 0; j < imgCols; j++) {
                    pixelData[i][j] = image.getRGB(j, i);
                }
            }
            return pixelData;
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getLocalizedMessage());
            return null;
        }
    }

    public static void twoDToImage(int[][] imgData, String fileName) {
        try {
            int imgRows = imgData.length;
            int imgCols = imgData[0].length;
            BufferedImage result = new BufferedImage(imgCols, imgRows, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < imgRows; i++) {
                for (int j = 0; j < imgCols; j++) {
                    result.setRGB(j, i, imgData[i][j]);
                }
            }
            File output = new File(fileName);
            ImageIO.write(result, "jpg", output);
        } catch (Exception e) {
            System.out.println("Failed to save image: " + e.getLocalizedMessage());
        }
    }

    public static int[] getRGBAFromPixel(int pixelColorValue) {
        Color pixelColor = new Color(pixelColorValue);
        return new int[] { pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue(), pixelColor.getAlpha() };
    }

    public static int getColorIntValFromRGBA(int[] colorData) {
        if (colorData.length == 4) {
            Color color = new Color(colorData[0], colorData[1], colorData[2], colorData[3]);
            return color.getRGB();
        } else {
            System.out.println("Incorrect number of elements in RGBA array.");
            return -1;
        }
    }

    public static void viewImageData(int[][] imageTwoD) {
        if (imageTwoD.length > 3 && imageTwoD[0].length > 3) {
            int[][] rawPixels = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    rawPixels[i][j] = imageTwoD[i][j];
                }
            }
            System.out.println("Raw pixel data from the top left corner.");
            System.out.print(Arrays.deepToString(rawPixels).replace("],", "],\n") + "\n");
            int[][][] rgbPixels = new int[3][3][4];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    rgbPixels[i][j] = getRGBAFromPixel(imageTwoD[i][j]);
                }
            }
            System.out.println();
            System.out.println("Extracted RGBA pixel data from top the left corner.");
            for (int[][] row : rgbPixels) {
                System.out.print(Arrays.deepToString(row) + System.lineSeparator());
            }
        } else {
            System.out.println("The image is not large enough to extract 9 pixels from the top left corner");
        }
    }
}