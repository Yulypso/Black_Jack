import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;

public class BlackJackGUI implements ActionListener {

	private JPanel playerCardPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel bankCardPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private BlackJack bj = new BlackJack();
  	private JButton button1 = new JButton("Another Card");
  	private	JButton button2 = new JButton("No More Card");
  	private JButton button3 = new JButton("Reset");

	public BlackJackGUI() throws FileNotFoundException {

		JFrame frame = new JFrame("BlackJack GUI");
		frame.setMinimumSize(new Dimension(680, 480));	//taille minimal de l'affichage
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //permet de quitter également le programme

		frame.setLayout(new BorderLayout()); //on definie la frame (panel global) comme une grande zone

		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT); //creation d'un flowLayout LEFT 
		//FlowLayout : Stack
  		JPanel topPanel = new JPanel(flowLayout);	//creation de topPanel avec le flowLayout créé
  		
  		topPanel.add(button1);
  		button1.setActionCommand("key1");
  		button1.addActionListener(this);

  		topPanel.add(button2);
  		button2.setActionCommand("key2");
  		button2.addActionListener(this);

  		topPanel.add(button3);
  		button3.setActionCommand("key3");
  		button3.addActionListener(this);

  		button1.setEnabled(true);
		button2.setEnabled(true);

		frame.add(topPanel, BorderLayout.NORTH);
  		

		GridLayout gridLayout = new GridLayout(2,1);	//creation d'un gridLayout (2,1) 2 lignes 1 colonne 
		JPanel centerPanel = new JPanel(gridLayout);	//création de centerPanel avec le gridlaout créé

  		JPanel bankPanel = new JPanel(new BorderLayout());	//création de bankPanel avec un BorderLayout
		//BorderLayout : Zone
  		bankPanel.setBorder(BorderFactory.createTitledBorder("Bank"));
  		JPanel playerPanel = new JPanel(new BorderLayout());	//création d'un playerPanel avec un BorderLayout
  		playerPanel.setBorder(BorderFactory.createTitledBorder("Player"));
	
  		centerPanel.add(bankPanel, BorderLayout.NORTH); 	//ajout de BankPanel dans CenterPanel en spécifiant NORTH
  		centerPanel.add(playerPanel, BorderLayout.SOUTH);	//ajoute de playerPanel dans CenterPanel en spécifiant SOUTH

		//frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
  		frame.add(centerPanel, BorderLayout.CENTER);



  		//JPanel playerCardPanel = new JPanel(flowLayout); //dans le panel du joueur, on créé un autre panel pour stacker a gauche
		playerPanel.add(playerCardPanel, BorderLayout.NORTH); // on ajoute le playerCardPanel au playerPanel au nord

		//JPanel bankCardPanel = new JPanel(flowLayout);
		bankPanel.add(bankCardPanel, BorderLayout.NORTH);

  		/*File file = new File("./img/card_SPADE_5.png");
  		if(!file.exists()) {
  			throw new FileNotFoundException("Can't find "+file.getPath());
  		}

  		ImageIcon icon = new ImageIcon(file.getPath());
  		JLabel label = new JLabel(icon);
  		playerCardPanel.add(label);
*/		bj.reset();
  		playerCardPanel.updateUI();
  		bankCardPanel.updateUI();

  		try{
			updatePlayerPanel();
			updateBankPanel();
		} catch(FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		}



		frame.pack();
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		switch(e.getActionCommand()) {
			case "key1" : 
			try {
				bj.playerDrawAnotherCard();
				}catch (EmptyDeckException ex) {
				System.err.println(ex.getMessage());
				System.exit(-1);
			}
				
				try {
					updatePlayerPanel();
				} catch(FileNotFoundException ex) {
					System.out.println(ex.getMessage());
				}

  				playerCardPanel.updateUI();
  				bankCardPanel.updateUI();
				break;

			case "key2" : 
			try {
				bj.bankLastTurn();
				}catch (EmptyDeckException ex) {
				System.err.println(ex.getMessage());
				System.exit(-1);
			}
 				try {
					updateBankPanel();
				} catch(FileNotFoundException ex) {
					System.out.println(ex.getMessage());
				}

  				playerCardPanel.updateUI();
  				bankCardPanel.updateUI();
 				break;

 			case "key3" :
 				bj.reset();
 				try {
 					updatePlayerPanel();
					updateBankPanel();
				} catch(FileNotFoundException ex) {
					System.out.println(ex.getMessage());
				}

  				playerCardPanel.updateUI();
  				bankCardPanel.updateUI();
 				break;
 		}

