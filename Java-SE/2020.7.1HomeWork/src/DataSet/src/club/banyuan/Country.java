package DataSet.src.club.banyuan;

import club.banyuan.DataSet;

public class Country implements Measurable {

  private int population;

  public Country(int population) {
    this.population = population;
  }

  public int getPopulation() {
    return population;
  }

  public void setPopulation(int population) {
    this.population = population;
  }

  @Override
  public double getMeasure() {
    return population;
  }
}
