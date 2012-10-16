package GUI;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import system.PegColor;

/*
 * @File PegColor.java
 * 
 * @Authors Becca Dudley
 * 
 * @Class Description This converts from Java.awt.Color to the ImageIcon 
 * Associated with that Color
 */
public class ColorImage extends ImageIcon{
	
	private PegColor color;//the Java.awt.Color of the PegUI
	
	public ColorImage(PegColor color, int size){
		
		this.color = color;
		
		try{
			if(size == 50){
				if(color == PegColor.NEUTRAL ){
					setImage(ImageIO.read(new File("resources/pegs/disabled.png")));
				}else if(color == PegColor.BLUE){
					setImage(ImageIO.read(new File("resources/pegs/bluePeg.png")));
				}else if(color == PegColor.BLACK){
					setImage(ImageIO.read(new File("resources/pegs/blackPeg.png")));
				}else if(color == PegColor.GREEN){
					setImage(ImageIO.read(new File("resources/pegs/greenPeg.png")));
				}else if(color == PegColor.RED){
					setImage(ImageIO.read(new File("resources/pegs/redPeg.png")));
				}else if(color == PegColor.WHITE){
					setImage(ImageIO.read(new File("resources/pegs/whitePeg.png")));
				}else if(color == PegColor.YELLOW){
					setImage(ImageIO.read(new File("resources/pegs/yellowPeg.png")));
				}
				
			}else{
				if(color.equals(Color.LIGHT_GRAY)){
					setImage(ImageIO.read(new File("resources/pegs/disabledSmall.png")));
				}else if(color.equals(Color.WHITE)){
					setImage(ImageIO.read(new File("resources/pegs/whiteSmallPeg.png")));
				}else if(color.equals(Color.BLACK)){
					setImage(ImageIO.read(new File("resources/pegs/blackSmallPeg.png")));
				}
			}
		}catch(IOException ioe){
			System.err.println("Image does not exist");
		}
	}
	
	/**
	 * getting for the original Java.awt.Color of the PegColor
	 * 
	 * @return the original Java.awt.Color of the PegColor
	 */
	public PegColor getColor(){
		return color;
	}
}