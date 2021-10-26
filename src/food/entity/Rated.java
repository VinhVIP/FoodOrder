package food.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "rated")
public class Rated {

	@EmbeddedId
	private RatedKey ratedId;

	@Column(name = "star")
	private int star;

	@Column(name = "comment")
	private String comment;

	@Column(name = "status")
	private int status;
	
	@Basic(optional = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="cmt_time", insertable = false)
	private Date cmtTime;
	
	@ManyToOne
	@MapsId("id_food")
	@JoinColumn(name="id_food")
	private Food food;
	
	@ManyToOne
	@MapsId("id_account")
	@JoinColumn(name="id_account")
	private Account account;

	
	// Setter and getters
	public RatedKey getRatedId() {
		return ratedId;
	}

	public void setRatedId(RatedKey ratedId) {
		this.ratedId = ratedId;
	}

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCmtTime() {
		return cmtTime;
	}

	public void setCmtTime(Date cmtTime) {
		this.cmtTime = cmtTime;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
