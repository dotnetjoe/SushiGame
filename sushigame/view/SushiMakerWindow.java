package sushigame.view;

import java.awt.CardLayout;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sushigame.model.NumIngredsInRollChangedEvent;
import sushigame.model.PlateTypeChangedEvent;
import sushigame.model.RollIngredAddedEvent;
import sushigame.model.SpecificTypeOfSushiChangedEvent;
import sushigame.model.TypeOfSushiChangedEvent;
import sushigame.model.UserCustomSushiInfo;

import java.util.HashMap;
import java.util.Map;

public class SushiMakerWindow extends java.util.Observable implements ItemListener{
	 
	private JFrame make_sushi;
	private JPanel main_container, sushi_type, specific_sushi_type, num_roll_sushi_types, roll_sushi_types, sn_plate_type,
			roll_plate_type, sn_gold_plate, roll_gold_plate, sn_set_plate_position, roll_set_plate_position, confirm_sushi;
	private JLabel types_of_sushi_label, specific_types_of_sushi_label, num_of_roll_ingreds, roll_sushi_types_label,
			ingred_amount_label, sn_plate_type_label, roll_plate_type_label, sn_gold_plate_label, roll_gold_plate_label,
			sn_set_plate_position_label, roll_set_plate_position_label, confirm_sushi_label, roll_name;
	private JButton ok_btn1, back_btn1, ok_btn2, ok_btn3, back_btn2, back_btn3, ok_btn4, back_btn4, back_btn5,
			ok_btn5, ok_btn6, back_btn6, ok_btn7, back_btn7, ok_btn8, back_btn8, ok_btn9, back_btn9, ok_btn10,
			back_btn10, ok_btn11;
	private JTextField ingred_amount, sn_gold_plate_price, roll_gold_plate_price, sn_set_plate_position_tf,
			roll_set_plate_position_tf, roll_name_box;
	private String[] types_of_sushi, specific_types_of_sushi, num_roll_types_of_sushi, roll_types_of_sushi, user_roll_ingreds,
			sn_plate_types, roll_plate_types;
	private Map<String, String> sushi_order;
	private Map<String, Double> ingreds_amount;
	private JComboBox types_of_sushi_box, specific_types_of_sushi_box, num_roll_types_of_sushi_box, roll_types_of_sushi_box,
			sn_plate_types_box, roll_plate_types_box;
	private String selected_sushi_type, selected_specific_sushi_type, num_ingreds_in_roll, plate_type, unchanging_num_of_ingreds;
	private UserCustomSushiInfo user_sushi_info;
	private CardLayout cl;
	private int pos, ingred_count;
	private boolean was_at_position_0, is_first_time;
	
