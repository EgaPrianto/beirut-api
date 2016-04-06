package com.ega.services;

import java.util.List;

import com.ega.entities.Mahasiswa;
import com.ega.entities.MataKuliah;

public interface SimpleCRUD {

  Mahasiswa deleteMahasiswaById(int id);

  MataKuliah deleteMataKuliahById(int id);

  Mahasiswa findMahasiswaById(int id);

  Mahasiswa findMahasiswaByNama(String nama);

  Mahasiswa findMahasiswaDetail(int id);

  MataKuliah findMataKuliahById(int id);

  MataKuliah findMataKuliahByNama(String nama);

  List<Mahasiswa> getAllMahasiswa();

  List<MataKuliah> getAllMataKuliah();

  void saveMahasiswa(Mahasiswa mahasiswa);

  void saveMataKuliah(MataKuliah mataKuliah);
}
