package org.openmrs.module.fuschiatokenyaemr.model;

import java.util.Date;

/**
 * Created by codehub on 22/05/15.
 */
public class FuschiaVisits {

    private Date dateCreated;
    private Date visitDate;
    private Date nextAppointmentDate;
    private Short cd4;
    private Integer viralLoad;
    private Float weight;
    private Short height;
    private Float hgb;

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Date getNextAppointmentDate() {
        return nextAppointmentDate;
    }

    public void setNextAppointmentDate(Date nextAppointmentDate) {
        this.nextAppointmentDate = nextAppointmentDate;
    }

    public Short getCd4() {
        return cd4;
    }

    public void setCd4(Short cd4) {
        this.cd4 = cd4;
    }

    public Integer getViralLoad() {
        return viralLoad;
    }

    public void setViralLoad(Integer viralLoad) {
        this.viralLoad = viralLoad;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Short getHeight() {
        return height;
    }

    public void setHeight(Short height) {
        this.height = height;
    }

    public Float getHgb() {
        return hgb;
    }

    public void setHgb(Float hgb) {
        this.hgb = hgb;
    }
}
