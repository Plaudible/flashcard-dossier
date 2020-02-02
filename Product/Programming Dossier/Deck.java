import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
public class Deck
{
    private List<Flashcard> flashcardList = new ArrayList<>();
    private int numCorrect;
    private int numWrong;
    int getNumCorrect(){
        return numCorrect;
    }
    int getNumWrong(){
        return numWrong;
    }
    void setNumCorrect(int val){
        numCorrect = val;
    }
    void setNumWrong(int val){
        numWrong = val;
    }
    void addFlashcard(String untranslated, String translated){
        flashcardList.add(new Flashcard(untranslated, translated));
    }
    List<Flashcard> getFlashcardList(){
        return flashcardList;
    }
    void shuffle(){
        Collections.shuffle(flashcardList);
    }
}
