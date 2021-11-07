package game;

import java.awt.image.BufferedImage;

public class Texture {

	SpriteSheet bs, ps, ss;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	private BufferedImage spider_sheet = null;
	
	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] player = new BufferedImage[12];
	public BufferedImage[] player_jump = new BufferedImage[2];
	public BufferedImage[] spider = new BufferedImage[2];
	
	
	public Texture() {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
			block_sheet = loader.loadImage("/my_block_sheet.png");
			player_sheet = loader.loadImage("/player_sheet.png");
			spider_sheet = loader.loadImage("/spider_sheet.png");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		ss = new SpriteSheet(spider_sheet);
		
		getTextures();
	}
	
	private void getTextures() {
		block[0] = bs.grabImage(1,1,32,32); // dirt block
		block[1] = bs.grabImage(2,1,32,32); // grass block
		
		
		player[0] = ps.grabImage(1,1,42,76); // idle right
		player[1] = ps.grabImage(2,1,42,76); // right walk1
		player[2] = ps.grabImage(3,1,42,76); // right walk2
		player[3] = ps.grabImage(4,1,42,76); // right walk3
		player[4] = ps.grabImage(5,1,42,76); // right walk4
		player[5] = ps.grabImage(12,1,42,76); // idle left
		player[6] = ps.grabImage(11,1,42,76); // left walk1
		player[7] = ps.grabImage(10,1,42,76); // left walk2
		player[8] = ps.grabImage(9,1,42,76); // left walk3
		player[9] = ps.grabImage(8,1,42,76); // left walk4
		
		
		player_jump[0] = ps.grabImage(6,1,42,76); // right jump
		player_jump[1] = ps.grabImage(7,1,42,76); // left jump
		
		spider[0] = ss.grabImage(1,1,32,22); // spider left
		spider[1] = ss.grabImage(2,1,32,22); // spider right
		
	}
	
}
