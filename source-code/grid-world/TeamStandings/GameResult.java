public class GameResult
{
  private String home;
  private String away;

  private int homeSc;
  private int awaySc;

  public GameResult(String homeName, String awayName, int homeScore, int awayScore)
  {
    home = homeName;
    away = awayName;
    homeSc = homeScore;
    awaySc = awayScore;
  }

  public String homeTeam()   // name of home team
  { return home; }

  public String awayTeam()   // name of away team
  { return away; }

  public int homeScore()     // score for home team
  { return homeSc; }

  public int awayScore()     // score for away team
  { return awaySc; }

}
