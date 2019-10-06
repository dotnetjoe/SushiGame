package sushigame.plate_imgs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*`class is used to read in Gold Plate Image
 * called from BeltView when wanting to create new Gold Plate Icon...
 * ...and store in pbutton at given position
 */
public class GoldPlateImg {
	
	public BufferedImage readImage() throws IOException{
		
		/*	creates new File instance to be read in by...
		 * 	...converting given pathway String into an abstract pathway
		 */
		File read = new File("src/gold_plate.png");
		//decodes a supplied File and returns a BufferedImage
		BufferedImage master = ImageIO.read(read);
		return master;
		
	}
}
