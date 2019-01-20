import java.io.*;
import java.util.*;

public class Game {
	Scanner input = new Scanner(System.in);
	// Objects
	private Player player; // Stores all information about the player
	public int courseNum = 3; // number of courses

	// Initialized objects
	private Stats initStats; // initial stats object
	private int initPath = 0; // initial path (0 is undetermined)
	private int initPathSkill = 0; // inital path skill

	// The courses, marks, and study arrays work in conjuction to each other
	private String[] initCourses = new String[courseNum];
	private int[] initMarks = new int[courseNum];
	private int[] initStudy = new int[courseNum];

	// The friendname and friendrel arrays work in conjuction to each other
	private String[] initFriendName = {"Ryan", "Enemy", "OrigGF", "Olivia"};
	private int[] initFriendRel = {1, -3, 1, 1};
	private int initWeek = 1; // initial week
	private boolean initOlivia = false; // Olivia is a character that gets introduced later in the game

	// This array is only relevant for Week One. 
	// 0: Anne; 1: Camila; 2: Sara
	private int[] girls = {1, 1, 1};

	// This method is only used in the first week.
	public void editGirls (int id, int rel) {
		this.girls[id] += rel;
	}

	// Initializes the player at the beginning of the game.
	public void initializeStats() {
		for (int i = 0; i < courseNum; i++) {
		initCourses[i] = "undetermined";
		initMarks[i] = 0;
		initStudy[i] = 0;
		}
		initStats = new Stats(initCourses, initMarks, initStudy, initPathSkill, initFriendName, initFriendRel);
		player = new Player (initPath, initStats, initWeek, initOlivia);
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


	public void readStory(BufferedReader in) {
		boolean valid = false;
		String flush;

		try {
			int numPassage = Integer.parseInt(in.readLine()); // how many passages there are
			in.readLine();
			int choice1;
			int choice2;
			int choice3;
			int choice1ID;
			int choice1Rel;
			int choice1Skill;
			int choice1Path;
			int choice1Girl;
			int choice2ID;
			int choice2Rel;
			int choice2Skill = 0;
			int choice2Path;
			int choice2Girl;
			int choice3Path;
			int choice3Girl;
			String thisLine; // holds each line of text

			for (int j = 0; j < numPassage; j++) {
				int type = Integer.parseInt(in.readLine()); // how this passage of text ends
				
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

						case 1: // editFriendRel
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
										player.getStats().editFriendRel(choice1ID, choice1Rel);

										for (int i = 0; i < choice1; i++) {
											System.out.print(in.readLine());
											input.nextLine();
										}

										for (int i = 0; i < choice2; i++) {
											in.readLine();
										}
									} else if (choice == 2) {
										valid = true;
										player.getStats().editFriendRel(choice2ID, choice2Rel);

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

						case 2: // editPathSkill
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
										player.getStats().editPathSkill(choice1Skill);

										for (int i = 0; i < choice1; i++) {
											System.out.print(in.readLine());
											input.nextLine();
										}

										for (int i = 0; i < choice2; i++) {
											in.readLine();
										}
									} else if (choice == 2) {
										valid = true;
										player.getStats().editPathSkill(choice2Skill);

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
										player.getStats().setEnemy("Nicholas");

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
										player.getStats().setEnemy("James");

										for (int i = 0; i < choice1; i++) {
											in.readLine();
										}

										for (int i = 0; i < choice2; i++) {
											System.out.print(in.readLine());
											input.nextLine();
										}

										for (int i = 0; i < choice3; i++) {
											in.readLine();
										}

									} else if (choice == 3) {
										valid = true;
										player.setPath(choice3Path);
										player.getStats().setEnemy("Fernando");

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

						case 4: // editGirls
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
										editGirls(choice1ID, choice1Rel);

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
										editGirls(choice2ID, choice2Rel);

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

						case 5: // setOrigGF
							choice1 = Integer.parseInt(in.readLine()); // lines in choice 1 text
							choice1Girl = Integer.parseInt(in.readLine());

							choice2 = Integer.parseInt(in.readLine()); // lines in choice 2 text
							choice2Girl = Integer.parseInt(in.readLine());

							choice3 = Integer.parseInt(in.readLine()); // lines in choice 1 text
							choice3Girl = Integer.parseInt(in.readLine());

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
										player.getStats().setOrigGF("Anne");
										player.getStats().setFriendRel(2, girls[choice]);

										for (int i = 0; i < choice1; i++) {
											System.out.print(in.readLine());
											input.nextLine();
										}

										for (int i = 0; i < choice2 + choice3; i++) {
											in.readLine();
										}

									} else if (choice == 2) {
										valid = true;
										player.getStats().setOrigGF("Camila");
										player.getStats().setFriendRel(2, girls[choice]);

										for (int i = 0; i < choice1; i++) {
											in.readLine();
										}

										for (int i = 0; i < choice2; i++) {
											System.out.print(in.readLine());
											input.nextLine();
										}

										for (int i = 0; i < choice3; i++) {
											in.readLine();
										}

									} else if (choice == 3) {
										valid = true;
										player.getStats().setOrigGF("Sara");
										player.getStats().setFriendRel(2, girls[choice]);

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

						default: 
							System.out.println("Error reading Week One.");
							break;
					}

					in.readLine();
					valid = false;

				} else if (type == 1) { // passage ends with a name insertion
					int nameType = Integer.parseInt(in.readLine());
					int lines = Integer.parseInt(in.readLine());
					int index = Integer.parseInt(in.readLine());

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
	}

	public void weekOne() {
		System.out.println("***** WEEK ONE *****");
		System.out.println("It's the day before school begins, and you have an appointment with the guidance counselor to sort out your courses for the semester.");
		player.getStats().courseSelection();
		player.setPath(0);
		this.saveGame();

		try {
			BufferedReader w1 = new BufferedReader(new FileReader("w1.txt"));
			readStory(w1);
		} catch (FileNotFoundException fnfx) {
			System.out.println("File not found.");
		}

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