package com.example.myflowers;

public class Kukka {
    private String kukkaNimi;
    private int kukkaKuva;
    private int kukkaKuvaus;

    public Kukka(String kukkaNimi, int kukkaKuva, int kukkaKuvaus) {
        this.kukkaNimi = kukkaNimi;
        this.kukkaKuva = kukkaKuva;
        this.kukkaKuvaus = kukkaKuvaus;
    }

    public String getKukkaNimi() {
        return kukkaNimi;
    }

    public void setKukkaNimi(String kukkaNimi) {
        this.kukkaNimi = kukkaNimi;
    }

    public int getKukkaKuva() {
        return kukkaKuva;
    }

    public void setKukkaKuva(int kukkaKuva) {
        this.kukkaKuva = kukkaKuva;
    }

    public int getKukkaKuvaus() {
        return kukkaKuvaus;
    }

    public void setKukkaKuvaus(int kukkaKuvaus) {
        this.kukkaKuvaus = kukkaKuvaus;
    }
}

