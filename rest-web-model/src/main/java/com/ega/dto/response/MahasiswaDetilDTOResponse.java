package com.ega.dto.response;

import java.util.Set;

public class MahasiswaDetilDTOResponse extends MahasiswaDTOResponse {
  private Set<MataKuliahDTOResponse> mataKuliahs;

  public Set<MataKuliahDTOResponse> getMataKuliahs() {
    return mataKuliahs;
  }

  public void setMataKuliahs(Set<MataKuliahDTOResponse> mataKuliahs) {
    this.mataKuliahs = mataKuliahs;
  }

}
