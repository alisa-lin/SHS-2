import java.io.*;
import java.util.*;

public class Game {
	Scanner input = new Scanner(System.in);
	// Objects
	private Player player; // Stores all information about the player
	public int courseNum = 3; // number of courses

	// Initialized objects
	private Stats initStats; // initial stats object
	private int initPath = 0;
	private String[] initCourses = new String[courseNum];
	private int[] initMarks = new int[courseNum];
	private int[] initStudy = new int[courseNum];
	private int initPathSkill = 0;
	private String[] initFriendName = {"Ryan", "Enemy", "OrigGF", "Olivia"};
	private int[] initFriendRel = {1, -3, 1, 1};
	private int initWeek = 1;
	private boolean initOlivia = false;
	private int[] initGirls = {1, 1, 1};

	// Initializes the player at the beginning of the game.
	public void initializeStats() {
		for (int i = 0; i < courseNum; i++) {
		initCourses[i] = "undetermined";
		initMarks[i] = 0;
		initStudy[i] = 0;
		}
		initStats = new Stats(initCourses, initMarks, initStudy, initPathSkill, initFriendName, initFriendRel);
		player = new Player (initPath, initStats, initWeek, initOlivia, initGirls);
	}

	// Start game!!
	public void startGame() {
		String flush;
		boolean valid = false;
		this.initializeStats();

		// Begins game
		System.out.println("Welcome to SHS 2, a text-based choose your own adventure game.");
		// Checks if there is a file to load
		if (this.checkFile()) {
			if (this.chooseSave()) {
				this.loadSave();
			} else {
				this.introduction();
			}
		} else {
			this.introduction();
		}

		int curWeek = player.getWeek();

		while (curWeek <= 12) {
			switch (curWeek) {
				case 1: this.weekOne();
						this.saveGame();
						curWeek++;
						break;
				case 2: this.weekTwo();
						// week++;
						curWeek = 13;
						break;
				default: System.out.println("Invalid week.");
						curWeek++;
						break;
			}
		}

	}

	public void introduction() {
		System.out.println("");
		System.out.println("Welcome to Belford High School!");
		System.out.println("It's the beginning of your senior year. This is a big one.");
		System.out.println("Senior year means that you will be working toward improving your grades for university admissions, enjoying the extracurriculars high school offers while you still can, making friends, and perhaps even pursuing a romantic relationship!");
		System.out.println("This semester is especially important because you're new to Belford HS, so you'll want to make good first impressions.");
		System.out.println("(Press enter to continue.)");
		input.nextLine();
		input.nextLine();
		System.out.println("");
	}

