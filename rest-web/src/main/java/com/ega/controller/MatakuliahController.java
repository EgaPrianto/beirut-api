package com.ega.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ega.dto.MahasiswaDTO;
import com.ega.dto.MataKuliahDTO;
import com.ega.entities.Mahasiswa;
import com.ega.entities.MataKuliah;
import com.ega.services.SimpleCRUD;
import com.gdn.common.web.wrapper.response.GdnRestSingleResponse;
import com.wordnik.swagger.annotations.ApiOperation;

public class MatakuliahController {

  @Autowired
  private SimpleCRUD simpleCRUD;

  @RequestMapping(value = "findMataKuliahById/", method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ApiOperation(value = "Ambil 1 MataKuliah sesuai id", notes = "ga detil")
  @ResponseBody
  public GdnRestSingleResponse<MataKuliahDTO> findMataKuliahById(@RequestParam String storeId,
      @RequestParam String channelId, @RequestParam String clientId, @RequestParam String requestId,
      @RequestParam int id) {
    final MataKuliah mataKuliah = simpleCRUD.findMataKuliahById(id);
    final MataKuliahDTO newDTO = MKToDTOConvert(mataKuliah);
    return new GdnRestSingleResponse<>(newDTO, requestId);
  }

  private MahasiswaDTO MahasiswaToDTOConvert(Mahasiswa in) {
    MahasiswaDTO res = new MahasiswaDTO();
    res.setNama(in.getNama());
    res.setNpm(in.getNpm());
    return res;
  }

  private MataKuliahDTO MKToDTOConvert(MataKuliah in) {
    MataKuliahDTO res = new MataKuliahDTO();
    res.setKode(in.getKode());
    res.setMahasiswa(MahasiswaToDTOConvert(in.getMahasiswa()));
    res.setNama(in.getNama());
    res.setNamaDosen(in.getNamaDosen());
    return res;
  }
}
