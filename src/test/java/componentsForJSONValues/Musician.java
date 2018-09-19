package componentsForJSONValues;

public class Musician
{
    private String name;

    private String instrument;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getInstrument ()
    {
        return instrument;
    }

    public void setInstrument (String instrument)
    {
        this.instrument = instrument;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", instrument = "+instrument+"]";
    }
}
