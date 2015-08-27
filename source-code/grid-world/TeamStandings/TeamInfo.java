public class TeamInfo
{
  private String name;
  private int pts;

  public TeamInfo(String name)
  {
    this.name = name;
    pts = 0;
  }

  public String teamName()
  { return name; }

  public void increasePoints(int points)
  { pts += points; }

  public int points()
  { return pts; }

  public String toString()
  {
    return teamName() + " " + points();
  }

}
