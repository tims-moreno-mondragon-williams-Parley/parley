package com.parley.parley.repositories;

import com.parley.parley.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Topic findTopicById(Long id);

    Topic findByName(String name);

    Topic findTopicByNameAndCategory_Id(String name, Long id);

    List<Topic> findTopicByCategory_Id(Long id);

    List<Topic> findAllByName(String name);
}
