package food.model;

public class Rating {
	
	private int star;
	
	private String comment;

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Rating(int star, String comment) {
		super();
		this.star = star;
		this.comment = comment;
	}

	public Rating() {
		super();
	}


}
