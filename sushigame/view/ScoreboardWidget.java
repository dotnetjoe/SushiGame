package sushigame.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.Chef;
import sushigame.model.SushiGameModel;

@SuppressWarnings({ "serial", "unused" })
public class ScoreboardWidget extends JPanel implements BeltObserver, ActionListener {

	private SushiGameModel game_model;
	private JLabel display;
	private String[] filter = {"Sort Chefs By:", "Chef's Balance", "Food Sold", "Food Spoiled"};
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox sorter = new JComboBox(filter);
	private String picked = "Sort Chefs By:";
	
	public ScoreboardWidget(SushiGameModel gm) {
		game_model = gm;
		game_model.getBelt().registerBeltObserver(this);
		
		display = new JLabel();
		display.setVerticalAlignment(SwingConstants.TOP);
		
		setLayout(new BorderLayout());
		add(display, BorderLayout.CENTER);
		display.setText(makeScoreboardHTML());
		
		display.add(sorter);
		
		add(sorter, BorderLayout.SOUTH);
		sorter.addActionListener(this);
	}

	private String makeScoreboardHTML() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>" + System.lineSeparator() + 
				"<p1>Select plates on the belt for more</p1> <br>" + 
				"<p1>information about their contents</p1> <br>" + 
				"<p1></p1> <br>" +
				"<p1>Sort chefs using the options at the </p1> <br>" + 
				"<p1>bottom of your screen</p1> <br>" + 
				"<p1></p1> <br>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
//		Arrays.sort(chefs, new HighToLowBalanceComparator());
		
		if (picked.equals("Chef's Balance")) {
			Arrays.sort(chefs, new HighToLowBalanceComparator());
		} else if (picked.equals("Food Sold")) {
			Arrays.sort(chefs, new HighToLowFoodSoldOrder());
		} else if (picked.equals("Food Spoiled")) {
			Arrays.sort(chefs, new LowToHighSpoilageComparator());
		}
		
//		for (Chef c : chefs) {
//			sb_html += c.getName() + " ($" + Math.round(c.getBalance()*100.0)/100.0 + ") <br>";
//		}
		
		for (Chef c : chefs) {
			if (picked.equals("Sort Chefs By:")) {
				sb_html += System.lineSeparator() + c.getName() + "<br>";
			} else if (picked.equals("Chef's Balance")) {
				sb_html += System.lineSeparator() + c.getName() + " ($" + Math.round(c.getBalance()*100.0)/100.0 + ") <br>";
			} else if (picked.equals("Food Sold")) {
				sb_html += System.lineSeparator() + c.getName() + " (" + Math.round(c.getConsumed()*100.0)/100.0 + " oz sold) <br>";
			} else if (picked.equals("Food Spoiled")) {
				sb_html += System.lineSeparator() + c.getName() + " (" + Math.round(c.getSpoiled()*100.0)/100.0 + " oz spoiled) <br>";
			}		
		}
		
		return sb_html;
	}

	public void refresh() {
		display.setText(makeScoreboardHTML());	
	}
	
	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			refresh();
		}		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JComboBox<String> combo = (JComboBox<String>)arg0.getSource();
		String chosen = (String) combo.getSelectedItem();
		picked = chosen;
		refresh();
	}
}
