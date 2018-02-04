public class UserEntity {

    private String position;
    private String userName;
    private String webAddres;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWebAddres() {
        return webAddres;
    }

    public void setWebAddres(String webAddres) {
        this.webAddres = webAddres;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "position='" + position + '\'' +
                ", userName='" + userName + '\'' +
                ", webAddres='" + webAddres + '\'' +
                '}';
    }
}
