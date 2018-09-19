package componentsForJSONValues;

public class Album
{
    private String title;

    private String[] songs;

    private Musician[] musician;

    private String[] links;

    private Artist artist;

    private String recordedcity;

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String[] getSongs ()
    {
        return songs;
    }

    public void setSongs (String[] songs)
    {
        this.songs = songs;
    }

    public Musician[] getMusician ()
    {
        return musician;
    }

    public void setMusician (Musician[] musician)
    {
        this.musician = musician;
    }

    public String[] getLinks ()
    {
        return links;
    }

    public void setLinks (String[] links)
    {
        this.links = links;
    }

    public Artist getArtist ()
    {
        return artist;
    }

    public void setArtist (Artist artist)
    {
        this.artist = artist;
    }

    public String getRecordedcity ()
    {
        return recordedcity;
    }

    public void setRecordedcity (String recordedcity)
    {
        this.recordedcity = recordedcity;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [title = "+title+", songs = "+songs+", musician = "+musician+", links = "+links+", artist = "+artist+", recordedcity = "+recordedcity+"]";
    }
}