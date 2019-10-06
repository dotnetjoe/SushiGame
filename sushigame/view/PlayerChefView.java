package sushigame.view;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import comp401.sushi.*;
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

public class PlayerChefView extends JPanel implements ActionListener {

	private List<ChefViewListener> listeners;
	private Sushi kmp_roll;
	private Sushi crab_sashimi;
	private Sushi eel_nigiri;
	private int belt_size;
	private SushiMakerWindow sushi_maker_wind;
	private volatile boolean is_sushi_ready_to_make;
	private JButton make_sushi_button;

	public PlayerChefView(int belt_size) {
		
		this.belt_size = belt_size;
		listeners = new ArrayList<ChefViewListener>();
		is_sushi_ready_to_make = false;
		make_sushi_button = new JButton();

		JButton sushi_button = new JButton("Make some Sushi!");
		sushi_button.setActionCommand("make sushi");
		sushi_button.addActionListener(this);
		add(sushi_button);
		
		make_sushi_button.setOpaque(false);
		make_sushi_button.setContentAreaFilled(false);
		make_sushi_button.setBorderPainted(false);
		
	}
	
	public void setSushiMakerWindow(SushiMakerWindow sushi_maker_wind){
		this.sushi_maker_wind = sushi_maker_wind;
	}
	

	public void registerChefListener(ChefViewListener cl) {
		listeners.add(cl);
	}

	private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleRedPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleGreenPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleBluePlateRequest(plate_sushi, plate_position);
		}
	}
	
	private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
		for (ChefViewListener l : listeners) {
			l.handleGoldPlateRequest(plate_sushi, plate_position, price);
		}
	}
	
	public JButton getMakeSushiButton(){
		return make_sushi_button;
	}
	
	public void makeSushi(){
		Sushi sushi = null;
		
		if(sushi_maker_wind.getSushiOrder().get("sushi type").equals("Roll")){
			IngredientPortion[] ingreds;
			int num_ingreds = Integer.parseInt(sushi_maker_wind.getSushiOrder().get("num of roll ingreds"));
			ingreds = new IngredientPortion[num_ingreds];
			String[] old_ingreds = sushi_maker_wind.getRollIngreds();
			Map<String, Double> old_ingreds_amounts = sushi_maker_wind.getIngredsAmount();
			
			for(int i = 0; i < num_ingreds; i++){
				if(old_ingreds[i] != null){	
					switch(old_ingreds[i]){
						case "Tuna": {
							ingreds[i] = new TunaPortion(old_ingreds_amounts.get("Tuna"));
							break;
						}
						case "Eel": {
							ingreds[i] = new EelPortion(old_ingreds_amounts.get("Eel"));
							break;
						}
						case "Salmon": {
							ingreds[i] = new SalmonPortion(old_ingreds_amounts.get("Salmon"));
							break;
						}
						case "Crab": {
							ingreds[i] = new CrabPortion(old_ingreds_amounts.get("Crab"));
							break;
						}
						case "Shrimp": {
							ingreds[i] = new ShrimpPortion(old_ingreds_amounts.get("Shrimp"));
							break;
						}
						case "Avocado": {
							ingreds[i] = new AvocadoPortion(old_ingreds_amounts.get("Avocado"));
							break;
						}
						case "Seaweed": {
							ingreds[i] = new SeaweedPortion(old_ingreds_amounts.get("Seaweed"));
							break;
						}
						case "Rice": {
							ingreds[i] = new RicePortion(old_ingreds_amounts.get("Rice"));
							break;
						}
					}	
				}	
			}
			
			sushi = new Roll(sushi_maker_wind.getSushiOrder().get("roll name"), ingreds);
		}	
		else if(sushi_maker_wind.getSushiOrder().get("sushi type").equals("Nigiri")){
			switch(sushi_maker_wind.getSushiOrder().get("specific sushi type")){
				case "Tuna": {
					sushi = new Nigiri(Nigiri.NigiriType.TUNA);
					break;
				}
				case "Eel": {
					sushi = new Nigiri(Nigiri.NigiriType.EEL);
					break;
				}
				case "Salmon": {
					sushi = new Nigiri(Nigiri.NigiriType.SALMON);
					break;
				}
				case "Crab": {
					sushi = new Nigiri(Nigiri.NigiriType.CRAB);
					break;
				}
				case "Shrimp": {
					sushi = new Nigiri(Nigiri.NigiriType.SHRIMP);
					break;
				}
			}
		}
		else{
			switch(sushi_maker_wind.getSushiOrder().get("specific sushi type")){
				case "Tuna": {
					sushi = new Sashimi(Sashimi.SashimiType.TUNA);
					break;
				}
				case "Eel": {
					sushi = new Sashimi(Sashimi.SashimiType.EEL);
					break;
				}
				case "Salmon": {
					sushi = new Sashimi(Sashimi.SashimiType.SALMON);
					break;
				}
				case "Crab": {
					sushi = new Sashimi(Sashimi.SashimiType.CRAB);
					break;
				}
				case "Shrimp": {
					sushi = new Sashimi(Sashimi.SashimiType.SHRIMP);
					break;
				}
			}
		}
		
		int pos = Integer.parseInt(sushi_maker_wind.getSushiOrder().get("plate position"));
		switch(sushi_maker_wind.getSushiOrder().get("plate type")){
			case "Red Plate":{
				makeRedPlateRequest(sushi, pos);
				break;
			}
			case "Blue Plate":{
				makeBluePlateRequest(sushi, pos);
				break;
			}
			case "Green Plate":{
				makeGreenPlateRequest(sushi, pos);
				break;
			}
			case "Gold Plate":{
				double gold_plate_price = Double.parseDouble(sushi_maker_wind.getSushiOrder().get("gold plate price"));
				makeGoldPlateRequest(sushi, pos, gold_plate_price);
				break;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		SushiMakerWindow sushi_maker_wind = new SushiMakerWindow(this);
		
		make_sushi_button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				setSushiMakerWindow(sushi_maker_wind);
				makeSushi();
			}	
		});
		
	}
}

