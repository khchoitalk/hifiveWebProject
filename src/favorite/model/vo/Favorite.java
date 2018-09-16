package favorite.model.vo;

public class Favorite implements java.io.Serializable {
	private static final long serialVersionUID = 10L;
	//찜
	
	private int favorite_no;
	private String user_id;
	private String f_user_id;
	
	public Favorite() {}

	public Favorite(int favorite_no, String user_id, String f_user_id) {
		super();
		this.favorite_no = favorite_no;
		this.user_id = user_id;
		this.f_user_id = f_user_id;
	}

	public int getFavorite_no() {
		return favorite_no;
	}

	public void setFavorite_no(int favorite_no) {
		this.favorite_no = favorite_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getF_user_id() {
		return f_user_id;
	}

	public void setF_user_id(String f_user_id) {
		this.f_user_id = f_user_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString(){
		return this.favorite_no + ", " + this.user_id + ", " + this.f_user_id;
	}
	

}