package com.hxy.OnlineDiningRoom.dao;

import com.hxy.OnlineDiningRoom.pojo.News;
import com.hxy.OnlineDiningRoom.pojo.NewsExample;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

class NewsMapperTest {

    @Mock
    private NewsMapper newsMapper;

    @Mock
    private NewsExample newsExample;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSelectByExample() {
        // Arrange
        List<News> mockedList = mock(List.class);
        when(newsMapper.selectByExample(newsExample)).thenReturn(mockedList);

        // Act
        List<News> resultList = newsMapper.selectByExample(newsExample);

        // Assert
        verify(newsMapper).selectByExample(newsExample);
        assertSame(mockedList, resultList);
    }

    @Test
    void testAudit() {
        // Arrange
        int zid = 1;

        // Act
        newsMapper.audit(zid);

        // Assert
        verify(newsMapper).audit(zid);
    }
}
