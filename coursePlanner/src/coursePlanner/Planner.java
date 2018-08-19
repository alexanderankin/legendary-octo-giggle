package coursePlanner;

public class Planner implements Cloneable{
	private final int MAX_COURSES = 50;
	private CoursePlannerItem[] courseList = new CoursePlannerItem[MAX_COURSES];
	public Planner () {}
	public Planner(CoursePlannerItem[] courseList){
		this.courseList = courseList;
	}
	
	/**
	 * 
	 * @return number of courses in the course list 
	 */
	public int size() {
		int size = 0;
		for (int i = 0; i < courseList.length; i++) {
			if (courseList[i] != null) {
				size++;
			}
		}
		return size;
	}
	
	public void addCourse(Course newCourse) {
		if (this.size() != 50) {
			try{
				courseList[findFreeIndex()] = new CoursePlannerItem(newCourse, biggestPosition());
			} catch (Exception e){
				
			}
		}
	}
	
	public void addCourse(Course newCourse, int position) throws IllegalArgumentException, FullPlannerException {
		if (size() == 50)
			throw new FullPlannerException("Planner is full.");
		if ((!(position > 0 && position < 51)) || position > (1 + biggestPosition())) 
			throw new IllegalArgumentException("Position is out of range.");
		if (exists(position)){
			shiftPositions(position);
			courseList[position - 1] = new CoursePlannerItem();
			courseList[position - 1].setCourse(newCourse);
			courseList[position - 1].setPosition(position);
		} else {
			int tmp = size();
			courseList[tmp] = new CoursePlannerItem();
			courseList[tmp].setCourse(newCourse);
			courseList[tmp].setPosition(position);	
		}	
	}
	
	private int findFreeIndex() throws Exception {
		for (int i = 0; i < courseList.length; i++) {
			if (courseList[i] == null) {
				return i;
			}
		}
		throw new Exception("no more room");
	}
	
	public CoursePlannerItem[] getCourseList() {
		return courseList;
	}

	public Course getCourse(int position) throws Exception {
		for (int i = 0; i < courseList.length; i++) {
			if (courseList[i].getPosition() == position) {
				return courseList[i].getCourse();
			}
		}
		throw new Exception("Course not found in that position");
	}
	
	public boolean exists(Course course) {
		for (int i = 0; i < courseList.length; i++) {
			if (courseList[i] == null)
				continue;
			if (courseList[i].getCourse().equals(course)) 
				return true;
		}
		return false;
	}
	
	public boolean exists(int position) {
		return (position <= biggestPosition());
	}
	
	public void removeCourse(int position) throws IllegalArgumentException {
		if (position > biggestPosition() || position < 1) {
			throw new IllegalArgumentException("Position is out of range");
		}
		for (int i = 0; i < courseList.length; i++) {
			if (courseList[i].getPosition() == position) {
				courseList[i] = null;
				fixPositionGap(position);
				return;
			}
		}
	}
	
	private void fixPositionGap(int position) {
		// address concerns about position information
		for (int i = 0; i < courseList.length; i++) {
			if (courseList[i] != null) {
				if (courseList[i].getPosition() < position) continue;
				courseList[i].setPosition(courseList[i].getPosition() - 1);
			}
		}
		// address concerns about array indices.
		for (int i = 0; i < courseList.length - 1; i++) {
			if (courseList[i] == null) {
				courseList[i] = (CoursePlannerItem) courseList[i + 1].clone();
				courseList[i + 1] = null;
			}
		}
	}
	
	/**
	 * 
	 * @param position	the value of the position to make available
	 */
	private void shiftPositions(int position) {
		// position information
		for (int i = 0; i < courseList.length; i++) {
			if (courseList[i] != null) {
				if (courseList[i].getPosition() < position) continue;
				courseList[i].setPosition(courseList[i].getPosition() + 1);
			}
		}
		// array indices.
		for (int i = size(); i >= position; i--) {
			courseList[i] = (CoursePlannerItem) courseList[i - 1].clone();
			courseList[i - 1] = null;
		}
	}
	
	/**
	 * Finds the lowest priority class in the list so far,
	 * and returns its priority value, used for moving all the others
	 * over and not skipping any 'priority levels'.
	 * @return	Integer value representing lowest priority class.
	 */
	private int biggestPosition() {
		int biggest = 0;
		for (int i = 0; i < courseList.length; i++) {
			try {
				if (biggest < courseList[i].getPosition())
					biggest = courseList[i].getPosition();
			} catch (Exception e) {
				continue;
			}
		}
		return biggest;
	}
	
	private boolean positionExists(int position) {
		for (int i = 0; i < courseList.length; i++) {
			if (courseList[i].getPosition() == position) return true;
		}
		return false;
	}
	
	public String toString() {
		StringBuilder a = new StringBuilder("");
		a.append("No. Course Name               Department Code Section Instructor");
		a.append("-------------------------------------------------------------------------------");
		return a.toString();
	}
	
	public void printAllCourses() {
		System.out.println(this.toString());
	}
	
	@Override
	public Object clone() {
		return new Planner(this.courseList);
	}

	public Course[] getCourses() {
		Course[] result = new Course[biggestPosition()];
		for (int i = 0; i < biggestPosition(); i++) {
			int j = i+1; // j is position (i is index)
			try {
				result[i] = getCourse(j);
			} catch (Exception e) {
				System.out.println("it happened");
				// shouldn't happen.
			}
		}
		return result;
	}
}
