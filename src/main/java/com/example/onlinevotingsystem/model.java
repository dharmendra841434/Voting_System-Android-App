package com.example.onlinevotingsystem;

public class model {

    String name,party,purl;

    public model() {


    }

    public model(String name, String party, String purl) {
        this.name = name;
        this.party = party;
        this.purl = purl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
