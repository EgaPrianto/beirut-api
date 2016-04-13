package com.ega.dto.request;

import java.util.Set;

import com.gdn.common.web.base.BaseRequest;

public class MahasiswaDTORequest extends BaseRequest {
  private String nama;

  private String npm;

  private Set<MataKuliahDTORequest> mataKuliahs;

  public Set<MataKuliahDTORequest> getMataKuliahs() {
    return mataKuliahs;
  }

  public String getNama() {
    return nama;
  }

  public String getNpm() {
    return npm;
  }

  public void setMataKuliahs(Set<MataKuliahDTORequest> mataKuliahs) {
    this.mataKuliahs = mataKuliahs;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public void setNpm(String npm) {
    this.npm = npm;
  }

}
