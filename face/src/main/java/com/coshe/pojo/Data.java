package com.coshe.pojo;


public class Data {

    private long id;
    private String number;
    private String position;
    private String name;
    private String pic;

    public Data() {
    }

    public Data(long id, String number, String position, String name, String pic) {
        this.id = id;
        this.number = number;
        this.position = position;
        this.name = name;
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", position='" + position + '\'' +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }


  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

}
