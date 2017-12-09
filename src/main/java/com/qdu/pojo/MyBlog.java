package com.qdu.pojo;

import java.io.Serializable;

public class MyBlog implements Serializable{
			private int blogId;
			private String blogAuthor;
			private String role;
			private String blogTitle;
			private String belongTo;
			private String blogContent;
			private String blogTarget;
			private String blogFifter;
			private String createTime;
			private int up;
			private int down;
			private int blogComment;
			private int hotClick;
			private String verify;
			
			
			
			public String getVerify() {
				return verify;
			}
			public void setVerify(String verify) {
				this.verify = verify;
			}
			public String getBlogTitle() {
				return blogTitle;
			}
			public void setBlogTitle(String blogTitle) {
				this.blogTitle = blogTitle;
			}
			public String getRole() {
				return role;
			}
			public void setRole(String role) {
				this.role = role;
			}
			public int getBlogId() {
				return blogId;
			}
			public void setBlogId(int blogId) {
				this.blogId = blogId;
			}
			public String getBlogAuthor() {
				return blogAuthor;
			}
			public void setBlogAuthor(String blogAuthor) {
				this.blogAuthor = blogAuthor;
			}
			public String getBelongTo() {
				return belongTo;
			}
			public void setBelongTo(String belongTo) {
				this.belongTo = belongTo;
			}
			public String getBlogContent() {
				return blogContent;
			}
			public void setBlogContent(String blogContent) {
				this.blogContent = blogContent;
			}
			public String getBlogTarget() {
				return blogTarget;
			}
			public void setBlogTarget(String blogTarget) {
				this.blogTarget = blogTarget;
			}
			public String getBlogFifter() {
				return blogFifter;
			}
			public void setBlogFifter(String blogFifter) {
				this.blogFifter = blogFifter;
			}
			public String getCreateTime() {
				return createTime;
			}
			public void setCreateTime(String createTime) {
				this.createTime = createTime;
			}
			public int getUp() {
				return up;
			}
			public void setUp(int up) {
				this.up = up;
			}
			public int getDown() {
				return down;
			}
			public void setDown(int down) {
				this.down = down;
			}
			public int getBlogComment() {
				return blogComment;
			}
			public void setBlogComment(int blogComment) {
				this.blogComment = blogComment;
			}
			public int getHotClick() {
				return hotClick;
			}
			public void setHotClick(int hotClick) {
				this.hotClick = hotClick;
			}
			
			
			

}
