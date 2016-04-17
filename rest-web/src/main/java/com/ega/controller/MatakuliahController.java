package com.ega.controller;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ega.dto.request.MataKuliahDTORequest;
import com.ega.dto.response.MataKuliahDTOResponse;
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
  @Autowired
  private Mapper dozerMapper;

  @RequestMapping(value = "deleteMataKuliahById/", method = RequestMethod.POST,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ApiOperation(value = "Delete 1 mahasiswa sesuai id")
  @ResponseBody
  public GdnRestSingleResponse<MataKuliahDTOResponse> deleteMataKuliahByID(
      @RequestParam String storeId, @RequestParam String channelId, @RequestParam String clientId,
      @RequestParam String requestId, @RequestParam String username, @RequestParam String id) {
    MataKuliah deleted = simpleCRUD.deleteMataKuliahById(id);
    MataKuliahDTOResponse delDTO = new MataKuliahDTOResponse();
    dozerMapper.map(deleted, delDTO);
    return new GdnRestSingleResponse<MataKuliahDTOResponse>(delDTO, requestId);
  }

  @RequestMapping(value = "findMataKuliahById/", method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ApiOperation(value = "Ambil 1 MataKuliah sesuai id", notes = "ga detil")
  @ResponseBody
  public GdnRestSingleResponse<MataKuliahDTOResponse> findMataKuliahById(
      @RequestParam String storeId, @RequestParam String channelId, @RequestParam String clientId,
      @RequestParam String requestId, @RequestParam String username, @RequestParam String id) {
    final MataKuliah mataKuliah = simpleCRUD.findMataKuliahById(id);
    MataKuliahDTOResponse newDTO = new MataKuliahDTOResponse();
    dozerMapper.map(mataKuliah, newDTO);
    return new GdnRestSingleResponse<>(newDTO, requestId);
  }

  @RequestMapping(value = "findMataKuliahByNama/", method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ApiOperation(value = "Ambil 1 MataKuliah sesuai nama", notes = "ga detil")
  @ResponseBody
  public GdnRestListResponse<MataKuliahDTOResponse> findMataKuliahByNama(
      @RequestParam String storeId, @RequestParam String channelId, @RequestParam String clientId,
      @RequestParam String requestId, @RequestParam String username, @RequestParam String nama) {
    final List<MataKuliah> mataKuliah = simpleCRUD.findMataKuliahByNama(nama);
    final List<MataKuliahDTOResponse> newDTO = new ArrayList<>();
    for (MataKuliah mataKuliah2 : mataKuliah) {
      MataKuliahDTOResponse newDTOIn = new MataKuliahDTOResponse();
      dozerMapper.map(mataKuliah2, newDTOIn);
      newDTO.add(newDTOIn);
    }
    return new GdnRestListResponse<>(newDTO, new PageMetaData(50, 0, newDTO.size()), requestId);
  }

  @RequestMapping(value = "updateMataKuliah/", method = RequestMethod.POST,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ApiOperation(value = "Update MataKuliah",
      notes = "@param MataKuliah yang diupdate   @param id id Matakuliah yang ingin di update")
  @ResponseBody
  public GdnRestSingleResponse<MataKuliahDTOResponse> updateMataKuliah(@RequestParam String storeId,
      @RequestParam String channelId, @RequestParam String clientId, @RequestParam String requestId,
      @RequestParam String username, @RequestBody MataKuliahDTORequest newMK,
      @RequestParam String id) {
    MataKuliah oldMK = this.simpleCRUD.findMataKuliahById(id);
    dozerMapper.map(newMK, oldMK);
    this.simpleCRUD.saveMataKuliah(oldMK);
    MataKuliahDTOResponse newDTO = new MataKuliahDTOResponse();
    dozerMapper.map(oldMK, newDTO);
    return new GdnRestSingleResponse<>(newDTO, requestId);
  }
}
