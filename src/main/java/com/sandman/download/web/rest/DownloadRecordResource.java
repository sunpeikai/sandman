package com.sandman.download.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.sandman.download.domain.BaseDto;
import com.sandman.download.service.DownloadRecordService;
import com.sandman.download.web.rest.errors.BadRequestAlertException;
import com.sandman.download.web.rest.util.HeaderUtil;
import com.sandman.download.service.dto.DownloadRecordDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for managing DownloadRecord.
 */
@RestController
@RequestMapping("/api/sandman/v1/downloadRecord")
public class DownloadRecordResource {

    private final Logger log = LoggerFactory.getLogger(DownloadRecordResource.class);

    private static final String ENTITY_NAME = "downloadRecord";

    private final DownloadRecordService downloadRecordService;

    public DownloadRecordResource(DownloadRecordService downloadRecordService) {
        this.downloadRecordService = downloadRecordService;
    }

    /**
     * GET : get all the downloadRecords.
     */
    @GetMapping("/getAllDownloadRecords")
    @Timed
    public BaseDto getAllDownloadRecords(Integer pageNumber, Integer size) {
        log.debug("REST request to get all DownloadRecords");
        Map data = null;
        try {
            data = downloadRecordService.getAllDownloadRecords(pageNumber, size);
        } catch (Exception e) {
            log.info("获取下载记录失败!异常:{}",e);
        }
        return new BaseDto(200,"请求成功!",data);
    }

    /**
     * GET  /download-records/:id : get the "id" downloadRecord.
     */
/*    @GetMapping("/download-records/{id}")
    @Timed
    public ResponseEntity<DownloadRecordDTO> getDownloadRecord(@PathVariable Long id) {
        log.debug("REST request to get DownloadRecord : {}", id);
        DownloadRecordDTO downloadRecordDTO = downloadRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(downloadRecordDTO));
    }*/

    /**
     * DELETE  /download-records/:id : delete the "id" downloadRecord.
     *
     * @param id the id of the downloadRecordDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
/*    @DeleteMapping("/download-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteDownloadRecord(@PathVariable Long id) {
        log.debug("REST request to delete DownloadRecord : {}", id);
        downloadRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }*/
}
