package com.springdemo.demo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springdemo.demo.Utils.PageUtils;
import com.springdemo.demo.dto.QuestionDTO;
import com.springdemo.demo.mapper.QuestionMapper;
import com.springdemo.demo.mapper.UserMapper;
import com.springdemo.demo.model.Question;
import com.springdemo.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PageInfo<QuestionDTO> list(Integer page) {
        PageHelper.startPage(page, 5);
        List<Question> questionList = questionMapper.list();
        PageInfo<Question> questionPageInfo = new PageInfo<>(questionList);
        PageInfo<QuestionDTO> questionPageDTOInfo = PageUtils.pageInfo2PageInfoDTO(questionPageInfo, QuestionDTO.class);

        for (QuestionDTO questionDTO : questionPageDTOInfo.getList()) {
            User user = userMapper.findById(questionDTO.getCreator());
            questionDTO.setUser(user);
        }
        return questionPageDTOInfo;
    }
}
