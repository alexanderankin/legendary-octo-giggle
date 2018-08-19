package coursePlanner;

public class Tests {

	public static void main(String[] args) {
		Planner a = new Planner();
		for (int i = 0; i <1000; i++) {
			a.addCourse(new Course());
		}
		a.addCourse(new Course());
		System.out.println(a.size());

	}

}
