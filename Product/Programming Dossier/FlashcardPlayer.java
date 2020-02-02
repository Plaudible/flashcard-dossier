import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
public class FlashcardPlayer
{
  private static final Dimension FRAME_SIZE = new Dimension(240, 200);
  private int cardCountCompleted;
  private int timesCompleted;
  private boolean isAnswerVisible;
  private Deck deck;
  private JButton correct;
  private JButton answer;
  private JButton incorrect;
  private JButton repeat;
  private JFrame frm;
  private JLabel label;
  private JPanel background;
  private JTextArea textArea;
  private FlashcardMaker flashcardMaker;    
  private FlashcardPlayer player;
  public FlashcardPlayer(Deck deck){
        this.deck = deck;
    }

  
    private class Correct extends ButtonListener {
        public void actionPerformed(ActionEvent click){
            deck.setNumCorrect(deck.getNumCorrect() + 1);
            super.actionPerformed(click);
        }
    }
    private class Incorrect extends ButtonListener {
        public void actionPerformed(ActionEvent click){
            deck.setNumWrong(deck.getNumWrong() + 1);
            super.actionPerformed(click);
        }
    }
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent click){
              if(cardCountCompleted < deck.getFlashcardList().size()) {
                if (isAnswerVisible) {
                    showNext();
                } else {
                    showAnswer();
                }
              }else if(cardCountCompleted == deck.getFlashcardList().size()) {
                showFinish();
              }else{
                closeFrame();
              }            
            }
        private void showAnswer(){
            label.setText("Ton reponse:");
            textArea.setText(deck.getFlashcardList().get(cardCountCompleted).getTranslated());
            isAnswerVisible = true;
            answer.setVisible(false);
            correct.setVisible(true);
            correct.requestFocusInWindow();
            incorrect.setVisible(true);
            cardCountCompleted++;
        }
        private void showNext(){ 
            label.setText("Le mot pour traduire:");
            textArea.setText(deck.getFlashcardList().get(cardCountCompleted).getUntranslated());
            isAnswerVisible = false;
            answer.setText("Montrer la reponse correct");
            answer.setVisible(true);
            answer.requestFocusInWindow();
            correct.setVisible(false);
            incorrect.setVisible(false);       
        }
        private void showFinish(){
            correct.setVisible(false);
            incorrect.setVisible(false);
            label.setText("Les resultes:");
            textArea.setText("Tu as " + deck.getNumCorrect() + " correct et " + deck.getNumWrong() + " pas correct.");
            answer.setText("Finis");
            answer.setVisible(true);
            repeat.setVisible(true);
            cardCountCompleted++;
        }
    }
    private class RepeatListener implements ActionListener {
        public void actionPerformed(ActionEvent click){
            cardCountCompleted = 0;
            deck.setNumCorrect(0);
            deck.setNumWrong(0);
			frm.dispose();
            run();
        }
    }
    private void buildButtons(){
            answer = new JButton("Montrer la reponse correct");
            answer.addActionListener(new ButtonListener());
            correct = new JButton("Correct");
            correct.addActionListener(new Correct());
            correct.setVisible(false);
             
            incorrect = new JButton("Pas Correct");
            incorrect.addActionListener(new Incorrect());
            incorrect.setVisible(false);
            
            repeat = new JButton("Encore une fois!");
            repeat.addActionListener(new RepeatListener());
            repeat.setVisible(false);
           
            
            
            JPanel buttons = new JPanel();
            buttons.add(answer);
            buttons.add(correct);
            buttons.add(incorrect);
            buttons.add(repeat);
            buttons.setAlignmentX(Component.RIGHT_ALIGNMENT);
            background.add(BorderLayout.SOUTH, buttons);
    }
    private void buildBackground(){
        background = new JPanel();
        background.setLayout(new BorderLayout());
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frm.setContentPane(background);
    }
    private void buildFrame(){
        frm = new JFrame("French Flashcard Quizzer");
    }
    private void buildLabels(){
        label = new JLabel("Le mot pour traduire:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        background.add(BorderLayout.NORTH, label);
    }
    private void buildTextArea(){
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText(deck.getFlashcardList().get(0).getUntranslated());
        JScrollPane jsp = new JScrollPane(textArea);
        jsp.setAlignmentX(Component.CENTER_ALIGNMENT);
        background.add(BorderLayout.CENTER, jsp);
    }
    private void closeFrame(){
        frm.dispose();
        deck.setNumCorrect(0);
        deck.setNumWrong(0);
    }
    private void displayFrame(){
        frm.setSize(FRAME_SIZE);
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }
    void run(){
        buildFrame();
        buildBackground();
        buildLabels();
        buildTextArea();
        buildButtons();
        displayFrame();
    }
    void registerFlashcardMaker(FlashcardMaker newFlashcardMaker){
        flashcardMaker = newFlashcardMaker;
    }
}
