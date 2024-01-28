package com.lt.pc_community.service;

import com.lt.pc_community.dto.QuestionDTO;
import com.lt.pc_community.mapper.QuestionMapper;
import com.lt.pc_community.mapper.UserMapper;
import com.lt.pc_community.model.Question;
import com.lt.pc_community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;
    public List<QuestionDTO> searchList() {
        List<Question> questions=questionMapper.searchQuestion();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question q : questions){
            User user=userMapper.findById(q.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
