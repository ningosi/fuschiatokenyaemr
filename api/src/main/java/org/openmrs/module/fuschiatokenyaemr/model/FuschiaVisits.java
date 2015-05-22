package org.openmrs.module.fuschiatokenyaemr.model;

import java.util.Date;

/**
 * Created by codehub on 22/05/15.
 */
public class FuschiaVisits {

    private Date dateCreated;
    private Date visitDate;
    private Date nextAppointmentDate;
    private Double cd4;
    private Double viralLoad;
    private Double weight;
    private Double height;
    private Double hgb;

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

    public Double getCd4() {
        return cd4;
    }

    public void setCd4(Double cd4) {
        this.cd4 = cd4;
    }

    public Double getViralLoad() {
        return viralLoad;
    }

    public void setViralLoad(Double viralLoad) {
        this.viralLoad = viralLoad;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getHgb() {
        return hgb;
    }

    public void setHgb(Double hgb) {
        this.hgb = hgb;
    }
}
