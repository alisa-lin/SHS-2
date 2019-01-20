import java.io.*;
import java.util.*;

public class Player {
	Scanner input = new Scanner(System.in);
	private Stats stats;        // Stores study, skill, and relationship stats
	private int path;  // Player's extracurricular path
	// Path 1: Basketball; Path 2: Orchestra; Path 3: School newspaper
	private int week;   // Current week
	private boolean olivia; // True of the player is dating Olivia, false otherwise.

	// Constructors
	public Player(int path, Stats stats, int week, boolean olivia) {
	this.stats = stats;
	this.path = path;
	this.week = week;
	this.olivia = olivia;
	}

	// Accessors
	public Stats getStats() {
		return stats;
	}

	public int getPath() {
		return path;
	}

	public String getPathName() {
		if (path == 1) {
			return "basketball";
		} else if (path == 2) {
			return "orchestra";
		} else if (path == 3) {
			return "school newspaper";
		} else {
			return "path not found";
		}
	}

	public int getWeek() {
		return week;
	}

	public boolean getOlivia() {
		return olivia;
	}

	// Mutators
	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public void setPath(int path) {
		this.path = path;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public void setOlivia(boolean olv) {
		this.olivia = olv;
	}

	// Allocation of free time after school every week.
	public void freeTime() {
		String flush;
		boolean reset = true;
		int choice1;
		int choice2;
		int studyChoice;
		int textChoice;
		boolean valid1 = false;
		boolean valid2 = false;
		boolean studyValid = false;
		boolean textValid = false;
		boolean study = false;
		boolean text = false;

		// Reset allows the player to change their decisions in the event
		// that they inputted their choices incorrectly.

		while (reset) {
			System.out.println("You have some time after school today. How would you like to spend it?");
			System.out.println("Choice 1: Study");
			System.out.println("Choice 2: Text a friend");

			while (!valid1) {
				System.out.print("Choice: ");
				try {
					choice1 = input.nextInt();
					if (choice1 == 1) {
						study = true;
						valid1 = true;
					} else if (choice1 == 2) {
						text = true;
						valid1 = true;
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

			while (!valid2) {
				if (study) {
					while (!studyValid) {
						System.out.println("Which class would you like to study for?");
						for (int i = 0; i < stats.getCourses().length; i++) {
							System.out.println(i + 1 + ": " + stats.getCourses()[i]);
						}
						
						try {
							studyChoice = input.nextInt();
							if (!(studyChoice > 0 && studyChoice <= stats.getCourses().length)) {
								System.out.println("Invalid input, please try again.");
							} else {
								stats.setStudy(studyChoice - 1, stats.getStudy()[studyChoice - 1] + 1);
								System.out.println(stats.getCourses()[studyChoice - 1] + " skill has increased!");
								System.out.println("");
								studyValid = true;
							}
						} catch (NumberFormatException nfx) {
							System.out.println("Invalid input, please try again.");
							flush = input.next();
						} catch (InputMismatchException imx) {
							System.out.println("Invalid input, please try again.");
							flush = input.next();
						}
					}
				} else if (text) {
					while (!textValid) {
						System.out.println("Who would you like to text?");

						for (int i = 0; i < stats.friendshipsNames(week, olivia).length; i++) {
							System.out.println(i + 1 + ": " + stats.friendshipsNames(week, olivia)[i]);
						}

						try {
							textChoice = input.nextInt();
							if (!(textChoice > 0 && textChoice <= stats.friendshipsNames(week, olivia).length)) {
								System.out.println("Invalid input, please try again.");
							} else {
								String friendName = stats.friendshipsNames(week, olivia)[textChoice - 1];
								int id = 0;
								for (int i = 0; i < stats.getFriendName().length; i++) {
									if (stats.getFriendName()[i] == friendName) {
										id = i;
									}
								}

								stats.setFriendRel(id, stats.getFriendRel()[id] + 1);
								System.out.println("Your relationship with " + stats.getFriendName()[id] + " has increased!");
								System.out.println("");
								textValid = true;
							}
						} catch (NumberFormatException nfx) {
							System.out.println("Invalid input, please try again.");
							flush = input.next();
						} catch (InputMismatchException imx) {
							System.out.println("Invalid input, please try again.");
							flush = input.next();
						}
					}
				}
			}
		}
	}
}