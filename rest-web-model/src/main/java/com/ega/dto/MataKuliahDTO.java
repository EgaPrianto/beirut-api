package com.ega.dto;

import com.gdn.common.web.base.BaseResponse;

public class MataKuliahDTO extends BaseResponse {
  private String id;

  private String nama;

  private String kode;

  private String namaDosen;

  private MahasiswaDTO mahasiswa;

  public String getId() {
    return id;
  }

  public String getKode() {
    return kode;
  }

  public MahasiswaDTO getMahasiswa() {
    return mahasiswa;
  }

  public String getNama() {
    return nama;
  }

  public String getNamaDosen() {
    return namaDosen;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setKode(String kode) {
    this.kode = kode;
  }

  public void setMahasiswa(MahasiswaDTO mahasiswa) {
    this.mahasiswa = mahasiswa;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public void setNamaDosen(String namaDosen) {
    this.namaDosen = namaDosen;
  }

}
