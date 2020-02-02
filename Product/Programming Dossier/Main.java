import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main
{
    private JButton btn;
    private JFrame frm;
    private JPanel pnl;
    private FlashcardMaker maker;
    public Main()
    {
    }
    private void buildFrame() {
        frm = new JFrame("Greetings!");
        frm.setMinimumSize(new Dimension(300, 100));
    }
    private void buildBackground() {
        pnl = new JPanel();
        pnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
        frm.setContentPane(pnl);
    }
    private void buildButtons() {
        btn = new JButton("Begin");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnl.add(btn);
        btn.addActionListener(ev -> go());
    }
        private void buildLabels(JLabel lbl) {
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnl.add(lbl);
    }
        private void displayFrame() {
        frm.pack();
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
    }
    void run() {
        buildFrame();
        buildBackground();
        buildLabels(new JLabel("Welcome to the French Vocab quizzer!"));
        buildLabels(new JLabel("Press button to begin."));
        buildButtons();
        displayFrame();
    }
    private void go(){
        FlashcardMaker flashcardMaker = new FlashcardMaker(new Deck());
        flashcardMaker.run();
        frm.dispose();
     }
}
