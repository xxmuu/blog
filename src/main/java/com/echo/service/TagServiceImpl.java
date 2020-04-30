package com.echo.service;

import com.echo.dao.TagRepository;
import com.echo.exception.NotFoundException;
import com.echo.po.Tag;
import com.echo.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }
    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.findById(id).get();
    }
    @Transactional
    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }
    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }
    @Transactional
    @Override
    public List<Tag> listTag(String ids) {
        return tagRepository.findAllById(toList(ids));
    }
    private List<Long> toList(String ids){
        List<Long> idList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        if (!"".equals(ids) && ids != null){
            String[] idArray = ids.split(",");

            for (int i = 0; i < idArray.length; i++){
                try{
                    idList.add(new Long(idArray[i]));
                }catch (NumberFormatException e){
                    nameList.add(idArray[i]);
                }
            }
            Tag t = new Tag();
            for (String name : nameList){
                Tag tag = tagRepository.findByName(name);
                if (tag == null){
                    t.setName(name);
                    idList.add( tagRepository.save(t).getId() );
                }else{
                    idList.add(tag.getId());
                }
            }
        }
        return idList;
    }
    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.findById(id).get();
        if ( t == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(tag,t);
        return tagRepository.save(t);
    }
    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
    @Transactional
    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,Sort.by(order));
        return tagRepository.findTop(pageable);
    }
}
