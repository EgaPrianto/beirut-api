/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.ega.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Ega Prianto
 */
@Entity
@Table(name = "Mahasiswa")
public class Mahasiswa implements Serializable {

  private static final long serialVersionUID = -8990289988119348524L;

  @Id
  // @GeneratedValue(strategy = GenerationType.AUTO)
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  private String id;

  @Column(name = "Nama_Mahasiswa")
  private String nama;

  @Column(name = "Npm_Mahasiswa")
  private String npm;

  @Column(name = "MataKuliah_Mahasiswa")
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mahasiswa")
  private Set<MataKuliah> mataKuliahs = new HashSet<MataKuliah>();

  public Mahasiswa() {
    // nothing to do here
  }

  public String getId() {
    return id;
  }

  public Set<MataKuliah> getMataKuliahs() {
    return mataKuliahs;
  }

  public String getNama() {
    return nama;
  }

  public String getNpm() {
    return npm;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setMataKuliahs(Set<MataKuliah> mataKuliahs) {
    this.mataKuliahs = mataKuliahs;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public void setNpm(String npm) {
    this.npm = npm;
  }
}
