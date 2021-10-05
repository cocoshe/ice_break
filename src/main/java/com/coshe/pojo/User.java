package com.coshe.pojo;


public class User {

  private long id;
  private String name;
  private String pwd;
  private long show;

  public User() {
  }

  public User(long id, String name, String pwd, long show) {
    this.id = id;
    this.name = name;
    this.pwd = pwd;
    this.show = show;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", pwd='" + pwd + '\'' +
            ", show=" + show +
            '}';
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }


  public long getShow() {
    return show;
  }

  public void setShow(long show) {
    this.show = show;
  }

}
