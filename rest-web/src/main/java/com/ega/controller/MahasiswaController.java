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

import com.ega.dto.request.MahasiswaDTORequest;
import com.ega.dto.response.MahasiswaDTOResponse;
import com.ega.dto.response.MahasiswaDetilDTOResponse;
import com.ega.entities.Mahasiswa;
import com.ega.services.SimpleCRUD;
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
  // private ObjectMapper mapper;


  @RequestMapping(value = "deleteMahasiswa", method = RequestMethod.POST)
  @ApiOperation(value = "delete mahasiswa dengan id value",
      notes = "param id adalah id mahasiswa yang ingin di ganti, param mahasiswaIn adalah data mahasiswa yang baru")
  @ResponseBody
  public GdnRestSingleResponse<MahasiswaDTOResponse> deleteMahasiswaById(
      @RequestParam String storeId, @RequestParam String channelId, @RequestParam String clientId,
      @RequestParam String requestId, @RequestParam String id) {
    final Mahasiswa deleted = this.simpleCRUD.deleteMahasiswaById(id);
    final MahasiswaDTOResponse deletedMahasiswaDTO = new MahasiswaDTOResponse();
    MahasiswaMapper.map(deleted, deletedMahasiswaDTO);
    final GdnRestSingleResponse<MahasiswaDTOResponse> gdnDeletedMahasiswa =
        new GdnRestSingleResponse<>(deletedMahasiswaDTO, requestId);
    return gdnDeletedMahasiswa;
  }

  @RequestMapping(value = "findMahasiswaById", method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ApiOperation(value = "Ambil 1 mahasiswa sesuai id", notes = "ga detil")
  @ResponseBody
  public GdnRestSingleResponse<MahasiswaDTOResponse> findMahasiswaById(@RequestParam String storeId,
      @RequestParam String channelId, @RequestParam String clientId, @RequestParam String requestId,
      @RequestParam String id) {
    final Mahasiswa mahasiswa = simpleCRUD.findMahasiswaById(id);
    final MahasiswaDTOResponse newDTO = new MahasiswaDTOResponse();
    MahasiswaMapper.map(mahasiswa, newDTO);
    return new GdnRestSingleResponse<MahasiswaDTOResponse>(newDTO, requestId);
  }

  @RequestMapping(value = "findMahasiswaByNama", method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ApiOperation(value = "Ambil 1 mahasiswa sesuai nama", notes = "ga detil")
  @ResponseBody
  public GdnRestListResponse<MahasiswaDTOResponse> findMahasiswaByNama(@RequestParam String storeId,
      @RequestParam String channelId, @RequestParam String clientId, @RequestParam String requestId,
      @RequestBody String nama) {
    final List<Mahasiswa> mahasiswa = simpleCRUD.findMahasiswaByNama(nama);
    final List<MahasiswaDTOResponse> mahasiswaDTOResponse = new ArrayList<>();
    for (Mahasiswa mahasiswa2 : mahasiswa) {
      MahasiswaDTOResponse res = new MahasiswaDTOResponse();
      MahasiswaMapper.map(mahasiswa2, res);
      mahasiswaDTOResponse.add(res);
    }
    return new GdnRestListResponse<>(mahasiswaDTOResponse,
        new PageMetaData(50, 0, mahasiswaDTOResponse.size()), requestId);
  }

  @RequestMapping(value = "findMahasiswaDetailById", method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ApiOperation(value = "Ambil 1 mahasiswa sesuai id", notes = "detil")
  @ResponseBody
  public GdnRestSingleResponse<MahasiswaDetilDTOResponse> findMahasiswaDetail(
      @RequestParam String storeId, @RequestParam String channelId, @RequestParam String clientId,
      @RequestParam String requestId, @RequestParam(required = true) String id) {
    Mahasiswa mahasiswa = simpleCRUD.findMahasiswaDetail(id);
    MahasiswaDetilDTOResponse newDTO = new MahasiswaDetilDTOResponse();
    MahasiswaMapper.map(mahasiswa, newDTO);
    return new GdnRestSingleResponse<MahasiswaDetilDTOResponse>(newDTO, requestId);
  }

  @RequestMapping(value = "getAllMahasiswa", method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ApiOperation(value = "Ambil semua mahasiswa", notes = "ambil semua mahasiswa yang ada")
  @ResponseBody
  public GdnRestListResponse<MahasiswaDTOResponse> getAllMahasiswa(@RequestParam String storeId,
      @RequestParam String channelId, @RequestParam String clientId,
      @RequestParam String requestId) {
    final List<MahasiswaDTOResponse> listDTO = new ArrayList<MahasiswaDTOResponse>();
    for (final Mahasiswa mahasiswa : simpleCRUD.getAllMahasiswa()) {
      final MahasiswaDTOResponse newDTO = new MahasiswaDTOResponse();
      MahasiswaMapper.map(mahasiswa, newDTO);
      listDTO.add(newDTO);
    }
    return new GdnRestListResponse<MahasiswaDTOResponse>(listDTO,
        new PageMetaData(50, 0, listDTO.size()), requestId);
  }


  @RequestMapping(value = "saveMahasiswa", method = RequestMethod.POST,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ApiOperation(value = "simpan 1 mahasiswa", notes = "")
  @ResponseBody
  public GdnRestSingleResponse<MahasiswaDTOResponse> saveMahasiswa(@RequestParam String storeId,
      @RequestParam String channelId, @RequestParam String clientId, @RequestParam String requestId,
      @RequestBody MahasiswaDTORequest newMahasiswa) {
    Mahasiswa dest = new Mahasiswa();
    MahasiswaMapper.map(newMahasiswa, dest);
    this.simpleCRUD.saveMahasiswa(dest);
    MahasiswaDTOResponse resDTO = new MahasiswaDTOResponse();
    return new GdnRestSingleResponse<>(resDTO, requestId);
  }

  @RequestMapping(value = "updatingMahasiswa", method = RequestMethod.POST,
      produces = {MediaType.APPLICATION_XML_VALUE},
      consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  @ApiOperation(value = "update mahasiswa dengan id value",
      notes = "param id adalah id mahasiswa yang ingin di ganti, param mahasiswaIn adalah data mahasiswa yang baru")
  @ResponseBody
  public GdnRestSingleResponse<MahasiswaDTOResponse> updateMahasiswa(@RequestParam String storeId,
      @RequestParam String channelId, @RequestParam String clientId, @RequestParam String requestId,
      @RequestParam String id, @RequestBody MahasiswaDTORequest mahasiswaIn) {
    final Mahasiswa mahasiswa = this.simpleCRUD.findMahasiswaById(id);
    MahasiswaMapper.map(mahasiswaIn, mahasiswa);
    this.simpleCRUD.saveMahasiswa(mahasiswa);
    final MahasiswaDTOResponse updatedMahasiswaDTO = new MahasiswaDTOResponse();
    MahasiswaMapper.map(mahasiswa, updatedMahasiswaDTO);
    final GdnRestSingleResponse<MahasiswaDTOResponse> gdnUpdatedMahasiswa =
        new GdnRestSingleResponse<>(updatedMahasiswaDTO, requestId);
    return gdnUpdatedMahasiswa;
  }
}
