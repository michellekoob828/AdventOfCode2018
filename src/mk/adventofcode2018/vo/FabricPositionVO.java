package mk.adventofcode2018.vo;

public class FabricPositionVO {
	private int x;
	private int y;
	
	public FabricPositionVO() {
		
	}
	
	public FabricPositionVO(int posX, int posY) {
		this.x = posX;
		this.y = posY;;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FabricPositionVO other = (FabricPositionVO) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
