import java.awt.Color;

public class PhotoMagic {
	
	public static Picture transform(Picture pic, LFSR lfsr) {
		int height = pic.height();
		int width = pic.width();
		
		int red;
		int green;
		int blue;
		
		for(int c = 0; c < width; c++) {
			for(int r = 0; r < height; r++) {
				Color pixel = pic.get(c, r);
				
				red = pixel.getRed();
				green = pixel.getGreen();
				blue = pixel.getBlue();
				
				red = red ^ lfsr.generate(8);
				green = green ^ lfsr.generate(8);
				blue = blue ^ lfsr.generate(8);
				
				pic.set(c, r, new Color(red, green, blue));
			}
		}
		
		return pic;
	}
	
	public static void main(String[] args) {
		Picture pic = new Picture("pipe.png");
		Picture newPic = PhotoMagic.transform(pic, new LFSR("01101000010100010000", 16));
		Picture original = PhotoMagic.transform(newPic, new LFSR("01101000010100010000", 16));
		
		original.show();
	}
}
