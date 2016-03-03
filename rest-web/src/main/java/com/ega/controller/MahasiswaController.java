package com.ega.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ega.dto.MahasiswaDTO;
import com.ega.entities.Mahasiswa;
import com.ega.services.SimpleCRUD;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdn.common.web.wrapper.response.GdnRestListResponse;
import com.gdn.common.web.wrapper.response.GdnRestSingleResponse;
import com.gdn.common.web.wrapper.response.PageMetaData;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "api/mahasiswa/")
@Api(value = "MahasiswaController", description = "Percobaan pertama, pembelajaran pertama")
public class MahasiswaController {

  @Autowired
  private SimpleCRUD simpleCRUD;
  private ObjectMapper mapper;

  @RequestMapping(value = "findMahasiswaById/", method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ApiOperation(value = "Ambil 1 mahasiswa sesuai id", notes = "ga detil")
  @ResponseBody
  public GdnRestSingleResponse<MahasiswaDTO> findMahasiswaById(@RequestParam String storeId,
      @RequestParam String channelId, @RequestParam String clientId, @RequestParam String requestId,
      @RequestParam(required = true) int id) {
    Mahasiswa mahasiswa = simpleCRUD.findMahasiswaById(id);
    MahasiswaDTO newDTO = new MahasiswaDTO();
    newDTO.setPrimaryKey(mahasiswa.getId() + "");
    newDTO.setNama(mahasiswa.getNama());
    newDTO.setNpm(mahasiswa.getNpm());
    return new GdnRestSingleResponse<MahasiswaDTO>(newDTO, requestId);
  }


  @RequestMapping(value = "findMahasiswaByNama/", method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ApiOperation(value = "Ambil 1 mahasiswa sesuai nama", notes = "ga detil")
  @ResponseBody
  public GdnRestSingleResponse<MahasiswaDTO> findMahasiswaByNama(@RequestParam String storeId,
      @RequestParam String channelId, @RequestParam String clientId, @RequestParam String requestId,
      @RequestParam(required = true) String nama) {
    Mahasiswa mahasiswa = simpleCRUD.findByNama(nama);
    MahasiswaDTO newDTO = new MahasiswaDTO();
    newDTO.setPrimaryKey(mahasiswa.getId() + "");
    newDTO.setNama(mahasiswa.getNama());
    newDTO.setNpm(mahasiswa.getNpm());
    return new GdnRestSingleResponse<MahasiswaDTO>(newDTO, requestId);
  }


  @RequestMapping(value = "getAllMahasiswa/", method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ApiOperation(value = "Ambil semua mahasiswa", notes = "ambil semua mahasiswa yang ada")
  @ResponseBody
  public GdnRestListResponse<MahasiswaDTO> getAllMahasiswa(@RequestParam String storeId,
      @RequestParam String channelId, @RequestParam String clientId,
      @RequestParam String requestId) {
    List<Mahasiswa> temp = simpleCRUD.getAllMahasiswa();
    List<MahasiswaDTO> listDTO = new ArrayList<MahasiswaDTO>();
    for (Mahasiswa mahasiswa : temp) {
      MahasiswaDTO newDTO = new MahasiswaDTO();
      newDTO.setPrimaryKey(mahasiswa.getId() + "");
      newDTO.setNama(mahasiswa.getNama());
      newDTO.setNpm(mahasiswa.getNpm());
      listDTO.add(newDTO);
    }
    return new GdnRestListResponse<MahasiswaDTO>(listDTO, new PageMetaData(50, 0, temp.size()),
        requestId);
  }

  //
  // @RequestMapping(value = "saveMahasiswa/", method = RequestMethod.GET,
  // produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
  // consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  // @ApiOperation(value = "simpan 1 mahasiswa", notes = "")
  // @ResponseBody
  // public GdnRestSingleRequest<SimpleRequestHolder> saveMahasiswa(@RequestParam String storeId,
  // @RequestParam String channelId, @RequestParam String clientId, @RequestParam String requestId,
  // @RequestParam(required = true) String nama, @RequestParam(required = true) String npm) {
  //
  // return new GdnRestSingleResponse<MahasiswaDTO>(newDTO, requestId);
  // }
  //
  // @RequestMapping(value = "findMahasiswaDetailById/", method = RequestMethod.GET,
  // produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
  // consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  // @ApiOperation(value = "Ambil 1 mahasiswa sesuai id", notes = "detil")
  // @ResponseBody
  // public GdnRestSingleResponse<MahasiswaDTO> findMahasiswaDetail(@RequestParam String storeId,
  // @RequestParam String channelId, @RequestParam String clientId, @RequestParam String requestId,
  // @RequestParam(required = true) int id) {
  // Mahasiswa mahasiswa = simpleCRUD.findMahasiswaDetail(id);
  // MahasiswaDetilDTO newDTO = new MahasiswaDetilDTO();
  // Set<MataKuliah> mahasiswaMK = mahasiswa.getMataKuliah();
  // Set<MataKuliahDTO> newSetMK = new HashSet<MataKuliahDTO>();
  // newDTO.setPrimaryKey(mahasiswa.getId() + "");
  // newDTO.setNama(mahasiswa.getNama());
  // newDTO.setNpm(mahasiswa.getNpm());
  // newDTO.setSetMataKuliah(newSetMK);
  // for (MataKuliah mataKuliah : mahasiswaMK) {
  // MataKuliahDTO newMKDTO = new MataKuliahDTO();
  // newMKDTO.setId(mataKuliah.getId() + "");
  // newMKDTO.setKode(mataKuliah.getKode());
  // newMKDTO.setMahasiswa(newDTO);
  // newMKDTO.setNama(mataKuliah.getNama());
  // newMKDTO.setNamaDosen(mataKuliah.getNamaDosen());
  // newSetMK.add(newMKDTO);
  // }
  // return new GdnRestSingleResponse<MahasiswaDTO>(newDTO, requestId);
  // }
}
