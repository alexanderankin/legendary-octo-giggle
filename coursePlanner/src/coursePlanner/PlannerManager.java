package coursePlanner;

import java.util.Scanner;

public class PlannerManager {
	public static Scanner s = new Scanner(System.in);
	public static Planner p = new Planner();
	public static Planner b;

	private static String input; // input buffer

	public static void main(String[] args) {

		mainLoop();
	}

	public static void mainLoop() {
		do {
			System.out.print(
					"(A) Add Course\n" + "(G) Get Course\n" + "(R) Remove Course\n" + "(P) Print Courses in Planner\n"
							+ "(F) Filter by Department Code\n" + "(L) Look For Course\n(S) Size\n" + "(B) Backup\n"
							+ "(PB) Print Courses in Backup\n" + "(RB) Revert to Backup\n" + "(Q) Quit\n\n");
			String nextOption = s.nextLine();
			switch (nextOption) {
			case "a":
			case "A":
				System.out.println("Enter course name: ");
				String courseName = s.nextLine();
				System.out.println("Enter department: ");
				String deptName = s.nextLine();
				System.out.println("Enter course code: ");
				String courseCode = s.nextLine();
				System.out.println("Enter course section: ");
				String courseSection = s.nextLine();
				System.out.println("Enter instructor: ");
				String instructor = s.nextLine();
				System.out.println("Enter position: ");
				String position = s.nextLine();
				if (!(deptName.matches("[A-Za-z]{3}"))) {
					System.out.println("Department name doesnt follow" + "SBU department naming style.");
					continue;
				} else { // caps it
					deptName = deptName.toUpperCase();
				}
				if (!(courseCode.matches("[\\d]{3}"))) {
					System.out.println("Course code must be " + "three digits.");
					continue;
				}
				if (!(courseSection.matches("[\\d]+"))) {
					System.out.println("Something is wrong with the " + "course section.");
					continue;
				}
				if (!(position.matches("[\\d]+"))) {
					System.out.println("Position must be an integer.");
					continue;
				}
				try {
					p.addCourse(new Course(courseName, deptName, Integer.valueOf(courseCode),
							Byte.valueOf(courseSection), instructor), Integer.valueOf(position));
				} catch (IllegalArgumentException | FullPlannerException e) {
					System.out.println(e.getMessage());
					continue;
				}
				System.out.println("Course added.");
				break; // switch break
			case "g":
			case "G":
				System.out.println("Enter priority number: ");
				input = s.nextLine();
				if (!(input.matches("[\\d]+"))) {
					System.out.println("Must enter a number.");
					continue;
				} else if (!(Integer.valueOf(input) > 0 && Integer.valueOf(input) < 51)) {
					System.out.println("Only 50 positions availible");
					continue;
				}

				try {
					Course a = p.getCourse(Integer.valueOf(input));
					System.out.println("No. Course Name               Department Code Section Instructor");
					System.out
							.println("-------------------------------------------------------------------------------");
					System.out.printf("%3d %s", Integer.valueOf(input), a.toString());
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
				break;
			case "r":
			case "R":
				System.out.println("Enter a priority number to remove: ");
				input = s.nextLine();
				if (!(input.matches("[\\d]+"))) {
					System.out.println("Must enter a number.");
					continue;
				} else if (!(Integer.valueOf(input) > 0 && Integer.valueOf(input) < 51)) {
					System.out.println("Only 50 positions availible");
					continue;
				}

				try {
					p.removeCourse(Integer.valueOf(input));
				} catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
				System.out.println("Course removed successfully.");
				break;
			case "p":
			case "P":
				System.out.println("No. Course Name               Department Code Section Instructor");
				System.out.println("-------------------------------------------------------------------------------");
				Course[] a = p.getCourses();
				for (int i = 0; i < a.length; i++) {
					System.out.printf("%3d %s", i+1, a[i].toString());
				}
				break;
			case "f":
			case "F":
				System.out.println("Not implemented such that assignment could be finished on time.");
				break;
			case "l":
			case "L":
				System.out.println("Enter course name: ");
				courseName = s.nextLine();
				System.out.println("Enter department: ");
				deptName = s.nextLine();
				System.out.println("Enter course code: ");
				courseCode = s.nextLine();
				System.out.println("Enter course section: ");
				courseSection = s.nextLine();
				System.out.println("Enter instructor: ");
				instructor = s.nextLine();
				System.out.println("Enter position: ");
				position = s.nextLine();
				if (!(deptName.matches("[A-Za-z]{3}"))) {
					System.out.println("Department name doesnt follow" + "SBU department naming style.");
					continue;
				} else { // caps it
					deptName = deptName.toUpperCase();
				}
				if (!(courseCode.matches("[\\d]{3}"))) {
					System.out.println("Course code must be " + "three digits.");
					continue;
				}
				if (!(courseSection.matches("[\\d]+"))) {
					System.out.println("Something is wrong with the " + "course section.");
					continue;
				}
				if (!(position.matches("[\\d]+"))) {
					System.out.println("Position must be an integer.");
					continue;
				}
				Course course = new Course(courseName, deptName, Integer.valueOf(courseCode),
						Byte.valueOf(courseSection), instructor);
				if (p.exists(course)) {
					int pos = 0;
					CoursePlannerItem[] coursesWPosition = p.getCourseList();
					for (int i = 0; i < coursesWPosition.length; i++) {
						if (coursesWPosition[i] != null) {
							if (coursesWPosition[i].getCourse().equals(course)) {
								pos = coursesWPosition[i].getPosition();
							} 
						}
					}
					System.out.println(deptName + " " + courseCode + "." + courseSection
							+ " is found in the planner at position " + pos);
				}

				break; // switch break
			case "s":
			case "S":
				System.out.println("There are " + p.size() + " courses in the planner.");
				break;
			case "b":
			case "B":
				b = (Planner) p.clone();
				break;
			case "pb":
			case "PB":
				System.out.println("No. Course Name               Department Code Section Instructor");
				System.out.println("-------------------------------------------------------------------------------");
				Course[] c = b.getCourses();
				for (int i = 0; i < c.length; i++) {
					System.out.printf("%3d %s", i, c[i].toString());
				}
				break;
			case "rb":
			case "RB":
				p = (Planner) b.clone();
				break;
			case "q":
			case "Q":
				quit();
			default:
				break;
			}
		} while (true);
	}

	public static void quit() {
		System.out.println("Goodbye!");
		System.exit(0);
	}
}
