package com.zaumal.download.xmla.bean.trackinfo;

import java.util.List;

public class Data {

	private long uid;
	private long albumId;
	private int sort;
	private int pageNum;
	private int pageSize;
	private List<TracksAudioPlay> tracksAudioPlay;
	private boolean hasMore;

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}

	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}

	public long getAlbumId() {
		return albumId;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getSort() {
		return sort;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setTracksAudioPlay(List<TracksAudioPlay> tracksAudioPlay) {
		this.tracksAudioPlay = tracksAudioPlay;
	}

	public List<TracksAudioPlay> getTracksAudioPlay() {
		return tracksAudioPlay;
	}

	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}

	public boolean getHasMore() {
		return hasMore;
	}

}