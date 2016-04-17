package com.ega.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ega.dao.MahasiswaDao;
import com.ega.entities.Mahasiswa;

public class SimpleCRUDServiceTest {

  @Mock
  private MahasiswaDao mahasiswaDao;

  @InjectMocks
  private SimpleCRUDService simpleCRUDService;

  @Before
  public void initialize() throws Exception {
    MockitoAnnotations.initMocks(this);
    // this.simpleCRUDService.getMahasiswaDao();
    Mahasiswa tesMah = new Mahasiswa("0");
    tesMah.setNama("ega");
    when(this.mahasiswaDao.findOne("0")).thenReturn(tesMah);
  }

  @After
  public void postTest() throws Exception {
    Mockito.verifyNoMoreInteractions(mahasiswaDao);
  }

  @Test
  public void testfindMahasiswaDetail() {
    Mahasiswa mahasiswa = this.simpleCRUDService.findMahasiswaDetail("0");
    verify(this.mahasiswaDao).findOne("0");
    assertNotNull(mahasiswa.getNama());
    assertNull(mahasiswa.getNpm());
    // assertNull(mahasiswa.getMataKuliahs());
    assertTrue(mahasiswa.getNama().equals("ega"));
  }
}