	public void weekOne() {
		System.out.println("***** WEEK ONE *****");
		System.out.println("It's the day before school begins, and you have an appointment with the guidance counselor to sort out your courses for the semester.");
		//player.getStats().courseSelection();
		//player.setPath(0);
		this.saveGame();

		boolean valid = false;
		String flush;

		try {
			BufferedReader in = new BufferedReader(new FileReader("w1.txt"));
			int numPassage = Integer.parseInt(in.readLine()); // how many passages there are
			in.readLine();
			int choice1;
			int choice2;
			int choice3;
			int choice1ID;
			int choice1Rel;
			int choice1Skill;
			int choice1Path;
			int choice2ID;
			int choice2Rel;
			int choice2Skill = 0;
			int choice2Path;
			int choice3Path;
			String thisLine; // holds each line of text

			for (int j = 0; j < numPassage; j++) {
				int type = Integer.parseInt(in.readLine()); // how this passage of text ends
				//System.out.printf("Type: %d\n", type);
				
				if (type == 0) { // story section that ends with a choice
					int effect = Integer.parseInt(in.readLine()); // type of effect of choice
					int lines = Integer.parseInt(in.readLine()); // number of lines in story before choice
					
					switch (effect) {
						case 0: // no effect
							choice1 = Integer.parseInt(in.readLine()); // lines in choice 1 text
							choice2 = Integer.parseInt(in.readLine()); // lines in choice 2 text

							// prints story text before choice
							for (int i = 0; i < lines + 2; i++) {
								if (Integer.parseInt(in.readLine()) == 0) {
									System.out.println("");
								} else {
									System.out.println(in.readLine());
									input.nextLine();
								}
							}

							// allows the user to make their choice and
							// prints the text corresponding to their selection.
							while (!valid) {
								System.out.print("Choice: ");
								try {
									int choice = input.nextInt();

									if (choice == 1) {
										valid = true;

										for (int i = 0; i < choice1; i++) {
											System.out.println(in.readLine());
											input.nextLine();
											input.nextLine();
										}

										for (int i = 0; i < choice2; i++) {
											in.readLine();
										}
									} else if (choice == 2) {
										valid = true;

										for (int i = 0; i < choice1; i++) {
											in.readLine();
										}

										for (int i = 0; i < choice2; i++) {
											System.out.println(in.readLine());
											input.nextLine();
											input.nextLine();
										}
									} else {
										System.out.println("Invalid input, please try again.");
									}
								} catch (NumberFormatException nfx) {
									System.out.println("Invalid input, please try again.");
									flush = input.next();
								} catch (InputMismatchException imx) {
									System.out.println("Invalid input, please try again.");
									flush = input.next();
								}
							}

							break;

						case 1: // setFriendRel
							choice1 = Integer.parseInt(in.readLine()); // lines in choice 1 text
							choice1ID = Character.getNumericValue(in.read());
							in.read();
							choice1Rel = Character.getNumericValue(in.read());
							in.readLine();

							choice2 = Integer.parseInt(in.readLine()); // lines in choice 2 text
							choice2ID = Character.getNumericValue(in.read());
							in.read();
							choice2Rel = Character.getNumericValue(in.read());
							in.readLine();

							// prints story text before choice
							for (int i = 0; i < lines + 2; i++) {
								thisLine = in.readLine();
								//System.out.println("I've read the line! Now I will determine of the line is blank.");
								if (thisLine == "") {
									//System.out.println("This line is blank!");
									System.out.println("");
								} else {
									//System.out.println("This line is not blank!");
									System.out.print(thisLine);
									input.nextLine();
								}
							}

							// allows the user to make their choice and
							// prints the text corresponding to their selection.
							while (!valid) {
								System.out.print("Choice: ");
								try {
									int choice = input.nextInt();

									if (choice == 1) {
										valid = true;
										player.getStats().setFriendRel(choice1ID, player.getStats().getFriendRel()[choice1ID] + choice1Rel);

										for (int i = 0; i < choice1; i++) {
											System.out.print(in.readLine());
											input.nextLine();
										}

										for (int i = 0; i < choice2; i++) {
											in.readLine();
										}
									} else if (choice == 2) {
										valid = true;
										player.getStats().setFriendRel(choice2ID, player.getStats().getFriendRel()[choice2ID] + choice2Rel);

										for (int i = 0; i < choice1; i++) {
											in.readLine();
										}

										for (int i = 0; i < choice2; i++) {
											System.out.print(in.readLine());
											input.nextLine();
										}
									} else {
										System.out.println("Invalid input, please try again.");
									}
								} catch (NumberFormatException nfx) {
									System.out.println("Invalid input, please try again.");
									flush = input.next();
								} catch (InputMismatchException imx) {
									System.out.println("Invalid input, please try again.");
									flush = input.next();
								}
							}

							break;

						case 2: // setPathSkill
							choice1 = Integer.parseInt(in.readLine()); // lines in choice 1 text
							choice1Skill = Character.getNumericValue(in.read());
							in.readLine();

							choice2 = Integer.parseInt(in.readLine()); // lines in choice 2 text
							choice2Skill = Character.getNumericValue(in.read());
							in.readLine();

							// prints story text before choice
							for (int i = 0; i < lines + 2; i++) {
								thisLine = in.readLine();
								if (thisLine == "") {
									System.out.println("");
								} else {
									System.out.print(thisLine);
									input.nextLine();
								}
							}

							// allows the user to make their choice and
							// prints the text corresponding to their selection.
							while (!valid) {
								System.out.print("Choice: ");
								try {
									int choice = input.nextInt();

									if (choice == 1) {
										valid = true;
										player.getStats().setPathSkill(player.getStats().getPathSkill() + choice1Skill);

										for (int i = 0; i < choice1; i++) {
											System.out.print(in.readLine());
											input.nextLine();
										}

										for (int i = 0; i < choice2; i++) {
											in.readLine();
										}
									} else if (choice == 2) {
										valid = true;
										player.getStats().setPathSkill(player.getStats().getPathSkill() + choice2Skill);

										for (int i = 0; i < choice1; i++) {
											in.readLine();
										}

										for (int i = 0; i < choice2; i++) {
											System.out.print(in.readLine());
											input.nextLine();
										}
									} else {
										System.out.println("Invalid input, please try again.");
									}
								} catch (NumberFormatException nfx) {
									System.out.println("Invalid input, please try again.");
									flush = input.next();
								} catch (InputMismatchException imx) {
									System.out.println("Invalid input, please try again.");
									flush = input.next();
								}
							}

							break;

						case 3: // setPath
							choice1 = Integer.parseInt(in.readLine()); // lines in choice 1 text
							choice1Path = Character.getNumericValue(in.read());
							in.readLine();

							choice2 = Integer.parseInt(in.readLine()); // lines in choice 2 text
							choice2Path = Character.getNumericValue(in.read());
							in.readLine();

							choice3 = Integer.parseInt(in.readLine()); // lines in choice 3 text
							choice3Path = Character.getNumericValue(in.read());
							in.readLine();

							// prints story text before choice
							for (int i = 0; i < lines + 3; i++) {
								thisLine = in.readLine();
								if (thisLine == "") {
									System.out.println("");
								} else {
									System.out.print(thisLine);
									input.nextLine();
								}
							}

							// allows the user to make their choice and
							// prints the text corresponding to their selection.
							while (!valid) {
								System.out.print("Choice: ");
								try {
									int choice = input.nextInt();

									if (choice == 1) {
										valid = true;
										player.setPath(choice1Path);

										for (int i = 0; i < choice1; i++) {
											System.out.print(in.readLine());
											input.nextLine();
										}

										for (int i = 0; i < choice2 + choice3; i++) {
											in.readLine();
										}

									} else if (choice == 2) {
										valid = true;
										player.setPath(choice2Path);

										for (int i = 0; i < choice1; i++) {
											in.readLine();
										}

										for (int i = 0; i < choice2; i++) {
											player.getStats().setPathSkill(player.getStats().getPathSkill() + choice2Skill);
											System.out.print(in.readLine());
											input.nextLine();
										}

										for (int i = 0; i < choice3; i++) {
											in.readLine();
										}

									} else if (choice == 3) {
										valid = true;
										player.setPath(choice3Path);

										for (int i = 0; i < choice1 + choice2; i++) {
											in.readLine();
										}

										for (int i = 0; i < choice3; i++) {
											System.out.print(in.readLine());
											input.nextLine();
										}

									} else {
										System.out.println("Invalid input, please try again.");
									}
								} catch (NumberFormatException nfx) {
									System.out.println("Invalid input, please try again.");
									flush = input.next();
								} catch (InputMismatchException imx) {
									System.out.println("Invalid input, please try again.");
									flush = input.next();
								}
							}

						break;

						case 4: // setGirlRel
							choice1 = Integer.parseInt(in.readLine()); // lines in choice 1 text
							choice1ID = Character.getNumericValue(in.read());
							in.read();
							choice1Rel = Character.getNumericValue(in.read());
							in.readLine();

							choice2 = Integer.parseInt(in.readLine()); // lines in choice 2 text
							choice2ID = Character.getNumericValue(in.read());
							in.read();
							choice2Rel = Character.getNumericValue(in.read());
							in.readLine();

							// prints story text before choice
							for (int i = 0; i < lines + 2; i++) {
								thisLine = in.readLine();
								if (thisLine == "") {
									System.out.println("");
								} else {
									System.out.print(thisLine);
									input.nextLine();
								}
							}

							// allows the user to make their choice and
							// prints the text corresponding to their selection.
							while (!valid) {
								System.out.print("Choice: ");
								try {
									int choice = input.nextInt();

									if (choice == 1) {
										valid = true;
										player.setGirlRel(choice1ID, player.getGirlRel()[choice1ID] + choice1Rel);

										for (int i = 0; i < choice1; i++) {
											System.out.print(in.readLine());
											input.nextLine();
											input.nextLine();
										}

										for (int i = 0; i < choice2; i++) {
											in.readLine();
										}
									} else if (choice == 2) {
										valid = true;
										player.setGirlRel(choice2ID, player.getGirlRel()[choice2ID] + choice2Rel);

										for (int i = 0; i < choice1; i++) {
											in.readLine();
										}

										for (int i = 0; i < choice2; i++) {
											//System.out.printf("index is: %d\n", i);
											System.out.print(in.readLine());
											input.nextLine();
										}
									} else {
										System.out.println("Invalid input, please try again.");
									}
								} catch (NumberFormatException nfx) {
									System.out.println("Invalid input, please try again.");
									flush = input.next();
								} catch (InputMismatchException imx) {
									System.out.println("Invalid input, please try again.");
									flush = input.next();
								}
							}

							break;

						default: 
							System.out.println("Error reading Week One.");
							break;
					}

					in.readLine();
					valid = false;

				} else if (type == 1) { // passage ends with a name insertion
					int nameType = Integer.parseInt(in.readLine());
					//System.out.printf("nameType: %d\n", nameType);
					int lines = Integer.parseInt(in.readLine());
					//System.out.printf("lines: %d\n", lines);
					int index = Integer.parseInt(in.readLine());
					//System.out.printf("name index: %d\n", index);

					for (int i = 0; i < lines; i ++) {
						thisLine = in.readLine();
						if (thisLine == "") {
							System.out.println("");
						} else {
							System.out.print(thisLine);
							input.nextLine();
						}
					}

					System.out.print(in.readLine());

					if (nameType == 1) {
						System.out.print(player.getStats().getCourses()[index]);
					} else if (nameType == 2) {
						System.out.print(player.getStats().getFriendName()[index]);
					} else {
						System.out.println("");
						System.out.println("Error reading Week One.");
					}

					in.readLine();

				} else if (type == 2) { // passage ends
					int lines = Integer.parseInt(in.readLine());

					for (int i = 0; i < lines; i ++) {
						thisLine = in.readLine();
						if (thisLine == "") {
							System.out.println("");
						} else {
							System.out.print(thisLine);
							input.nextLine();
						}
					}

					System.out.println("");
				} else {
					System.out.println("Error reading Week One.");
				}
			}
		} catch (IOException iox) {
			System.out.println("Error printing file.");
		}

		// first free time method; no texting option.

		this.saveGame();
		System.out.println("The game has been saved.");
	}

