package com.jk.polsource.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    private int id;
    private String title;
    private String content;
    private Date created;
    private Date modified;
    private Boolean isDeleted;
    private Long threadId;
    private int version;

    //constructor for a new note
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.created = new Date();
        this.modified = this.created;
        this.isDeleted = false;
        this.threadId = UUID.randomUUID().getMostSignificantBits() & Long.valueOf("999999999999");
        this.version = 1;
    }

    //constructor for an existing note
    public Note(String title, String content, Date created, Long threadId, int version) {
        this.title = title;
        this.content = content;
        this.created = created;
        this.modified = new Date();
        this.isDeleted = false;
        this.threadId = threadId;
        this.version = version+1;
    }

    @Id
    @Column(name="id", nullable = false)
    public int getId(){
        return this.id;
    }

    @Basic
    @Column(name = "note_title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "content", nullable = false, length = 1000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "is_deleted", nullable = false)
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Basic
    @Column(name = "note_version", nullable = false)
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Basic
    @Column(name = "created", nullable = false)
    public Date getCreated() {
        return created;
    }

    @Basic
    @Column(name = "modified", nullable = false)
    public Date getModified() {
        return modified;
    }

    @Basic
    @Column(name = "thread_id", nullable = false)
    public Long getThreadId() {
        return threadId;
    }
}
