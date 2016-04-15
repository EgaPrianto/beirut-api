package com.ega.controller;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.ega.dto.request.MahasiswaDTORequest;
import com.ega.dto.request.MataKuliahDTORequest;
import com.ega.dto.response.MahasiswaDetilDTOResponse;
import com.ega.dto.response.MataKuliahDTOResponse;
import com.ega.entities.Mahasiswa;
import com.ega.entities.MataKuliah;

public class MahasiswaMapper {
  @Autowired
  private static Mapper dozerMapper;

  public static void map(Mahasiswa source, MahasiswaDetilDTOResponse destination) {
    map(source, destination);
    if (source.getMataKuliahs() != null) {
      for (MataKuliah iterable_element : source.getMataKuliahs()) {
        MataKuliahDTOResponse mk = new MataKuliahDTOResponse();
        map(iterable_element, mk);
        destination.getMataKuliahs().add(mk);
      }
    }
  }

  public static void map(MahasiswaDTORequest source, Mahasiswa destination) {
    map(source, destination);
    if (source.getMataKuliahs() != null) {
      for (MataKuliahDTORequest iterable_element : source.getMataKuliahs()) {
        MataKuliah mk = new MataKuliah();
        map(iterable_element, mk);
        destination.getMataKuliahs().add(mk);
      }
    }
  }

  public static void map(Object source, Object destination) {
    dozerMapper.map(source, destination);
  }

}
