package game;

import java.awt.image.BufferedImage;

public class Texture {

	SpriteSheet bs, ps, ss, es, shu;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	private BufferedImage spider_sheet = null;
	private BufferedImage enemy_sheet = null;
	private BufferedImage shuriken_sheet = null;
	
	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] player = new BufferedImage[4];
	public BufferedImage[] player_jump = new BufferedImage[2];
	public BufferedImage[] spider = new BufferedImage[2];
	public BufferedImage[] enemy = new BufferedImage[4];
	public BufferedImage[] shuriken = new BufferedImage[2];
	
	
	public Texture() {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
			block_sheet = loader.loadImage("/my_block_sheet.png");
			player_sheet = loader.loadImage("/player_sheet.png");
			spider_sheet = loader.loadImage("/spider_sheet.png");
			enemy_sheet = loader.loadImage("/enemy_sheet.png");
			shuriken_sheet = loader.loadImage("/shuriken_sheet.png");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		ss = new SpriteSheet(spider_sheet);
		es = new SpriteSheet(enemy_sheet);
		shu = new SpriteSheet(shuriken_sheet);
		
		getTextures();
	}
	
	private void getTextures() {
		block[0] = bs.grabImage(1,1,32,32); // dirt block
		block[1] = bs.grabImage(2,1,32,32); // grass block
		
		
		player[0] = ps.grabImage(1,1,42,76); // idle right
		player[1] = ps.grabImage(2,1,42,76); // walk right
		player[2] = ps.grabImage(5,1,42,76); // walk left
		player[3] = ps.grabImage(6,1,42,76); // idle left
			
		player_jump[0] = ps.grabImage(3,1,42,76); // right jump
		player_jump[1] = ps.grabImage(4,1,42,76); // left jump
		
		enemy[0] = es.grabImage(1,1,50,86); // idle left
		enemy[1] = es.grabImage(2,1,50,86); // walk left
		enemy[2] = es.grabImage(3,1,50,86); // walk right
		enemy[3] = es.grabImage(4,1,50,86); // idle right
		
		spider[0] = ss.grabImage(1,1,32,22); // spider left
		spider[1] = ss.grabImage(2,1,32,22); // spider right
		
		shuriken[0] = shu.grabImage(1,1,17,17);
		shuriken[1] = shu.grabImage(2,1,17,17);
		
	}
	
}
