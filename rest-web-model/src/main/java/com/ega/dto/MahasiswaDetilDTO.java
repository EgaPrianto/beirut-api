package com.ega.dto;

import java.util.Set;

public class MahasiswaDetilDTO extends MahasiswaDTO {
  private Set<MataKuliahDTO> setMataKuliah;

  public Set<MataKuliahDTO> getListMataKuliah() {
    return setMataKuliah;
  }

  public void setSetMataKuliah(Set<MataKuliahDTO> set) {
    this.setMataKuliah = set;
  }



}
