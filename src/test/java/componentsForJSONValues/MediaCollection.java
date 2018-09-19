package componentsForJSONValues;

public class MediaCollection
{
    private Album[] album;

    private String collection_type;

    private String collection;

    public Album[] getAlbum ()
    {
        return album;
    }

    public void setAlbum (Album[] album)
    {
        this.album = album;
    }

    public String getCollection_type ()
    {
        return collection_type;
    }

    public void setCollection_type (String collection_type)
    {
        this.collection_type = collection_type;
    }

    public String getCollection ()
    {
        return collection;
    }

    public void setCollection (String collection)
    {
        this.collection = collection;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [album = "+album+", collection_type = "+collection_type+", collection = "+collection+"]";
    }
}
