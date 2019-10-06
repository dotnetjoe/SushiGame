package sushigame.view;

import java.awt.Color;
import java.awt.Component;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import comp401.sushi.IngredientPortion;
import comp401.sushi.Plate;
import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.plate_imgs.*;

/* class contains view of the Sushi Belt on main frame and all components...
*  ...associated with the view of the Belt
*/
public class BeltView extends JPanel implements BeltObserver, ActionListener {

	//encapsulates Belt object passed to constructor
	private Belt belt;
	//stores JButtons, JFrames, and JLabels in individual array for easy access to whole class
	private JButton[] belt_buttons;
	private JFrame[] info_frames;
	private JLabel[] plate_info;
	private final Dimension screenSize, windowSize;
	private final int wdwLeft, wdwTop;

	/*	Initializes instance variables and registers observers
	 * 	sets layout of this JPanel
	 * 	sets every position of Belt initially to zero...
	 *  ...sets all JFrames to no plate info, and sets all JButtons to nothing
	 */
	public BeltView(Belt b) throws IOException {
		this.belt = b;
		belt.registerBeltObserver(this);
		setLayout(new CircleLayout(true));
		belt_buttons = new JButton[belt.getSize()];
		plate_info = new JLabel[belt.getSize()];
		info_frames = new JFrame[belt.getSize()];
		screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
		windowSize = new Dimension(getPreferredSize());
		wdwLeft = -650 + screenSize.width / 2 - windowSize.width / 2;
		wdwTop = -100 + screenSize.height / 2 - windowSize.height / 2;
		
		//creates all new components needed for each position on Belt
		for (int i = 0; i < belt.getSize(); i++) {
			JButton plateButton = new JButton("");
			plateButton.setBackground(Color.GRAY);
			plateButton.addActionListener(this);
			
			info_frames[i] = new JFrame("");
			plate_info[i] = new JLabel("");
			plate_info[i].setText("There is no plate at position. This position on the Belt is: " + i);
			plate_info[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			//dimensions are optimal size for fitting entire button on main_frame evenly
			plateButton.setMinimumSize(new Dimension(50, 50));
			plateButton.setPreferredSize(new Dimension(50, 50));
			plateButton.setOpaque(true);

			/* required for getting positions in info_frames array...
			 * ...because a int labeled final is required inside MouseListener
			 */
			final int x = i;
			
			//plateButton is added as a Component to JPanel, then plateButton is added to belt_buttons array
			add(plateButton);
			belt_buttons[i] = plateButton;
		}
		//called to get updated version of Belt
		refresh();
	}

	//refresh() is called whenever any changes on Belt occur
	@Override
	public void handleBeltEvent(BeltEvent e) throws IOException {	
		refresh();
	}

	//updates the view of the Belt
	private void refresh() throws IOException {
		//updates all components at every position in Belt
		for (int i=0; i<belt.getSize(); i++) {
			Plate p = belt.getPlateAtPosition(i);
			JButton plateButton = belt_buttons[i];
			
			/* required for getting positions in info_frames array...
			 * ...because a int labeled final is required inside MouseListener
			 */
			final int x = i;

			/* if the plate at the given position is null (i.e. no plate at position)...
			 * ...then reset JFrame to say there is no plate at position, add to...
			 * ...info_frames array, and set the Icon previously associated with position to null
			 */
			if (p == null) {
				plate_info[x].setText("");
				info_frames[x].add(plate_info[x], SwingConstants.CENTER);
				plateButton.setIcon(null);
				
			} else {
				//switches on the Color of the given Plate at position
				switch (p.getColor()) {
				case RED:
					//creates new RedPlate Icon, associates it with plateButton at location
					RedPlateImg red = new RedPlateImg();
					plateButton.setIcon(new ImageIcon(red.readImage()));
					break;
				case GREEN:
					//creates new GreenPlate Icon, associates it with plateButton at location
					GreenPlateImg green = new GreenPlateImg();
					plateButton.setIcon(new ImageIcon(green.readImage()));
					break;
				case BLUE:
					//creates new BluePlate Icon, associates it with plateButton at location
					BluePlateImg blue = new BluePlateImg();
					plateButton.setIcon(new ImageIcon(blue.readImage()));
					break;
				case GOLD:
					//creates new GoldPlate Icon, associates it with plateButton at location
					GoldPlateImg gold = new GoldPlateImg();
					plateButton.setIcon(new ImageIcon(gold.readImage())); 
					break;
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		Plate p = null;
		int x = 0;
		String rot = " rotation";
		
		for (int i = 0; i < belt.getSize(); i++) {
			if (button == belt_buttons[i]) {
				p = belt.getPlateAtPosition(i);
				x = i;
			}
		}
		
		if (belt.getAgeOfPlateAtPosition(x) > 1 || belt.getAgeOfPlateAtPosition(x) < 1) {
			rot = " rotations";
		}
		
		String info = "";
		
		// if there is a plate at the position, update its info with the information
		if (p != null) {			
			info = "Plate Color: " + p.getColor()
			+ System.lineSeparator() + "Belt Position: " + x 
			+ System.lineSeparator() + "Sushi: " + p.getContents().getName()
			+ System.lineSeparator() + "Made by: " + p.getChef().getName()
			+ System.lineSeparator() + "Age of Plate: " + belt.getAgeOfPlateAtPosition(x) + rot
			+ System.lineSeparator() + "Ingredients: " 
			+ System.lineSeparator();

			for (int j = 0; j < p.getContents().getIngredients().length; j++) {
				info += "        " + p.getContents().getIngredients()[j].getAmount() + " ounces of " 
						+ p.getContents().getIngredients()[j].getName() + System.lineSeparator();
			}
		} else {
			// if there is no plate at the position, its info should reflect that
			info = "No plate in belt position: " + x;
		}
		
		UIManager.put("OptionPane.messageFont", new Font("Helvetica Neue", Font.PLAIN, 10));
		UIManager.put("OptionPane.background", Color.GRAY);
		UIManager.put("Panel.background", Color.GRAY);
		JOptionPane.showMessageDialog(null, info, "Plate Information", JOptionPane.PLAIN_MESSAGE);
	}
}

/**
 * This layout manager allows you to place components to form a circle within a
 * Container 
 */

class CircleLayout implements LayoutManager {

  ArrayList components;
  ArrayList names;

  private boolean isCircle;

  /**
   * Creates a new CircleLayout that lays out components in a perfect circle
   */

  public CircleLayout() {
    this(true);
  }

  /*
   * Creates a new CircleLayout that lays out components in either an Ellipse or
   * a Circle. Ellipse Layout is not yet implemented.
   * 
   * @param circle
   *          Indicated the shape to use. It's true for circle or false for
   *          ellipse.
   */
  public CircleLayout(boolean circle) {
    isCircle = circle;
  }

  /**
   * For compatibility with LayoutManager interface
   */
  public void addLayoutComponent(String name, Component comp) {
  }

  /**
   * Arranges the parent's Component objects in either an Ellipse or a Circle.
   * Ellipse is not yet implemented.
   */
  public void layoutContainer(Container parent) {
    int x, y, w, h, s, c;
    int n = parent.getComponentCount();
    double parentWidth = parent.getSize().width;
    double parentHeight = parent.getSize().height;
    Insets insets = parent.getInsets();
    int centerX = (int) (parentWidth - (insets.left + insets.right)) / 2;
    int centerY = (int) (parentHeight - (insets.top + insets.bottom)) / 2;

    Component comp = null;
    Dimension compPS = null;
    if (n == 1) {
      comp = parent.getComponent(0);
      x = centerX;
      y = centerY;
      compPS = comp.getPreferredSize();
      w = compPS.width;
      h = compPS.height;
      comp.setBounds(x, y, w, h);
    } else {
      double r = (Math.min(parentWidth - (insets.left + insets.right), parentHeight
          - (insets.top + insets.bottom))) / 2;
      r *= 0.75; // Multiply by .75 to account for extreme right and bottom
                  // Components
      for (int i = 0; i < n; i++) {
        comp = parent.getComponent(i);
        compPS = comp.getPreferredSize();
        if (isCircle) {
          c = (int) (r * Math.cos(2 * i * Math.PI / n));
          s = (int) (r * Math.sin(2 * i * Math.PI / n));
        } else {
          c = (int) ((centerX * 0.75) * Math.cos(2 * i * Math.PI / n));
          s = (int) ((centerY * 0.75) * Math.sin(2 * i * Math.PI / n));
        }
        x = c + centerX;
        y = s + centerY;

        w = compPS.width;
        h = compPS.height;

        comp.setBounds(x, y, w, h);
      }
    }

  }

  /**
   * Returns this CircleLayout's preferred size based on its Container
   * 
   * @param target
   *          This CircleLayout's target container
   * @return The preferred size
   */

  public Dimension preferredLayoutSize(Container target) {
    return target.getSize();
  }

  /**
   * Returns this CircleLayout's minimum size based on its Container
   * 
   * @param target
   *          This CircleLayout's target container
   * @return The minimum size
   */
  public Dimension minimumLayoutSize(Container target) {
    return target.getSize();
  }

  /**
   * For compatibility with LayoutManager interface
   */
  public void removeLayoutComponent(Component comp) {
  }

  /**
   * Returns a String representation of this CircleLayout.
   * 
   * @return A String that represents this CircleLayout
   */
  public String toString() {
    return this.getClass().getName();
  }

}
