public class Punkt {
  private float x;
  private float y;

  public Punkt(float x, float y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "" + x + ", " + y + " ";
  }
  
  public boolean equalsPunkt(Punkt p) {   
    return(this.getX() == p.getX() && this.getY() == p.getY()) ;
  }

  public java.awt.Point toAWTPoint() {
    return new java.awt.Point((int) Math.round(x), (int) Math.round(y));
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }
  public int getXint() {
    return (int) Math.round(x);
  }

  public int getYint() {
    return(int) Math.round(y);
  }
}
