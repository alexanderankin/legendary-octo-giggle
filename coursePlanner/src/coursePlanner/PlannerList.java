package coursePlanner;

public interface PlannerList {
	public final int MAX_COURSES = 50;
	public int size();
	public void addCourse(Course newCourse, int position);
	
}
