package openworld.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class EnvironmentPanel extends JPanel implements ActionListener {

    private GameWorld gameWorld;

    private JButton respawnButton, startButton, stopButton;

    private Boolean monstersMove = false;

    public EnvironmentPanel(GameWorld gameWorld) {
        this.gameWorld = gameWorld;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(new TitledBorder("Monster and NPC controls"));

        respawnButton = new JButton("Respawn all");
        respawnButton.addActionListener(this);
        startButton = new JButton("Start moving");
        startButton.addActionListener(this);
        stopButton = new JButton("Stop moving");
        stopButton.addActionListener(this);

        add(respawnButton);
        add(startButton);
        add(stopButton);
    }

    public void disableAll() {
        startButton.setEnabled(false);
        stopButton.setEnabled(false);
        respawnButton.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(respawnButton)) {
            gameWorld.respawnWorld();
        } else if (e.getSource().equals(startButton) || e.getSource().equals(stopButton)) {
            Runnable MonsterLoop = () -> {
                try {
                    if (e.getSource().equals(startButton) && !monstersMove) {
                        monstersMove = true;
                        while (monstersMove) {
                            System.out.println("ITS WORKING");
                            gameWorld.getWorld().nonPlayerCharactersMove();
                            gameWorld.getWorld().monsterMove();
                            Thread.sleep(1000);
                        }
                        // start actions in UI thread
                    } else if (e.getSource().equals(stopButton) && monstersMove == true) {
                        monstersMove = false;
                    }

                } catch (InterruptedException ex) {
                    System.out.println(ex);
                    Thread.currentThread().interrupt();
                }
            };
            Thread t = new Thread(MonsterLoop);
            t.start();
        }

    }

}
