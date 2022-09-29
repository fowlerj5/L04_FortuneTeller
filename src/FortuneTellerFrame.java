import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class FortuneTellerFrame extends JFrame
{
    JPanel mainPnl, titlePnl, displayPnl, cmdPnl;
    JLabel titleLbl;
    ImageIcon icon;
    JScrollPane scroller;
    JTextArea fortuneTA;
    JButton quitBtn, fortuneBtn;

    int curFortuneDex = -1;
    int newDex = 0;
    Random rnd = new Random();

    String[] fortunes =
            {
                    "You think it's a secret, but they know.",
                    "You have the sudden urge to yawn.",
                    "You should give me your address and leave the trash can open.",
                    "You ask yourself \"Why am I asking a raccoon for my fortune?\"",
                    "You are now aware of your breathing.", //5
                    "One day the snail will catch up to you and you will have nowhere to run.",
                    "You are terribly afraid of something in this world, but you don't know what.",
                    "The world will gift you something soon. And it might just be bird poop.",
                    "This fortune is a lie.",
                    "You are number 1. \"At what?\"  you might ask. I don't get paid enough to know.", //10
                    "To truly find yourself, you should play hide and seek alone.",
                    "It is impossible to say \"bubbles\"  angrily."
            };

    public FortuneTellerFrame() throws HeadlessException
    {
        setTitle("Fortune Teller");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int scrnHeight = screenSize.height;
        int scrnWidth = screenSize.width;
        setSize(scrnWidth*3/4, scrnHeight*3/4);
        setLocation(scrnWidth/8, scrnHeight/8);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        add(mainPnl);

        createTitlePanel();
        createDisplayPanel();
        createCommandPanel();

        setVisible(true);
    }
    private void createCommandPanel()
    {
        cmdPnl = new JPanel();
        cmdPnl.setLayout(new GridLayout(1,2));

        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Bold", Font.BOLD, 18));
        fortuneBtn = new JButton("Read My Fortune!");
        fortuneBtn.setFont(new Font("Bold", Font.BOLD, 18));

        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));
        fortuneBtn.addActionListener((ActionEvent ae) ->
        {
            do {
                newDex = rnd.nextInt(fortunes.length);
            }while(newDex == curFortuneDex);

            curFortuneDex = newDex;

            fortuneTA.append(fortunes[curFortuneDex] + "\n");
        });

        cmdPnl.add(fortuneBtn);
        cmdPnl.add(quitBtn);

        mainPnl.add(cmdPnl, BorderLayout.SOUTH);
    }
    private void createDisplayPanel()
    {
        displayPnl = new JPanel();

        fortuneTA = new JTextArea(10,60);
        fortuneTA.setFont(new Font("Italic", Font.ITALIC, 12));
        scroller = new JScrollPane(fortuneTA);

        displayPnl.add(scroller);

        mainPnl.add(displayPnl, BorderLayout.CENTER);
    }
    private void createTitlePanel()
    {
        titlePnl = new JPanel();

        icon = new ImageIcon(System.getProperty("user.dir") + "/src/FortuneTeller.jpg");
        titleLbl = new JLabel("Fortune Teller",icon, JLabel.CENTER);
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);
        titleLbl.setFont(new Font("Bold Italic", Font.BOLD | Font.ITALIC, 36));

        titlePnl.add(titleLbl);

        mainPnl.add(titlePnl, BorderLayout.NORTH);
    }
}
