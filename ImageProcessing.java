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
        // viewImageData(imageData);
        // -------------------------------------------------------------
        // int[][] trimmed = trimBorders(imageData, 60);
        // twoDToImage(trimmed, "trimmed_" + fileName);
        // ------------
        // int[][] negative = negativeColor(imageData);
        // twoDToImage(negative, "negative_" + fileName);
        // ------------
        // int[][] horizontalStrech = stretchHorizontally(imageData);
        // twoDToImage(horizontalStrech, "horizontalStretch_" + fileName);
        // ------------
        // int[][] verticalStrech = stretchVertically(imageData);
        // twoDToImage(verticalStrech, "verticalStretch_" + fileName);
        // ------------
        // int[][] horizonalShrink = shrinkHorizontally(imageData);
        // twoDToImage(horizonalShrink, "horizonalShrink_" + fileName);
        // ------------
        // int[][] verticalShrink = shrinkVertically(imageData);
        // twoDToImage(verticalShrink, "verticalShrink_" + fileName);
        // -----------
        // int[][] inverted = invertImage(imageData);
        // twoDToImage(inverted, "inverted_" + fileName);
        // -------------------------------------------------------------
        // int[][] colorFilter = colorFilter(imageData, 125, 0, 0, 4);
        // twoDToImage(colorFilter, "colorFilter_" + fileName);
        // -------------------------------------------------------------
        // int [][] paintedRandomImage = paintRandomImage(imageData);
        // twoDToImage(paintedRandomImage, "random_img_" + fileName);
        // -------------------------------------------------------------
        // int [][] paintedRectangle = paintRectangle(imageData, 1600, 600,
        // 400, 400, 222);
        // twoDToImage(paintedRectangle, "rectangleImg_"+fileName);
        // -------------------------------------------------------------
        int [][] generateRectangles(canvas, width, height, rowPosition, colPosition,
        color)
        twoDToImage(generateRectangles, "some_rectangles");
        // -------------------------------------------------------------
        // ##
        // ------------ !! Stretch Goals !! ------------
        // ##
        // -------------------------------------------------------------
        // int [][] paintedCircle = paintCircle(canvas, width, height, rowPosition,
        // colPosition, color)
        // twoDToImage(paintedCircle, "circleImg_"+fileName);
        // // -------------------------------------------------------------
        // int [][] paintedTriangle = paintTriangle(canvas, width, height, rowPosition,
        // colPosition, color)
        // twoDToImage(paintedTriangle, "triangleImg_"+fileName);
        // // -------------------------------------------------------------
        // int [][] paintedEllipse = paintEllipse(canvas, width, height, rowPosition,
        // colPosition, color)
        // twoDToImage(paintedEllipse, "ellipseImg_"+fileName);
        // // -------------------------------------------------------------
        // int [][] paintedLine = paintLine(canvas, width, height, rowPosition,
        // colPosition, color)
        // twoDToImage(paintedLine, "lineImg_"+fileName);
        // // -------------------------------------------------------------
        // int [][] paintedCurve = paintCurve(canvas, width, height, rowPosition,
        // colPosition, color)
        // twoDToImage(paintedCurve, "curveImg_"+fileName);
        // // -------------------------------------------------------------
        // int [][] paintedText = paintText(canvas, width, height, rowPosition,
        // colPosition, color)
        // twoDToImage(paintedText, "textImg_"+fileName);
        // // -------------------------------------------------------------
        // int [][] paintedImage = paintImage(canvas, width, height, rowPosition,
        // colPosition, color)
        // twoDToImage(paintedImage, "imageImg_"+fileName);
        // // -------------------------------------------------------------
        // int[][] gaussianBlur = gaussianBlur(imageData);
        // twoDToImage(gaussianBlur, "gaussianBlur_" + fileName);
        // // -------------------------------------------------------------
        // int[][] sobel = sobel(imageData);
        // twoDToImage(sobel, "sobel_" + fileName);
        // // -------------------------------------------------------------
        // int[][] edgeDetection = edgeDetection(imageData);
        // twoDToImage(edgeDetection, "edgeDetection_" + fileName);
        // // -------------------------------------------------------------
        // int[][] scharr = scharr(imageData);
        // twoDToImage(scharr, "scharr_" + fileName);
        // -------------------------------------------------------------
        //
        // ##
        // {Project End}
        // ##
        // int[][] allFilters =
        // stretchHorizontally(shrinkVertically(colorFilter(negativeColor(trimBorders(invertImage(imageData),
        // 50)), 200, 20, 40)));
        // Painting with pixels
    }

    // ================================================================================================
    // ## Image Processing Methods
    public static int[][] trimBorders(int[][] imageTwoD, int pixelCount) {
        if (imageTwoD.length > pixelCount * 2 && imageTwoD[0].length > pixelCount * 2) {
            int[][] trimmedImg = new int[imageTwoD.length - pixelCount * 2][imageTwoD[0].length - pixelCount * 2];
            // ----
            for (int v = 0; v < trimmedImg.length; v++) {
                for (int h = 0; h < trimmedImg[v].length; h++) {
                    trimmedImg[v][h] = imageTwoD[v + pixelCount][h + pixelCount];
                } // -------
            } // -----------------------
              // RETURN
            return trimmedImg;
            // ------------------------
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
        try {
            for (int v = 0; v < imageTwoD.length; v++) {
                //
                for (int h = 1; h < imageTwoD[v].length; h++) {
                    //
                    int[] rgba = getRGBAFromPixel(imageTwoD[v][h]);
                    rgba[0] = 255 - rgba[0];
                    rgba[1] = 255 - rgba[1];
                    rgba[2] = 255 - rgba[2];
                    negativeImg[v][h] = getColorIntValFromRGBA(rgba);
                } // -------
            } // --------------------
            return negativeImg;
            // --!!--
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    // ================================================================================================
    public static int[][] stretchHorizontally(int[][] imageTwoD) {
        // doubles each pixel horizonally
        int[][] horizontalStrech = new int[imageTwoD.length][imageTwoD[0].length * 2];
        // ----
        try {
            for (int v = 0; v < imageTwoD.length; v++) {
                int hPointer = 0;
                // ----
                for (int h = 0; h < imageTwoD[0].length; h++) {
                    // doubles each pixel horizontally
                    horizontalStrech[v][hPointer] = imageTwoD[v][h];
                    horizontalStrech[v][hPointer + 1] = imageTwoD[v][h];
                    hPointer += 2;
                } // -------------------
            } // ---------------------------------------------
            return horizontalStrech;
            // --!!--
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    // ==========================================================================================================
    public static int[][] stretchVertically(int[][] imageTwoD) {
        // doubles each pixel vertically
        int[][] verticalStrech = new int[imageTwoD.length * 2][imageTwoD[0].length];
        int vPointer = 0;
        // -------------
        try {
            for (int v = 0; v < imageTwoD.length; v++) {
                for (int h = 0; h < imageTwoD[0].length; h++) {
                    //
                    verticalStrech[vPointer][h] = imageTwoD[v][h];
                    verticalStrech[vPointer + 1][h] = imageTwoD[v][h];
                } // --------------
                vPointer += 2;
            } // ----------------
            return verticalStrech;
            // --!!--
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } // ------
    }

    // =====================================================================================
    public static int[][] shrinkHorizontally(int[][] imageTwoD) {
        int ifOdd = imageTwoD[0].length % 2;
        int[][] horizontalShrink = new int[imageTwoD.length][imageTwoD[0].length / 2 + ifOdd];
        // -------------
        try {
            for (int v = 0; v < horizontalShrink.length; v++) {
                int hPointer = 0;
                for (int h = 0; h < horizontalShrink[0].length; h++) {
                    //
                    horizontalShrink[v][h] = imageTwoD[v][hPointer];
                    hPointer += 2;
                } // --------------

            } // --------------
            return horizontalShrink;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } // ------
    }

    // ===================================================================================
    public static int[][] shrinkVertically(int[][] imageTwoD) {
        int ifOdd = imageTwoD.length % 2;
        int[][] verticalShrink = new int[imageTwoD.length / 2 + ifOdd][imageTwoD[0].length];
        // -------------
        try {
            int vPointer = 0;
            for (int v = 0; v < verticalShrink.length; v++) {
                for (int h = 0; h < verticalShrink[0].length; h++) {
                    //
                    verticalShrink[v][h] = imageTwoD[vPointer][h];

                } // --------------
                vPointer += 2;
            } // -------------------------
            return verticalShrink;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // =================================================================================
    public static int[][] invertImage(int[][] imageTwoD) {
        int[][] invertedImg = new int[imageTwoD.length][imageTwoD[0].length];
        //
        for (int v = 0; v < imageTwoD.length; v++) {
            for (int h = 0; h < imageTwoD[v].length; h++) {
                invertedImg[v][h] = imageTwoD[(imageTwoD.length - 1) - v][(imageTwoD[v].length - 1) - h];
            } // -------
        } // ------------------------------
        return invertedImg;
    }

    // ====================================================================================
    public static int[][] colorFilter(int[][] imageTwoD, int redChangeValue, int greenChangeValue,
            int blueChangeValue, int alphaChangeValue) {
        int[][] manipulatedImg = new int[imageTwoD.length][imageTwoD[0].length];
        for (int v = 0; v < imageTwoD.length; v++) {
            for (int h = 0; h < imageTwoD[v].length; h++) {
                // ------
                int[] rgba = getRGBAFromPixel(imageTwoD[v][h]);
                int newR = rgba[0] + redChangeValue;
                int newG = rgba[1] + greenChangeValue;
                int newB = rgba[2] + blueChangeValue;
                int newA = rgba[3] + alphaChangeValue;
                // --------
                if (newR > 255) {
                    newR = 255;
                } else if (newR < 0) {
                    newR = 0;
                }

                if (newG > 255) {
                    newG = 255;
                } else if (newG < 0) {
                    newG = 0;
                }

                if (newB > 255) {
                    newB = 255;
                } else if (newB < 0) {
                    newB = 0;
                }

                if (newA > 255) {
                    newA = 255;
                } else if (newA < 0) {
                    newA = 0;
                }
                // -------
                rgba[0] = newR;
                rgba[1] = newG;
                rgba[2] = newB;
                rgba[3] = newA;
                // ------

                manipulatedImg[v][h] = getColorIntValFromRGBA(rgba);
            } // ----
        } // -----------------------------------------------
        return manipulatedImg;
    }

    // ====================================================================================
    // Painting Methods
    public static int[][] paintRandomImage(int[][] canvas) {
        Random rand = new Random();
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[i].length; j++) {
                //
                int[] rgba = { rand.nextInt(256), rand.nextInt(256), rand.nextInt(256), 255 };
                canvas[i][j] = getColorIntValFromRGBA(rgba);
            } // ------------
        } // -------------------------------------
        return canvas;
    }

    // ====================================================================================
    public static int[][] paintRectangle(int[][] canvas, int width, int height, int rowPosition, int colPosition,
            int color) {
        for (int v = 0; v < canvas.length; v++) {
            //
            for (int h = 0; h < canvas[v].length; h++) {
                //
                if (v >= rowPosition && v <= rowPosition + width) {
                    //
                    if (h >= colPosition && h <= colPosition + height) {
                        canvas[v][h] = color;
                    } // ---
                } // ------------
            } // -----------------------
        } // --------------------------------
        return canvas;
    }

    // ====================================================================================
    public static int[][] generateRectangles(int[][] canvas, int numRectangles) {
        // TODO: Fill in the code for this method
        return null;
    }

    // ====================================================================================
    // ## Utility Methods
    // ----------------
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
            //
            for (int i = 0; i < imgRows; i++) {
                for (int j = 0; j < imgCols; j++) {
                    pixelData[i][j] = image.getRGB(j, i);
                } // ----
            } // ------------
            return pixelData;
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getLocalizedMessage());
            return null;
        }
    }

    // =============================== ImageIO
    // ======================================================
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

    // ====================================================================================================
    public static int[] getRGBAFromPixel(int pixelColorValue) {
        Color pixelColor = new Color(pixelColorValue);
        return new int[] { pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue(), pixelColor.getAlpha() };
    }

    // ===================================================================================================
    public static int getColorIntValFromRGBA(int[] colorData) {
        if (colorData.length == 4) {
            Color color = new Color(colorData[0], colorData[1], colorData[2], colorData[3]);
            return color.getRGB();
        } else {
            System.out.println("Incorrect number of elements in RGBA array.");
            return -1;
        }
    }

    // ==================================================================================================
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