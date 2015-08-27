public class TeamStandings
{
  TeamInfo[] standings; // maintained in decreasing order by points,
                        // when two are tied on points decreasing order by total score

  public TeamStandings(String[] teamNames)
  {
    standings = new TeamInfo[teamNames.length];

    for(int k = 0; k < standings.length; k++)
      standings[k] = new TeamInfo(teamNames[k]);
  }


  public void recordGameResult(GameResult result)
  {
    int homeSc = result.homeScore();
    int awaySc = result.awayScore();
    int homeIndex = teamIndex(result.homeTeam());
    int awayIndex = teamIndex(result.awayTeam());
    if(homeSc > awaySc)
    {
      adjust(homeIndex, 2);
    }
    else if(homeSc < awaySc)
    {
      adjust(awayIndex, 2);
    }
    else // draw
    {
      adjust(homeIndex, 1);
      adjust(awayIndex, 1);
    }
  }

  private int teamIndex(String name)
  {
     for(TeamInfo i : standings){
         if(name.equals(i.teamName()))
            return k;
     }
        
    for(int k = 0; k < standings.length; k++)
    {
      if(name.equals(standings[k].teamName()))
        return k;
    }

    return -1;
  }

  private void adjust(int index, int points)
  {
    standings[index].increasePoints(points);

    TeamInfo temp = standings[index];

    while(index > 0 && temp.points() > standings[index-1].points())
    {
      standings[index] = standings[index-1];
      index--;
    }

    standings[index] = temp;
  }

  ///////////////////////////////////////////////

  public String toString()
  {
    String res = "";
    for(int k = 0; k < standings.length; k++)
      res += standings[k] + "\n";

    return res;
  }

  public static void main(String[] args)
  {
    String[] names = {"A", "B", "C", "D"};

    TeamStandings table = new TeamStandings(names);

    System.out.println(table);

    table.recordGameResult(new GameResult("A","B", 11, 16));
    System.out.println(table);

    table.recordGameResult(new GameResult("C","D", 11, 6));
    System.out.println(table);

    table.recordGameResult(new GameResult("A","C", 11, 16));
    System.out.println(table);

    table.recordGameResult(new GameResult("D","B", 11, 16));
    System.out.println(table);

    table.recordGameResult(new GameResult("A","D", 11, 11));
    System.out.println(table);

    table.recordGameResult(new GameResult("C","B", 21, 16));
    System.out.println(table);

    table.recordGameResult(new GameResult("A","B", 21, 16));
    System.out.println(table);

    table.recordGameResult(new GameResult("D","C", 21, 16));
    System.out.println(table);

    table.recordGameResult(new GameResult("C","A", 26, 16));
    System.out.println(table);

    table.recordGameResult(new GameResult("B","D", 16, 16));
    System.out.println(table);

    table.recordGameResult(new GameResult("C","B", 21, 21));
    System.out.println(table);

    table.recordGameResult(new GameResult("A","D", 21, 16));
    System.out.println(table);

  }

}