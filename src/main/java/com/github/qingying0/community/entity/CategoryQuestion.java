package com.github.qingying0.community.entity;

import javax.persistence.*;

@Table(name = "category_question")
public class CategoryQuestion {
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "question_id")
    private Long questionId;

    /**
     * @return category_id
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return question_id
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}