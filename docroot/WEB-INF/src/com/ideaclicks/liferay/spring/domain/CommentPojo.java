package com.ideaclicks.liferay.spring.domain;

import java.io.Serializable;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

@Entity
@Table(name="X_COMMENTS")
public class CommentPojo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COMMENTSID",length = 10)
	@GeneratedValue
	private String commentsId;
	
	@Column(name="PARENT_COMMENT_ID",length = 10)
	private String parentCommentsId;

	@Column(name="PARENT_IDEA_ID",length = 255 )	
	private String parentIdeaId;

	@Column(name="COMMENTS_TEXT",length=2555)
	@NotBlank
	private String commentsText;

	@Column(name="SUBMITTED_BY",length = 50)
	private String submittedBy;
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<CommentPojo> getChildComment() {
		return childComment;
	}

	public void setChildComment(List<CommentPojo> childComment) {
		this.childComment = childComment;
	}

	@Transient
	private int level;
	
	@Transient
	private List<CommentPojo> childComment;

	public String getCommentsId() {
		return commentsId;
	}

	public void setCommentsId(String commentsId) {
		this.commentsId = commentsId;
	}

	public String getParentCommentsId() {
		return parentCommentsId;
	}

	public void setParentCommentsId(String parentCommentsId) {
		this.parentCommentsId = parentCommentsId;
	}

	public String getParentIdeaId() {
		return parentIdeaId;
	}

	public void setParentIdeaId(String parentIdeaId) {
		this.parentIdeaId = parentIdeaId;
	}

	public String getCommentsText() {
		return commentsText;
	}

	public void setCommentsText(String commentsText) {
		this.commentsText = commentsText;
	}

	@Override
	public String toString() {
		return "CommentPojo [commentsId=" + commentsId + ", parentCommentsId="
				+ parentCommentsId + ", parentIdeaId=" + parentIdeaId
				+ ", commentsText=" + commentsText + ", submittedBy="
				+ submittedBy + ", level=" + level + ", childComment="
				+ childComment + "]";
	}

	public String getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}

	




}