	public void weekTwo() {
		System.out.println("***** WEEK TWO *****");
	}

	// Checks if a save file exists
	public boolean checkFile() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("save.txt"));
			if (Integer.parseInt(in.readLine()) == 0) {
				return false;
			} else {
				return true;
			}
		} catch (IOException iox) {
			return false;
		}
	}

	public boolean chooseSave() {
		int choice;
		String flush;
		boolean valid = false;
		System.out.println("Do you want to start a new game or load your last game?");
		System.out.println("1: New game");
		System.out.println("2: Previous game");
		System.out.print("Choice: ");

		while (!valid) {
			try {
				choice = input.nextInt();
				if (choice == 1) {
					// restarts game
					return false;
				} else if (choice == 2) {
					// continue previous game
					return true;
				} else {
					System.out.println("Invalid input, please try again.");
				}
			} catch (InputMismatchException imx) {
				System.out.println("Invalid input, please try again.");
				flush = input.nextLine();
			}
		}

		System.out.println("");
		return true;
	}

	// Save
	public void saveGame() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("save.txt", false));
			out.write("" + player.getWeek());
			out.newLine();
			out.write("" + player.getPath());
			out.newLine();
			for (int i = 0; i < courseNum; i++) {
				out.write("" + player.getStats().getCourses()[i]);
				out.newLine();
			}
			out.newLine();
			for (int i = 0; i < courseNum; i++) {
				out.write("" + player.getStats().getMarks()[i] + " ");
			}
			out.newLine();
			for (int i = 0; i < courseNum; i++) {
				out.write("" + player.getStats().getStudy()[i] + " ");
			}
			out.newLine();
			out.write("" + player.getStats().getPathSkill());
			out.newLine();
			for (int i = 0; i < 4; i++) {
				out.write("" + player.getStats().getFriendName()[i]);
				out.newLine();
			}
			out.newLine();
			for (int i = 0; i < 4; i++) {
				out.write("" + player.getStats().getFriendRel()[i] + " ");
			}
			out.newLine();
			out.write("" + player.getOlivia());
			out.close();
		} catch (IOException iox) {
			System.out.println("Error printing file.");
		}
	}

	// Loads previous saved game
	public void loadSave() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("save.txt"));
			player.setWeek(Integer.parseInt(in.readLine())); // + 1
			player.setPath(Integer.parseInt(in.readLine()));
			for (int i = 0; i < courseNum; i++) {
				player.getStats().setCourses(i, in.readLine());
			}
			in.readLine();
			for (int i = 0; i < courseNum; i++) {
				player.getStats().setMark(i, Character.getNumericValue(in.read()));
				in.read();
			}
			in.readLine();
			for (int i = 0; i < courseNum; i++) {
				player.getStats().setStudy(i, Character.getNumericValue(in.read()));
				in.read();
			}
			in.readLine();
			player.getStats().setPathSkill(Integer.parseInt(in.readLine()));
			for (int i = 0; i < 4; i++) {
				player.getStats().setFriendName(i, in.readLine());
			}
			in.readLine();
			for (int i = 0; i < 4; i++) {
				player.getStats().setFriendRel(i, Character.getNumericValue(in.read()));
				in.read();
			}
			in.readLine();
			player.setOlivia(Boolean.parseBoolean(in.readLine()));
			in.close();
		} catch (IOException iox) {
			System.out.println("Error reading file.");
		}
	}


}