 		if(bj.gameFinished == true) {
			button1.setEnabled(false);
			button2.setEnabled(false);
		}else {
			button1.setEnabled(true);
			button2.setEnabled(true);
		}
	}

	public void addToPanel(JPanel p, String token) throws FileNotFoundException {

  		File file = new File("./img/card_"+token+".png");
  		if(!file.exists()) {
  			throw new FileNotFoundException("Can't find "+file.getPath());
  		}

  		ImageIcon icon = new ImageIcon(file.getPath());
  		JLabel label = new JLabel(icon);
  		p.add(label);

	}

	public void updatePlayerPanel() throws FileNotFoundException {
		

		playerCardPanel.removeAll();
		String save = "HEART";
		String va;
		String co;


		//List<Card> playerCardList = bj.getPlayerCardList();

			for(Card c : bj.getPlayerCardList()) {
				co = c.getColorSymbole();
				va = c.getValueSymbole();


				if(co == "♡") {
					save = "HEART"; 
					//addToPanel(playerCardPanel, "HEART_6");
				} else if(co == "♠") {
					save = "SPADE";
				} else if(co == "♣") {
					save = "CLUB"; 
				} else if(co == "♢") {
					save = "DIAMOND";
				}

				addToPanel(playerCardPanel, save+"_"+va);
			}
			bj.playerHand.count();
			playerCardPanel.add(new JLabel("Player Best : "+ bj.PlayerBest()));


			if(bj.PlayerBest()==21) {
				addToPanel(playerCardPanel, "blackjack");
			}

			if(bj.isPlayerWinner() == true && bj.isBankWinner() == true) {
						
						if(bj.PlayerBest() > bj.BankBest()) {
							addToPanel(playerCardPanel, "winner");
							addToPanel(bankCardPanel, "looser");
							
						}
						else if(bj.PlayerBest() == bj.BankBest()) {
							addToPanel(playerCardPanel, "draw");
							addToPanel(bankCardPanel, "draw");
							
						}
						else {
							addToPanel(bankCardPanel, "winner");
							addToPanel(playerCardPanel, "looser");
						}
			} 
			else if(bj.isBankWinner() == false && bj.isPlayerWinner() == true) {
				addToPanel(playerCardPanel, "winner");
				addToPanel(bankCardPanel, "looser");
			} 
			else if(bj.isPlayerWinner() == false && bj.isBankWinner() == true) {
				addToPanel(bankCardPanel, "winner");
				addToPanel(playerCardPanel, "looser");
			}
	
	}


	public void updateBankPanel() throws FileNotFoundException {
		

		bankCardPanel.removeAll();
		String save = "HEART";
		String va;
		String co;


		//List<Card> playerCardList = bj.getPlayerCardList();

		for(Card c : bj.getBankCardList()) {
			co = c.getColorSymbole();
			va = c.getValueSymbole();


			if(co == "♡") {
				save = "HEART"; 
				//addToPanel(playerCardPanel, "HEART_6");
			} else if(co == "♠") {
				save = "SPADE";
			} else if(co == "♣") {
				save = "CLUB"; 
			} else if(co == "♢") {
				save = "DIAMOND";
			}

			addToPanel(bankCardPanel, save+"_"+va);
		}
		bj.bankHand.count();
		bankCardPanel.add(new JLabel("Bank Best : "+ bj.BankBest()));

		if(bj.BankBest()==21) {
			addToPanel(bankCardPanel, "blackjack");
		}

		if(bj.isPlayerWinner() == true && bj.isBankWinner() == true) {
						
						if(bj.PlayerBest() > bj.BankBest()) {
							addToPanel(playerCardPanel, "winner");
							addToPanel(bankCardPanel, "looser");
							
						}
						else if(bj.PlayerBest() == bj.BankBest()) {
							addToPanel(playerCardPanel, "draw");
							addToPanel(bankCardPanel, "draw");
							
						}
						else {
							addToPanel(bankCardPanel, "winner");
							addToPanel(playerCardPanel, "looser");
						}
			} 
			else if(bj.isBankWinner() == false && bj.isPlayerWinner() == true) {
				addToPanel(playerCardPanel, "winner");
				addToPanel(bankCardPanel, "looser");
			} 
			else if(bj.isPlayerWinner() == false && bj.isBankWinner() == true) {
				addToPanel(bankCardPanel, "winner");
				addToPanel(playerCardPanel, "looser");
			}
	}

	
	public static void main(String[] args) throws FileNotFoundException {
		new BlackJackGUI();
	}
		
}