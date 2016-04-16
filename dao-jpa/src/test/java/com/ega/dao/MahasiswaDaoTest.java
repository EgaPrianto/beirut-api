package com.ega.dao;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.ega.entities.Mahasiswa;
import com.ega.entities.MataKuliah;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional(readOnly = false)
public class MahasiswaDaoTest {
  @Autowired
  private MahasiswaDao mahasiswaDao;

  @Before
  public void initialize() throws Exception {
    for (int i = 0; i < 10; i++) {
      Mahasiswa newMas = new Mahasiswa();
      newMas.setNama("Ega Prianto" + i);
      newMas.setNpm("2013730047" + i);
      Set<MataKuliah> mataKuliahs = new HashSet<>();
      for (int k = 0; k < 3; k++) {
        MataKuliah newMK = new MataKuliah();
        newMK.setKode("AIF" + k);
        newMK.setMahasiswa(newMas);
        newMK.setNama("ProSI" + k);
        newMK.setNamaDosen("vero");
        mataKuliahs.add(newMK);
      }
      newMas.setMataKuliahs(mataKuliahs);
      mahasiswaDao.save(newMas);
    }
  }

  @Test
  public void testFindMahasiswaById() {
    Assert.assertTrue(
        mahasiswaDao.findByNama("Ega Prianto0").get(0).getNama().equals("Ega Prianto0"));
  }
}
