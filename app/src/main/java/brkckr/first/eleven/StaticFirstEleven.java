package brkckr.first.eleven;

import java.util.List;

public class StaticFirstEleven
{
    public String club;
    public String coach;
    public String formation;
    public List<StaticPlayer> staticPlayerList;

    public StaticFirstEleven(String club, String coach, String formation, List<StaticPlayer> staticPlayerList)
    {
        this.club = club;
        this.coach = coach;
        this.formation = formation;
        this.staticPlayerList = staticPlayerList;
    }
}
