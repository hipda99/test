package com.zaumal.download.xmla.bean.albuminfo;

public class RecommendInfo {

	private String cover;
	private long albumId;
	private long anchorId;
	private String title;
	private String url;
	private String anchorName;

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getCover() {
		return cover;
	}

	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}

	public long getAlbumId() {
		return albumId;
	}

	public void setAnchorId(long anchorId) {
		this.anchorId = anchorId;
	}

	public long getAnchorId() {
		return anchorId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setAnchorName(String anchorName) {
		this.anchorName = anchorName;
	}

	public String getAnchorName() {
		return anchorName;
	}

}