	public SushiMakerWindow(PlayerChefView player){
		make_sushi = new JFrame("Make your own Sushi!");
		user_sushi_info = new UserCustomSushiInfo(this);
		sushi_order = new HashMap<String, String>();
		ingreds_amount = new HashMap<String, Double>();
		selected_sushi_type = "Sashimi";
		selected_specific_sushi_type = "Tuna";
		main_container = new JPanel();
		cl = new CardLayout();
		main_container.setLayout(cl);
		pos = 0;
		ingred_count = 1;
		user_roll_ingreds = new String[8];
		user_roll_ingreds[0] = "Tuna";
		plate_type = "Red Plate";
		num_ingreds_in_roll = "1";
		was_at_position_0 = true;
		is_first_time = true;
		
		make_sushi.setLocationRelativeTo(null);
		make_sushi.setPreferredSize(new Dimension(600, 220));
		
		sushi_type = new JPanel();
		types_of_sushi_label = new JLabel("Select the kind of Sushi you want: ");
		types_of_sushi_label.setVisible(true);
		sushi_type.add(types_of_sushi_label, JLabel.LEFT_ALIGNMENT);
		types_of_sushi = new String[]{"Sashimi", "Nigiri", "Roll"};
		types_of_sushi_box = new JComboBox(types_of_sushi);
		types_of_sushi_box.addItemListener(this);
		types_of_sushi_box.setVisible(true);
		sushi_type.add(types_of_sushi_box);
		
		ok_btn1 = new JButton("OK");
		
		ok_btn1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				sushi_order.put("sushi type", selected_sushi_type);
				if(selected_sushi_type.equals("Sashimi") || selected_sushi_type.equals("Nigiri")){
					cl.show(main_container, "specific sushi type");
				}else{
					cl.show(main_container, "num roll specific types");
				}
			}
		});
		
		sushi_type.add(ok_btn1);
		
		specific_sushi_type = new JPanel();
		specific_types_of_sushi_label = new JLabel("Select the kind of shellfish or fish you want in your Sushi: ");
		specific_types_of_sushi_label.setVisible(true);
		specific_sushi_type.add(specific_types_of_sushi_label, JLabel.LEFT_ALIGNMENT);
		specific_types_of_sushi = new String[]{"Tuna", "Eel", "Salmon", "Crab", "Shrimp"};
		specific_types_of_sushi_box = new JComboBox(specific_types_of_sushi);
		specific_types_of_sushi_box.addItemListener(this);
		specific_types_of_sushi_box.setVisible(true);
		specific_sushi_type.add(specific_types_of_sushi_box);
			
		back_btn1 = new JButton("Back to Sushi type choice");
		back_btn1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				cl.show(main_container, "sushi type");
			}
		});
		
		ok_btn2 = new JButton("OK");
		ok_btn2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				sushi_order.put("specific sushi type", selected_specific_sushi_type);
				cl.show(main_container, "sashimi or nigiri plate type");
			}
		});
		
		specific_sushi_type.add(back_btn1);
		specific_sushi_type.add(ok_btn2);
		
		num_roll_sushi_types = new JPanel();
		roll_name = new JLabel("Name your roll: ");
		roll_name.setVisible(true);
		num_roll_sushi_types.add(roll_name, JLabel.LEFT_ALIGNMENT);
		roll_name_box = new JTextField(10);
		num_roll_sushi_types.add(roll_name_box);
		num_of_roll_ingreds = new JLabel("Choose the number of ingredients you want in your Roll: ");
		num_of_roll_ingreds.setVisible(true);
		num_roll_sushi_types.add(num_of_roll_ingreds, JLabel.LEFT_ALIGNMENT);
		num_roll_types_of_sushi = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};
		num_roll_types_of_sushi_box = new JComboBox(num_roll_types_of_sushi);
		num_roll_types_of_sushi_box.addItemListener(this);
		num_roll_types_of_sushi_box.setVisible(true);
		num_roll_sushi_types.add(num_roll_types_of_sushi_box);
		
		back_btn2 = new JButton("Back to Sushi type choice");
		back_btn2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				cl.show(main_container, "sushi type");
			}
		});
		
		ok_btn3 = new JButton("OK");
		ok_btn3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				String roll_name = roll_name_box.getText();
				if(roll_name.equals("")){
					JOptionPane.showMessageDialog(null, "No name was entered.", "No name error", JOptionPane.ERROR_MESSAGE);
				}else{
					sushi_order.put("roll name", roll_name);
					sushi_order.put("num of roll ingreds", num_ingreds_in_roll);
					setNumOfRollIngreds(num_ingreds_in_roll);
					cl.show(main_container, "roll ingreds types");
				}	
			}
		});
		
		num_roll_sushi_types.add(back_btn2);
		num_roll_sushi_types.add(ok_btn3);
		
		roll_sushi_types = new JPanel();
		roll_sushi_types_label = new JLabel("Choose the ingredient: ");
		roll_sushi_types_label.setVisible(true);
		roll_sushi_types.add(roll_sushi_types_label, JLabel.LEFT_ALIGNMENT);
		roll_types_of_sushi = new String[]{"Tuna", "Eel", "Salmon", "Crab", "Shrimp", "Avocado", "Seaweed", "Rice"};
		roll_types_of_sushi_box = new JComboBox(roll_types_of_sushi);
		roll_types_of_sushi_box.addItemListener(this);
		roll_types_of_sushi_box.setVisible(true);
		roll_sushi_types.add(roll_types_of_sushi_box);
		
		ingred_amount_label = new JLabel("<html>Enter amount of Ingredient you want in your Sushi <br> (must be"
				+ "	greater than 0 and less than or equal to 1.5): ");
		ingred_amount = new JTextField(10);
		roll_sushi_types.add(ingred_amount_label);
		roll_sushi_types.add(ingred_amount);
		
		back_btn3 = new JButton("Back to number of Roll Ingredients choice");
		back_btn3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				cl.show(main_container, "num roll specific types");
			}
		});
		
		ok_btn4 = new JButton("OK");
		ok_btn4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				String user_amount = ingred_amount.getText();
				try{	
					if((user_amount.equals("")) || (Double.parseDouble(user_amount) <= 0) || 
							(Double.parseDouble(user_amount) > 1.5)){
						JOptionPane.showMessageDialog(null, "Either no amount for the Ingredient was entered, amount was"
								+ " less than or equal to 0, or amount was greater than 1.5", 
								"Invalid Input", JOptionPane.ERROR_MESSAGE);
					}
					else{
						String roll_ingred_added = user_sushi_info.getRollIngredAdded();
						setAndUpdateRollIngredAdded(roll_ingred_added);
						double ingred_amount = Double.parseDouble(user_amount);
						ingred_amount = ((int) ((ingred_amount * 100.0)+0.5))/100.0;
						if(ingreds_amount.get(user_roll_ingreds[0]) != null && was_at_position_0){
								double new_amount = ingreds_amount.get(user_roll_ingreds[0]);
								new_amount += ingred_amount;
								new_amount = ((int) ((new_amount * 100.0)+0.5))/100.0;
								ingreds_amount.put(user_roll_ingreds[0], new_amount);
								int num_ingreds = Integer.parseInt(num_ingreds_in_roll);
								num_ingreds--;
								String new_num_ingreds = Integer.toString(num_ingreds);
								sushi_order.put("num of roll ingreds", new_num_ingreds);
							
						}		
						else if(ingreds_amount.get(user_roll_ingreds[pos - 1]) != null){
								double new_amount = ingreds_amount.get(user_roll_ingreds[pos - 1]);
								new_amount += ingred_amount;
								new_amount = ((int) ((new_amount * 100.0)+0.5))/100.0;
								ingreds_amount.put(user_roll_ingreds[pos - 1], new_amount);	
								int num_ingreds = Integer.parseInt(num_ingreds_in_roll);
								num_ingreds--;
								String new_num_ingreds = Integer.toString(num_ingreds);
								sushi_order.put("num of roll ingreds", new_num_ingreds);
						}else{
							ingreds_amount.put(user_roll_ingreds[pos - 1], ingred_amount);
						}
						JOptionPane.showMessageDialog(null, "Successfully added Ingredient to Roll!", "Ingredient added",
								JOptionPane.INFORMATION_MESSAGE);
					}
					if((Integer.parseInt(num_ingreds_in_roll) == ingred_count) && (is_first_time == false)){
						resetCounts();
						setNumOfRollIngreds(unchanging_num_of_ingreds);
						cl.show(main_container, "roll plate type");
					}
				}catch(NumberFormatException exception){
					JOptionPane.showMessageDialog(null, "Incorrect input entered. Input must be an Integer or a number with"
							+ " a decimal (Double) between a number greater than 0 and no more than 1.5.", "Invalid Input", 
							JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		
		roll_sushi_types.add(back_btn3);
		roll_sushi_types.add(ok_btn4);
		
		sn_plate_type = new JPanel();
		sn_plate_type_label = new JLabel("Select the kind of Plate you want to put your Sushi on: ");
		sn_plate_type_label.setVisible(true);
		sn_plate_type.add(sn_plate_type_label, JLabel.LEFT_ALIGNMENT);
		sn_plate_types = new String[]{"Red Plate", "Blue Plate", "Green Plate", "Gold Plate"};
		sn_plate_types_box = new JComboBox(sn_plate_types);
		sn_plate_types_box.addItemListener(this);
		sn_plate_types_box.setVisible(true);
		sn_plate_type.add(sn_plate_types_box);
		
		back_btn4 = new JButton ("Back to specific Sashimi and Nigiri types");
		back_btn4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				cl.show(main_container, "specific sushi type");
			}
		});
		
		ok_btn5 = new JButton("OK");
		ok_btn5.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				sushi_order.put("plate type", plate_type);
				if(plate_type.equals("Gold Plate")){
					cl.show(main_container, "sashimi or nigiri gold plate");
				}else{
					cl.show(main_container, "sashimi or nigiri plate position");
				}
			}
		});
		
		sn_plate_type.add(back_btn4);
		sn_plate_type.add(ok_btn5);
		
		roll_plate_type = new JPanel();
		roll_plate_type_label = new JLabel("Select the kind of Plate you want to put your Sushi on: ");
		roll_plate_type_label.setVisible(true);
		roll_plate_type.add(roll_plate_type_label, JLabel.LEFT_ALIGNMENT);
		roll_plate_types = new String[]{"Red Plate", "Blue Plate", "Green Plate", "Gold Plate"};
		roll_plate_types_box = new JComboBox(roll_plate_types);
		roll_plate_types_box.addItemListener(this);
		roll_plate_types_box.setVisible(true);
		roll_plate_type.add(roll_plate_types_box);
		
		back_btn5 = new JButton ("Back to specific Sashimi and Nigiri types");
		back_btn5.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				sushi_order.put("num of roll ingreds", unchanging_num_of_ingreds);
				resetAmounts();
				cl.show(main_container, "roll ingreds types");
			}
		});
		
		ok_btn6 = new JButton("OK");
		ok_btn6.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				sushi_order.put("plate type", plate_type);
				if(plate_type.equals("Gold Plate")){
					cl.show(main_container, "roll gold plate");
				}else{
					cl.show(main_container, "roll plate position");
				}
			}
		});
		
		roll_plate_type.add(back_btn5);
		roll_plate_type.add(ok_btn6);
		
		sn_gold_plate = new JPanel();
		sn_gold_plate_label = new JLabel("<html> Enter the price of the Gold Plate <br>"
				+ "(must be between $5.00 and $10.00), inclusive: ");
		sn_gold_plate_price = new JTextField("$", 10);
		sn_gold_plate_label.setVisible(true);
		sn_gold_plate.add(sn_gold_plate_label, JLabel.LEFT_ALIGNMENT);
		sn_gold_plate.add(sn_gold_plate_price);
		
		back_btn6 = new JButton ("Back to Plate types for your Sushi");
		back_btn6.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				cl.show(main_container, "sashimi or nigiri plate type");
			}
		});
		
		ok_btn7 = new JButton("OK");
		ok_btn7.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				String user_price = sn_gold_plate_price.getText();
				boolean no_error = false;
				try{	
					if((user_price.equals("")) || (user_price.equals("$"))){
						JOptionPane.showMessageDialog(null, "No price for the Gold Plate was entered",
								"Price Error", JOptionPane.ERROR_MESSAGE);
					}
					else{
						if(user_price.contains("$")){
							int pos = user_price.lastIndexOf('$');
							user_price = user_price.substring(pos + 1);
							if((Double.parseDouble(user_price) < 5.00) || (Double.parseDouble(user_price) > 10.00)){
								JOptionPane.showMessageDialog(null, "Either the price entered was less than $5.00, "
										+ "or the price entered was greater than $10.00", "Price Error", JOptionPane.ERROR_MESSAGE);
							}else{
								double rounded_price = Double.parseDouble(user_price);
								rounded_price = ((int) ((rounded_price * 100.0)+0.5))/100.0;
								user_price = Double.toString(rounded_price);
								if(rounded_price % 1.0 == 0){
									user_price += "0";
								}
								sushi_order.put("gold plate price", user_price);	
								no_error = true;
							}
								
						}else{
							if((Double.parseDouble(user_price) < 5.00) || (Double.parseDouble(user_price) > 10.00)){
								JOptionPane.showMessageDialog(null, "Either the price entered was less than $5.00, "
										+ "or the price entered was greater than $10.00", "Price Error", JOptionPane.ERROR_MESSAGE);
							}else{
								double rounded_price = Double.parseDouble(user_price);
								rounded_price = ((int) ((rounded_price * 100.0)+0.5))/100.0;
								user_price = Double.toString(rounded_price);
								if(rounded_price % 1.0 == 0){
									user_price += "0";
								}
								sushi_order.put("gold plate price", user_price);	
								no_error = true;
							}	
						}
					}
				}catch(NumberFormatException exception){
					JOptionPane.showMessageDialog(null, "<html> Incorrect input entered. Input must be an Integer or a number with"
							+ " a decimal (price aka a Double) between $5.00 and $10.00, inclusive. <br> You may enter your answer with"
							+ " no $, one $, or as many $ as you like as long as the all occur in consecution at the beginning of the number", 
							"Invalid Input", JOptionPane.ERROR_MESSAGE);	
				}
			
				if(no_error){
					cl.show(main_container, "sashimi or nigiri plate position");
				}	
			}
		});
		
		sn_gold_plate.add(back_btn6);
		sn_gold_plate.add(ok_btn7);
		
		roll_gold_plate = new JPanel();
		roll_gold_plate_label = new JLabel("<html> Enter the price of the Gold Plate <br>"
				+ "(must be between $5.00 and $10.00), inclusive: ");
		roll_gold_plate_price = new JTextField("$", 10);
		roll_gold_plate_label.setVisible(true);
		roll_gold_plate.add(roll_gold_plate_label, JLabel.LEFT_ALIGNMENT);
		roll_gold_plate.add(roll_gold_plate_price);
		
		back_btn7 = new JButton ("Back to Plate types for your Sushi");
		back_btn7.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				cl.show(main_container, "roll plate type");
			}
		});
		
		ok_btn8 = new JButton("OK");
		ok_btn8.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				String user_price = roll_gold_plate_price.getText();
				boolean no_error = false;
				try{	
					if((user_price.equals("")) || (user_price.equals("$"))){
						JOptionPane.showMessageDialog(null, "No price for the Gold Plate was entered",
								"Price Error", JOptionPane.ERROR_MESSAGE);
					}
					else{
						if(user_price.contains("$")){
							int pos = user_price.lastIndexOf('$');
							user_price = user_price.substring(pos + 1);
							if((Double.parseDouble(user_price) < 5.00) || (Double.parseDouble(user_price) > 10.00)){
								JOptionPane.showMessageDialog(null, "Either the price entered was less than $5.00, "
										+ "or the price entered was greater than $10.00", "Price Error", JOptionPane.ERROR_MESSAGE);
							}else{
								double rounded_price = Double.parseDouble(user_price);
								rounded_price = ((int) ((rounded_price * 100.0)+0.5))/100.0;
								user_price = Double.toString(rounded_price);
								if(rounded_price % 1.0 == 0){
									user_price += "0";
								}
								sushi_order.put("gold plate price", user_price);	
								no_error = true;
							}
								
						}else{
							if((Double.parseDouble(user_price) < 5.00) || (Double.parseDouble(user_price) > 10.00)){
								JOptionPane.showMessageDialog(null, "Either the price entered was less than $5.00, "
										+ "or the price entered was greater than $10.00", "Price Error", JOptionPane.ERROR_MESSAGE);
							}else{
								double rounded_price = Double.parseDouble(user_price);
								rounded_price = ((int) ((rounded_price * 100.0)+0.5))/100.0;
								user_price = Double.toString(rounded_price);
								if(rounded_price % 1.0 == 0){
									user_price += "0";
								}
								sushi_order.put("gold plate price", user_price);	
								no_error = true;
							}	
						}
					}	
				}catch(NumberFormatException exception){
					JOptionPane.showMessageDialog(null, "<html> Incorrect input entered. Input must be an Integer or a number with"
							+ " a decimal (price aka a Double) between $5.00 and $10.00, inclusive. <br> You may enter your answer with"
							+ " no $, one $, or as many $ as you like as long as the all occur in consecution at the beginning of the number", 
							"Invalid Input", JOptionPane.ERROR_MESSAGE);	
				}
				if(no_error){
					cl.show(main_container, "roll plate position");
				}	
			}
		});
		
		roll_gold_plate.add(back_btn7);
		roll_gold_plate.add(ok_btn8);
		
		sn_set_plate_position = new JPanel();
		sn_set_plate_position_label = new JLabel("<html> Enter the positon you would like your Plate to be placed at: " 
				+ "<br> (Note: Plate will be placed at next avaible position if position you enter "
				+ "<br> is occupied. Mouse over a position to see it's position number (0-19).)");
		sn_set_plate_position_tf = new JTextField(10);
		sn_set_plate_position_label.setVisible(true);
		sn_set_plate_position.add(sn_set_plate_position_label);
		sn_set_plate_position.add(sn_set_plate_position_tf);
		
		back_btn8 = new JButton ("Back to Plate types for your Sushi");
		back_btn8.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(plate_type.equals("Gold Plate")){
					cl.show(main_container, "sashimi or nigiri gold plate");
				}else{
					cl.show(main_container, "sashimi or nigiri plate type");
				}
			}
		});
		
		ok_btn9 = new JButton("OK");
		ok_btn9.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				String user_position = sn_set_plate_position_tf.getText();
				try{	
					if(user_position.equals("") || (Integer.parseInt(user_position) < 0) ||
							Integer.parseInt(user_position) > 19){
						JOptionPane.showMessageDialog(null, "Either no position was entered, position was less than 0, "
								+ "or position was greater than 19", "Position Error", JOptionPane.ERROR_MESSAGE);
					}else{
						sushi_order.put("plate position", user_position);
						determineConfirmSushiPanel();
						cl.show(main_container, "confirm sushi");
					}
				}catch(NumberFormatException exception){
					JOptionPane.showMessageDialog(null, "Incorrect input entered. Input must be an Integer between 0 and 19, inclusive.", 
							"Invalid Input", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		sn_set_plate_position.add(back_btn8);
		sn_set_plate_position.add(ok_btn9);
		
		roll_set_plate_position = new JPanel();
		roll_set_plate_position_label = new JLabel("<html> Enter the positon you would like your Plate to be placed at: " 
				+ "<br> (Note: Plate will be placed at next avaible position if position you enter "
				+ "<br> is occupied. Mouse over a position to see it's position number (0-19).)");
		roll_set_plate_position_tf = new JTextField(10);
		roll_set_plate_position_label.setVisible(true);
		roll_set_plate_position.add(roll_set_plate_position_label);
		roll_set_plate_position.add(roll_set_plate_position_tf);
		
		back_btn9 = new JButton ("Back to Plate types for your Sushi");
		back_btn9.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(plate_type.equals("Gold Plate")){
					cl.show(main_container, "roll gold plate");
				}else{
					cl.show(main_container, "roll plate type");
				}
			}
		});
		
		ok_btn10 = new JButton("OK");
		ok_btn10.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				String user_position = roll_set_plate_position_tf.getText();
				try{	
					if(user_position.equals("") || (Integer.parseInt(user_position) < 0) ||
							Integer.parseInt(user_position) > 19){
						JOptionPane.showMessageDialog(null, "Either no position was entered, position was less than 0, "
								+ "or position was greater than 19", "Position Error", JOptionPane.ERROR_MESSAGE);
					}else{
						sushi_order.put("plate position", user_position);
						determineConfirmSushiPanel();
						cl.show(main_container, "confirm sushi");
					}
				}catch(NumberFormatException exception){
					JOptionPane.showMessageDialog(null, "Incorrect input entered. Input must be an Integer between 0 and 19, inclusive.", 
							"Invalid Input", JOptionPane.ERROR_MESSAGE);
				}	
				
			}
		});
		
		roll_set_plate_position.add(back_btn9);
		roll_set_plate_position.add(ok_btn10);
		
		confirm_sushi = new JPanel();
		confirm_sushi_label = new JLabel("");
		
		confirm_sushi.add(confirm_sushi_label);
		
		back_btn10 = new JButton("Back to set position for your Plate");
		back_btn10.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(selected_sushi_type.equals("Roll")){
					cl.show(main_container, "roll plate position");
				}else{
					cl.show(main_container, "sashimi or nigiri plate position");
				}
			}
		});
		
		ok_btn11 = new JButton("Make my Sushi!");
		ok_btn11.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				make_sushi.dispose();
				player.getMakeSushiButton().doClick();
			}
		});
		
		
		main_container.add(sushi_type, "sushi type");
		main_container.add(specific_sushi_type, "specific sushi type");
		main_container.add(num_roll_sushi_types, "num roll specific types");
		main_container.add(roll_sushi_types, "roll ingreds types");
		main_container.add(sn_plate_type, "sashimi or nigiri plate type");
		main_container.add(roll_plate_type, "roll plate type");
		main_container.add(sn_gold_plate, "sashimi or nigiri gold plate");
		main_container.add(roll_gold_plate, "roll gold plate");
		main_container.add(sn_set_plate_position, "sashimi or nigiri plate position");
		main_container.add(roll_set_plate_position, "roll plate position");
		main_container.add(confirm_sushi, "confirm sushi");
		
		cl.show(main_container, "sushi type");
		
		make_sushi.add(main_container);
		
		make_sushi.pack();
		make_sushi.setVisible(true);
		
	}

	public void setSelectedSushiType(String selected_sushi_type){
		this.selected_sushi_type = selected_sushi_type;
	}
	
	public void setSelectedSpecificSushiType(String selected_specific_sushi_type){
		this.selected_specific_sushi_type = selected_specific_sushi_type;
	}
	
	public void setNumOfRollIngreds(String num_ingreds_in_roll){
		this.num_ingreds_in_roll = num_ingreds_in_roll;	
		unchanging_num_of_ingreds = num_ingreds_in_roll;
	}
	
	public void resetCounts(){
		pos = 0;
		ingred_count = 1;
		num_ingreds_in_roll = "1";
		is_first_time = true;
	}
	
	public void resetAmounts(){
		ingreds_amount = new HashMap<String, Double>();
	}
	
	public void setAndUpdateRollIngredAdded(String roll_ingred_added){
		if(roll_ingred_added != null){
			user_roll_ingreds[pos] = roll_ingred_added;
			was_at_position_0 = false;
		}	
		if(!is_first_time){
			ingred_count++;
		}
		pos++;
		is_first_time = false;
	}
	
	public void setPlateType(String plate_type){
		this.plate_type = plate_type;
	}
	
	public Map<String, String> getSushiOrder(){
		return sushi_order;
	}
	
	public Map<String, Double> getIngredsAmount(){
		return ingreds_amount;
	}
	
	public String[] getRollIngreds(){
		return user_roll_ingreds;
	}
	
	public void determineConfirmSushiPanel(){
		if(plate_type.equals("Gold Plate") && selected_sushi_type.equals("Roll")){
			confirm_sushi_label.setText("<html> <b>Confirm Sushi</b> <br> Sushi Type: " + sushi_order.get("sushi type")
				+ "<br> Name of Roll: " + sushi_order.get("roll name") + "<br> Number of Ingredients in Roll: " 
				+ sushi_order.get("num of roll ingreds") + "<br> Ingredients and amount of each:" 
				+ printRollIngreds(user_roll_ingreds, ingreds_amount) + "<br> Type of Plate: Gold Plate" + "<br> Gold Plate price: $" 
				+ sushi_order.get("gold plate price") + "<br> Position Plate will be" + " set to (or next nearest " 
				+ "position to the one entered): " + sushi_order.get("plate position"));
			confirm_sushi_label.setVisible(true);
			confirm_sushi.add(back_btn10);
			confirm_sushi.add(ok_btn11);
		}else if(selected_sushi_type.equals("Roll")){
			confirm_sushi_label.setText("<html> <b>Confirm Sushi</b> <br> Sushi Type: " + sushi_order.get("sushi type")
				+ "<br> Name of Roll: " + sushi_order.get("roll name") + "<br> Number of Ingredients in Roll: " 
				+ sushi_order.get("num of roll ingreds") + "<br> Ingredients and amount of each:" + printRollIngreds(user_roll_ingreds, ingreds_amount) 
				+ "<br> Type of Plate: " + plate_type + "<br> Position Plate will be" + " set to (or next nearest " 
				+ "position to the one entered): " + sushi_order.get("plate position"));
			confirm_sushi_label.setVisible(true);
			confirm_sushi.add(back_btn10);
			confirm_sushi.add(ok_btn11);
		}else if(plate_type.equals("Gold Plate")){
			confirm_sushi_label.setText("<html> <b>Confirm Sushi</b> <br> Sushi Type: " + sushi_order.get("sushi type")
				+ "<br> Specific kind of " + selected_sushi_type + ": " + selected_specific_sushi_type +  
				"<br> Type of Plate: Gold Plate" + "<br> Gold Plate price: $" + sushi_order.get("gold plate price") + 
				"<br> Position Plate will be" + " set to (or next nearest " + "position to the one entered): " 
				+ sushi_order.get("plate position"));
			confirm_sushi_label.setVisible(true);
			confirm_sushi.add(back_btn10);
			confirm_sushi.add(ok_btn11);
		}else{
			confirm_sushi_label.setText("<html> <b>Confirm Sushi</b> <br> Sushi Type: " + sushi_order.get("sushi type")
				+ "<br> Specific kind of " + selected_sushi_type + ": " + selected_specific_sushi_type +  
				"<br> Type of Plate: " + plate_type + "<br> Position Plate will be" + " set to (or next nearest " + 
				"position to the one entered): " + sushi_order.get("plate position"));
			confirm_sushi_label.setVisible(true);
			confirm_sushi.add(back_btn10);
			confirm_sushi.add(ok_btn11);
		}
	}
	
	private String printRollIngreds(String[] roll_ingreds, Map<String, Double> ingreds_amount){
		String ingreds_printed = "<html>";
		int count = 1;
		/*	prints 4 ingreds on one line, then goes to next line if...
		 * 	...there is still more Ingredients
		 */
		for(String ingreds : roll_ingreds){
			if(count < 5){
				if(ingreds_amount.get(ingreds) != null && !ingreds_printed.contains(ingreds)){	
					//count used to determine where commas and line breaks should be placed
					if(count == roll_ingreds.length){
						ingreds_printed += ingreds + ": " + ingreds_amount.get(ingreds);
					}
					else if(count == 1){
						ingreds_printed += " " + ingreds + ": " + ingreds_amount.get(ingreds) + ", ";
					}
					else{
						ingreds_printed += ingreds + ": " + ingreds_amount.get(ingreds)+ ", ";
					}
				}	
			}
			else{
				if(ingreds_amount.get(ingreds) != null && !ingreds_printed.contains(ingreds)){
					//breaks line if 4 ingreds have been stored already, then keeps printing on next line
					if(count == 5){
						ingreds_printed += "<br>";
					}
					if(count == roll_ingreds.length){
						ingreds_printed += ingreds + ": " + ingreds_amount.get(ingreds);
					}
					else{
						ingreds_printed += ingreds + ": " + ingreds_amount.get(ingreds) + ", ";
					}
				}	
			}
			count++;
		}
		
		if(ingreds_printed.lastIndexOf(',') == ingreds_printed.length() - 2){
			ingreds_printed = ingreds_printed.substring(0, ingreds_printed.length() - 2);
		}
		
		return ingreds_printed;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if((JComboBox) e.getSource() == types_of_sushi_box){
			setChanged();
			notifyObservers(new TypeOfSushiChangedEvent(e));
			String selected_sushi_type = user_sushi_info.getSelectedSushiType();
			setSelectedSushiType(selected_sushi_type);
		}else if((JComboBox) e.getSource() == specific_types_of_sushi_box){
			setChanged();
			notifyObservers(new SpecificTypeOfSushiChangedEvent(e));
			String selected_specific_sushi_type = user_sushi_info.getSelectedSpecificSushiType();
			setSelectedSpecificSushiType(selected_specific_sushi_type);
		}else if((JComboBox) e.getSource() == num_roll_types_of_sushi_box){
			setChanged();
			notifyObservers(new NumIngredsInRollChangedEvent(e));
			String num_ingreds_in_roll = user_sushi_info.getNumOfRollIngreds();
			setNumOfRollIngreds(num_ingreds_in_roll);
		}else if((JComboBox) e.getSource() == roll_types_of_sushi_box){
			setChanged();
			notifyObservers(new RollIngredAddedEvent(e));
		}else if((JComboBox) e.getSource() == sn_plate_types_box || (JComboBox) e.getSource() == roll_plate_types_box){
			setChanged();
			notifyObservers(new PlateTypeChangedEvent(e));
			String plate_type = user_sushi_info.getPlateType();
			setPlateType(plate_type);
		}
		
	}
	
}
