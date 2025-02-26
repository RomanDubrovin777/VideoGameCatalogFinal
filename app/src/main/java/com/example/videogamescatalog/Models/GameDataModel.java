package com.example.videogamescatalog.Models;

public class GameDataModel
{
    private String m_Picture;
    private String m_Date;
    private String m_Genre;
    private String m_GameName;
    private String m_Platform;
    private String m_Developer;
    private String m_Description;
    private String m_GameUrl;

    public GameDataModel(String gameName, String launchDate, String picture)
    {
        this.m_GameName = gameName;
        this.m_Date = launchDate;
        this.m_Picture = picture;
    }

    public GameDataModel(String gameName, String launchDate, String picture,
                         String platform, String developer, String description, String GameUrl, String i_genre)
    {
        this.m_GameName = gameName;
        this.m_Date = launchDate;
        this.m_Picture = picture;
        this.m_Platform = platform;
        this.m_Developer = developer;
        this.m_Description = description;
        this.m_GameUrl = GameUrl;
        this.m_Genre = i_genre;
    }

    public String getM_Genre() {
        return m_Genre;
    }
    public void setM_Genre(String m_Genre){ this.m_Genre = m_Genre;}
    public String getM_Picture() {
        return m_Picture;
    }
    public void setM_Picture(String m_Picture) {
        this.m_Picture = m_Picture;
    }
    public String getM_Date() {
        return m_Date;
    }
    public void setM_Date(String m_Date) {
        this.m_Date = m_Date;
    }
    public String getM_GameName() {
        return m_GameName;
    }
    public void setM_GameName(String m_GameName) {
        this.m_GameName = m_GameName;
    }
    public String getM_Platform() {
        return m_Platform;
    }
    public void setM_Platform(String m_Platform) {
        this.m_Platform = m_Platform;
    }
    public String getM_Developer() {
        return m_Developer;
    }
    public void setM_Developer(String m_Developer) {
        this.m_Developer = m_Developer;
    }
    public String getM_Description() {
        return m_Description;
    }
    public void setM_Description(String m_Description) {
        this.m_Description = m_Description;
    }
    public String getM_GameUrl() {return m_GameUrl;}
    public void setM_GameUrl(String m_TrailerUrl) {
        this.m_GameUrl = m_TrailerUrl;
    }
}
