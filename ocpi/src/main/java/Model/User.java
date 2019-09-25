package Model;

public class User {
	private String id;
	private String version;
	private String url;
	private String cpoId;

	public String getCpoId() {
		return cpoId;
	}

	public void setCpoId(String cpoId) {
		this.cpoId = cpoId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
