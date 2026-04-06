package com.ntkhai.demolistviewnangcao;

public class City {
    private String name;
    private int image;
    private String linkWiki;

    public City(){
    }
    public City(String name, int image, String linkWiki) {
        this.name = name;
        this.image = image;
        this.linkWiki = linkWiki;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public String getLinkWiki() {
        return linkWiki;
    }
    public void setLinkWiki(String linkWiki) {
        this.linkWiki = linkWiki;
    }
}
