package com.lzg.entity;

public class Blog {
	private long blogId;
	private String blogName;
	private String content;
	private String url;
	private long authorId;
	public long getBlogId() {
		return blogId;
	}
	public void setBlogId(long blogId) {
		this.blogId = blogId;
	}
	public String getBlogName() {
		return blogName;
	}
	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}
	
	
}
