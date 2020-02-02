public class Flashcard
{
    private String untranslated;
    private String translated;
    public Flashcard(String q, String a)
    {
        setUntranslated(q);
        setTranslated(a);
    }
        String getUntranslated(){
        return untranslated;
    }
    String getTranslated(){
        return translated;
    }
    void setUntranslated(String text){
        untranslated = text;
    }
    void setTranslated(String text){
        translated = text;
    }
}
