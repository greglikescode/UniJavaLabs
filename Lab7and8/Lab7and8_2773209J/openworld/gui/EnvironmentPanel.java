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
	private boolean nonPlayerCharactersAreMoving = false;
	
	public EnvironmentPanel(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(new TitledBorder("Monster and NPC controls"));
		
		respawnButton = new JButton("Respawn all");
		respawnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				System.out.println("Respawning enemies!!");
				gameWorld.respawnWorld();
			}
		});
		startButton = new JButton("Start moving");
		startButton.addActionListener(this);
		stopButton = new JButton("Stop moving");
		stopButton.addActionListener(this);
		
		add(respawnButton);
		add(startButton);
		add(stopButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(startButton) || source.equals(stopButton)) {

			// The loop for getting the monsters to move
			Runnable MonsterLoop = () -> {
				try {
					// If start button is hit and monsters are NOT moving
					// NOTE does not matter if it is the NPC's or the monsters.
					if (source.equals(startButton) && !nonPlayerCharactersAreMoving) {
						nonPlayerCharactersAreMoving = true;
						// All NPC's and Monsters move every 1000 milliseconds (1 second)
						while (nonPlayerCharactersAreMoving) {
							gameWorld.getWorld().nonPlayerCharactersMove();
							gameWorld.getWorld().monsterMove();;
							Thread.sleep(1000);							
						}
					// If stop button is hit and monsters are moving
					} else if (source.equals(stopButton) && nonPlayerCharactersAreMoving == true) {
						nonPlayerCharactersAreMoving = false;
					}
				} catch (InterruptedException error) {
					System.out.println(error);
					Thread.currentThread().interrupt();
				}
			};
			Thread t = new Thread(MonsterLoop);
			t.start();
		}
	}

	public void disableAll() {
		startButton.setEnabled(false);
		stopButton.setEnabled(false);
		respawnButton.setEnabled(false);
	}

}
