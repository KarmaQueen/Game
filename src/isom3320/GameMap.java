package isom3320;

public class GameMap extends GameObject {
	
	private int size;
	private char[][] map;
	
	/**
	 * ' ' = blank
	 * 'X' = wall
	 * @param map
	 */
	public GameMap(int[][] map) {
		super();
		//this.map = map;
	}
	
	@Override
	public void init(){
		String[] stringMap = new String[]{
				"X XXXXXXXXXXXXXXXXXXXXXXXX   XXX",
				"X                              X",
				"XXX  X X X                     X",
				"X    X XXXX                    X",
				"XXXXXX                         X",
				"XXXXXXXXXXX          O         X",
				"X        XX                    X",
				"X                              X",
				"X        XX                    X",
				"X        XX                    X",
				"X        XXXXXXXXXX    XXXXXXXXX",
				"X        XX                    X",
				"XXX   XXXXX                    X",
				"X        XX                    X",
				"X        XX                    X",
				"X                              X",
				"XXXXXXXXXXX                    X",
				"XXXXXXXXXXX                    X",
				"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"};
		
		map = convert(stringMap);
		size = 50;
	}
	
	@Override
	public void update(){
		
	}

	@Override
	public void render(double framestep) {
		// TODO Auto-generated method stub
		if(map == null) return;
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				switch(map[i][j]){
				case 'X':
					R.fill(255);
					R.stroke(255);
					break;
				case 'O':
					R.stroke(255, 255, 0);
					R.fill(255, 255, 0);
					break;
				default: continue;
				}
				R.strokeWeight(1);
				R.rect(i*size, j*size, size, size);
			}
		}
	}
	
	
	public static char[][] convert(String[] str){
		char[][] map = new char[str[0].length()][str.length];
		for(int i = 0; i < str.length; i++)
			for(int j = 0; j < str[i].length(); j++)
				map[j][i] = str[i].charAt(j);
		return map;
	}
}
