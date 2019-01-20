import java.io.*;
import java.util.*;

public class Stats {
	// Object stores all the individual mark, skill, and relationship
	// stats of the player.

	// Number of courses the player takes.
	final static int courseNum = 3;
	// The names of the player's courses.
	private String[] courses = new String[courseNum];
	// The player's marks in their three subjects.
	private int[] marks = new int[courseNum];
	// The player's abilities in their three subjects.
	private int[] study = new int[courseNum];

	// The player's abilities in their extracurricular field.
	private int pathSkill;

	// Number of non-playable main characters
	private int friends = 4;

	// The names of the non-playable main characters
	private String[] friendName = new String[friends];
	/* Friend 0 is Ryan. Friend 1 is Enemy. 
	   Friend 2 is OrigGF. Friend 3 is Olivia. */
	// The player's relationship with the non-playable main characters.
	private int[] friendRel = new int[friends];

	// Constructors
	public Stats (String[] courses, int[] marks, int[] study, int pathSkill, String[] friendName, int[]friendRel) {
		this.courses = courses;
		this.marks = marks;
		this.study = study;
		this.pathSkill = pathSkill;
		this.friendName = friendName;
		this.friendRel = friendRel;
	}

	// Accessors
	public String[] getCourses() {
		return courses;
	}

	public int[] getMarks() {
		return marks;
	}

	public int[] getStudy() {
		return study;
	}

	public int getPathSkill() {
		return pathSkill;
	}

	public String[] getFriendName() {
		return friendName;
	}

	public int[] getFriendRel() {
		return friendRel;
	}

	// Mutators

	// Most of the setBlank methods are for loadSave() in the Game class.
	// The editBlank methods are for gameplay.

	public void setCourses(int courseID, String course) {
		courses[courseID] = course;
	}

	public void setMark(int courseID, int courseMark) {
		marks[courseID] = courseMark;
	}

	public void setStudy(int courseID, int courseStudy) {
		study[courseID] = courseStudy;
	}

	public void setPathSkill(int ps) {
		pathSkill = ps;
	}

	public void editMark(int courseID, int markChange) {
		marks[courseID] += markChange;
	}

	public void editStudy(int courseID, int studyChange) {
		study[courseID] += studyChange;
	}

	public void editPathSkill(int ps) {
		pathSkill += ps;
	}

	public void setFriendRel(int friendID, int rel) {
		friendRel[friendID] = rel;
	}

	public void editFriendRel(int friendID, int relChange) {
		friendRel[friendID] += relChange;
	}

	public void setFriendName(int friendID, String name) {
		friendName[friendID] = name;
	}

	// sets original GF
	public void setOrigGF(String ogf) {
		friendName[2] = ogf;
	}

	// sets Enemy
	public void setEnemy(String enemy) {
		friendName[1] = enemy;
	}

	// Returns marks
	public String displayMarks() {
		String strMarks = "Your marks:\n";
		for (int i = 0; i < courseNum; i++) {
			strMarks += courses[i] + ": " + marks[i] + "\n";
		}
		return strMarks;
	}

	// Returns an array of names of friends with positive relationships.
	public String[] friendshipsNames(int week, boolean Olivia) {
		if (week < 5)
			Olivia = false;
		if (week == 5)
			Olivia = true;

		int friendNum = 0;
		if (!Olivia) {
			for (int i = 0; i < friends; i++) {
				if (i != 3 && friendRel[i] > 0) {
					friendNum++;
				}
			}
		} else {
			for (int i = 0; i < friends; i++) {
				if (i != 2 && friendRel[i] > 0) {
					friendNum++;
				} 
			}
		}

		String[] friendships = new String[friendNum];
		int foundPositive = 0;
		if (!Olivia) {
			for (int i = 0; i < friends; i++) {
				if (i != 3 && friendRel[i] > 0) {
					friendships[foundPositive] = friendName[i];
					foundPositive++;
				}
			}
		} else {
			for (int i = 0; i < friends; i++) {
				if (i != 2 && friendRel[i] > 0) {
					friendships[foundPositive] = friendName[i];
					foundPositive++;
				} 
			}
		}

		return friendships;
	}

	// Returns an array of relationship stats of friends with positive
	// relationships.
	public int[] friendshipsRel(int week, boolean Olivia) {
		if (week < 5)
			Olivia = false;
		if (week == 5)
			Olivia = true;

		int friendNum = 0;
		if (!Olivia) {
			for (int i = 0; i < friends; i++) {
				if (i != 3 && friendRel[i] > 0) {
					friendNum++;
				}
			}
		} else {
			for (int i = 0; i < friends; i++) {
				if (i != 2 && friendRel[i] > 0) {
					friendNum++;
				} 
			}
		}

		int[] friendships = new int[friendNum];
		int foundPositive = 0;
		if (!Olivia) {
			for (int i = 0; i < friends; i++) {
				if (i != 3 && friendRel[i] > 0) {
					friendships[foundPositive] = friendRel[i];
					foundPositive++;
				}
			}
		} else {
			for (int i = 0; i < friends; i++) {
				if (i != 2 && friendRel[i] > 0) {
					friendships[foundPositive] = friendRel[i];
					foundPositive++;
				} 
			}
		}

		return friendships;
	}

