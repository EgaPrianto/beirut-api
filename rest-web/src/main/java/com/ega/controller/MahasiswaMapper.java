package com.ega.controller;

import org.dozer.Mapper;

import com.ega.dto.request.MahasiswaDTORequest;
import com.ega.dto.request.MataKuliahDTORequest;
import com.ega.dto.response.MahasiswaDetilDTOResponse;
import com.ega.dto.response.MataKuliahDTOResponse;
import com.ega.entities.Mahasiswa;
import com.ega.entities.MataKuliah;

public final class MahasiswaMapper {


  public static void map(Mapper dozerMapper, Mahasiswa source,
      MahasiswaDetilDTOResponse destination) {
    // System.out.println(dozerMapper);
    dozerMapper.map(source, destination);
    if (source.getMataKuliahs() != null) {
      for (MataKuliah iterable_element : source.getMataKuliahs()) {
        MataKuliahDTOResponse mk = new MataKuliahDTOResponse();
        dozerMapper.map(iterable_element, mk);
        destination.getMataKuliahs().add(mk);
      }
    }
  }

  public static void map(Mapper dozerMapper, MahasiswaDTORequest source, Mahasiswa destination) {
    // System.out.println(dozerMapper);
    dozerMapper.map(source, destination);
    if (source.getMataKuliahs() != null) {
      for (MataKuliahDTORequest iterable_element : source.getMataKuliahs()) {
        MataKuliah mk = new MataKuliah();
        dozerMapper.map(iterable_element, mk);
        destination.getMataKuliahs().add(mk);
      }
    }
  }

  public static void map(Mapper dozerMapper, Object source, Object destination) {
    // System.out.println(dozerMapper);
    dozerMapper.map(source, destination);
  }

}
