package com.hokumus.course.management.model.ogretmen;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.hokumus.course.management.model.base.BaseEntity;
import com.hokumus.course.management.model.yonetim.Grup;
import com.hokumus.course.management.model.yonetim.Kurs;

/**
 *
 * @author vektorel
 */
@Entity
@Table(name = "student_attendance")
public class YoklamaBilgileri extends BaseEntity {

    private Long id;
    private YoklamaFormu yoklamaFormu;
    private Grup grup;
    private Kurs kurs;

    @Id
    @SequenceGenerator(name = "seq_student_attendance", allocationSize = 1, sequenceName = "seq_student_attendance")
    @GeneratedValue(generator = "seq_student_attendance", strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "inspectionForm_id")
    public YoklamaFormu getYoklamaFormu() {
        return yoklamaFormu;
    }

    public void setYoklamaFormu(YoklamaFormu yoklamaFormu) {
        this.yoklamaFormu = yoklamaFormu;
    }

    @ManyToOne
    @JoinColumn(name = "group_id")
    public Grup getGrup() {
        return grup;
    }

    public void setGrup(Grup grup) {
        this.grup = grup;
    }

    @ManyToOne
    @JoinColumn(name = "course_id")
    public Kurs getKurs() {
        return kurs;
    }

    public void setKurs(Kurs kurs) {
        this.kurs = kurs;
    }

    @Override
    public String toString() {
        return "YoklamaBilgileri{" + "id=" + id + ", yoklamaFormu=" + yoklamaFormu + ", grup=" + grup + ", kurs=" + kurs + '}';
    }

    
}

