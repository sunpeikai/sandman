package com.sandman.download.service;

import com.sandman.download.common.repository.PageableTools;
import com.sandman.download.common.repository.SortDto;
import com.sandman.download.domain.ResourceRecord;
import com.sandman.download.domain.User;
import com.sandman.download.repository.ResourceRecordRepository;
import com.sandman.download.security.SecurityUtils;
import com.sandman.download.service.dto.ResourceDTO;
import com.sandman.download.service.dto.ResourceRecordDTO;
import com.sandman.download.service.mapper.ResourceRecordMapper;
import com.sandman.download.web.rest.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ResourceRecord.
 */
@Service
@Transactional
public class ResourceRecordService {

    private final Logger log = LoggerFactory.getLogger(ResourceRecordService.class);

    private final ResourceRecordRepository resourceRecordRepository;

    private final ResourceRecordMapper resourceRecordMapper;

    public ResourceRecordService(ResourceRecordRepository resourceRecordRepository, ResourceRecordMapper resourceRecordMapper) {
        this.resourceRecordRepository = resourceRecordRepository;
        this.resourceRecordMapper = resourceRecordMapper;
    }

    /**
     * Save a resourceRecord.
     *
     * @param resourceRecordDTO the entity to save
     * @return the persisted entity
     */
    public ResourceRecordDTO save(ResourceRecordDTO resourceRecordDTO) {
        log.debug("Request to save ResourceRecord : {}", resourceRecordDTO);
        ResourceRecord resourceRecord = resourceRecordMapper.toEntity(resourceRecordDTO);
        resourceRecord = resourceRecordRepository.save(resourceRecord);
        return resourceRecordMapper.toDto(resourceRecord);
    }

    /**
     * Get all the resourceRecords.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Map getAllResourceRecords(Integer pageNumber, Integer size)throws Exception {
        log.debug("Request to get all ResourceRecords");
        pageNumber = (pageNumber==null || pageNumber<1)?1:pageNumber;
        size = (size==null || size<0)?10:size;
        Pageable pageable = PageableTools.basicPage(pageNumber,size,new SortDto("desc","recordTime"));
        Page resourceRecordPage = resourceRecordRepository.findAllByUserId(SecurityUtils.getCurrentUserId(),pageable);
        Map data = new HashMap();
        data.put("totalRow",resourceRecordPage.getTotalElements());
        data.put("totalPage",resourceRecordPage.getTotalPages());
        data.put("currentPage",resourceRecordPage.getNumber()+1);//默认0就是第一页
        data.put("recordList",resourceRecordPage.getContent());
        return data;
    }

    /**
     * Get one resourceRecord by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public ResourceRecordDTO findOne(Long id) {
        log.debug("Request to get ResourceRecord : {}", id);
        ResourceRecord resourceRecord = resourceRecordRepository.findOne(id);
        return resourceRecordMapper.toDto(resourceRecord);
    }

    /**
     * Delete the resourceRecord by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ResourceRecord : {}", id);
        resourceRecordRepository.delete(id);
    }

    /**
     * 积分增加
     * */
    public ResourceRecord addGold(User user, ResourceDTO resourceDTO,int gold,String opDesc){//resourceDTO和gold 二者其一不为null即可
        ResourceRecord resourceRecord = new ResourceRecord();
        resourceRecord.setUserId(user.getId());
        if(resourceDTO!=null){
            resourceRecord.setResId(resourceDTO.getId());
            resourceRecord.setResGold(resourceDTO.getResGold());
            resourceRecord.setCurGold(user.getGold() + resourceDTO.getResGold());
            resourceRecord.setResName(resourceDTO.getResName());
        }else{
            resourceRecord.setCurGold(user.getGold() + gold);
        }
        resourceRecord.setOriGold(user.getGold());
        resourceRecord.setOpDesc(opDesc);
        resourceRecord.setRecordTime(DateUtils.getLongTime());
        return resourceRecordRepository.save(resourceRecord);//保存数据
    }
    /**
     * 积分减少
     * */
    public ResourceRecord reduceGold(User user, ResourceDTO resourceDTO,int gold,String opDesc){//resourceDTO和gold 二者其一不为null即可
        ResourceRecord resourceRecord = new ResourceRecord();
        resourceRecord.setUserId(user.getId());
        if(resourceDTO!=null){
            resourceRecord.setResId(resourceDTO.getId());
            resourceRecord.setResGold(resourceDTO.getResGold());
            resourceRecord.setCurGold(user.getGold() - resourceDTO.getResGold());
            resourceRecord.setResName(resourceDTO.getResName());
        }else{
            resourceRecord.setCurGold(user.getGold() - gold);
        }
        resourceRecord.setOriGold(user.getGold());
        resourceRecord.setOpDesc(opDesc);
        resourceRecord.setRecordTime(DateUtils.getLongTime());
        return resourceRecordRepository.save(resourceRecord);//保存数据
    }
}
