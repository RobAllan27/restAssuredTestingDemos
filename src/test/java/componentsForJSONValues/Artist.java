package componentsForJSONValues;

public class Artist
{
    private String name;

    private String birthDate;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getBirthDate ()
    {
        return birthDate;
    }

    public void setBirthDate (String birthDate)
    {
        this.birthDate = birthDate;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", birthDate = "+birthDate+"]";
    }
}

