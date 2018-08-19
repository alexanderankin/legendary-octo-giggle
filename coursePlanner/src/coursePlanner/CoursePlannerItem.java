package coursePlanner;

public class CoursePlannerItem {
	private Course course;
	private int position;

	public CoursePlannerItem() {
	}

	public CoursePlannerItem(Course course, int position) {
		this.course = course;
		this.position = position;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	public Object clone() {
		return new CoursePlannerItem(this.course, this.position);
	}
}
