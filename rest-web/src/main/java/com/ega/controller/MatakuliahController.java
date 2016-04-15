package com.ega.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ega.dto.response.MahasiswaDTOResponse;
import com.ega.dto.response.MataKuliahDTOResponse;
import com.ega.entities.Mahasiswa;
import com.ega.entities.MataKuliah;
import com.ega.services.SimpleCRUD;
import com.gdn.common.web.wrapper.response.GdnRestListResponse;
import com.gdn.common.web.wrapper.response.GdnRestSingleResponse;
import com.gdn.common.web.wrapper.response.PageMetaData;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "api/mataKuliah/")
@Api(value = "MatakuliahController", description = "Percobaan pertama, pembelajaran pertama")
public class MatakuliahController {

  @Autowired
  private SimpleCRUD simpleCRUD;

  @RequestMapping(value = "deleteMataKuliahById/", method = RequestMethod.POST,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ApiOperation(value = "Delete 1 mahasiswa sesuai id")
  @ResponseBody
  public GdnRestSingleResponse<MataKuliahDTOResponse> deleteMataKuliahByID(@RequestParam String storeId,
      @RequestParam String channelId, @RequestParam String clientId, @RequestParam String requestId,
      @RequestParam String id) {
    MataKuliah deleted = simpleCRUD.deleteMataKuliahById(id);
    return new GdnRestSingleResponse<MataKuliahDTOResponse>(MKToDTOConvert(deleted), requestId);
  }

  @RequestMapping(value = "findMataKuliahById/", method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ApiOperation(value = "Ambil 1 MataKuliah sesuai id", notes = "ga detil")
  @ResponseBody
  public GdnRestSingleResponse<MataKuliahDTOResponse> findMataKuliahById(@RequestParam String storeId,
      @RequestParam String channelId, @RequestParam String clientId, @RequestParam String requestId,
      @RequestParam String id) {
    final MataKuliah mataKuliah = simpleCRUD.findMataKuliahById(id);
    final MataKuliahDTOResponse newDTO = MKToDTOConvert(mataKuliah);
    return new GdnRestSingleResponse<>(newDTO, requestId);
  }

  @RequestMapping(value = "findMataKuliahByNama/", method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ApiOperation(value = "Ambil 1 MataKuliah sesuai nama", notes = "ga detil")
  @ResponseBody
  public GdnRestListResponse<MataKuliahDTOResponse> findMataKuliahByNama(@RequestParam String storeId,
      @RequestParam String channelId, @RequestParam String clientId, @RequestParam String requestId,
      @RequestParam String nama) {
    final List<MataKuliah> mataKuliah = simpleCRUD.findMataKuliahByNama(nama);
    final List<MataKuliahDTOResponse> newDTO = new ArrayList<>();
    for (MataKuliah mataKuliah2 : mataKuliah) {
      newDTO.add(MKToDTOConvert(mataKuliah2));
    }
    return new GdnRestListResponse<>(newDTO, new PageMetaData(50, 0, newDTO.size()), requestId);
  }

  private MahasiswaDTOResponse MahasiswaToDTOConvert(Mahasiswa in) {
    MahasiswaDTOResponse res = new MahasiswaDTOResponse();
    res.setNama(in.getNama());
    res.setNpm(in.getNpm());
    return res;
  }

  private MataKuliahDTOResponse MKToDTOConvert(MataKuliah in) {
    MataKuliahDTOResponse res = new MataKuliahDTOResponse();
    res.setKode(in.getKode());
    res.setNama(in.getNama());
    res.setNamaDosen(in.getNamaDosen());
    return res;
  }

  @RequestMapping(value = "updateMataKuliah/", method = RequestMethod.POST,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ApiOperation(value = "Update MataKuliah",
      notes = "@param MataKuliah yang diupdate   @param id id Matakuliah yang ingin di update")
  @ResponseBody
  public GdnRestSingleResponse<MataKuliahDTOResponse> updateMataKuliah(@RequestParam String storeId,
      @RequestParam String channelId, @RequestParam String clientId, @RequestParam String requestId,
      @RequestBody MataKuliah newMK, @RequestParam String id) {
    MataKuliah oldMK = this.simpleCRUD.findMataKuliahById(id);
    oldMK.setKode(newMK.getKode());
    oldMK.setMahasiswa(newMK.getMahasiswa());
    oldMK.setNama(newMK.getNama());
    oldMK.setNamaDosen(newMK.getNamaDosen());
    this.simpleCRUD.saveMataKuliah(oldMK);
    return new GdnRestSingleResponse<>(MKToDTOConvert(oldMK), requestId);
  }
}