	/* The following code allows the player to select their courses
	   in Week 1. */
	public void courseSelection() {
		String flush;
		int choice0 = 0;
		int choice1 = 0;
		int choice2;
		boolean valid0 = false;
		boolean valid1 = false;
		boolean valid2 = false;
		Scanner sc = new Scanner(System.in);

		// Asks player to select a post-secondary goal and selects a
		// required course based on that choice.
		System.out.println("\"Last year, you told me that you want to go to university,\" she says. \"Do you know what sort of program you're interested in?\"");
		System.out.println("Choice 1: \"I'd like to study General Arts at Belford University.\"");
		System.out.println("Choice 2: \"I'd like to study General Science at Belford University.\"");

		while (!valid0) {
			System.out.print("Choice: ");
			try {
				choice0 = sc.nextInt();
				System.out.println("");
				if (choice0 == 1) {
					courses[0] = "English";
					System.out.println("\n\"That's great!\" says your guidance counselor. \"Now let's see... Arts at Belford requires English, so you'll be taking English this semester.\"");
					System.out.println("(English has been added to your schedule.)");
					valid0 = true;
				} else if (choice0 == 2) {
					courses[0] = "Math";
					System.out.println("\n\"That's great!\" says your guidance counselor. \"Now let's see... Science at Belford requires Math, so you'll be taking Math this semester.\"");
					System.out.println("(Math has been added to your schedule.)");
					valid0 = true;
				} else {
					System.out.println("Invalid input, please try again.");
				}
			} catch (NumberFormatException nfx) {
				System.out.println("Invalid input, please try again.");
				flush = sc.next();
			} catch (InputMismatchException imx) {
				System.out.println("Invalid input, please try again.");
				flush = sc.next();
			}
		}

		System.out.println("(Press enter to continue.)");
		sc.nextLine();
		sc.nextLine();

		// Creates a course list array for the player to select their 
		// elective courses.
		try {
			BufferedReader in = new BufferedReader (new FileReader("courseList.txt"));
			int courseOptionNum = Integer.parseInt(in.readLine());
			String[] courseOptions = new String[courseOptionNum];

			for (int i = 0; i < courseOptionNum; i++) {
				courseOptions[i] = in.readLine();
			}

			// Asks player to select two elective courses.
			System.out.println("\n\"Now let's choose your electives.\" She pulls out a sheet of paper and hands it to you. \"These are your options. You have two elective slots, so choose wisely.\"");
			System.out.println("Your choices are: ");
			for (int i = 0; i < courseOptionNum; i++) {
				System.out.println(i + 1 + ": " + courseOptions[i]);
			}

			in.close();

			while (!valid1) {
				System.out.print("\nSelect your first elective: ");
				try {
					choice1 = sc.nextInt();
					if (!(choice1 > 0 && choice1 <= courseOptionNum)) {
						System.out.println("Invalid input, please try again.");
					} else {
						courses[1] = courseOptions[choice1 - 1];
						System.out.println("(" + courses[1] + " has been added to your schedule.)");
						valid1 = true;
					}
				} catch (NumberFormatException nfx) {
					System.out.println("Invalid input, please try again.");
					flush = sc.next();
				} catch (InputMismatchException imx) {
					System.out.println("Invalid input, please try again.");
					flush = sc.next();
				}
			}

			while (!valid2) {
				System.out.print("\nSelect your second elective: ");
				try {
					choice2 = sc.nextInt();
					if (choice2 == choice1) {
						System.out.println("You cannot select the same elective twice. Please try again.");
					} else if (!(choice1 > 0 && choice1 <= courseOptionNum)) {
						System.out.println("Invalid input, please try again.");
					} else {
						courses[2] = courseOptions[choice2 - 1];
						System.out.println("(" + courses[2] + " has been added to your schedule.)");
						valid2 = true;
					}
				} catch (NumberFormatException nfx) {
					System.out.println("Invalid input, please try again.");
					flush = sc.next();
				} catch (InputMismatchException imx) {
					System.out.println("Invalid input, please try again.");
					flush = sc.next();
				}
			}

			String major = "Undeclared";
			if (choice0 == 1) {
				major = "Arts";
			} else if (choice0 == 2) {
				major = "Science";
			}

			System.out.println("\n\"That's all for today!\" Your guidance counselor smiles at you. \"Before you leave, just remember that the cutoff for " + major + " at Belford is an 88%, so work hard!\"");
			System.out.println("And just like that, you're off to your first day of senior year.");
			System.out.println("");
		} catch (IOException iox) {
			System.out.println("Error retrieving courselist.");
		}
	}
}