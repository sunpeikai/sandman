package com.sandman.download.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A UploadRecord.
 */
@Entity
@Table(name = "upload_record")
public class UploadRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "record_time")
    private Long recordTime;

    @OneToOne
    @JoinColumn(unique = true)
    private Resource res;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public UploadRecord userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRecordTime() {
        return recordTime;
    }

    public UploadRecord recordTime(Long recordTime) {
        this.recordTime = recordTime;
        return this;
    }

    public void setRecordTime(Long recordTime) {
        this.recordTime = recordTime;
    }

    public Resource getRes() {
        return res;
    }

    public UploadRecord res(Resource resource) {
        this.res = resource;
        return this;
    }

    public void setRes(Resource resource) {
        this.res = resource;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UploadRecord uploadRecord = (UploadRecord) o;
        if (uploadRecord.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), uploadRecord.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UploadRecord{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", recordTime=" + getRecordTime() +
            "}";
    }
}
