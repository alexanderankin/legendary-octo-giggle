package coursePlanner;

/**
 * 
 * @author David
 *
 */
public class Course implements Cloneable {
	private String name;
	private String department;
	private int code;
	private byte section;
	private String instructor;

	public Course () {
		
	}
	
	public Course(String name, 
			String department, 
			int code, 
			byte section, 
			String instructor) {
		this.name = name;
		this.department = department;
		this.code = code;
		this.section = section;
		this.instructor = instructor;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) throws CourseInformationException {
		if (code < 0) {
			throw new CourseInformationException("Course code is negative");
		}
		this.code = code;
	}

	public byte getSection() {
		return section;
	}

	public void setSection(byte section) throws CourseInformationException {
		if (section < 0) {
			throw new CourseInformationException("Course Section is negative");
		}
		this.section = section;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	@Override
	public Object clone() {
		return new Course(this.name, this.department, this.code,this.section, this.instructor);
	}
	
	public boolean equals(Course course) {
		return (this.code == course.getCode() && 
				this.department.equals(course.getDepartment()) &&
				this.instructor.equals(course.getInstructor()) &&
				this.name.equals(course.getName()) &&
				this.section == course.getSection());
	}
	
	/**
	 * This string representation is missing the position value because that
	 * information is stored in a {@link CoursePlannerItem} object. Consequently,
	 * this string is 4 characters shorter (on the left side).
	 */
	@Override
	public String toString() {
		return String.format("%-25s %-10s %4d %7d %-26s\n", this.name, 
				this.department, 
				this.code,
				this.section,
				this.instructor);
	}
	
}
