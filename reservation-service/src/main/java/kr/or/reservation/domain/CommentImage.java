package kr.or.reservation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CommentImage {
	private long id;
	private long reservationUserCommentId;
	private long fileId;

	
	
	public CommentImage() {
		super();
	}

	public CommentImage(long reservationUserCommentId, long fileId) {
		super();
		this.reservationUserCommentId = reservationUserCommentId;
		this.fileId = fileId;
	}

	public long getReservationUserCommentId() {
		return reservationUserCommentId;
	}

	public void setReservationUserCommentId(long reservationUserCommentId) {
		this.reservationUserCommentId = reservationUserCommentId;
	}

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE).toString();
	}

	
}
