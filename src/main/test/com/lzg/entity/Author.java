package com.lzg.entity;

import java.io.Serializable;
import java.util.List;

public class Author implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 653598475955166755L;
	private long authorId;
	private String authorName;
	private List<Blog> blogs;
	
	public long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public List<Blog> getBlogs() {
		return blogs;
	}
	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}
	
}
