package sushigame.plate_imgs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*`class is used to read in Red Plate Image
 * called from BeltView when wanting to create new Red Plate Icon...
 * ...and store in pbutton at given position
 */
public class RedPlateImg {
	
	public BufferedImage readImage() throws IOException{
		
		/*	creates new File instance to be read in by...
		 * 	...converting given pathway String into an abstract pathway
		 */
		File read = new File("src/red_plate.png");
		//decodes a supplied File and returns a BufferedImage
		BufferedImage master = ImageIO.read(read);
		return master;
		
	}
}
