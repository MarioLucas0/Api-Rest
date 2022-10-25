package com.serratec.ecommerce.ecommerce.service.img;

public class Data {

	public String id;
	public String title;
	public String url_viewer;
	public String url;
	public Image image;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl_viewer() {
		return url_viewer;
	}

	public void setUrl_viewer(String url_viewer) {
		this.url_viewer = url_viewer;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Image getImage() {
		return image;
	}

	@Override
	public String toString() {
		return "Data [id=" + id + ", title=" + title + ", url_viewer=" + url_viewer + ", url=" + url;
	}

}
