package com.sandman.download.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the DownloadRecord entity.
 */
public class DownloadRecordDTO implements Serializable {

    private Long id;

    private Long userId;

    private Long resId;

    private Long recordTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public Long getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Long recordTime) {
        this.recordTime = recordTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DownloadRecordDTO downloadRecordDTO = (DownloadRecordDTO) o;
        if(downloadRecordDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), downloadRecordDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DownloadRecordDTO{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", resId=" + getResId() +
            ", recordTime=" + getRecordTime() +
            "}";
    }
}
