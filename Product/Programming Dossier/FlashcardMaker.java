import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class FlashcardMaker {
    private Deck deck;
    private JButton btn;
    private JFrame frm;
    private JTextArea aText = new JTextArea();
    private JTextArea qText = new JTextArea();
    private JTextArea hText = new JTextArea();
    private JPanel pnl;
    private FlashcardPlayer player;
    public FlashcardMaker(Deck deck) {
        this.deck = deck;
        createFlashcardPlayer();
    }
    private void buildHelpFrame() {
        frm = new JFrame("French Flashcard Quizzer");
        frm.setMinimumSize(new Dimension(200, 150));
    }
    private void buildHelpBackground() {
        pnl = new JPanel();
        pnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
        frm.setContentPane(pnl);
    }
    private void buildHelpBar() {
        pnl = new JPanel();
        pnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
        frm.setContentPane(pnl);
    }
    private void buildHelpLabels(JLabel lbl) {
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnl.add(lbl);
    }
    private void buildHelpTextArea(JTextArea text) {
        JScrollPane jsp = new JScrollPane(text);
        jsp.setAlignmentX(Component.CENTER_ALIGNMENT);
        text.setEditable(false);
        pnl.add(jsp);
    }
    void runhelp() {
        buildHelpFrame();
        buildHelpBackground();
        buildHelpBar();
        buildHelpLabels(new JLabel("Welcome to the French Vocabulary Practice application! This allows you to insert a French or English word and translate it for practice.")); 
        buildHelpLabels(new JLabel("Feel free to use this tool to your advantage and practice the vocabulary you'd like as much as you need to."));
        buildHelpLabels(new JLabel("To use this application, fill in the top box with the untranslated word, and the second with the translated word."));
        buildHelpLabels(new JLabel("Then, click 'Ajouter' to add the card. The text boxes will be emptied and you can add another card as necessary."));
        buildHelpLabels(new JLabel("Add as many as you need, then when you're ready click the dropdown under file and click 'Commencer l'examin'"));
        displayFrame();
    }
        private JTextArea getAText() {
        return aText;
    }
    JTextArea getQText() {
        return qText;
    }
    private void setAText(String txt) {
        SwingUtilities.invokeLater(() -> aText.setText(txt));
    }
    private void setQText(String txt) {
        SwingUtilities.invokeLater(() -> qText.setText(txt));
    }
    private void addCard(){
        deck.addFlashcard(getQText().getText(), getAText().getText());
        setQText(" ");
        setAText(" ");
    }
    private void buildFrame() {
        frm = new JFrame("French Flashcard Quizzer");
        frm.setMinimumSize(new Dimension(200, 150));
    }
    private void buildBackground() {
        pnl = new JPanel();
        pnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
        frm.setContentPane(pnl);
    }
    private void buildBar() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        file.add(Play);
        jMenuBar.add(file);
        frm.setJMenuBar(jMenuBar);
        file.add(Shuffle);
        JMenu help = new JMenu("Help");
        jMenuBar.add(help);
        help.add(Help);
    } 
    private void buildLabels(JLabel lbl) {
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnl.add(lbl);
    }
    private void buildTextArea(JTextArea text) {
        JScrollPane jsp = new JScrollPane(text);
        jsp.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnl.add(jsp);
    }
    private void buildButtons() {
        btn = new JButton("Ajouter");
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.addActionListener(ev -> addCard());
        pnl.add(btn);
    }
    private void close(){
        System.exit(0);
    }
    private void displayFrame() {
        frm.pack();
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }
    private Action Play = new AbstractAction("Commence l'examin")
    {
        public void actionPerformed(ActionEvent ev){
                    player.run();               
        }
    };
     private Action Shuffle = new AbstractAction("Melanger les cartes")
    {
        public void actionPerformed(ActionEvent ev){
                    deck.shuffle();               
        }
    };
    private Action Help = new AbstractAction("L'assistance")
    {
        public void actionPerformed(ActionEvent ev){
                    runhelp();               
        }
    };
    void run() {
        buildFrame();
        buildBackground();
        buildBar();
        buildLabels(new JLabel("Le mot en Francais:"));
        buildTextArea(qText);
        buildLabels(new JLabel("Le mot en Anglais:"));
        buildTextArea(aText);
        buildButtons();
        displayFrame();
    }
        public void createFlashcardPlayer(){
        player = new FlashcardPlayer(deck);
        player.registerFlashcardMaker(this);   
    }
}
