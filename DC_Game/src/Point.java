public class Point {
	private int xVal;
	private int yVal;
	
	public Point (int x, int y){
		xVal = x;
		yVal = y;
	}
	
	public int getX() {
		return xVal;
	}
	public int getY() {
		return yVal;
	}
	
	public void setX(int x) {
		 xVal = x;
	}
	public void setY(int y) {
		yVal = y;
	}
	
	@Override 
	public boolean equals(Object other) {
	    boolean result = false;
	    if (other instanceof Point) {
	        Point that = (Point) other;
	        result = (this.getX() == that.getX() && this.getY() == that.getY());
	    }
	    return result;
	}
	
    @Override 
    public int hashCode() {
        return (31 * (31 + getX()) + getY());
    }
	
}
